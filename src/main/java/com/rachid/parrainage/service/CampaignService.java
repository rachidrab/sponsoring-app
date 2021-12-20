package com.rachid.parrainage.service;

import com.rachid.parrainage.domain.Campaign;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link Campaign}.
 */
public interface CampaignService {
    /**
     * Save a campaign.
     *
     * @param campaign the entity to save.
     * @return the persisted entity.
     */
    Campaign save(Campaign campaign);

    /**
     * Partially updates a campaign.
     *
     * @param campaign the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Campaign> partialUpdate(Campaign campaign);

    /**
     * Get all the campaigns.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Campaign> findAll(Pageable pageable);

    /**
     * Get all the campaigns with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Campaign> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" campaign.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Campaign> findOne(Long id);

    /**
     * Delete the "id" campaign.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
