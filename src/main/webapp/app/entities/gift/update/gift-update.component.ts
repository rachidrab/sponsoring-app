import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { IGift, Gift } from '../gift.model';
import { GiftService } from '../service/gift.service';
import { ActiveNoiseCancellation } from 'app/entities/enumerations/active-noise-cancellation.model';
import { VolumeControl } from 'app/entities/enumerations/volume-control.model';
import { TouchScreen } from 'app/entities/enumerations/touch-screen.model';
import { Hooded } from 'app/entities/enumerations/hooded.model';

@Component({
  selector: 'jhi-gift-update',
  templateUrl: './gift-update.component.html',
})
export class GiftUpdateComponent implements OnInit {
  isSaving = false;
  activeNoiseCancellationValues = Object.keys(ActiveNoiseCancellation);
  volumeControlValues = Object.keys(VolumeControl);
  touchScreenValues = Object.keys(TouchScreen);
  hoodedValues = Object.keys(Hooded);

  editForm = this.fb.group({
    id: [],
    brandName: [],
    material: [],
    style: [],
    season: [],
    type: [],
    clothingLength: [],
    collar: [],
    sleeveLength: [],
    design: [],
    gender: [],
    occasion: [],
    decoration: [],
    closureType: [],
    sleeveType: [],
    color: [],
    quality: [],
    features: [],
    activeNoiseCancellation: [],
    volumeControl: [],
    wirelessType: [],
    functions: [],
    packageList: [],
    bluetoothVersion: [],
    frequency: [],
    modelNumber: [],
    description: [null, [Validators.required]],
    ram: [],
    suitableFor: [],
    screenStyle: [],
    weight: [],
    rom: [],
    battery: [],
    touchScreen: [],
    hooded: [],
    madeIn: [],
    shippingFrom: [],
    sizee: [],
  });

  constructor(protected giftService: GiftService, protected activatedRoute: ActivatedRoute, protected fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ gift }) => {
      this.updateForm(gift);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const gift = this.createFromForm();
    if (gift.id !== undefined) {
      this.subscribeToSaveResponse(this.giftService.update(gift));
    } else {
      this.subscribeToSaveResponse(this.giftService.create(gift));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IGift>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(gift: IGift): void {
    this.editForm.patchValue({
      id: gift.id,
      brandName: gift.brandName,
      material: gift.material,
      style: gift.style,
      season: gift.season,
      type: gift.type,
      clothingLength: gift.clothingLength,
      collar: gift.collar,
      sleeveLength: gift.sleeveLength,
      design: gift.design,
      gender: gift.gender,
      occasion: gift.occasion,
      decoration: gift.decoration,
      closureType: gift.closureType,
      sleeveType: gift.sleeveType,
      color: gift.color,
      quality: gift.quality,
      features: gift.features,
      activeNoiseCancellation: gift.activeNoiseCancellation,
      volumeControl: gift.volumeControl,
      wirelessType: gift.wirelessType,
      functions: gift.functions,
      packageList: gift.packageList,
      bluetoothVersion: gift.bluetoothVersion,
      frequency: gift.frequency,
      modelNumber: gift.modelNumber,
      description: gift.description,
      ram: gift.ram,
      suitableFor: gift.suitableFor,
      screenStyle: gift.screenStyle,
      weight: gift.weight,
      rom: gift.rom,
      battery: gift.battery,
      touchScreen: gift.touchScreen,
      hooded: gift.hooded,
      madeIn: gift.madeIn,
      shippingFrom: gift.shippingFrom,
      sizee: gift.sizee,
    });
  }

  protected createFromForm(): IGift {
    return {
      ...new Gift(),
      id: this.editForm.get(['id'])!.value,
      brandName: this.editForm.get(['brandName'])!.value,
      material: this.editForm.get(['material'])!.value,
      style: this.editForm.get(['style'])!.value,
      season: this.editForm.get(['season'])!.value,
      type: this.editForm.get(['type'])!.value,
      clothingLength: this.editForm.get(['clothingLength'])!.value,
      collar: this.editForm.get(['collar'])!.value,
      sleeveLength: this.editForm.get(['sleeveLength'])!.value,
      design: this.editForm.get(['design'])!.value,
      gender: this.editForm.get(['gender'])!.value,
      occasion: this.editForm.get(['occasion'])!.value,
      decoration: this.editForm.get(['decoration'])!.value,
      closureType: this.editForm.get(['closureType'])!.value,
      sleeveType: this.editForm.get(['sleeveType'])!.value,
      color: this.editForm.get(['color'])!.value,
      quality: this.editForm.get(['quality'])!.value,
      features: this.editForm.get(['features'])!.value,
      activeNoiseCancellation: this.editForm.get(['activeNoiseCancellation'])!.value,
      volumeControl: this.editForm.get(['volumeControl'])!.value,
      wirelessType: this.editForm.get(['wirelessType'])!.value,
      functions: this.editForm.get(['functions'])!.value,
      packageList: this.editForm.get(['packageList'])!.value,
      bluetoothVersion: this.editForm.get(['bluetoothVersion'])!.value,
      frequency: this.editForm.get(['frequency'])!.value,
      modelNumber: this.editForm.get(['modelNumber'])!.value,
      description: this.editForm.get(['description'])!.value,
      ram: this.editForm.get(['ram'])!.value,
      suitableFor: this.editForm.get(['suitableFor'])!.value,
      screenStyle: this.editForm.get(['screenStyle'])!.value,
      weight: this.editForm.get(['weight'])!.value,
      rom: this.editForm.get(['rom'])!.value,
      battery: this.editForm.get(['battery'])!.value,
      touchScreen: this.editForm.get(['touchScreen'])!.value,
      hooded: this.editForm.get(['hooded'])!.value,
      madeIn: this.editForm.get(['madeIn'])!.value,
      shippingFrom: this.editForm.get(['shippingFrom'])!.value,
      sizee: this.editForm.get(['sizee'])!.value,
    };
  }
}
