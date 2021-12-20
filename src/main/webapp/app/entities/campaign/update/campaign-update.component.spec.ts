jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { CampaignService } from '../service/campaign.service';
import { ICampaign, Campaign } from '../campaign.model';

import { IUser } from 'app/entities/user/user.model';
import { UserService } from 'app/entities/user/user.service';
import { IGift } from 'app/entities/gift/gift.model';
import { GiftService } from 'app/entities/gift/service/gift.service';

import { CampaignUpdateComponent } from './campaign-update.component';

describe('Component Tests', () => {
  describe('Campaign Management Update Component', () => {
    let comp: CampaignUpdateComponent;
    let fixture: ComponentFixture<CampaignUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let campaignService: CampaignService;
    let userService: UserService;
    let giftService: GiftService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [CampaignUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(CampaignUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CampaignUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      campaignService = TestBed.inject(CampaignService);
      userService = TestBed.inject(UserService);
      giftService = TestBed.inject(GiftService);

      comp = fixture.componentInstance;
    });

    describe('ngOnInit', () => {
      it('Should call User query and add missing value', () => {
        const campaign: ICampaign = { id: 456 };
        const participants: IUser[] = [{ id: 35607 }];
        campaign.participants = participants;

        const userCollection: IUser[] = [{ id: 11575 }];
        jest.spyOn(userService, 'query').mockReturnValue(of(new HttpResponse({ body: userCollection })));
        const additionalUsers = [...participants];
        const expectedCollection: IUser[] = [...additionalUsers, ...userCollection];
        jest.spyOn(userService, 'addUserToCollectionIfMissing').mockReturnValue(expectedCollection);

        activatedRoute.data = of({ campaign });
        comp.ngOnInit();

        expect(userService.query).toHaveBeenCalled();
        expect(userService.addUserToCollectionIfMissing).toHaveBeenCalledWith(userCollection, ...additionalUsers);
        expect(comp.usersSharedCollection).toEqual(expectedCollection);
      });

      it('Should call gift query and add missing value', () => {
        const campaign: ICampaign = { id: 456 };
        const gift: IGift = { id: 60529 };
        campaign.gift = gift;

        const giftCollection: IGift[] = [{ id: 13552 }];
        jest.spyOn(giftService, 'query').mockReturnValue(of(new HttpResponse({ body: giftCollection })));
        const expectedCollection: IGift[] = [gift, ...giftCollection];
        jest.spyOn(giftService, 'addGiftToCollectionIfMissing').mockReturnValue(expectedCollection);

        activatedRoute.data = of({ campaign });
        comp.ngOnInit();

        expect(giftService.query).toHaveBeenCalled();
        expect(giftService.addGiftToCollectionIfMissing).toHaveBeenCalledWith(giftCollection, gift);
        expect(comp.giftsCollection).toEqual(expectedCollection);
      });

      it('Should update editForm', () => {
        const campaign: ICampaign = { id: 456 };
        const participants: IUser = { id: 59502 };
        campaign.participants = [participants];
        const gift: IGift = { id: 35649 };
        campaign.gift = gift;

        activatedRoute.data = of({ campaign });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(campaign));
        expect(comp.usersSharedCollection).toContain(participants);
        expect(comp.giftsCollection).toContain(gift);
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', () => {
        // GIVEN
        const saveSubject = new Subject<HttpResponse<Campaign>>();
        const campaign = { id: 123 };
        jest.spyOn(campaignService, 'update').mockReturnValue(saveSubject);
        jest.spyOn(comp, 'previousState');
        activatedRoute.data = of({ campaign });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: campaign }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(campaignService.update).toHaveBeenCalledWith(campaign);
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', () => {
        // GIVEN
        const saveSubject = new Subject<HttpResponse<Campaign>>();
        const campaign = new Campaign();
        jest.spyOn(campaignService, 'create').mockReturnValue(saveSubject);
        jest.spyOn(comp, 'previousState');
        activatedRoute.data = of({ campaign });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: campaign }));
        saveSubject.complete();

        // THEN
        expect(campaignService.create).toHaveBeenCalledWith(campaign);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it('Should set isSaving to false on error', () => {
        // GIVEN
        const saveSubject = new Subject<HttpResponse<Campaign>>();
        const campaign = { id: 123 };
        jest.spyOn(campaignService, 'update').mockReturnValue(saveSubject);
        jest.spyOn(comp, 'previousState');
        activatedRoute.data = of({ campaign });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error('This is an error!');

        // THEN
        expect(campaignService.update).toHaveBeenCalledWith(campaign);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).not.toHaveBeenCalled();
      });
    });

    describe('Tracking relationships identifiers', () => {
      describe('trackUserById', () => {
        it('Should return tracked User primary key', () => {
          const entity = { id: 123 };
          const trackResult = comp.trackUserById(0, entity);
          expect(trackResult).toEqual(entity.id);
        });
      });

      describe('trackGiftById', () => {
        it('Should return tracked Gift primary key', () => {
          const entity = { id: 123 };
          const trackResult = comp.trackGiftById(0, entity);
          expect(trackResult).toEqual(entity.id);
        });
      });
    });

    describe('Getting selected relationships', () => {
      describe('getSelectedUser', () => {
        it('Should return option if no User is selected', () => {
          const option = { id: 123 };
          const result = comp.getSelectedUser(option);
          expect(result === option).toEqual(true);
        });

        it('Should return selected User for according option', () => {
          const option = { id: 123 };
          const selected = { id: 123 };
          const selected2 = { id: 456 };
          const result = comp.getSelectedUser(option, [selected2, selected]);
          expect(result === selected).toEqual(true);
          expect(result === selected2).toEqual(false);
          expect(result === option).toEqual(false);
        });

        it('Should return option if this User is not selected', () => {
          const option = { id: 123 };
          const selected = { id: 456 };
          const result = comp.getSelectedUser(option, [selected]);
          expect(result === option).toEqual(true);
          expect(result === selected).toEqual(false);
        });
      });
    });
  });
});
