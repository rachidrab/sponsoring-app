package com.rachid.parrainage.web.rest;

import com.rachid.parrainage.domain.Avatar;
import com.rachid.parrainage.repository.AvatarRepository;
import com.rachid.parrainage.service.AvatarService;
import com.rachid.parrainage.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.rachid.parrainage.domain.Avatar}.
 */
@RestController
@RequestMapping("/api")
public class AvatarResource {

    private final Logger log = LoggerFactory.getLogger(AvatarResource.class);

    private static final String ENTITY_NAME = "avatar";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AvatarService avatarService;

    private final AvatarRepository avatarRepository;

    public AvatarResource(AvatarService avatarService, AvatarRepository avatarRepository) {
        this.avatarService = avatarService;
        this.avatarRepository = avatarRepository;
    }

    /**
     * {@code POST  /avatars} : Create a new avatar.
     *
     * @param avatar the avatar to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new avatar, or with status {@code 400 (Bad Request)} if the avatar has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/avatars")
    public ResponseEntity<Avatar> createAvatar(@RequestBody Avatar avatar) throws URISyntaxException {
        log.debug("REST request to save Avatar : {}", avatar);
        if (avatar.getId() != null) {
            throw new BadRequestAlertException("A new avatar cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Avatar result = avatarService.save(avatar);
        return ResponseEntity
            .created(new URI("/api/avatars/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /avatars/:id} : Updates an existing avatar.
     *
     * @param id the id of the avatar to save.
     * @param avatar the avatar to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated avatar,
     * or with status {@code 400 (Bad Request)} if the avatar is not valid,
     * or with status {@code 500 (Internal Server Error)} if the avatar couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/avatars/{id}")
    public ResponseEntity<Avatar> updateAvatar(@PathVariable(value = "id", required = false) final Long id, @RequestBody Avatar avatar)
        throws URISyntaxException {
        log.debug("REST request to update Avatar : {}, {}", id, avatar);
        if (avatar.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, avatar.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!avatarRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Avatar result = avatarService.save(avatar);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, avatar.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /avatars/:id} : Partial updates given fields of an existing avatar, field will ignore if it is null
     *
     * @param id the id of the avatar to save.
     * @param avatar the avatar to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated avatar,
     * or with status {@code 400 (Bad Request)} if the avatar is not valid,
     * or with status {@code 404 (Not Found)} if the avatar is not found,
     * or with status {@code 500 (Internal Server Error)} if the avatar couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/avatars/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Avatar> partialUpdateAvatar(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Avatar avatar
    ) throws URISyntaxException {
        log.debug("REST request to partial update Avatar partially : {}, {}", id, avatar);
        if (avatar.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, avatar.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!avatarRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Avatar> result = avatarService.partialUpdate(avatar);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, avatar.getId().toString())
        );
    }

    /**
     * {@code GET  /avatars} : get all the avatars.
     *
     * @param pageable the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of avatars in body.
     */
    @GetMapping("/avatars")
    public ResponseEntity<List<Avatar>> getAllAvatars(
        Pageable pageable,
        @RequestParam(required = false, defaultValue = "false") boolean eagerload
    ) {
        log.debug("REST request to get a page of Avatars");
        Page<Avatar> page;
        if (eagerload) {
            page = avatarService.findAllWithEagerRelationships(pageable);
        } else {
            page = avatarService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /avatars/:id} : get the "id" avatar.
     *
     * @param id the id of the avatar to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the avatar, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/avatars/{id}")
    public ResponseEntity<Avatar> getAvatar(@PathVariable Long id) {
        log.debug("REST request to get Avatar : {}", id);
        Optional<Avatar> avatar = avatarService.findOne(id);
        return ResponseUtil.wrapOrNotFound(avatar);
    }

    /**
     * {@code DELETE  /avatars/:id} : delete the "id" avatar.
     *
     * @param id the id of the avatar to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/avatars/{id}")
    public ResponseEntity<Void> deleteAvatar(@PathVariable Long id) {
        log.debug("REST request to delete Avatar : {}", id);
        avatarService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
