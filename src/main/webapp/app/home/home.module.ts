import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SharedModule } from 'app/shared/shared.module';
import { HOME_ROUTE } from './home.route';
import { HomeComponent } from './home.component';
import { NbCardModule, NbFormFieldModule, NbIconModule, NbInputModule, NbButtonModule } from '@nebular/theme';

@NgModule({
  imports: [SharedModule, RouterModule.forChild([HOME_ROUTE]), NbCardModule, NbFormFieldModule, NbIconModule, NbInputModule, NbButtonModule],
  declarations: [HomeComponent],
})
export class HomeModule {}
