import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as dayjs from 'dayjs';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IWeek, getWeekIdentifier } from '../week.model';

export type EntityResponseType = HttpResponse<IWeek>;
export type EntityArrayResponseType = HttpResponse<IWeek[]>;

@Injectable({ providedIn: 'root' })
export class WeekService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/weeks');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(week: IWeek): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(week);
    return this.http
      .post<IWeek>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(week: IWeek): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(week);
    return this.http
      .put<IWeek>(`${this.resourceUrl}/${getWeekIdentifier(week) as number}`, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  partialUpdate(week: IWeek): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(week);
    return this.http
      .patch<IWeek>(`${this.resourceUrl}/${getWeekIdentifier(week) as number}`, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IWeek>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IWeek[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addWeekToCollectionIfMissing(weekCollection: IWeek[], ...weeksToCheck: (IWeek | null | undefined)[]): IWeek[] {
    const weeks: IWeek[] = weeksToCheck.filter(isPresent);
    if (weeks.length > 0) {
      const weekCollectionIdentifiers = weekCollection.map(weekItem => getWeekIdentifier(weekItem)!);
      const weeksToAdd = weeks.filter(weekItem => {
        const weekIdentifier = getWeekIdentifier(weekItem);
        if (weekIdentifier == null || weekCollectionIdentifiers.includes(weekIdentifier)) {
          return false;
        }
        weekCollectionIdentifiers.push(weekIdentifier);
        return true;
      });
      return [...weeksToAdd, ...weekCollection];
    }
    return weekCollection;
  }

  protected convertDateFromClient(week: IWeek): IWeek {
    return Object.assign({}, week, {
      start: week.start?.isValid() ? week.start.format(DATE_FORMAT) : undefined,
      end: week.end?.isValid() ? week.end.format(DATE_FORMAT) : undefined,
    });
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.start = res.body.start ? dayjs(res.body.start) : undefined;
      res.body.end = res.body.end ? dayjs(res.body.end) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((week: IWeek) => {
        week.start = week.start ? dayjs(week.start) : undefined;
        week.end = week.end ? dayjs(week.end) : undefined;
      });
    }
    return res;
  }
}
