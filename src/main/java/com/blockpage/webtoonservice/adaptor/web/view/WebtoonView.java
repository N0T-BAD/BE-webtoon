package com.blockpage.webtoonservice.adaptor.web.view;

import com.blockpage.webtoonservice.application.port.out.ResponseWebtoon;
import lombok.Getter;

@Getter
public class WebtoonView {

    private String webtoonTitle;
    private String webtoonThumbnail;
    private String creator;
    private String illustrator;
    private Integer views;
    private Integer interestCount;
    private Integer genreType;

    public WebtoonView(String webtoonTitle, String webtoonThumbnail, String creator, String illustrator, Integer views,
        Integer interestCount, Integer genreType) {
        this.webtoonTitle = webtoonTitle;
        this.webtoonThumbnail = webtoonThumbnail;
        this.creator = creator;
        this.illustrator = illustrator;
        this.views = views;
        this.interestCount = interestCount;
        this.genreType = genreType;
    }

    public static WebtoonView toViewFromResponse(ResponseWebtoon webtoon) {
        return new WebtoonView(
            webtoon.getWebtoonTitle(),
            webtoon.getWebtoonThumbnail(),
            webtoon.getCreator(),
            webtoon.getIllustrator(),
            webtoon.getViews(),
            webtoon.getInterestCount(),
            webtoon.getGenreType());
    }
}
