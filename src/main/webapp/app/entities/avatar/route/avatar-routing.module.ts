import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { AvatarComponent } from '../list/avatar.component';
import { AvatarDetailComponent } from '../detail/avatar-detail.component';
import { AvatarUpdateComponent } from '../update/avatar-update.component';
import { AvatarRoutingResolveService } from './avatar-routing-resolve.service';

const avatarRoute: Routes = [
  {
    path: '',
    component: AvatarComponent,
    data: {
      defaultSort: 'id,asc',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: AvatarDetailComponent,
    resolve: {
      avatar: AvatarRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: AvatarUpdateComponent,
    resolve: {
      avatar: AvatarRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: AvatarUpdateComponent,
    resolve: {
      avatar: AvatarRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(avatarRoute)],
  exports: [RouterModule],
})
export class AvatarRoutingModule {}
