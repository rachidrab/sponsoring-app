package com.rachid.parrainage.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rachid.parrainage.domain.enumeration.ActiveNoiseCancellation;
import com.rachid.parrainage.domain.enumeration.Hooded;
import com.rachid.parrainage.domain.enumeration.TouchScreen;
import com.rachid.parrainage.domain.enumeration.VolumeControl;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A Gift.
 */
@Entity
@Table(name = "gift")
public class Gift implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "brand_name")
    private String brandName;

    @Column(name = "material")
    private String material;

    @Column(name = "style")
    private String style;

    @Column(name = "season")
    private String season;

    @Column(name = "type")
    private String type;

    @Column(name = "clothing_length")
    private String clothingLength;

    @Column(name = "collar")
    private String collar;

    @Column(name = "sleeve_length")
    private String sleeveLength;

    @Column(name = "design")
    private String design;

    @Column(name = "gender")
    private String gender;

    @Column(name = "occasion")
    private String occasion;

    @Column(name = "decoration")
    private String decoration;

    @Column(name = "closure_type")
    private String closureType;

    @Column(name = "sleeve_type")
    private String sleeveType;

    @Column(name = "color")
    private String color;

    @Column(name = "quality")
    private String quality;

    @Column(name = "features")
    private String features;

    @Enumerated(EnumType.STRING)
    @Column(name = "active_noise_cancellation")
    private ActiveNoiseCancellation activeNoiseCancellation;

    @Enumerated(EnumType.STRING)
    @Column(name = "volume_control")
    private VolumeControl volumeControl;

    @Column(name = "wireless_type")
    private String wirelessType;

    @Column(name = "functions")
    private String functions;

    @Column(name = "package_list")
    private String packageList;

    @Column(name = "bluetooth_version")
    private String bluetoothVersion;

    @Column(name = "frequency")
    private String frequency;

    @Column(name = "model_number")
    private String modelNumber;

    @NotNull
    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "ram")
    private String ram;

    @Column(name = "suitable_for")
    private String suitableFor;

    @Column(name = "screen_style")
    private String screenStyle;

    @Column(name = "weight")
    private String weight;

    @Column(name = "rom")
    private String rom;

    @Column(name = "battery")
    private String battery;

    @Enumerated(EnumType.STRING)
    @Column(name = "touch_screen")
    private TouchScreen touchScreen;

    @Enumerated(EnumType.STRING)
    @Column(name = "hooded")
    private Hooded hooded;

    @Column(name = "made_in")
    private String madeIn;

    @Column(name = "shipping_from")
    private String shippingFrom;

    @Column(name = "sizee")
    private String sizee;

    @OneToMany(mappedBy = "gift")
    @JsonIgnoreProperties(value = { "gift" }, allowSetters = true)
    private Set<Picture> pictures = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Gift id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrandName() {
        return this.brandName;
    }

    public Gift brandName(String brandName) {
        this.setBrandName(brandName);
        return this;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getMaterial() {
        return this.material;
    }

    public Gift material(String material) {
        this.setMaterial(material);
        return this;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getStyle() {
        return this.style;
    }

    public Gift style(String style) {
        this.setStyle(style);
        return this;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getSeason() {
        return this.season;
    }

    public Gift season(String season) {
        this.setSeason(season);
        return this;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getType() {
        return this.type;
    }

    public Gift type(String type) {
        this.setType(type);
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getClothingLength() {
        return this.clothingLength;
    }

    public Gift clothingLength(String clothingLength) {
        this.setClothingLength(clothingLength);
        return this;
    }

    public void setClothingLength(String clothingLength) {
        this.clothingLength = clothingLength;
    }

    public String getCollar() {
        return this.collar;
    }

    public Gift collar(String collar) {
        this.setCollar(collar);
        return this;
    }

    public void setCollar(String collar) {
        this.collar = collar;
    }

    public String getSleeveLength() {
        return this.sleeveLength;
    }

    public Gift sleeveLength(String sleeveLength) {
        this.setSleeveLength(sleeveLength);
        return this;
    }

    public void setSleeveLength(String sleeveLength) {
        this.sleeveLength = sleeveLength;
    }

    public String getDesign() {
        return this.design;
    }

    public Gift design(String design) {
        this.setDesign(design);
        return this;
    }

    public void setDesign(String design) {
        this.design = design;
    }

    public String getGender() {
        return this.gender;
    }

    public Gift gender(String gender) {
        this.setGender(gender);
        return this;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getOccasion() {
        return this.occasion;
    }

    public Gift occasion(String occasion) {
        this.setOccasion(occasion);
        return this;
    }

    public void setOccasion(String occasion) {
        this.occasion = occasion;
    }

    public String getDecoration() {
        return this.decoration;
    }

    public Gift decoration(String decoration) {
        this.setDecoration(decoration);
        return this;
    }

    public void setDecoration(String decoration) {
        this.decoration = decoration;
    }

    public String getClosureType() {
        return this.closureType;
    }

    public Gift closureType(String closureType) {
        this.setClosureType(closureType);
        return this;
    }

    public void setClosureType(String closureType) {
        this.closureType = closureType;
    }

    public String getSleeveType() {
        return this.sleeveType;
    }

    public Gift sleeveType(String sleeveType) {
        this.setSleeveType(sleeveType);
        return this;
    }

    public void setSleeveType(String sleeveType) {
        this.sleeveType = sleeveType;
    }

    public String getColor() {
        return this.color;
    }

    public Gift color(String color) {
        this.setColor(color);
        return this;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getQuality() {
        return this.quality;
    }

    public Gift quality(String quality) {
        this.setQuality(quality);
        return this;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getFeatures() {
        return this.features;
    }

    public Gift features(String features) {
        this.setFeatures(features);
        return this;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public ActiveNoiseCancellation getActiveNoiseCancellation() {
        return this.activeNoiseCancellation;
    }

    public Gift activeNoiseCancellation(ActiveNoiseCancellation activeNoiseCancellation) {
        this.setActiveNoiseCancellation(activeNoiseCancellation);
        return this;
    }

    public void setActiveNoiseCancellation(ActiveNoiseCancellation activeNoiseCancellation) {
        this.activeNoiseCancellation = activeNoiseCancellation;
    }

    public VolumeControl getVolumeControl() {
        return this.volumeControl;
    }

    public Gift volumeControl(VolumeControl volumeControl) {
        this.setVolumeControl(volumeControl);
        return this;
    }

    public void setVolumeControl(VolumeControl volumeControl) {
        this.volumeControl = volumeControl;
    }

    public String getWirelessType() {
        return this.wirelessType;
    }

    public Gift wirelessType(String wirelessType) {
        this.setWirelessType(wirelessType);
        return this;
    }

    public void setWirelessType(String wirelessType) {
        this.wirelessType = wirelessType;
    }

    public String getFunctions() {
        return this.functions;
    }

    public Gift functions(String functions) {
        this.setFunctions(functions);
        return this;
    }

    public void setFunctions(String functions) {
        this.functions = functions;
    }

    public String getPackageList() {
        return this.packageList;
    }

    public Gift packageList(String packageList) {
        this.setPackageList(packageList);
        return this;
    }

    public void setPackageList(String packageList) {
        this.packageList = packageList;
    }

    public String getBluetoothVersion() {
        return this.bluetoothVersion;
    }

    public Gift bluetoothVersion(String bluetoothVersion) {
        this.setBluetoothVersion(bluetoothVersion);
        return this;
    }

    public void setBluetoothVersion(String bluetoothVersion) {
        this.bluetoothVersion = bluetoothVersion;
    }

    public String getFrequency() {
        return this.frequency;
    }

    public Gift frequency(String frequency) {
        this.setFrequency(frequency);
        return this;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getModelNumber() {
        return this.modelNumber;
    }

    public Gift modelNumber(String modelNumber) {
        this.setModelNumber(modelNumber);
        return this;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    public String getDescription() {
        return this.description;
    }

    public Gift description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRam() {
        return this.ram;
    }

    public Gift ram(String ram) {
        this.setRam(ram);
        return this;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getSuitableFor() {
        return this.suitableFor;
    }

    public Gift suitableFor(String suitableFor) {
        this.setSuitableFor(suitableFor);
        return this;
    }

    public void setSuitableFor(String suitableFor) {
        this.suitableFor = suitableFor;
    }

    public String getScreenStyle() {
        return this.screenStyle;
    }

    public Gift screenStyle(String screenStyle) {
        this.setScreenStyle(screenStyle);
        return this;
    }

    public void setScreenStyle(String screenStyle) {
        this.screenStyle = screenStyle;
    }

    public String getWeight() {
        return this.weight;
    }

    public Gift weight(String weight) {
        this.setWeight(weight);
        return this;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getRom() {
        return this.rom;
    }

    public Gift rom(String rom) {
        this.setRom(rom);
        return this;
    }

    public void setRom(String rom) {
        this.rom = rom;
    }

    public String getBattery() {
        return this.battery;
    }

    public Gift battery(String battery) {
        this.setBattery(battery);
        return this;
    }

    public void setBattery(String battery) {
        this.battery = battery;
    }

    public TouchScreen getTouchScreen() {
        return this.touchScreen;
    }

    public Gift touchScreen(TouchScreen touchScreen) {
        this.setTouchScreen(touchScreen);
        return this;
    }

    public void setTouchScreen(TouchScreen touchScreen) {
        this.touchScreen = touchScreen;
    }

    public Hooded getHooded() {
        return this.hooded;
    }

    public Gift hooded(Hooded hooded) {
        this.setHooded(hooded);
        return this;
    }

    public void setHooded(Hooded hooded) {
        this.hooded = hooded;
    }

    public String getMadeIn() {
        return this.madeIn;
    }

    public Gift madeIn(String madeIn) {
        this.setMadeIn(madeIn);
        return this;
    }

    public void setMadeIn(String madeIn) {
        this.madeIn = madeIn;
    }

    public String getShippingFrom() {
        return this.shippingFrom;
    }

    public Gift shippingFrom(String shippingFrom) {
        this.setShippingFrom(shippingFrom);
        return this;
    }

    public void setShippingFrom(String shippingFrom) {
        this.shippingFrom = shippingFrom;
    }

    public String getSizee() {
        return this.sizee;
    }

    public Gift sizee(String sizee) {
        this.setSizee(sizee);
        return this;
    }

    public void setSizee(String sizee) {
        this.sizee = sizee;
    }

    public Set<Picture> getPictures() {
        return this.pictures;
    }

    public void setPictures(Set<Picture> pictures) {
        if (this.pictures != null) {
            this.pictures.forEach(i -> i.setGift(null));
        }
        if (pictures != null) {
            pictures.forEach(i -> i.setGift(this));
        }
        this.pictures = pictures;
    }

    public Gift pictures(Set<Picture> pictures) {
        this.setPictures(pictures);
        return this;
    }

    public Gift addPicture(Picture picture) {
        this.pictures.add(picture);
        picture.setGift(this);
        return this;
    }

    public Gift removePicture(Picture picture) {
        this.pictures.remove(picture);
        picture.setGift(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Gift)) {
            return false;
        }
        return id != null && id.equals(((Gift) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Gift{" +
            "id=" + getId() +
            ", brandName='" + getBrandName() + "'" +
            ", material='" + getMaterial() + "'" +
            ", style='" + getStyle() + "'" +
            ", season='" + getSeason() + "'" +
            ", type='" + getType() + "'" +
            ", clothingLength='" + getClothingLength() + "'" +
            ", collar='" + getCollar() + "'" +
            ", sleeveLength='" + getSleeveLength() + "'" +
            ", design='" + getDesign() + "'" +
            ", gender='" + getGender() + "'" +
            ", occasion='" + getOccasion() + "'" +
            ", decoration='" + getDecoration() + "'" +
            ", closureType='" + getClosureType() + "'" +
            ", sleeveType='" + getSleeveType() + "'" +
            ", color='" + getColor() + "'" +
            ", quality='" + getQuality() + "'" +
            ", features='" + getFeatures() + "'" +
            ", activeNoiseCancellation='" + getActiveNoiseCancellation() + "'" +
            ", volumeControl='" + getVolumeControl() + "'" +
            ", wirelessType='" + getWirelessType() + "'" +
            ", functions='" + getFunctions() + "'" +
            ", packageList='" + getPackageList() + "'" +
            ", bluetoothVersion='" + getBluetoothVersion() + "'" +
            ", frequency='" + getFrequency() + "'" +
            ", modelNumber='" + getModelNumber() + "'" +
            ", description='" + getDescription() + "'" +
            ", ram='" + getRam() + "'" +
            ", suitableFor='" + getSuitableFor() + "'" +
            ", screenStyle='" + getScreenStyle() + "'" +
            ", weight='" + getWeight() + "'" +
            ", rom='" + getRom() + "'" +
            ", battery='" + getBattery() + "'" +
            ", touchScreen='" + getTouchScreen() + "'" +
            ", hooded='" + getHooded() + "'" +
            ", madeIn='" + getMadeIn() + "'" +
            ", shippingFrom='" + getShippingFrom() + "'" +
            ", sizee='" + getSizee() + "'" +
            "}";
    }
}
