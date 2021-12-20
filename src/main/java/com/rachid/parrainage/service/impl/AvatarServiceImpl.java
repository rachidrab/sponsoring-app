package com.rachid.parrainage.service.impl;

import com.rachid.parrainage.domain.Avatar;
import com.rachid.parrainage.repository.AvatarRepository;
import com.rachid.parrainage.service.AvatarService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Avatar}.
 */
@Service
@Transactional
public class AvatarServiceImpl implements AvatarService {

    private final Logger log = LoggerFactory.getLogger(AvatarServiceImpl.class);

    private final AvatarRepository avatarRepository;

    public AvatarServiceImpl(AvatarRepository avatarRepository) {
        this.avatarRepository = avatarRepository;
    }

    @Override
    public Avatar save(Avatar avatar) {
        log.debug("Request to save Avatar : {}", avatar);
        return avatarRepository.save(avatar);
    }

    @Override
    public Optional<Avatar> partialUpdate(Avatar avatar) {
        log.debug("Request to partially update Avatar : {}", avatar);

        return avatarRepository
            .findById(avatar.getId())
            .map(existingAvatar -> {
                if (avatar.getAvatar() != null) {
                    existingAvatar.setAvatar(avatar.getAvatar());
                }
                if (avatar.getAvatarContentType() != null) {
                    existingAvatar.setAvatarContentType(avatar.getAvatarContentType());
                }
                if (avatar.getTitle() != null) {
                    existingAvatar.setTitle(avatar.getTitle());
                }

                return existingAvatar;
            })
            .map(avatarRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Avatar> findAll(Pageable pageable) {
        log.debug("Request to get all Avatars");
        return avatarRepository.findAll(pageable);
    }

    public Page<Avatar> findAllWithEagerRelationships(Pageable pageable) {
        return avatarRepository.findAllWithEagerRelationships(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Avatar> findOne(Long id) {
        log.debug("Request to get Avatar : {}", id);
        return avatarRepository.findOneWithEagerRelationships(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Avatar : {}", id);
        avatarRepository.deleteById(id);
    }
}
