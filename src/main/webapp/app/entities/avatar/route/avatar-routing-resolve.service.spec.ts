jest.mock('@angular/router');

import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of } from 'rxjs';

import { IAvatar, Avatar } from '../avatar.model';
import { AvatarService } from '../service/avatar.service';

import { AvatarRoutingResolveService } from './avatar-routing-resolve.service';

describe('Avatar routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let routingResolveService: AvatarRoutingResolveService;
  let service: AvatarService;
  let resultAvatar: IAvatar | undefined;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [Router, ActivatedRouteSnapshot],
    });
    mockRouter = TestBed.inject(Router);
    mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
    routingResolveService = TestBed.inject(AvatarRoutingResolveService);
    service = TestBed.inject(AvatarService);
    resultAvatar = undefined;
  });

  describe('resolve', () => {
    it('should return IAvatar returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultAvatar = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultAvatar).toEqual({ id: 123 });
    });

    it('should return new IAvatar if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultAvatar = result;
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultAvatar).toEqual(new Avatar());
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse({ body: null as unknown as Avatar })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultAvatar = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultAvatar).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
