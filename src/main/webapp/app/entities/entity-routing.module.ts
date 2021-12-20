import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'campaign',
        data: { pageTitle: 'parrainageApp.campaign.home.title' },
        loadChildren: () => import('./campaign/campaign.module').then(m => m.CampaignModule),
      },
      {
        path: 'week',
        data: { pageTitle: 'parrainageApp.week.home.title' },
        loadChildren: () => import('./week/week.module').then(m => m.WeekModule),
      },
      {
        path: 'gift',
        data: { pageTitle: 'parrainageApp.gift.home.title' },
        loadChildren: () => import('./gift/gift.module').then(m => m.GiftModule),
      },
      {
        path: 'picture',
        data: { pageTitle: 'parrainageApp.picture.home.title' },
        loadChildren: () => import('./picture/picture.module').then(m => m.PictureModule),
      },
      {
        path: 'avatar',
        data: { pageTitle: 'parrainageApp.avatar.home.title' },
        loadChildren: () => import('./avatar/avatar.module').then(m => m.AvatarModule),
      },
      {
        path: 'category',
        data: { pageTitle: 'parrainageApp.category.home.title' },
        loadChildren: () => import('./category/category.module').then(m => m.CategoryModule),
      },
      {
        path: 'address',
        data: { pageTitle: 'parrainageApp.address.home.title' },
        loadChildren: () => import('./address/address.module').then(m => m.AddressModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EntityRoutingModule {}
