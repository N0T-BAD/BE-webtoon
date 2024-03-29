package com.blockpage.webtoonservice.adaptor.infrastructure.persistence;

import com.blockpage.webtoonservice.adaptor.infrastructure.entity.EpisodeEntity;
import com.blockpage.webtoonservice.adaptor.infrastructure.entity.ImageEntity;
import com.blockpage.webtoonservice.adaptor.infrastructure.repository.EpisodeRepository;
import com.blockpage.webtoonservice.adaptor.infrastructure.repository.ImageRepository;
import com.blockpage.webtoonservice.adaptor.infrastructure.repository.WebtoonRepository;
import com.blockpage.webtoonservice.adaptor.infrastructure.value.WebtoonStatus;
import com.blockpage.webtoonservice.application.port.out.EpisodePort;
import com.blockpage.webtoonservice.application.port.out.ResponseEpisodeContent;
import com.blockpage.webtoonservice.application.port.out.ResponseEpisodeDetail;
import com.blockpage.webtoonservice.domain.Episode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Slf4j
public class EpisodeAdaptor implements EpisodePort {

    private final EpisodeRepository episodeRepository;
    private final ImageRepository imageRepository;
    private final WebtoonRepository webtoonRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Episode> findEpisode(Long webtoonId, String sort) {

        List<Episode> episodeList;
        List<EpisodeEntity> episodeEntityList = null;
        if (sort.equals("ASC")) {
            episodeEntityList = episodeRepository.findByWebtoonIdAndEpisodeStatusOrderByEpisodeNumberAsc(webtoonId,
                WebtoonStatus.PUBLISH);
        } else if (sort.equals("DESC")) {
            episodeEntityList = episodeRepository.findByWebtoonIdAndEpisodeStatusOrderByEpisodeNumberDesc(webtoonId,
                WebtoonStatus.PUBLISH);
        }

        episodeList = episodeEntityList.stream().map(Episode::toDomainFromEntity).collect(Collectors.toList());

        return episodeList;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Episode> findCreatorEpisode(Long webtoonId) {
        List<Episode> episodeList;
        List<EpisodeEntity> episodeEntityList1 = episodeRepository.findByWebtoonIdAndEpisodeStatusOrderByEpisodeNumberDesc(webtoonId,
            WebtoonStatus.PUBLISH);
        List<EpisodeEntity> episodeEntityList2 = episodeRepository.findByWebtoonIdAndEpisodeStatusOrderByEpisodeNumberDesc(webtoonId,
            WebtoonStatus.REGISTRATION_WAITING);
        List<EpisodeEntity> episodeEntityList3 = episodeRepository.findByWebtoonIdAndEpisodeStatusOrderByEpisodeNumberDesc(webtoonId,
            WebtoonStatus.MODIFICATION_WAITING);
        List<EpisodeEntity> episodeEntityList4 = episodeRepository.findByWebtoonIdAndEpisodeStatusOrderByEpisodeNumberDesc(webtoonId,
            WebtoonStatus.REMOVE_WAITING);

        List<EpisodeEntity> result = new ArrayList<>();
        result.addAll(episodeEntityList1);
        result.addAll(episodeEntityList2);
        result.addAll(episodeEntityList3);
        result.addAll(episodeEntityList4);

        episodeList = result.stream().map(Episode::toDomainFromEntity)
            .collect(Collectors.toList());
        return episodeList;
    }

    @Override
    @Transactional(readOnly = true)
    public Episode findEpisodeDetail(Long episodeId, Long webtoonId, Integer episodeNumber) {

        ResponseEpisodeDetail responseEpisodeDetail;
        List<ImageEntity> imageEntityList = imageRepository.findByWebtoonIdAndEpisodeNumber(webtoonId, episodeNumber);
        EpisodeEntity episodeEntity = episodeRepository.findById(episodeId).get();
        String creator = webtoonRepository.findById(webtoonId).get().getCreator();

        List<EpisodeEntity> episodeEntityList = episodeRepository.findByEpisodeStatusAndWebtoonIdOrderByEpisodeNumber(WebtoonStatus.PUBLISH,
            webtoonId);
        String nextTitle = "", nextThumbnail = "", nextUploadDate = "";
        Integer nextBlockPrice = 0;
        double nextRating = 0;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy.MM.dd");

        if (episodeNumber < episodeEntityList.size()) {
            nextTitle = episodeEntityList.get(episodeNumber).getEpisodeTitle();
            nextThumbnail = episodeEntityList.get(episodeNumber).getEpisodeThumbnail();
            nextBlockPrice = episodeEntityList.get(episodeNumber).getEpisodePrice();
            nextUploadDate = simpleDateFormat.format(episodeEntityList.get(episodeNumber).getUploadDate());
            if (episodeEntityList.get(episodeNumber).getParticipantCount() != 0) {
                nextRating =
                    episodeEntityList.get(episodeNumber).getTotalScore() / episodeEntityList.get(episodeNumber).getParticipantCount();
            }
        }
        responseEpisodeDetail = ResponseEpisodeDetail.toResponseFromEntity(episodeEntity, creator, imageEntityList, nextTitle,
            nextThumbnail, nextRating, nextUploadDate, nextBlockPrice);

        return Episode.toDomainFromResponse(responseEpisodeDetail);
    }

    @Override
    public Episode findEpisodeContent(Long episodeId, Long webtoonId, Integer episodeNumber) {
        Optional<EpisodeEntity> episode = episodeRepository.findById(episodeId);
        List<ImageEntity> imageEntityList = imageRepository.findByEpisodeId(episodeId);
        ResponseEpisodeContent responseEpisodeContent = ResponseEpisodeContent.fromEntity(episode.get(), imageEntityList);
        return Episode.fromResponseContent(responseEpisodeContent);
    }

    @Override
    @Transactional
    public void updateRating(Long episodeId, Integer totalScore, Integer participantCount) {
        Optional<EpisodeEntity> episodeEntity = episodeRepository.findById(episodeId);
        Integer total = episodeEntity.get().getTotalScore();
        Integer participants = episodeEntity.get().getParticipantCount();
        episodeEntity.get().updateRating(totalScore + total, participants + participantCount);
        log.info("before total : " + totalScore);
        log.info("after total : " + (total + totalScore));
        log.info("before participantCount : " + participantCount);
        log.info("after participantCount : " + (participants + participantCount));

    }


    @Transactional
    @Scheduled(cron = "0 0 0 * * ?")
    public void scheduleTask() {
        Date now = new Date();
        episodeRepository.bulkPriceChange(now);
    }
}
