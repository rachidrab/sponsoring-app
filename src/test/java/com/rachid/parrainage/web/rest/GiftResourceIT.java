package com.rachid.parrainage.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.rachid.parrainage.IntegrationTest;
import com.rachid.parrainage.domain.Gift;
import com.rachid.parrainage.domain.enumeration.ActiveNoiseCancellation;
import com.rachid.parrainage.domain.enumeration.Hooded;
import com.rachid.parrainage.domain.enumeration.TouchScreen;
import com.rachid.parrainage.domain.enumeration.VolumeControl;
import com.rachid.parrainage.repository.GiftRepository;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link GiftResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class GiftResourceIT {

    private static final String DEFAULT_BRAND_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BRAND_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_MATERIAL = "AAAAAAAAAA";
    private static final String UPDATED_MATERIAL = "BBBBBBBBBB";

    private static final String DEFAULT_STYLE = "AAAAAAAAAA";
    private static final String UPDATED_STYLE = "BBBBBBBBBB";

    private static final String DEFAULT_SEASON = "AAAAAAAAAA";
    private static final String UPDATED_SEASON = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_CLOTHING_LENGTH = "AAAAAAAAAA";
    private static final String UPDATED_CLOTHING_LENGTH = "BBBBBBBBBB";

    private static final String DEFAULT_COLLAR = "AAAAAAAAAA";
    private static final String UPDATED_COLLAR = "BBBBBBBBBB";

    private static final String DEFAULT_SLEEVE_LENGTH = "AAAAAAAAAA";
    private static final String UPDATED_SLEEVE_LENGTH = "BBBBBBBBBB";

    private static final String DEFAULT_DESIGN = "AAAAAAAAAA";
    private static final String UPDATED_DESIGN = "BBBBBBBBBB";

    private static final String DEFAULT_GENDER = "AAAAAAAAAA";
    private static final String UPDATED_GENDER = "BBBBBBBBBB";

    private static final String DEFAULT_OCCASION = "AAAAAAAAAA";
    private static final String UPDATED_OCCASION = "BBBBBBBBBB";

    private static final String DEFAULT_DECORATION = "AAAAAAAAAA";
    private static final String UPDATED_DECORATION = "BBBBBBBBBB";

    private static final String DEFAULT_CLOSURE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_CLOSURE_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_SLEEVE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_SLEEVE_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_COLOR = "AAAAAAAAAA";
    private static final String UPDATED_COLOR = "BBBBBBBBBB";

    private static final String DEFAULT_QUALITY = "AAAAAAAAAA";
    private static final String UPDATED_QUALITY = "BBBBBBBBBB";

    private static final String DEFAULT_FEATURES = "AAAAAAAAAA";
    private static final String UPDATED_FEATURES = "BBBBBBBBBB";

    private static final ActiveNoiseCancellation DEFAULT_ACTIVE_NOISE_CANCELLATION = ActiveNoiseCancellation.YES;
    private static final ActiveNoiseCancellation UPDATED_ACTIVE_NOISE_CANCELLATION = ActiveNoiseCancellation.NO;

    private static final VolumeControl DEFAULT_VOLUME_CONTROL = VolumeControl.YES;
    private static final VolumeControl UPDATED_VOLUME_CONTROL = VolumeControl.NO;

    private static final String DEFAULT_WIRELESS_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_WIRELESS_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_FUNCTIONS = "AAAAAAAAAA";
    private static final String UPDATED_FUNCTIONS = "BBBBBBBBBB";

    private static final String DEFAULT_PACKAGE_LIST = "AAAAAAAAAA";
    private static final String UPDATED_PACKAGE_LIST = "BBBBBBBBBB";

    private static final String DEFAULT_BLUETOOTH_VERSION = "AAAAAAAAAA";
    private static final String UPDATED_BLUETOOTH_VERSION = "BBBBBBBBBB";

    private static final String DEFAULT_FREQUENCY = "AAAAAAAAAA";
    private static final String UPDATED_FREQUENCY = "BBBBBBBBBB";

    private static final String DEFAULT_MODEL_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_MODEL_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_RAM = "AAAAAAAAAA";
    private static final String UPDATED_RAM = "BBBBBBBBBB";

    private static final String DEFAULT_SUITABLE_FOR = "AAAAAAAAAA";
    private static final String UPDATED_SUITABLE_FOR = "BBBBBBBBBB";

    private static final String DEFAULT_SCREEN_STYLE = "AAAAAAAAAA";
    private static final String UPDATED_SCREEN_STYLE = "BBBBBBBBBB";

    private static final String DEFAULT_WEIGHT = "AAAAAAAAAA";
    private static final String UPDATED_WEIGHT = "BBBBBBBBBB";

    private static final String DEFAULT_ROM = "AAAAAAAAAA";
    private static final String UPDATED_ROM = "BBBBBBBBBB";

    private static final String DEFAULT_BATTERY = "AAAAAAAAAA";
    private static final String UPDATED_BATTERY = "BBBBBBBBBB";

    private static final TouchScreen DEFAULT_TOUCH_SCREEN = TouchScreen.YES;
    private static final TouchScreen UPDATED_TOUCH_SCREEN = TouchScreen.NO;

    private static final Hooded DEFAULT_HOODED = Hooded.YES;
    private static final Hooded UPDATED_HOODED = Hooded.NO;

    private static final String DEFAULT_MADE_IN = "AAAAAAAAAA";
    private static final String UPDATED_MADE_IN = "BBBBBBBBBB";

    private static final String DEFAULT_SHIPPING_FROM = "AAAAAAAAAA";
    private static final String UPDATED_SHIPPING_FROM = "BBBBBBBBBB";

    private static final String DEFAULT_SIZEE = "AAAAAAAAAA";
    private static final String UPDATED_SIZEE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/gifts";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private GiftRepository giftRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restGiftMockMvc;

    private Gift gift;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Gift createEntity(EntityManager em) {
        Gift gift = new Gift()
            .brandName(DEFAULT_BRAND_NAME)
            .material(DEFAULT_MATERIAL)
            .style(DEFAULT_STYLE)
            .season(DEFAULT_SEASON)
            .type(DEFAULT_TYPE)
            .clothingLength(DEFAULT_CLOTHING_LENGTH)
            .collar(DEFAULT_COLLAR)
            .sleeveLength(DEFAULT_SLEEVE_LENGTH)
            .design(DEFAULT_DESIGN)
            .gender(DEFAULT_GENDER)
            .occasion(DEFAULT_OCCASION)
            .decoration(DEFAULT_DECORATION)
            .closureType(DEFAULT_CLOSURE_TYPE)
            .sleeveType(DEFAULT_SLEEVE_TYPE)
            .color(DEFAULT_COLOR)
            .quality(DEFAULT_QUALITY)
            .features(DEFAULT_FEATURES)
            .activeNoiseCancellation(DEFAULT_ACTIVE_NOISE_CANCELLATION)
            .volumeControl(DEFAULT_VOLUME_CONTROL)
            .wirelessType(DEFAULT_WIRELESS_TYPE)
            .functions(DEFAULT_FUNCTIONS)
            .packageList(DEFAULT_PACKAGE_LIST)
            .bluetoothVersion(DEFAULT_BLUETOOTH_VERSION)
            .frequency(DEFAULT_FREQUENCY)
            .modelNumber(DEFAULT_MODEL_NUMBER)
            .description(DEFAULT_DESCRIPTION)
            .ram(DEFAULT_RAM)
            .suitableFor(DEFAULT_SUITABLE_FOR)
            .screenStyle(DEFAULT_SCREEN_STYLE)
            .weight(DEFAULT_WEIGHT)
            .rom(DEFAULT_ROM)
            .battery(DEFAULT_BATTERY)
            .touchScreen(DEFAULT_TOUCH_SCREEN)
            .hooded(DEFAULT_HOODED)
            .madeIn(DEFAULT_MADE_IN)
            .shippingFrom(DEFAULT_SHIPPING_FROM)
            .sizee(DEFAULT_SIZEE);
        return gift;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Gift createUpdatedEntity(EntityManager em) {
        Gift gift = new Gift()
            .brandName(UPDATED_BRAND_NAME)
            .material(UPDATED_MATERIAL)
            .style(UPDATED_STYLE)
            .season(UPDATED_SEASON)
            .type(UPDATED_TYPE)
            .clothingLength(UPDATED_CLOTHING_LENGTH)
            .collar(UPDATED_COLLAR)
            .sleeveLength(UPDATED_SLEEVE_LENGTH)
            .design(UPDATED_DESIGN)
            .gender(UPDATED_GENDER)
            .occasion(UPDATED_OCCASION)
            .decoration(UPDATED_DECORATION)
            .closureType(UPDATED_CLOSURE_TYPE)
            .sleeveType(UPDATED_SLEEVE_TYPE)
            .color(UPDATED_COLOR)
            .quality(UPDATED_QUALITY)
            .features(UPDATED_FEATURES)
            .activeNoiseCancellation(UPDATED_ACTIVE_NOISE_CANCELLATION)
            .volumeControl(UPDATED_VOLUME_CONTROL)
            .wirelessType(UPDATED_WIRELESS_TYPE)
            .functions(UPDATED_FUNCTIONS)
            .packageList(UPDATED_PACKAGE_LIST)
            .bluetoothVersion(UPDATED_BLUETOOTH_VERSION)
            .frequency(UPDATED_FREQUENCY)
            .modelNumber(UPDATED_MODEL_NUMBER)
            .description(UPDATED_DESCRIPTION)
            .ram(UPDATED_RAM)
            .suitableFor(UPDATED_SUITABLE_FOR)
            .screenStyle(UPDATED_SCREEN_STYLE)
            .weight(UPDATED_WEIGHT)
            .rom(UPDATED_ROM)
            .battery(UPDATED_BATTERY)
            .touchScreen(UPDATED_TOUCH_SCREEN)
            .hooded(UPDATED_HOODED)
            .madeIn(UPDATED_MADE_IN)
            .shippingFrom(UPDATED_SHIPPING_FROM)
            .sizee(UPDATED_SIZEE);
        return gift;
    }

    @BeforeEach
    public void initTest() {
        gift = createEntity(em);
    }

    @Test
    @Transactional
    void createGift() throws Exception {
        int databaseSizeBeforeCreate = giftRepository.findAll().size();
        // Create the Gift
        restGiftMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(gift)))
            .andExpect(status().isCreated());

        // Validate the Gift in the database
        List<Gift> giftList = giftRepository.findAll();
        assertThat(giftList).hasSize(databaseSizeBeforeCreate + 1);
        Gift testGift = giftList.get(giftList.size() - 1);
        assertThat(testGift.getBrandName()).isEqualTo(DEFAULT_BRAND_NAME);
        assertThat(testGift.getMaterial()).isEqualTo(DEFAULT_MATERIAL);
        assertThat(testGift.getStyle()).isEqualTo(DEFAULT_STYLE);
        assertThat(testGift.getSeason()).isEqualTo(DEFAULT_SEASON);
        assertThat(testGift.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testGift.getClothingLength()).isEqualTo(DEFAULT_CLOTHING_LENGTH);
        assertThat(testGift.getCollar()).isEqualTo(DEFAULT_COLLAR);
        assertThat(testGift.getSleeveLength()).isEqualTo(DEFAULT_SLEEVE_LENGTH);
        assertThat(testGift.getDesign()).isEqualTo(DEFAULT_DESIGN);
        assertThat(testGift.getGender()).isEqualTo(DEFAULT_GENDER);
        assertThat(testGift.getOccasion()).isEqualTo(DEFAULT_OCCASION);
        assertThat(testGift.getDecoration()).isEqualTo(DEFAULT_DECORATION);
        assertThat(testGift.getClosureType()).isEqualTo(DEFAULT_CLOSURE_TYPE);
        assertThat(testGift.getSleeveType()).isEqualTo(DEFAULT_SLEEVE_TYPE);
        assertThat(testGift.getColor()).isEqualTo(DEFAULT_COLOR);
        assertThat(testGift.getQuality()).isEqualTo(DEFAULT_QUALITY);
        assertThat(testGift.getFeatures()).isEqualTo(DEFAULT_FEATURES);
        assertThat(testGift.getActiveNoiseCancellation()).isEqualTo(DEFAULT_ACTIVE_NOISE_CANCELLATION);
        assertThat(testGift.getVolumeControl()).isEqualTo(DEFAULT_VOLUME_CONTROL);
        assertThat(testGift.getWirelessType()).isEqualTo(DEFAULT_WIRELESS_TYPE);
        assertThat(testGift.getFunctions()).isEqualTo(DEFAULT_FUNCTIONS);
        assertThat(testGift.getPackageList()).isEqualTo(DEFAULT_PACKAGE_LIST);
        assertThat(testGift.getBluetoothVersion()).isEqualTo(DEFAULT_BLUETOOTH_VERSION);
        assertThat(testGift.getFrequency()).isEqualTo(DEFAULT_FREQUENCY);
        assertThat(testGift.getModelNumber()).isEqualTo(DEFAULT_MODEL_NUMBER);
        assertThat(testGift.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testGift.getRam()).isEqualTo(DEFAULT_RAM);
        assertThat(testGift.getSuitableFor()).isEqualTo(DEFAULT_SUITABLE_FOR);
        assertThat(testGift.getScreenStyle()).isEqualTo(DEFAULT_SCREEN_STYLE);
        assertThat(testGift.getWeight()).isEqualTo(DEFAULT_WEIGHT);
        assertThat(testGift.getRom()).isEqualTo(DEFAULT_ROM);
        assertThat(testGift.getBattery()).isEqualTo(DEFAULT_BATTERY);
        assertThat(testGift.getTouchScreen()).isEqualTo(DEFAULT_TOUCH_SCREEN);
        assertThat(testGift.getHooded()).isEqualTo(DEFAULT_HOODED);
        assertThat(testGift.getMadeIn()).isEqualTo(DEFAULT_MADE_IN);
        assertThat(testGift.getShippingFrom()).isEqualTo(DEFAULT_SHIPPING_FROM);
        assertThat(testGift.getSizee()).isEqualTo(DEFAULT_SIZEE);
    }

    @Test
    @Transactional
    void createGiftWithExistingId() throws Exception {
        // Create the Gift with an existing ID
        gift.setId(1L);

        int databaseSizeBeforeCreate = giftRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restGiftMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(gift)))
            .andExpect(status().isBadRequest());

        // Validate the Gift in the database
        List<Gift> giftList = giftRepository.findAll();
        assertThat(giftList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkDescriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = giftRepository.findAll().size();
        // set the field null
        gift.setDescription(null);

        // Create the Gift, which fails.

        restGiftMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(gift)))
            .andExpect(status().isBadRequest());

        List<Gift> giftList = giftRepository.findAll();
        assertThat(giftList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllGifts() throws Exception {
        // Initialize the database
        giftRepository.saveAndFlush(gift);

        // Get all the giftList
        restGiftMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(gift.getId().intValue())))
            .andExpect(jsonPath("$.[*].brandName").value(hasItem(DEFAULT_BRAND_NAME)))
            .andExpect(jsonPath("$.[*].material").value(hasItem(DEFAULT_MATERIAL)))
            .andExpect(jsonPath("$.[*].style").value(hasItem(DEFAULT_STYLE)))
            .andExpect(jsonPath("$.[*].season").value(hasItem(DEFAULT_SEASON)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].clothingLength").value(hasItem(DEFAULT_CLOTHING_LENGTH)))
            .andExpect(jsonPath("$.[*].collar").value(hasItem(DEFAULT_COLLAR)))
            .andExpect(jsonPath("$.[*].sleeveLength").value(hasItem(DEFAULT_SLEEVE_LENGTH)))
            .andExpect(jsonPath("$.[*].design").value(hasItem(DEFAULT_DESIGN)))
            .andExpect(jsonPath("$.[*].gender").value(hasItem(DEFAULT_GENDER)))
            .andExpect(jsonPath("$.[*].occasion").value(hasItem(DEFAULT_OCCASION)))
            .andExpect(jsonPath("$.[*].decoration").value(hasItem(DEFAULT_DECORATION)))
            .andExpect(jsonPath("$.[*].closureType").value(hasItem(DEFAULT_CLOSURE_TYPE)))
            .andExpect(jsonPath("$.[*].sleeveType").value(hasItem(DEFAULT_SLEEVE_TYPE)))
            .andExpect(jsonPath("$.[*].color").value(hasItem(DEFAULT_COLOR)))
            .andExpect(jsonPath("$.[*].quality").value(hasItem(DEFAULT_QUALITY)))
            .andExpect(jsonPath("$.[*].features").value(hasItem(DEFAULT_FEATURES)))
            .andExpect(jsonPath("$.[*].activeNoiseCancellation").value(hasItem(DEFAULT_ACTIVE_NOISE_CANCELLATION.toString())))
            .andExpect(jsonPath("$.[*].volumeControl").value(hasItem(DEFAULT_VOLUME_CONTROL.toString())))
            .andExpect(jsonPath("$.[*].wirelessType").value(hasItem(DEFAULT_WIRELESS_TYPE)))
            .andExpect(jsonPath("$.[*].functions").value(hasItem(DEFAULT_FUNCTIONS)))
            .andExpect(jsonPath("$.[*].packageList").value(hasItem(DEFAULT_PACKAGE_LIST)))
            .andExpect(jsonPath("$.[*].bluetoothVersion").value(hasItem(DEFAULT_BLUETOOTH_VERSION)))
            .andExpect(jsonPath("$.[*].frequency").value(hasItem(DEFAULT_FREQUENCY)))
            .andExpect(jsonPath("$.[*].modelNumber").value(hasItem(DEFAULT_MODEL_NUMBER)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].ram").value(hasItem(DEFAULT_RAM)))
            .andExpect(jsonPath("$.[*].suitableFor").value(hasItem(DEFAULT_SUITABLE_FOR)))
            .andExpect(jsonPath("$.[*].screenStyle").value(hasItem(DEFAULT_SCREEN_STYLE)))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT)))
            .andExpect(jsonPath("$.[*].rom").value(hasItem(DEFAULT_ROM)))
            .andExpect(jsonPath("$.[*].battery").value(hasItem(DEFAULT_BATTERY)))
            .andExpect(jsonPath("$.[*].touchScreen").value(hasItem(DEFAULT_TOUCH_SCREEN.toString())))
            .andExpect(jsonPath("$.[*].hooded").value(hasItem(DEFAULT_HOODED.toString())))
            .andExpect(jsonPath("$.[*].madeIn").value(hasItem(DEFAULT_MADE_IN)))
            .andExpect(jsonPath("$.[*].shippingFrom").value(hasItem(DEFAULT_SHIPPING_FROM)))
            .andExpect(jsonPath("$.[*].sizee").value(hasItem(DEFAULT_SIZEE)));
    }

    @Test
    @Transactional
    void getGift() throws Exception {
        // Initialize the database
        giftRepository.saveAndFlush(gift);

        // Get the gift
        restGiftMockMvc
            .perform(get(ENTITY_API_URL_ID, gift.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(gift.getId().intValue()))
            .andExpect(jsonPath("$.brandName").value(DEFAULT_BRAND_NAME))
            .andExpect(jsonPath("$.material").value(DEFAULT_MATERIAL))
            .andExpect(jsonPath("$.style").value(DEFAULT_STYLE))
            .andExpect(jsonPath("$.season").value(DEFAULT_SEASON))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.clothingLength").value(DEFAULT_CLOTHING_LENGTH))
            .andExpect(jsonPath("$.collar").value(DEFAULT_COLLAR))
            .andExpect(jsonPath("$.sleeveLength").value(DEFAULT_SLEEVE_LENGTH))
            .andExpect(jsonPath("$.design").value(DEFAULT_DESIGN))
            .andExpect(jsonPath("$.gender").value(DEFAULT_GENDER))
            .andExpect(jsonPath("$.occasion").value(DEFAULT_OCCASION))
            .andExpect(jsonPath("$.decoration").value(DEFAULT_DECORATION))
            .andExpect(jsonPath("$.closureType").value(DEFAULT_CLOSURE_TYPE))
            .andExpect(jsonPath("$.sleeveType").value(DEFAULT_SLEEVE_TYPE))
            .andExpect(jsonPath("$.color").value(DEFAULT_COLOR))
            .andExpect(jsonPath("$.quality").value(DEFAULT_QUALITY))
            .andExpect(jsonPath("$.features").value(DEFAULT_FEATURES))
            .andExpect(jsonPath("$.activeNoiseCancellation").value(DEFAULT_ACTIVE_NOISE_CANCELLATION.toString()))
            .andExpect(jsonPath("$.volumeControl").value(DEFAULT_VOLUME_CONTROL.toString()))
            .andExpect(jsonPath("$.wirelessType").value(DEFAULT_WIRELESS_TYPE))
            .andExpect(jsonPath("$.functions").value(DEFAULT_FUNCTIONS))
            .andExpect(jsonPath("$.packageList").value(DEFAULT_PACKAGE_LIST))
            .andExpect(jsonPath("$.bluetoothVersion").value(DEFAULT_BLUETOOTH_VERSION))
            .andExpect(jsonPath("$.frequency").value(DEFAULT_FREQUENCY))
            .andExpect(jsonPath("$.modelNumber").value(DEFAULT_MODEL_NUMBER))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.ram").value(DEFAULT_RAM))
            .andExpect(jsonPath("$.suitableFor").value(DEFAULT_SUITABLE_FOR))
            .andExpect(jsonPath("$.screenStyle").value(DEFAULT_SCREEN_STYLE))
            .andExpect(jsonPath("$.weight").value(DEFAULT_WEIGHT))
            .andExpect(jsonPath("$.rom").value(DEFAULT_ROM))
            .andExpect(jsonPath("$.battery").value(DEFAULT_BATTERY))
            .andExpect(jsonPath("$.touchScreen").value(DEFAULT_TOUCH_SCREEN.toString()))
            .andExpect(jsonPath("$.hooded").value(DEFAULT_HOODED.toString()))
            .andExpect(jsonPath("$.madeIn").value(DEFAULT_MADE_IN))
            .andExpect(jsonPath("$.shippingFrom").value(DEFAULT_SHIPPING_FROM))
            .andExpect(jsonPath("$.sizee").value(DEFAULT_SIZEE));
    }

    @Test
    @Transactional
    void getNonExistingGift() throws Exception {
        // Get the gift
        restGiftMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewGift() throws Exception {
        // Initialize the database
        giftRepository.saveAndFlush(gift);

        int databaseSizeBeforeUpdate = giftRepository.findAll().size();

        // Update the gift
        Gift updatedGift = giftRepository.findById(gift.getId()).get();
        // Disconnect from session so that the updates on updatedGift are not directly saved in db
        em.detach(updatedGift);
        updatedGift
            .brandName(UPDATED_BRAND_NAME)
            .material(UPDATED_MATERIAL)
            .style(UPDATED_STYLE)
            .season(UPDATED_SEASON)
            .type(UPDATED_TYPE)
            .clothingLength(UPDATED_CLOTHING_LENGTH)
            .collar(UPDATED_COLLAR)
            .sleeveLength(UPDATED_SLEEVE_LENGTH)
            .design(UPDATED_DESIGN)
            .gender(UPDATED_GENDER)
            .occasion(UPDATED_OCCASION)
            .decoration(UPDATED_DECORATION)
            .closureType(UPDATED_CLOSURE_TYPE)
            .sleeveType(UPDATED_SLEEVE_TYPE)
            .color(UPDATED_COLOR)
            .quality(UPDATED_QUALITY)
            .features(UPDATED_FEATURES)
            .activeNoiseCancellation(UPDATED_ACTIVE_NOISE_CANCELLATION)
            .volumeControl(UPDATED_VOLUME_CONTROL)
            .wirelessType(UPDATED_WIRELESS_TYPE)
            .functions(UPDATED_FUNCTIONS)
            .packageList(UPDATED_PACKAGE_LIST)
            .bluetoothVersion(UPDATED_BLUETOOTH_VERSION)
            .frequency(UPDATED_FREQUENCY)
            .modelNumber(UPDATED_MODEL_NUMBER)
            .description(UPDATED_DESCRIPTION)
            .ram(UPDATED_RAM)
            .suitableFor(UPDATED_SUITABLE_FOR)
            .screenStyle(UPDATED_SCREEN_STYLE)
            .weight(UPDATED_WEIGHT)
            .rom(UPDATED_ROM)
            .battery(UPDATED_BATTERY)
            .touchScreen(UPDATED_TOUCH_SCREEN)
            .hooded(UPDATED_HOODED)
            .madeIn(UPDATED_MADE_IN)
            .shippingFrom(UPDATED_SHIPPING_FROM)
            .sizee(UPDATED_SIZEE);

        restGiftMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedGift.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedGift))
            )
            .andExpect(status().isOk());

        // Validate the Gift in the database
        List<Gift> giftList = giftRepository.findAll();
        assertThat(giftList).hasSize(databaseSizeBeforeUpdate);
        Gift testGift = giftList.get(giftList.size() - 1);
        assertThat(testGift.getBrandName()).isEqualTo(UPDATED_BRAND_NAME);
        assertThat(testGift.getMaterial()).isEqualTo(UPDATED_MATERIAL);
        assertThat(testGift.getStyle()).isEqualTo(UPDATED_STYLE);
        assertThat(testGift.getSeason()).isEqualTo(UPDATED_SEASON);
        assertThat(testGift.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testGift.getClothingLength()).isEqualTo(UPDATED_CLOTHING_LENGTH);
        assertThat(testGift.getCollar()).isEqualTo(UPDATED_COLLAR);
        assertThat(testGift.getSleeveLength()).isEqualTo(UPDATED_SLEEVE_LENGTH);
        assertThat(testGift.getDesign()).isEqualTo(UPDATED_DESIGN);
        assertThat(testGift.getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testGift.getOccasion()).isEqualTo(UPDATED_OCCASION);
        assertThat(testGift.getDecoration()).isEqualTo(UPDATED_DECORATION);
        assertThat(testGift.getClosureType()).isEqualTo(UPDATED_CLOSURE_TYPE);
        assertThat(testGift.getSleeveType()).isEqualTo(UPDATED_SLEEVE_TYPE);
        assertThat(testGift.getColor()).isEqualTo(UPDATED_COLOR);
        assertThat(testGift.getQuality()).isEqualTo(UPDATED_QUALITY);
        assertThat(testGift.getFeatures()).isEqualTo(UPDATED_FEATURES);
        assertThat(testGift.getActiveNoiseCancellation()).isEqualTo(UPDATED_ACTIVE_NOISE_CANCELLATION);
        assertThat(testGift.getVolumeControl()).isEqualTo(UPDATED_VOLUME_CONTROL);
        assertThat(testGift.getWirelessType()).isEqualTo(UPDATED_WIRELESS_TYPE);
        assertThat(testGift.getFunctions()).isEqualTo(UPDATED_FUNCTIONS);
        assertThat(testGift.getPackageList()).isEqualTo(UPDATED_PACKAGE_LIST);
        assertThat(testGift.getBluetoothVersion()).isEqualTo(UPDATED_BLUETOOTH_VERSION);
        assertThat(testGift.getFrequency()).isEqualTo(UPDATED_FREQUENCY);
        assertThat(testGift.getModelNumber()).isEqualTo(UPDATED_MODEL_NUMBER);
        assertThat(testGift.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testGift.getRam()).isEqualTo(UPDATED_RAM);
        assertThat(testGift.getSuitableFor()).isEqualTo(UPDATED_SUITABLE_FOR);
        assertThat(testGift.getScreenStyle()).isEqualTo(UPDATED_SCREEN_STYLE);
        assertThat(testGift.getWeight()).isEqualTo(UPDATED_WEIGHT);
        assertThat(testGift.getRom()).isEqualTo(UPDATED_ROM);
        assertThat(testGift.getBattery()).isEqualTo(UPDATED_BATTERY);
        assertThat(testGift.getTouchScreen()).isEqualTo(UPDATED_TOUCH_SCREEN);
        assertThat(testGift.getHooded()).isEqualTo(UPDATED_HOODED);
        assertThat(testGift.getMadeIn()).isEqualTo(UPDATED_MADE_IN);
        assertThat(testGift.getShippingFrom()).isEqualTo(UPDATED_SHIPPING_FROM);
        assertThat(testGift.getSizee()).isEqualTo(UPDATED_SIZEE);
    }

    @Test
    @Transactional
    void putNonExistingGift() throws Exception {
        int databaseSizeBeforeUpdate = giftRepository.findAll().size();
        gift.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGiftMockMvc
            .perform(
                put(ENTITY_API_URL_ID, gift.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(gift))
            )
            .andExpect(status().isBadRequest());

        // Validate the Gift in the database
        List<Gift> giftList = giftRepository.findAll();
        assertThat(giftList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchGift() throws Exception {
        int databaseSizeBeforeUpdate = giftRepository.findAll().size();
        gift.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGiftMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(gift))
            )
            .andExpect(status().isBadRequest());

        // Validate the Gift in the database
        List<Gift> giftList = giftRepository.findAll();
        assertThat(giftList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamGift() throws Exception {
        int databaseSizeBeforeUpdate = giftRepository.findAll().size();
        gift.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGiftMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(gift)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Gift in the database
        List<Gift> giftList = giftRepository.findAll();
        assertThat(giftList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateGiftWithPatch() throws Exception {
        // Initialize the database
        giftRepository.saveAndFlush(gift);

        int databaseSizeBeforeUpdate = giftRepository.findAll().size();

        // Update the gift using partial update
        Gift partialUpdatedGift = new Gift();
        partialUpdatedGift.setId(gift.getId());

        partialUpdatedGift
            .brandName(UPDATED_BRAND_NAME)
            .material(UPDATED_MATERIAL)
            .season(UPDATED_SEASON)
            .type(UPDATED_TYPE)
            .sleeveLength(UPDATED_SLEEVE_LENGTH)
            .design(UPDATED_DESIGN)
            .gender(UPDATED_GENDER)
            .occasion(UPDATED_OCCASION)
            .closureType(UPDATED_CLOSURE_TYPE)
            .color(UPDATED_COLOR)
            .features(UPDATED_FEATURES)
            .activeNoiseCancellation(UPDATED_ACTIVE_NOISE_CANCELLATION)
            .bluetoothVersion(UPDATED_BLUETOOTH_VERSION)
            .modelNumber(UPDATED_MODEL_NUMBER)
            .ram(UPDATED_RAM)
            .suitableFor(UPDATED_SUITABLE_FOR)
            .screenStyle(UPDATED_SCREEN_STYLE)
            .weight(UPDATED_WEIGHT)
            .rom(UPDATED_ROM)
            .touchScreen(UPDATED_TOUCH_SCREEN)
            .hooded(UPDATED_HOODED)
            .madeIn(UPDATED_MADE_IN)
            .sizee(UPDATED_SIZEE);

        restGiftMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedGift.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedGift))
            )
            .andExpect(status().isOk());

        // Validate the Gift in the database
        List<Gift> giftList = giftRepository.findAll();
        assertThat(giftList).hasSize(databaseSizeBeforeUpdate);
        Gift testGift = giftList.get(giftList.size() - 1);
        assertThat(testGift.getBrandName()).isEqualTo(UPDATED_BRAND_NAME);
        assertThat(testGift.getMaterial()).isEqualTo(UPDATED_MATERIAL);
        assertThat(testGift.getStyle()).isEqualTo(DEFAULT_STYLE);
        assertThat(testGift.getSeason()).isEqualTo(UPDATED_SEASON);
        assertThat(testGift.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testGift.getClothingLength()).isEqualTo(DEFAULT_CLOTHING_LENGTH);
        assertThat(testGift.getCollar()).isEqualTo(DEFAULT_COLLAR);
        assertThat(testGift.getSleeveLength()).isEqualTo(UPDATED_SLEEVE_LENGTH);
        assertThat(testGift.getDesign()).isEqualTo(UPDATED_DESIGN);
        assertThat(testGift.getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testGift.getOccasion()).isEqualTo(UPDATED_OCCASION);
        assertThat(testGift.getDecoration()).isEqualTo(DEFAULT_DECORATION);
        assertThat(testGift.getClosureType()).isEqualTo(UPDATED_CLOSURE_TYPE);
        assertThat(testGift.getSleeveType()).isEqualTo(DEFAULT_SLEEVE_TYPE);
        assertThat(testGift.getColor()).isEqualTo(UPDATED_COLOR);
        assertThat(testGift.getQuality()).isEqualTo(DEFAULT_QUALITY);
        assertThat(testGift.getFeatures()).isEqualTo(UPDATED_FEATURES);
        assertThat(testGift.getActiveNoiseCancellation()).isEqualTo(UPDATED_ACTIVE_NOISE_CANCELLATION);
        assertThat(testGift.getVolumeControl()).isEqualTo(DEFAULT_VOLUME_CONTROL);
        assertThat(testGift.getWirelessType()).isEqualTo(DEFAULT_WIRELESS_TYPE);
        assertThat(testGift.getFunctions()).isEqualTo(DEFAULT_FUNCTIONS);
        assertThat(testGift.getPackageList()).isEqualTo(DEFAULT_PACKAGE_LIST);
        assertThat(testGift.getBluetoothVersion()).isEqualTo(UPDATED_BLUETOOTH_VERSION);
        assertThat(testGift.getFrequency()).isEqualTo(DEFAULT_FREQUENCY);
        assertThat(testGift.getModelNumber()).isEqualTo(UPDATED_MODEL_NUMBER);
        assertThat(testGift.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testGift.getRam()).isEqualTo(UPDATED_RAM);
        assertThat(testGift.getSuitableFor()).isEqualTo(UPDATED_SUITABLE_FOR);
        assertThat(testGift.getScreenStyle()).isEqualTo(UPDATED_SCREEN_STYLE);
        assertThat(testGift.getWeight()).isEqualTo(UPDATED_WEIGHT);
        assertThat(testGift.getRom()).isEqualTo(UPDATED_ROM);
        assertThat(testGift.getBattery()).isEqualTo(DEFAULT_BATTERY);
        assertThat(testGift.getTouchScreen()).isEqualTo(UPDATED_TOUCH_SCREEN);
        assertThat(testGift.getHooded()).isEqualTo(UPDATED_HOODED);
        assertThat(testGift.getMadeIn()).isEqualTo(UPDATED_MADE_IN);
        assertThat(testGift.getShippingFrom()).isEqualTo(DEFAULT_SHIPPING_FROM);
        assertThat(testGift.getSizee()).isEqualTo(UPDATED_SIZEE);
    }

    @Test
    @Transactional
    void fullUpdateGiftWithPatch() throws Exception {
        // Initialize the database
        giftRepository.saveAndFlush(gift);

        int databaseSizeBeforeUpdate = giftRepository.findAll().size();

        // Update the gift using partial update
        Gift partialUpdatedGift = new Gift();
        partialUpdatedGift.setId(gift.getId());

        partialUpdatedGift
            .brandName(UPDATED_BRAND_NAME)
            .material(UPDATED_MATERIAL)
            .style(UPDATED_STYLE)
            .season(UPDATED_SEASON)
            .type(UPDATED_TYPE)
            .clothingLength(UPDATED_CLOTHING_LENGTH)
            .collar(UPDATED_COLLAR)
            .sleeveLength(UPDATED_SLEEVE_LENGTH)
            .design(UPDATED_DESIGN)
            .gender(UPDATED_GENDER)
            .occasion(UPDATED_OCCASION)
            .decoration(UPDATED_DECORATION)
            .closureType(UPDATED_CLOSURE_TYPE)
            .sleeveType(UPDATED_SLEEVE_TYPE)
            .color(UPDATED_COLOR)
            .quality(UPDATED_QUALITY)
            .features(UPDATED_FEATURES)
            .activeNoiseCancellation(UPDATED_ACTIVE_NOISE_CANCELLATION)
            .volumeControl(UPDATED_VOLUME_CONTROL)
            .wirelessType(UPDATED_WIRELESS_TYPE)
            .functions(UPDATED_FUNCTIONS)
            .packageList(UPDATED_PACKAGE_LIST)
            .bluetoothVersion(UPDATED_BLUETOOTH_VERSION)
            .frequency(UPDATED_FREQUENCY)
            .modelNumber(UPDATED_MODEL_NUMBER)
            .description(UPDATED_DESCRIPTION)
            .ram(UPDATED_RAM)
            .suitableFor(UPDATED_SUITABLE_FOR)
            .screenStyle(UPDATED_SCREEN_STYLE)
            .weight(UPDATED_WEIGHT)
            .rom(UPDATED_ROM)
            .battery(UPDATED_BATTERY)
            .touchScreen(UPDATED_TOUCH_SCREEN)
            .hooded(UPDATED_HOODED)
            .madeIn(UPDATED_MADE_IN)
            .shippingFrom(UPDATED_SHIPPING_FROM)
            .sizee(UPDATED_SIZEE);

        restGiftMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedGift.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedGift))
            )
            .andExpect(status().isOk());

        // Validate the Gift in the database
        List<Gift> giftList = giftRepository.findAll();
        assertThat(giftList).hasSize(databaseSizeBeforeUpdate);
        Gift testGift = giftList.get(giftList.size() - 1);
        assertThat(testGift.getBrandName()).isEqualTo(UPDATED_BRAND_NAME);
        assertThat(testGift.getMaterial()).isEqualTo(UPDATED_MATERIAL);
        assertThat(testGift.getStyle()).isEqualTo(UPDATED_STYLE);
        assertThat(testGift.getSeason()).isEqualTo(UPDATED_SEASON);
        assertThat(testGift.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testGift.getClothingLength()).isEqualTo(UPDATED_CLOTHING_LENGTH);
        assertThat(testGift.getCollar()).isEqualTo(UPDATED_COLLAR);
        assertThat(testGift.getSleeveLength()).isEqualTo(UPDATED_SLEEVE_LENGTH);
        assertThat(testGift.getDesign()).isEqualTo(UPDATED_DESIGN);
        assertThat(testGift.getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testGift.getOccasion()).isEqualTo(UPDATED_OCCASION);
        assertThat(testGift.getDecoration()).isEqualTo(UPDATED_DECORATION);
        assertThat(testGift.getClosureType()).isEqualTo(UPDATED_CLOSURE_TYPE);
        assertThat(testGift.getSleeveType()).isEqualTo(UPDATED_SLEEVE_TYPE);
        assertThat(testGift.getColor()).isEqualTo(UPDATED_COLOR);
        assertThat(testGift.getQuality()).isEqualTo(UPDATED_QUALITY);
        assertThat(testGift.getFeatures()).isEqualTo(UPDATED_FEATURES);
        assertThat(testGift.getActiveNoiseCancellation()).isEqualTo(UPDATED_ACTIVE_NOISE_CANCELLATION);
        assertThat(testGift.getVolumeControl()).isEqualTo(UPDATED_VOLUME_CONTROL);
        assertThat(testGift.getWirelessType()).isEqualTo(UPDATED_WIRELESS_TYPE);
        assertThat(testGift.getFunctions()).isEqualTo(UPDATED_FUNCTIONS);
        assertThat(testGift.getPackageList()).isEqualTo(UPDATED_PACKAGE_LIST);
        assertThat(testGift.getBluetoothVersion()).isEqualTo(UPDATED_BLUETOOTH_VERSION);
        assertThat(testGift.getFrequency()).isEqualTo(UPDATED_FREQUENCY);
        assertThat(testGift.getModelNumber()).isEqualTo(UPDATED_MODEL_NUMBER);
        assertThat(testGift.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testGift.getRam()).isEqualTo(UPDATED_RAM);
        assertThat(testGift.getSuitableFor()).isEqualTo(UPDATED_SUITABLE_FOR);
        assertThat(testGift.getScreenStyle()).isEqualTo(UPDATED_SCREEN_STYLE);
        assertThat(testGift.getWeight()).isEqualTo(UPDATED_WEIGHT);
        assertThat(testGift.getRom()).isEqualTo(UPDATED_ROM);
        assertThat(testGift.getBattery()).isEqualTo(UPDATED_BATTERY);
        assertThat(testGift.getTouchScreen()).isEqualTo(UPDATED_TOUCH_SCREEN);
        assertThat(testGift.getHooded()).isEqualTo(UPDATED_HOODED);
        assertThat(testGift.getMadeIn()).isEqualTo(UPDATED_MADE_IN);
        assertThat(testGift.getShippingFrom()).isEqualTo(UPDATED_SHIPPING_FROM);
        assertThat(testGift.getSizee()).isEqualTo(UPDATED_SIZEE);
    }

    @Test
    @Transactional
    void patchNonExistingGift() throws Exception {
        int databaseSizeBeforeUpdate = giftRepository.findAll().size();
        gift.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGiftMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, gift.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(gift))
            )
            .andExpect(status().isBadRequest());

        // Validate the Gift in the database
        List<Gift> giftList = giftRepository.findAll();
        assertThat(giftList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchGift() throws Exception {
        int databaseSizeBeforeUpdate = giftRepository.findAll().size();
        gift.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGiftMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(gift))
            )
            .andExpect(status().isBadRequest());

        // Validate the Gift in the database
        List<Gift> giftList = giftRepository.findAll();
        assertThat(giftList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamGift() throws Exception {
        int databaseSizeBeforeUpdate = giftRepository.findAll().size();
        gift.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGiftMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(gift)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Gift in the database
        List<Gift> giftList = giftRepository.findAll();
        assertThat(giftList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteGift() throws Exception {
        // Initialize the database
        giftRepository.saveAndFlush(gift);

        int databaseSizeBeforeDelete = giftRepository.findAll().size();

        // Delete the gift
        restGiftMockMvc
            .perform(delete(ENTITY_API_URL_ID, gift.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Gift> giftList = giftRepository.findAll();
        assertThat(giftList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
