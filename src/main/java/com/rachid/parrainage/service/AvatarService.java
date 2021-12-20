package com.rachid.parrainage.service;

import com.rachid.parrainage.domain.Avatar;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link Avatar}.
 */
public interface AvatarService {
    /**
     * Save a avatar.
     *
     * @param avatar the entity to save.
     * @return the persisted entity.
     */
    Avatar save(Avatar avatar);

    /**
     * Partially updates a avatar.
     *
     * @param avatar the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Avatar> partialUpdate(Avatar avatar);

    /**
     * Get all the avatars.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Avatar> findAll(Pageable pageable);

    /**
     * Get all the avatars with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Avatar> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" avatar.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Avatar> findOne(Long id);

    /**
     * Delete the "id" avatar.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
