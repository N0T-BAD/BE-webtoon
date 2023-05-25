package com.blockpage.webtoonservice.adaptor.infrastructure.entity;

import com.blockpage.webtoonservice.adaptor.infrastructure.value.WebtoonStatus;
import com.blockpage.webtoonservice.domain.Demand;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "episode")
public class EpisodeEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Long webtoonId;
    @Column
    private Long creatorId;
    @Column
    private String episodeTitle;
    @Column
    private String episodeThumbnail;
    @Column
    private Date uploadDate;
    @Column
    private String authorWords;
    @Column
    private Integer totalScore;
    @Column
    private Integer participantCount;
    @Column
    @Enumerated(EnumType.STRING)
    private WebtoonStatus episodeStatus;
    @Column
    private Integer episodePrice;
    @Column
    private Integer episodeNumber;
    @Column
    private Integer commentCount;

    public void update(WebtoonStatus webtoonStatus) {
        this.episodeStatus = webtoonStatus;
    }

    public static EpisodeEntity toEntity(Demand demand, int type) throws ParseException {

        return EpisodeEntity.builder()
            .episodeTitle(demand.getEpisodeTitle())
            .episodeNumber(demand.getEpisodeNumber())
            .creatorId(demand.getCreatorId())
            .webtoonId(demand.getWebtoonId())
            .uploadDate(new SimpleDateFormat("yyyyMMdd").parse(demand.getUploadDate()))
            .authorWords(demand.getAuthorWords())
            .episodeThumbnail(demand.getThumbnail())
            .episodeStatus(WebtoonStatus.findWebtoonStatusByKey(type))
            .build();
    }

    public static EpisodeEntity toEntity(Demand demand, String thumbnail, int type) throws ParseException {
        return EpisodeEntity.builder()
            .episodeTitle(demand.getEpisodeTitle())
            .episodeNumber(demand.getEpisodeNumber())
            .webtoonId(demand.getWebtoonId())
            .creatorId(demand.getCreatorId())
            .uploadDate(new SimpleDateFormat("yyyyMMdd").parse(demand.getUploadDate()))
            .authorWords(demand.getAuthorWords())
            .episodeThumbnail(thumbnail)
            .episodeStatus(WebtoonStatus.findWebtoonStatusByKey(type))
            .commentCount(0)
            .episodePrice(4)
            .participantCount(0)
            .totalScore(0)
            .build();
    }

}