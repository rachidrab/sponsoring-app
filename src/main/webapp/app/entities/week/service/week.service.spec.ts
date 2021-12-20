import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as dayjs from 'dayjs';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IWeek, Week } from '../week.model';

import { WeekService } from './week.service';

describe('Week Service', () => {
  let service: WeekService;
  let httpMock: HttpTestingController;
  let elemDefault: IWeek;
  let expectedResult: IWeek | IWeek[] | boolean | null;
  let currentDate: dayjs.Dayjs;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(WeekService);
    httpMock = TestBed.inject(HttpTestingController);
    currentDate = dayjs();

    elemDefault = {
      id: 0,
      start: currentDate,
      end: currentDate,
    };
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = Object.assign(
        {
          start: currentDate.format(DATE_FORMAT),
          end: currentDate.format(DATE_FORMAT),
        },
        elemDefault
      );

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(elemDefault);
    });

    it('should create a Week', () => {
      const returnedFromService = Object.assign(
        {
          id: 0,
          start: currentDate.format(DATE_FORMAT),
          end: currentDate.format(DATE_FORMAT),
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          start: currentDate,
          end: currentDate,
        },
        returnedFromService
      );

      service.create(new Week()).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Week', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          start: currentDate.format(DATE_FORMAT),
          end: currentDate.format(DATE_FORMAT),
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          start: currentDate,
          end: currentDate,
        },
        returnedFromService
      );

      service.update(expected).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Week', () => {
      const patchObject = Object.assign(
        {
          end: currentDate.format(DATE_FORMAT),
        },
        new Week()
      );

      const returnedFromService = Object.assign(patchObject, elemDefault);

      const expected = Object.assign(
        {
          start: currentDate,
          end: currentDate,
        },
        returnedFromService
      );

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Week', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          start: currentDate.format(DATE_FORMAT),
          end: currentDate.format(DATE_FORMAT),
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          start: currentDate,
          end: currentDate,
        },
        returnedFromService
      );

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toContainEqual(expected);
    });

    it('should delete a Week', () => {
      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult);
    });

    describe('addWeekToCollectionIfMissing', () => {
      it('should add a Week to an empty array', () => {
        const week: IWeek = { id: 123 };
        expectedResult = service.addWeekToCollectionIfMissing([], week);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(week);
      });

      it('should not add a Week to an array that contains it', () => {
        const week: IWeek = { id: 123 };
        const weekCollection: IWeek[] = [
          {
            ...week,
          },
          { id: 456 },
        ];
        expectedResult = service.addWeekToCollectionIfMissing(weekCollection, week);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Week to an array that doesn't contain it", () => {
        const week: IWeek = { id: 123 };
        const weekCollection: IWeek[] = [{ id: 456 }];
        expectedResult = service.addWeekToCollectionIfMissing(weekCollection, week);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(week);
      });

      it('should add only unique Week to an array', () => {
        const weekArray: IWeek[] = [{ id: 123 }, { id: 456 }, { id: 55562 }];
        const weekCollection: IWeek[] = [{ id: 123 }];
        expectedResult = service.addWeekToCollectionIfMissing(weekCollection, ...weekArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const week: IWeek = { id: 123 };
        const week2: IWeek = { id: 456 };
        expectedResult = service.addWeekToCollectionIfMissing([], week, week2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(week);
        expect(expectedResult).toContain(week2);
      });

      it('should accept null and undefined values', () => {
        const week: IWeek = { id: 123 };
        expectedResult = service.addWeekToCollectionIfMissing([], null, week, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(week);
      });

      it('should return initial array if no Week is added', () => {
        const weekCollection: IWeek[] = [{ id: 123 }];
        expectedResult = service.addWeekToCollectionIfMissing(weekCollection, undefined, null);
        expect(expectedResult).toEqual(weekCollection);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
