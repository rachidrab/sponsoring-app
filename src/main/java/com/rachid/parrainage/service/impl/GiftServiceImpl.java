package com.rachid.parrainage.service.impl;

import com.rachid.parrainage.domain.Gift;
import com.rachid.parrainage.repository.GiftRepository;
import com.rachid.parrainage.service.GiftService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Gift}.
 */
@Service
@Transactional
public class GiftServiceImpl implements GiftService {

    private final Logger log = LoggerFactory.getLogger(GiftServiceImpl.class);

    private final GiftRepository giftRepository;

    public GiftServiceImpl(GiftRepository giftRepository) {
        this.giftRepository = giftRepository;
    }

    @Override
    public Gift save(Gift gift) {
        log.debug("Request to save Gift : {}", gift);
        return giftRepository.save(gift);
    }

    @Override
    public Optional<Gift> partialUpdate(Gift gift) {
        log.debug("Request to partially update Gift : {}", gift);

        return giftRepository
            .findById(gift.getId())
            .map(existingGift -> {
                if (gift.getBrandName() != null) {
                    existingGift.setBrandName(gift.getBrandName());
                }
                if (gift.getMaterial() != null) {
                    existingGift.setMaterial(gift.getMaterial());
                }
                if (gift.getStyle() != null) {
                    existingGift.setStyle(gift.getStyle());
                }
                if (gift.getSeason() != null) {
                    existingGift.setSeason(gift.getSeason());
                }
                if (gift.getType() != null) {
                    existingGift.setType(gift.getType());
                }
                if (gift.getClothingLength() != null) {
                    existingGift.setClothingLength(gift.getClothingLength());
                }
                if (gift.getCollar() != null) {
                    existingGift.setCollar(gift.getCollar());
                }
                if (gift.getSleeveLength() != null) {
                    existingGift.setSleeveLength(gift.getSleeveLength());
                }
                if (gift.getDesign() != null) {
                    existingGift.setDesign(gift.getDesign());
                }
                if (gift.getGender() != null) {
                    existingGift.setGender(gift.getGender());
                }
                if (gift.getOccasion() != null) {
                    existingGift.setOccasion(gift.getOccasion());
                }
                if (gift.getDecoration() != null) {
                    existingGift.setDecoration(gift.getDecoration());
                }
                if (gift.getClosureType() != null) {
                    existingGift.setClosureType(gift.getClosureType());
                }
                if (gift.getSleeveType() != null) {
                    existingGift.setSleeveType(gift.getSleeveType());
                }
                if (gift.getColor() != null) {
                    existingGift.setColor(gift.getColor());
                }
                if (gift.getQuality() != null) {
                    existingGift.setQuality(gift.getQuality());
                }
                if (gift.getFeatures() != null) {
                    existingGift.setFeatures(gift.getFeatures());
                }
                if (gift.getActiveNoiseCancellation() != null) {
                    existingGift.setActiveNoiseCancellation(gift.getActiveNoiseCancellation());
                }
                if (gift.getVolumeControl() != null) {
                    existingGift.setVolumeControl(gift.getVolumeControl());
                }
                if (gift.getWirelessType() != null) {
                    existingGift.setWirelessType(gift.getWirelessType());
                }
                if (gift.getFunctions() != null) {
                    existingGift.setFunctions(gift.getFunctions());
                }
                if (gift.getPackageList() != null) {
                    existingGift.setPackageList(gift.getPackageList());
                }
                if (gift.getBluetoothVersion() != null) {
                    existingGift.setBluetoothVersion(gift.getBluetoothVersion());
                }
                if (gift.getFrequency() != null) {
                    existingGift.setFrequency(gift.getFrequency());
                }
                if (gift.getModelNumber() != null) {
                    existingGift.setModelNumber(gift.getModelNumber());
                }
                if (gift.getDescription() != null) {
                    existingGift.setDescription(gift.getDescription());
                }
                if (gift.getRam() != null) {
                    existingGift.setRam(gift.getRam());
                }
                if (gift.getSuitableFor() != null) {
                    existingGift.setSuitableFor(gift.getSuitableFor());
                }
                if (gift.getScreenStyle() != null) {
                    existingGift.setScreenStyle(gift.getScreenStyle());
                }
                if (gift.getWeight() != null) {
                    existingGift.setWeight(gift.getWeight());
                }
                if (gift.getRom() != null) {
                    existingGift.setRom(gift.getRom());
                }
                if (gift.getBattery() != null) {
                    existingGift.setBattery(gift.getBattery());
                }
                if (gift.getTouchScreen() != null) {
                    existingGift.setTouchScreen(gift.getTouchScreen());
                }
                if (gift.getHooded() != null) {
                    existingGift.setHooded(gift.getHooded());
                }
                if (gift.getMadeIn() != null) {
                    existingGift.setMadeIn(gift.getMadeIn());
                }
                if (gift.getShippingFrom() != null) {
                    existingGift.setShippingFrom(gift.getShippingFrom());
                }
                if (gift.getSizee() != null) {
                    existingGift.setSizee(gift.getSizee());
                }

                return existingGift;
            })
            .map(giftRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Gift> findAll(Pageable pageable) {
        log.debug("Request to get all Gifts");
        return giftRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Gift> findOne(Long id) {
        log.debug("Request to get Gift : {}", id);
        return giftRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Gift : {}", id);
        giftRepository.deleteById(id);
    }
}
