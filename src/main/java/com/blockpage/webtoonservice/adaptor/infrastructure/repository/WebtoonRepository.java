package com.blockpage.webtoonservice.adaptor.infrastructure.repository;

import com.blockpage.webtoonservice.adaptor.infrastructure.entity.WebtoonEntity;
import com.blockpage.webtoonservice.adaptor.infrastructure.value.GenreType;
import com.blockpage.webtoonservice.adaptor.infrastructure.value.PublicationDays;
import com.blockpage.webtoonservice.adaptor.infrastructure.value.WebtoonStatus;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WebtoonRepository extends JpaRepository<WebtoonEntity, Long>, JpaSpecificationExecutor<WebtoonEntity> {

    List<WebtoonEntity> findAllByGenreTypeAndWebtoonStatusOrderByViews(GenreType genre, WebtoonStatus status);

    List<WebtoonEntity> findByPublicationDaysAndWebtoonStatusOrderByViews(PublicationDays publicationDays, WebtoonStatus status);

    List<WebtoonEntity> findByWebtoonStatusOrderByViews(WebtoonStatus status);

    List<WebtoonEntity> findByCreatorId(String creatorId);

    Page<WebtoonEntity> findByCreatorIdAndWebtoonStatus(String creatorId, WebtoonStatus webtoonStatus, Pageable pageable);

    Optional<WebtoonEntity> findByWebtoonTitleAndCreatorIdAndWebtoonStatus(String title, String creatorId, WebtoonStatus status);

    void deleteByWebtoonTitleAndCreatorIdAndWebtoonStatus(String title, String creatorId, WebtoonStatus status);


}
