import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ActiveNoiseCancellation } from 'app/entities/enumerations/active-noise-cancellation.model';
import { VolumeControl } from 'app/entities/enumerations/volume-control.model';
import { TouchScreen } from 'app/entities/enumerations/touch-screen.model';
import { Hooded } from 'app/entities/enumerations/hooded.model';
import { IGift, Gift } from '../gift.model';

import { GiftService } from './gift.service';

describe('Gift Service', () => {
  let service: GiftService;
  let httpMock: HttpTestingController;
  let elemDefault: IGift;
  let expectedResult: IGift | IGift[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(GiftService);
    httpMock = TestBed.inject(HttpTestingController);

    elemDefault = {
      id: 0,
      brandName: 'AAAAAAA',
      material: 'AAAAAAA',
      style: 'AAAAAAA',
      season: 'AAAAAAA',
      type: 'AAAAAAA',
      clothingLength: 'AAAAAAA',
      collar: 'AAAAAAA',
      sleeveLength: 'AAAAAAA',
      design: 'AAAAAAA',
      gender: 'AAAAAAA',
      occasion: 'AAAAAAA',
      decoration: 'AAAAAAA',
      closureType: 'AAAAAAA',
      sleeveType: 'AAAAAAA',
      color: 'AAAAAAA',
      quality: 'AAAAAAA',
      features: 'AAAAAAA',
      activeNoiseCancellation: ActiveNoiseCancellation.YES,
      volumeControl: VolumeControl.YES,
      wirelessType: 'AAAAAAA',
      functions: 'AAAAAAA',
      packageList: 'AAAAAAA',
      bluetoothVersion: 'AAAAAAA',
      frequency: 'AAAAAAA',
      modelNumber: 'AAAAAAA',
      description: 'AAAAAAA',
      ram: 'AAAAAAA',
      suitableFor: 'AAAAAAA',
      screenStyle: 'AAAAAAA',
      weight: 'AAAAAAA',
      rom: 'AAAAAAA',
      battery: 'AAAAAAA',
      touchScreen: TouchScreen.YES,
      hooded: Hooded.YES,
      madeIn: 'AAAAAAA',
      shippingFrom: 'AAAAAAA',
      sizee: 'AAAAAAA',
    };
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = Object.assign({}, elemDefault);

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(elemDefault);
    });

    it('should create a Gift', () => {
      const returnedFromService = Object.assign(
        {
          id: 0,
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.create(new Gift()).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Gift', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          brandName: 'BBBBBB',
          material: 'BBBBBB',
          style: 'BBBBBB',
          season: 'BBBBBB',
          type: 'BBBBBB',
          clothingLength: 'BBBBBB',
          collar: 'BBBBBB',
          sleeveLength: 'BBBBBB',
          design: 'BBBBBB',
          gender: 'BBBBBB',
          occasion: 'BBBBBB',
          decoration: 'BBBBBB',
          closureType: 'BBBBBB',
          sleeveType: 'BBBBBB',
          color: 'BBBBBB',
          quality: 'BBBBBB',
          features: 'BBBBBB',
          activeNoiseCancellation: 'BBBBBB',
          volumeControl: 'BBBBBB',
          wirelessType: 'BBBBBB',
          functions: 'BBBBBB',
          packageList: 'BBBBBB',
          bluetoothVersion: 'BBBBBB',
          frequency: 'BBBBBB',
          modelNumber: 'BBBBBB',
          description: 'BBBBBB',
          ram: 'BBBBBB',
          suitableFor: 'BBBBBB',
          screenStyle: 'BBBBBB',
          weight: 'BBBBBB',
          rom: 'BBBBBB',
          battery: 'BBBBBB',
          touchScreen: 'BBBBBB',
          hooded: 'BBBBBB',
          madeIn: 'BBBBBB',
          shippingFrom: 'BBBBBB',
          sizee: 'BBBBBB',
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.update(expected).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Gift', () => {
      const patchObject = Object.assign(
        {
          material: 'BBBBBB',
          season: 'BBBBBB',
          type: 'BBBBBB',
          sleeveLength: 'BBBBBB',
          occasion: 'BBBBBB',
          decoration: 'BBBBBB',
          quality: 'BBBBBB',
          wirelessType: 'BBBBBB',
          packageList: 'BBBBBB',
          bluetoothVersion: 'BBBBBB',
          frequency: 'BBBBBB',
          modelNumber: 'BBBBBB',
          description: 'BBBBBB',
          ram: 'BBBBBB',
          rom: 'BBBBBB',
          battery: 'BBBBBB',
          touchScreen: 'BBBBBB',
          hooded: 'BBBBBB',
          madeIn: 'BBBBBB',
          shippingFrom: 'BBBBBB',
        },
        new Gift()
      );

      const returnedFromService = Object.assign(patchObject, elemDefault);

      const expected = Object.assign({}, returnedFromService);

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Gift', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          brandName: 'BBBBBB',
          material: 'BBBBBB',
          style: 'BBBBBB',
          season: 'BBBBBB',
          type: 'BBBBBB',
          clothingLength: 'BBBBBB',
          collar: 'BBBBBB',
          sleeveLength: 'BBBBBB',
          design: 'BBBBBB',
          gender: 'BBBBBB',
          occasion: 'BBBBBB',
          decoration: 'BBBBBB',
          closureType: 'BBBBBB',
          sleeveType: 'BBBBBB',
          color: 'BBBBBB',
          quality: 'BBBBBB',
          features: 'BBBBBB',
          activeNoiseCancellation: 'BBBBBB',
          volumeControl: 'BBBBBB',
          wirelessType: 'BBBBBB',
          functions: 'BBBBBB',
          packageList: 'BBBBBB',
          bluetoothVersion: 'BBBBBB',
          frequency: 'BBBBBB',
          modelNumber: 'BBBBBB',
          description: 'BBBBBB',
          ram: 'BBBBBB',
          suitableFor: 'BBBBBB',
          screenStyle: 'BBBBBB',
          weight: 'BBBBBB',
          rom: 'BBBBBB',
          battery: 'BBBBBB',
          touchScreen: 'BBBBBB',
          hooded: 'BBBBBB',
          madeIn: 'BBBBBB',
          shippingFrom: 'BBBBBB',
          sizee: 'BBBBBB',
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toContainEqual(expected);
    });

    it('should delete a Gift', () => {
      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult);
    });

    describe('addGiftToCollectionIfMissing', () => {
      it('should add a Gift to an empty array', () => {
        const gift: IGift = { id: 123 };
        expectedResult = service.addGiftToCollectionIfMissing([], gift);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(gift);
      });

      it('should not add a Gift to an array that contains it', () => {
        const gift: IGift = { id: 123 };
        const giftCollection: IGift[] = [
          {
            ...gift,
          },
          { id: 456 },
        ];
        expectedResult = service.addGiftToCollectionIfMissing(giftCollection, gift);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Gift to an array that doesn't contain it", () => {
        const gift: IGift = { id: 123 };
        const giftCollection: IGift[] = [{ id: 456 }];
        expectedResult = service.addGiftToCollectionIfMissing(giftCollection, gift);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(gift);
      });

      it('should add only unique Gift to an array', () => {
        const giftArray: IGift[] = [{ id: 123 }, { id: 456 }, { id: 60409 }];
        const giftCollection: IGift[] = [{ id: 123 }];
        expectedResult = service.addGiftToCollectionIfMissing(giftCollection, ...giftArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const gift: IGift = { id: 123 };
        const gift2: IGift = { id: 456 };
        expectedResult = service.addGiftToCollectionIfMissing([], gift, gift2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(gift);
        expect(expectedResult).toContain(gift2);
      });

      it('should accept null and undefined values', () => {
        const gift: IGift = { id: 123 };
        expectedResult = service.addGiftToCollectionIfMissing([], null, gift, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(gift);
      });

      it('should return initial array if no Gift is added', () => {
        const giftCollection: IGift[] = [{ id: 123 }];
        expectedResult = service.addGiftToCollectionIfMissing(giftCollection, undefined, null);
        expect(expectedResult).toEqual(giftCollection);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
