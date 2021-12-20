import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IAvatar, Avatar } from '../avatar.model';
import { AvatarService } from '../service/avatar.service';

@Injectable({ providedIn: 'root' })
export class AvatarRoutingResolveService implements Resolve<IAvatar> {
  constructor(protected service: AvatarService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IAvatar> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((avatar: HttpResponse<Avatar>) => {
          if (avatar.body) {
            return of(avatar.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Avatar());
  }
}
