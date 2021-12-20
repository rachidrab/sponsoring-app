package com.rachid.parrainage.web.rest;

import com.rachid.parrainage.domain.Week;
import com.rachid.parrainage.repository.WeekRepository;
import com.rachid.parrainage.service.WeekService;
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
 * REST controller for managing {@link com.rachid.parrainage.domain.Week}.
 */
@RestController
@RequestMapping("/api")
public class WeekResource {

    private final Logger log = LoggerFactory.getLogger(WeekResource.class);

    private static final String ENTITY_NAME = "week";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WeekService weekService;

    private final WeekRepository weekRepository;

    public WeekResource(WeekService weekService, WeekRepository weekRepository) {
        this.weekService = weekService;
        this.weekRepository = weekRepository;
    }

    /**
     * {@code POST  /weeks} : Create a new week.
     *
     * @param week the week to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new week, or with status {@code 400 (Bad Request)} if the week has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/weeks")
    public ResponseEntity<Week> createWeek(@RequestBody Week week) throws URISyntaxException {
        log.debug("REST request to save Week : {}", week);
        if (week.getId() != null) {
            throw new BadRequestAlertException("A new week cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Week result = weekService.save(week);
        return ResponseEntity
            .created(new URI("/api/weeks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /weeks/:id} : Updates an existing week.
     *
     * @param id the id of the week to save.
     * @param week the week to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated week,
     * or with status {@code 400 (Bad Request)} if the week is not valid,
     * or with status {@code 500 (Internal Server Error)} if the week couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/weeks/{id}")
    public ResponseEntity<Week> updateWeek(@PathVariable(value = "id", required = false) final Long id, @RequestBody Week week)
        throws URISyntaxException {
        log.debug("REST request to update Week : {}, {}", id, week);
        if (week.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, week.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!weekRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Week result = weekService.save(week);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, week.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /weeks/:id} : Partial updates given fields of an existing week, field will ignore if it is null
     *
     * @param id the id of the week to save.
     * @param week the week to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated week,
     * or with status {@code 400 (Bad Request)} if the week is not valid,
     * or with status {@code 404 (Not Found)} if the week is not found,
     * or with status {@code 500 (Internal Server Error)} if the week couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/weeks/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Week> partialUpdateWeek(@PathVariable(value = "id", required = false) final Long id, @RequestBody Week week)
        throws URISyntaxException {
        log.debug("REST request to partial update Week partially : {}, {}", id, week);
        if (week.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, week.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!weekRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Week> result = weekService.partialUpdate(week);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, week.getId().toString())
        );
    }

    /**
     * {@code GET  /weeks} : get all the weeks.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of weeks in body.
     */
    @GetMapping("/weeks")
    public ResponseEntity<List<Week>> getAllWeeks(Pageable pageable) {
        log.debug("REST request to get a page of Weeks");
        Page<Week> page = weekService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /weeks/:id} : get the "id" week.
     *
     * @param id the id of the week to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the week, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/weeks/{id}")
    public ResponseEntity<Week> getWeek(@PathVariable Long id) {
        log.debug("REST request to get Week : {}", id);
        Optional<Week> week = weekService.findOne(id);
        return ResponseUtil.wrapOrNotFound(week);
    }

    /**
     * {@code DELETE  /weeks/:id} : delete the "id" week.
     *
     * @param id the id of the week to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/weeks/{id}")
    public ResponseEntity<Void> deleteWeek(@PathVariable Long id) {
        log.debug("REST request to delete Week : {}", id);
        weekService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
