import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SharedModule } from 'app/shared/shared.module';
import { LOGIN_ROUTE } from './login.route';
import { LoginComponent } from './login.component';
import {
  NbActionsModule,
  NbButtonModule,
  NbCardModule,
  NbContextMenuModule,
  NbFormFieldComponent,
  NbFormFieldModule,
  NbIconModule,
  NbLayoutModule,
  NbMenuModule,
  NbSelectModule,
  NbSidebarModule,
  NbThemeModule,
  NbThemeService,
  NbUserModule,
} from '@nebular/theme';
import { NbEvaIconsModule } from '@nebular/eva-icons';
@NgModule({
  imports: [SharedModule, RouterModule.forChild([LOGIN_ROUTE]),
  NbLayoutModule,
  NbIconModule,
  NbActionsModule,
  NbUserModule,
  NbContextMenuModule,
  NbEvaIconsModule,
  NbButtonModule,
  NbSelectModule,
  NbCardModule,
  NbFormFieldModule
],
  declarations: [LoginComponent],
})
export class LoginModule {}
