package com.blockpage.webtoonservice.adaptor.web.view;

import com.blockpage.webtoonservice.application.port.out.ResponseEpisodeDetail;
import com.blockpage.webtoonservice.application.port.out.ResponseEpisodeImage;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EpisodeDetailView {

    private List<EpisodeImageView> images;
    private Long episodeId;
    private int commentCount;
    private double rating;
    private String author;
    private String authorWords;
    private String nextEpisodeTitle;
    private String nextEpisodeThumbnail;


    public EpisodeDetailView(List<EpisodeImageView> images, Long episodeId, int commentCount, double rating, String author,
        String authorWords,
        String nextEpisodeTitle, String nextEpisodeThumbnail) {
        this.images = images;
        this.episodeId = episodeId;
        this.commentCount = commentCount;
        this.rating = rating;
        this.author = author;
        this.authorWords = authorWords;
        this.nextEpisodeTitle = nextEpisodeTitle;
        this.nextEpisodeThumbnail = nextEpisodeThumbnail;
    }


    public static EpisodeDetailView toViewFromResponse(ResponseEpisodeDetail responseEpisodeDetail,
        List<ResponseEpisodeImage> responseEpisodeImages) {
        return EpisodeDetailView.builder()
            .images(responseEpisodeImages.stream()
                .map(EpisodeImageView::toViewFromResponse)
                .collect(Collectors.toList())
            )
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