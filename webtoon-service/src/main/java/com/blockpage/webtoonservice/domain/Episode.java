package com.blockpage.webtoonservice.domain;

import com.blockpage.webtoonservice.adaptor.infrastructure.entity.EpisodeEntity;
import com.blockpage.webtoonservice.application.port.out.ResponseEpisodeDetail;
import com.blockpage.webtoonservice.application.port.out.ResponseEpisodeImage;
import java.sql.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Episode {

    private String episodeTitle;
    private int episodeNumber;
    private String episodeThumbnail;
    private int totalScore;
    private int participantCount;
    private Date uploadDate;
    private List<Image> images;
    private Long episodeId;
    private int commentCount;
    private double rating;
    private String author;
    private String authorWords;
    private String nextEpisodeTitle;
    private String nextEpisodeThumbnail;

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Image {

        String images;

        public static Image toImage(ResponseEpisodeImage responseEpisodeImage) {
            return Image.builder()
                .images(responseEpisodeImage.getEpisodeImage())
                .build();
        }
    }

    public static Episode toDomainFromEntity(EpisodeEntity episodeEntity) {
        return Episode.builder()
            .episodeTitle(episodeEntity.getEpisodeTitle())
            .episodeNumber(episodeEntity.getEpisodeNumber())
            .episodeThumbnail(episodeEntity.getEpisodeThumbnail())
            .totalScore(episodeEntity.getTotalScore())
            .participantCount(episodeEntity.getParticipantCount())
            .uploadDate(episodeEntity.getUploadDate())
            .build();
    }

    public static Episode toDomainFromResponse(ResponseEpisodeDetail responseEpisodeDetail) {
        return Episode.builder()
            .images(responseEpisodeDetail.getImages().stream().map(Image::toImage).toList())
            .episodeId(responseEpisodeDetail.getEpisodeId())
            .commentCount(responseEpisodeDetail.getCommentCount())
            .rating(responseEpisodeDetail.getRating())
            .author(responseEpisodeDetail.getAuthor())
            .authorWords(responseEpisodeDetail.getAuthorWords())
            .nextEpisodeTitle(responseEpisodeDetail.getNextEpisodeTitle())
            .nextEpisodeThumbnail(responseEpisodeDetail.getNextEpisodeThumbnail())
            .build();
    }

}