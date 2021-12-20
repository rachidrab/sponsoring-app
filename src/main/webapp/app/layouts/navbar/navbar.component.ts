import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { VERSION } from 'app/app.constants';
import { AccountService } from 'app/core/auth/account.service';
import { LoginService } from 'app/login/login.service';
import { ProfileService } from 'app/layouts/profiles/profile.service';
import { Subscription } from 'rxjs';
import { NbMenuService, NbSidebarService, NbThemeService } from '@nebular/theme';
import { LayoutService } from 'app/services/layout.service';
import { filter, map } from 'rxjs/operators';
import { Account } from 'app/core/auth/account.model';

@Component({
  selector: 'jhi-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss'],
})
export class NavbarComponent implements OnInit {
  inProduction?: boolean;
  isNavbarCollapsed = true;
  openAPIEnabled?: boolean;
  version = '';


  userMenu = [{ title: 'Profile' }, { title: 'Changer mon mot de passe' }, { title: 'Deconnexion' }];

  account: Account | null = null;
  authSubscription?: Subscription;
  items = [{ title: 'Profile' }, { title: 'Logout' }];
  constructor(
    private loginService: LoginService,
    private accountService: AccountService,
    private profileService: ProfileService,
    private router: Router,
    private themeService: NbThemeService,
    private layoutService: LayoutService,
    private sidebarService: NbSidebarService,
    private menuService: NbMenuService,
  ) {
    if (VERSION) {
      this.version = VERSION.toLowerCase().startsWith('v') ? VERSION : 'v' + VERSION;
    }
  }

  ngOnInit(): void {

    this.profileService.getProfileInfo().subscribe(profileInfo => {
      this.inProduction = profileInfo.inProduction;
      this.openAPIEnabled = profileInfo.openAPIEnabled;
    });
    this.authSubscription = this.accountService.getAuthenticationState().subscribe(account => (this.account = account));
    this.menuService
      .onItemClick()
      .pipe(
        filter(({ tag }) => tag === 'my-context-menu'),
        map(({ item: { title } }) => title)
      )
      .subscribe(title => {
        if (title === 'Deconnexion') {
          this.logout();
        } else if (title === 'Profile') {
          this.router.navigateByUrl('account/settings');
        } else {
          this.router.navigateByUrl('account/password');
        }
      });
  }

  collapseNavbar(): void {
    this.isNavbarCollapsed = true;
  }

  isAuthenticated(): boolean {
    return this.accountService.isAuthenticated();
  }

  login(): void {
    this.router.navigate(['/login']);
  }

  logout(): void {
    this.collapseNavbar();
    this.loginService.logout();
    this.router.navigate(['']);
  }

  toggleNavbar(): void {
    this.isNavbarCollapsed = !this.isNavbarCollapsed;
  }



  toggleSidebar(): boolean {
    this.sidebarService.toggle(false, 'menu-sidebar');
    this.layoutService.changeLayoutSize();
    return false;
  }
}
