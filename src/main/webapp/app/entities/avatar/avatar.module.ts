import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { AvatarComponent } from './list/avatar.component';
import { AvatarDetailComponent } from './detail/avatar-detail.component';
import { AvatarUpdateComponent } from './update/avatar-update.component';
import { AvatarDeleteDialogComponent } from './delete/avatar-delete-dialog.component';
import { AvatarRoutingModule } from './route/avatar-routing.module';

@NgModule({
  imports: [SharedModule, AvatarRoutingModule],
  declarations: [AvatarComponent, AvatarDetailComponent, AvatarUpdateComponent, AvatarDeleteDialogComponent],
  entryComponents: [AvatarDeleteDialogComponent],
})
export class AvatarModule {}
