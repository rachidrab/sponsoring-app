import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SharedModule } from 'app/shared/shared.module';
import { PasswordStrengthBarComponent } from './password/password-strength-bar/password-strength-bar.component';
import { RegisterComponent } from './register/register.component';
import { ActivateComponent } from './activate/activate.component';
import { PasswordComponent } from './password/password.component';
import { PasswordResetInitComponent } from './password-reset/init/password-reset-init.component';
import { PasswordResetFinishComponent } from './password-reset/finish/password-reset-finish.component';
import { SettingsComponent } from './settings/settings.component';
import { accountState } from './account.route';
import { SponsoredByComponent } from 'app/account/sponsored-by/sponsored-by.component';
import { NbButtonModule, NbCardModule, NbSelectModule, NbStepperModule } from '@nebular/theme';

@NgModule({
  imports: [SharedModule, RouterModule.forChild(accountState), NbCardModule, NbStepperModule, NbButtonModule,
  NbSelectModule],
  declarations: [
    ActivateComponent,
    RegisterComponent,
    PasswordComponent,
    PasswordStrengthBarComponent,
    PasswordResetInitComponent,
    PasswordResetFinishComponent,
    SettingsComponent,
    SponsoredByComponent
  ],
})
export class AccountModule {}
