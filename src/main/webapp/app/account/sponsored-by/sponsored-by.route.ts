import { Route } from '@angular/router';

import { SponsoredByComponent } from './sponsored-by.component';

export const sponsoredByRoute: Route = {
  path: 'uidparrain/:code',
  component: SponsoredByComponent,
  data: {
    pageTitle: 'sponsored.title',
  },
};
