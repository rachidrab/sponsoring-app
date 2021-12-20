package com.rachid.parrainage.service.impl;

import com.rachid.parrainage.domain.Campaign;
import com.rachid.parrainage.repository.CampaignRepository;
import com.rachid.parrainage.service.CampaignService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Campaign}.
 */
@Service
@Transactional
public class CampaignServiceImpl implements CampaignService {

    private final Logger log = LoggerFactory.getLogger(CampaignServiceImpl.class);

    private final CampaignRepository campaignRepository;

    public CampaignServiceImpl(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    @Override
    public Campaign save(Campaign campaign) {
        log.debug("Request to save Campaign : {}", campaign);
        return campaignRepository.save(campaign);
    }

    @Override
    public Optional<Campaign> partialUpdate(Campaign campaign) {
        log.debug("Request to partially update Campaign : {}", campaign);

        return campaignRepository
            .findById(campaign.getId())
            .map(existingCampaign -> {
                if (campaign.getIsWeek() != null) {
                    existingCampaign.setIsWeek(campaign.getIsWeek());
                }
                if (campaign.getIsMonth() != null) {
                    existingCampaign.setIsMonth(campaign.getIsMonth());
                }

                return existingCampaign;
            })
            .map(campaignRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Campaign> findAll(Pageable pageable) {
        log.debug("Request to get all Campaigns");
        return campaignRepository.findAll(pageable);
    }

    public Page<Campaign> findAllWithEagerRelationships(Pageable pageable) {
        return campaignRepository.findAllWithEagerRelationships(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Campaign> findOne(Long id) {
        log.debug("Request to get Campaign : {}", id);
        return campaignRepository.findOneWithEagerRelationships(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Campaign : {}", id);
        campaignRepository.deleteById(id);
    }
}
