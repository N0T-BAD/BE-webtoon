package com.blockpage.webtoonservice.application.service;

import com.blockpage.webtoonservice.application.port.in.InterestUseCase;
import com.blockpage.webtoonservice.application.port.in.RatingUseCase;
import com.blockpage.webtoonservice.application.port.in.ViewCountUseCase;
import com.blockpage.webtoonservice.application.port.in.WebtoonUseCase;
import com.blockpage.webtoonservice.application.port.out.ResponseWebtoon;
import com.blockpage.webtoonservice.application.port.out.WebtoonPort;
import com.blockpage.webtoonservice.domain.Webtoon;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WebtoonService implements WebtoonUseCase, ViewCountUseCase, InterestUseCase, RatingUseCase {

    private final WebtoonPort webtoonPort;

    @Override
    public List<ResponseWebtoon> findWebtoonByGenre(String type) {
        List<ResponseWebtoon> responseWebtoonList;
        List<Webtoon> webtoonList = webtoonPort.findWebtoonByGenre(type);
        responseWebtoonList = webtoonList.stream().map(ResponseWebtoon::toResponseFromDomain).collect(Collectors.toList());

        return responseWebtoonList;
    }

    @Override
    public List<ResponseWebtoon> findWebtoonByWeekdays(String type) {
        List<ResponseWebtoon> responseWebtoonList;
        List<Webtoon> webtoonList = webtoonPort.findWebtoonByWeekdays(type);
        responseWebtoonList = webtoonList.stream().map(ResponseWebtoon::toResponseFromDomain).collect(Collectors.toList());

        return responseWebtoonList;
    }

    @Override
    public List<ResponseWebtoon> findWebtoonBest() {
        List<ResponseWebtoon> responseWebtoonList;
        List<Webtoon> webtoonList = webtoonPort.findWebtoonBest();
        responseWebtoonList = webtoonList.stream().map(ResponseWebtoon::toResponseFromDomain).collect(Collectors.toList());
        return responseWebtoonList;
    }

    @Override
    public List<ResponseWebtoon> findWebtoonByCreator(String creatorId) {
        List<ResponseWebtoon> responseWebtoonList;
        List<Webtoon> webtoonList = webtoonPort.findWebtoonByCreator(creatorId);
        responseWebtoonList = webtoonList.stream().map(ResponseWebtoon::toResponseFromDomain).collect(Collectors.toList());

        return responseWebtoonList;
    }

    @Override
    public ResponseWebtoon findWebtoon(Long id) {
        Webtoon webtoon = webtoonPort.findWebtoon(id);

        return ResponseWebtoon.toResponseFromDomain(webtoon);
    }

    @Override
    public List<ResponseWebtoon> findAll(String keyword) {
        List<ResponseWebtoon> responseWebtoonList;
        List<Webtoon> webtoonList = webtoonPort.findAll(keyword);
        responseWebtoonList = webtoonList.stream().map(ResponseWebtoon::toResponseFromDomain).toList();
        return responseWebtoonList;
    }


    @Override
    public void updateViewCount(viewCountQuery viewCountQuery) {
        webtoonPort.updateViewCount(viewCountQuery.getWebtoonId(), viewCountQuery.getViewCount());
    }

    @Override
    public void updateInterest(InterestQuery interestQuery) {
        webtoonPort.updateInterestCount(interestQuery.getWebtoonId(), interestQuery.getInterestCount());
    }

    @Override
    public void updateRating(RatingQuery ratingQuery) {
        webtoonPort.updateRating(ratingQuery.getWebtoonId(), ratingQuery.getTotalScore(), ratingQuery.getParticipantCount());
    }
}
