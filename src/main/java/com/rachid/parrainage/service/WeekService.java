package com.rachid.parrainage.service;

import com.rachid.parrainage.domain.Week;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link Week}.
 */
public interface WeekService {
    /**
     * Save a week.
     *
     * @param week the entity to save.
     * @return the persisted entity.
     */
    Week save(Week week);

    /**
     * Partially updates a week.
     *
     * @param week the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Week> partialUpdate(Week week);

    /**
     * Get all the weeks.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Week> findAll(Pageable pageable);

    /**
     * Get the "id" week.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Week> findOne(Long id);

    /**
     * Delete the "id" week.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
