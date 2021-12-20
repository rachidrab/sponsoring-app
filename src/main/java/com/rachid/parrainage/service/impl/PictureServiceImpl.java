package com.rachid.parrainage.service.impl;

import com.rachid.parrainage.domain.Picture;
import com.rachid.parrainage.repository.PictureRepository;
import com.rachid.parrainage.service.PictureService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Picture}.
 */
@Service
@Transactional
public class PictureServiceImpl implements PictureService {

    private final Logger log = LoggerFactory.getLogger(PictureServiceImpl.class);

    private final PictureRepository pictureRepository;

    public PictureServiceImpl(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    @Override
    public Picture save(Picture picture) {
        log.debug("Request to save Picture : {}", picture);
        return pictureRepository.save(picture);
    }

    @Override
    public Optional<Picture> partialUpdate(Picture picture) {
        log.debug("Request to partially update Picture : {}", picture);

        return pictureRepository
            .findById(picture.getId())
            .map(existingPicture -> {
                if (picture.getImage() != null) {
                    existingPicture.setImage(picture.getImage());
                }
                if (picture.getImageContentType() != null) {
                    existingPicture.setImageContentType(picture.getImageContentType());
                }
                if (picture.getTitle() != null) {
                    existingPicture.setTitle(picture.getTitle());
                }

                return existingPicture;
            })
            .map(pictureRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Picture> findAll(Pageable pageable) {
        log.debug("Request to get all Pictures");
        return pictureRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Picture> findOne(Long id) {
        log.debug("Request to get Picture : {}", id);
        return pictureRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Picture : {}", id);
        pictureRepository.deleteById(id);
    }
}
