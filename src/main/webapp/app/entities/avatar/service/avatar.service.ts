import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IAvatar, getAvatarIdentifier } from '../avatar.model';

export type EntityResponseType = HttpResponse<IAvatar>;
export type EntityArrayResponseType = HttpResponse<IAvatar[]>;

@Injectable({ providedIn: 'root' })
export class AvatarService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/avatars');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(avatar: IAvatar): Observable<EntityResponseType> {
    return this.http.post<IAvatar>(this.resourceUrl, avatar, { observe: 'response' });
  }

  update(avatar: IAvatar): Observable<EntityResponseType> {
    return this.http.put<IAvatar>(`${this.resourceUrl}/${getAvatarIdentifier(avatar) as number}`, avatar, { observe: 'response' });
  }

  partialUpdate(avatar: IAvatar): Observable<EntityResponseType> {
    return this.http.patch<IAvatar>(`${this.resourceUrl}/${getAvatarIdentifier(avatar) as number}`, avatar, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IAvatar>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IAvatar[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addAvatarToCollectionIfMissing(avatarCollection: IAvatar[], ...avatarsToCheck: (IAvatar | null | undefined)[]): IAvatar[] {
    const avatars: IAvatar[] = avatarsToCheck.filter(isPresent);
    if (avatars.length > 0) {
      const avatarCollectionIdentifiers = avatarCollection.map(avatarItem => getAvatarIdentifier(avatarItem)!);
      const avatarsToAdd = avatars.filter(avatarItem => {
        const avatarIdentifier = getAvatarIdentifier(avatarItem);
        if (avatarIdentifier == null || avatarCollectionIdentifiers.includes(avatarIdentifier)) {
          return false;
        }
        avatarCollectionIdentifiers.push(avatarIdentifier);
        return true;
      });
      return [...avatarsToAdd, ...avatarCollection];
    }
    return avatarCollection;
  }
}
