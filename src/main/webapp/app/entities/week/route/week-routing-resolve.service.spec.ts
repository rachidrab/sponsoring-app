jest.mock('@angular/router');

import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of } from 'rxjs';

import { IWeek, Week } from '../week.model';
import { WeekService } from '../service/week.service';

import { WeekRoutingResolveService } from './week-routing-resolve.service';

describe('Week routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let routingResolveService: WeekRoutingResolveService;
  let service: WeekService;
  let resultWeek: IWeek | undefined;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [Router, ActivatedRouteSnapshot],
    });
    mockRouter = TestBed.inject(Router);
    mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
    routingResolveService = TestBed.inject(WeekRoutingResolveService);
    service = TestBed.inject(WeekService);
    resultWeek = undefined;
  });

  describe('resolve', () => {
    it('should return IWeek returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultWeek = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultWeek).toEqual({ id: 123 });
    });

    it('should return new IWeek if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultWeek = result;
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultWeek).toEqual(new Week());
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse({ body: null as unknown as Week })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultWeek = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultWeek).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
