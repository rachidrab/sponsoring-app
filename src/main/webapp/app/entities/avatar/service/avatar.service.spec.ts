import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IAvatar, Avatar } from '../avatar.model';

import { AvatarService } from './avatar.service';

describe('Avatar Service', () => {
  let service: AvatarService;
  let httpMock: HttpTestingController;
  let elemDefault: IAvatar;
  let expectedResult: IAvatar | IAvatar[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(AvatarService);
    httpMock = TestBed.inject(HttpTestingController);

    elemDefault = {
      id: 0,
      avatarContentType: 'image/png',
      avatar: 'AAAAAAA',
      title: 'AAAAAAA',
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

    it('should create a Avatar', () => {
      const returnedFromService = Object.assign(
        {
          id: 0,
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.create(new Avatar()).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Avatar', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          avatar: 'BBBBBB',
          title: 'BBBBBB',
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.update(expected).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Avatar', () => {
      const patchObject = Object.assign({}, new Avatar());

      const returnedFromService = Object.assign(patchObject, elemDefault);

      const expected = Object.assign({}, returnedFromService);

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Avatar', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          avatar: 'BBBBBB',
          title: 'BBBBBB',
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

    it('should delete a Avatar', () => {
      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult);
    });

    describe('addAvatarToCollectionIfMissing', () => {
      it('should add a Avatar to an empty array', () => {
        const avatar: IAvatar = { id: 123 };
        expectedResult = service.addAvatarToCollectionIfMissing([], avatar);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(avatar);
      });

      it('should not add a Avatar to an array that contains it', () => {
        const avatar: IAvatar = { id: 123 };
        const avatarCollection: IAvatar[] = [
          {
            ...avatar,
          },
          { id: 456 },
        ];
        expectedResult = service.addAvatarToCollectionIfMissing(avatarCollection, avatar);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Avatar to an array that doesn't contain it", () => {
        const avatar: IAvatar = { id: 123 };
        const avatarCollection: IAvatar[] = [{ id: 456 }];
        expectedResult = service.addAvatarToCollectionIfMissing(avatarCollection, avatar);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(avatar);
      });

      it('should add only unique Avatar to an array', () => {
        const avatarArray: IAvatar[] = [{ id: 123 }, { id: 456 }, { id: 48349 }];
        const avatarCollection: IAvatar[] = [{ id: 123 }];
        expectedResult = service.addAvatarToCollectionIfMissing(avatarCollection, ...avatarArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const avatar: IAvatar = { id: 123 };
        const avatar2: IAvatar = { id: 456 };
        expectedResult = service.addAvatarToCollectionIfMissing([], avatar, avatar2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(avatar);
        expect(expectedResult).toContain(avatar2);
      });

      it('should accept null and undefined values', () => {
        const avatar: IAvatar = { id: 123 };
        expectedResult = service.addAvatarToCollectionIfMissing([], null, avatar, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(avatar);
      });

      it('should return initial array if no Avatar is added', () => {
        const avatarCollection: IAvatar[] = [{ id: 123 }];
        expectedResult = service.addAvatarToCollectionIfMissing(avatarCollection, undefined, null);
        expect(expectedResult).toEqual(avatarCollection);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
