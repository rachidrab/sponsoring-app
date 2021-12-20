package com.rachid.parrainage.repository;

import com.rachid.parrainage.domain.Campaign;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Campaign entity.
 */
@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long> {
    @Query(
        value = "select distinct campaign from Campaign campaign left join fetch campaign.participants",
        countQuery = "select count(distinct campaign) from Campaign campaign"
    )
    Page<Campaign> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct campaign from Campaign campaign left join fetch campaign.participants")
    List<Campaign> findAllWithEagerRelationships();

    @Query("select campaign from Campaign campaign left join fetch campaign.participants where campaign.id =:id")
    Optional<Campaign> findOneWithEagerRelationships(@Param("id") Long id);
}
