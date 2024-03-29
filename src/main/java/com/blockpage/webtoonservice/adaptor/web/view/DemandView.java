package com.blockpage.webtoonservice.adaptor.web.view;

import com.blockpage.webtoonservice.application.port.DemandDto;
import com.blockpage.webtoonservice.domain.Webtoon.GenreType;
import com.blockpage.webtoonservice.domain.Webtoon.PublicationDays;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class DemandView {

    private Long webtoonId;
    private String webtoonTitle;
    private String webtoonDescription;
    private String genre;
    private String publicationDays;
    private String illustrator;
    private String webtoonMainImage;
    private String webtoonThumbnail;
    private Integer episodeStatus;

    private Long episodeId;
    private String episodeTitle;
    private String uploadDate;
    private String authorWords;
    private String episodeThumbnail;
    private List<String> episodeImages;
    private Integer webtoonStatus;
    private Integer episodeNumber;

    private String main;
    private String thumbnail;
    private List<String> images;


    public DemandView(String webtoonTitle, String episodeTitle, String uploadDate, String authorWords, String episodeThumbnail,
        List<String> episodeImages, Integer webtoonStatus) {
        this.webtoonTitle = webtoonTitle;
        this.episodeTitle = episodeTitle;
        this.uploadDate = uploadDate;
        this.authorWords = authorWords;
        this.episodeThumbnail = episodeThumbnail;
        this.episodeImages = episodeImages;
        this.webtoonStatus = webtoonStatus;
    }

    public DemandView(String webtoonTitle, String webtoonDescription, String genre, String publicationDays, String illustrator,
        String webtoonMainImage, String webtoonThumbnail, Integer episodeStatus) {
        this.webtoonTitle = webtoonTitle;
        this.webtoonDescription = webtoonDescription;
        this.genre = genre;
        this.publicationDays = publicationDays;
        this.illustrator = illustrator;
        this.webtoonMainImage = webtoonMainImage;
        this.webtoonThumbnail = webtoonThumbnail;
        this.episodeStatus = episodeStatus;
    }

    public static DemandView toView(DemandDto demandDto) {
        return DemandView.builder()
            .webtoonId(demandDto.getWebtoonId())
            .webtoonTitle(demandDto.getWebtoonTitle())
            .webtoonDescription(demandDto.getWebtoonDescription())
            .genre(demandDto.getGenre() != null ? GenreType.findGenreTypeByKey(demandDto.getGenre()).toString() : null)
            .publicationDays(demandDto.getPublicationDays() != null ? PublicationDays.findPublicationDaysByKey(demandDto.getPublicationDays()).toString() : null)
            .webtoonMainImage(demandDto.getWebtoonMainImage() != null ? demandDto.getWebtoonMainImage().getName() : null)
            .webtoonThumbnail(demandDto.getWebtoonThumbnail() != null ? demandDto.getWebtoonThumbnail().getName() : null)
            .episodeId(demandDto.getEpisodeId())
            .episodeTitle(demandDto.getEpisodeTitle() != null ? demandDto.getEpisodeTitle() : null)
            .uploadDate(demandDto.getUploadDate() != null ? demandDto.getUploadDate().toString() : null)
            .authorWords(demandDto.getAuthorWords() != null ? demandDto.getAuthorWords() : null)
            .episodeNumber(demandDto.getEpisodeNumber())
            .main(demandDto.getMain())
            .thumbnail(demandDto.getThumbnail())
            .images(demandDto.getImages())
            .build();
    }
}
