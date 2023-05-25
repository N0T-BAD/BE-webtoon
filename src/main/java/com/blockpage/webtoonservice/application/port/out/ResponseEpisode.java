package com.blockpage.webtoonservice.application.port.out;

import com.blockpage.webtoonservice.adaptor.infrastructure.entity.EpisodeEntity;
import com.blockpage.webtoonservice.domain.Episode;
import lombok.Getter;

@Getter
public class ResponseEpisode {

    private String episodeTitle;
    private String episodeThumbnail;
    private String uploadDate;
    private Integer totalScore;


    public ResponseEpisode(String episodeTitle, String episodeThumbnail, String uploadDate, Integer totalScore) {
        this.episodeTitle = episodeTitle;
        this.episodeThumbnail = episodeThumbnail;
        this.uploadDate = uploadDate;
        this.totalScore = totalScore;
    }

    public static ResponseEpisode toResponseFromEntity(EpisodeEntity episode) {
        return new ResponseEpisode(
            episode.getEpisodeTitle(),
            episode.getEpisodeThumbnail(),
            episode.getUploadDate().toString(),
            episode.getTotalScore());
    }

    public static ResponseEpisode toResponseFromDomain(Episode episode) {
        return new ResponseEpisode(
            episode.getEpisodeTitle(),
            episode.getEpisodeThumbnail(),
            episode.getUploadDate(),
            episode.getTotalScore()
        );
    }

}