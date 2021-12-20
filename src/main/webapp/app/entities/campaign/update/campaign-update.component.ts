import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { ICampaign, Campaign } from '../campaign.model';
import { CampaignService } from '../service/campaign.service';
import { IUser } from 'app/entities/user/user.model';
import { UserService } from 'app/entities/user/user.service';
import { IGift } from 'app/entities/gift/gift.model';
import { GiftService } from 'app/entities/gift/service/gift.service';

@Component({
  selector: 'jhi-campaign-update',
  templateUrl: './campaign-update.component.html',
})
export class CampaignUpdateComponent implements OnInit {
  isSaving = false;

  usersSharedCollection: IUser[] = [];
  giftsCollection: IGift[] = [];

  editForm = this.fb.group({
    id: [],
    isWeek: [],
    isMonth: [],
    participants: [],
    gift: [],
  });

  constructor(
    protected campaignService: CampaignService,
    protected userService: UserService,
    protected giftService: GiftService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ campaign }) => {
      this.updateForm(campaign);

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const campaign = this.createFromForm();
    if (campaign.id !== undefined) {
      this.subscribeToSaveResponse(this.campaignService.update(campaign));
    } else {
      this.subscribeToSaveResponse(this.campaignService.create(campaign));
    }
  }

  trackUserById(index: number, item: IUser): number {
    return item.id!;
  }

  trackGiftById(index: number, item: IGift): number {
    return item.id!;
  }

  getSelectedUser(option: IUser, selectedVals?: IUser[]): IUser {
    if (selectedVals) {
      for (const selectedVal of selectedVals) {
        if (option.id === selectedVal.id) {
          return selectedVal;
        }
      }
    }
    return option;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICampaign>>): void {
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

  protected updateForm(campaign: ICampaign): void {
    this.editForm.patchValue({
      id: campaign.id,
      isWeek: campaign.isWeek,
      isMonth: campaign.isMonth,
      participants: campaign.participants,
      gift: campaign.gift,
    });

    this.usersSharedCollection = this.userService.addUserToCollectionIfMissing(
      this.usersSharedCollection,
      ...(campaign.participants ?? [])
    );
    this.giftsCollection = this.giftService.addGiftToCollectionIfMissing(this.giftsCollection, campaign.gift);
  }

  protected loadRelationshipsOptions(): void {
    this.userService
      .query()
      .pipe(map((res: HttpResponse<IUser[]>) => res.body ?? []))
      .pipe(
        map((users: IUser[]) => this.userService.addUserToCollectionIfMissing(users, ...(this.editForm.get('participants')!.value ?? [])))
      )
      .subscribe((users: IUser[]) => (this.usersSharedCollection = users));

    this.giftService
      .query({ filter: 'campaign-is-null' })
      .pipe(map((res: HttpResponse<IGift[]>) => res.body ?? []))
      .pipe(map((gifts: IGift[]) => this.giftService.addGiftToCollectionIfMissing(gifts, this.editForm.get('gift')!.value)))
      .subscribe((gifts: IGift[]) => (this.giftsCollection = gifts));
  }

  protected createFromForm(): ICampaign {
    return {
      ...new Campaign(),
      id: this.editForm.get(['id'])!.value,
      isWeek: this.editForm.get(['isWeek'])!.value,
      isMonth: this.editForm.get(['isMonth'])!.value,
      participants: this.editForm.get(['participants'])!.value,
      gift: this.editForm.get(['gift'])!.value,
    };
  }
}
