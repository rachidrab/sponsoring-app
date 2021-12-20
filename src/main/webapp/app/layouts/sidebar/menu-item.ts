import { NbMenuItem } from '@nebular/theme';

export const ADMIN_MENU: NbMenuItem[] = [
  {
    title: 'Dashboard',
    icon: 'home-outline',
    link: '/',
  },
  {
    title: 'Administration',
    icon: 'monitor-outline',
    expanded: false,
    children: [
      {
        title: 'Utilisateurs',
        icon: 'people-outline',
        link: 'admin/user-management',
      },
      {
        title: 'Audit',
        icon: 'monitor-outline',
        expanded: false,
        children: [
          {
            title: 'Audit Habilitations',
            icon: 'options-outline',
            link: 'admin/audits',
          },
          {
            title: 'Audit Rapports',
            icon: 'options-outline',
            link: 'admin/audit-report',
          },
        ],
      },
    ],
  },
  {
    title: 'Datasets',
    icon: 'grid-outline',
    expanded: false,
    children: [
      {
        title: 'Contexts',
        icon: 'layers-outline',
        link: 'context',
      },
      {
        title: 'Entités',
        icon: 'layers-outline',
        link: 'entite',
      },
    ],
  },
];

export const USER_MENU: NbMenuItem[] = [
  {
    title: 'Dashboard',
    icon: 'home-outline',
    link: '/',
  },
  {
    title: 'Administration',
    icon: 'monitor-outline',
    expanded: false,
    children: [
      {
        title: 'Utilisateurs',
        icon: 'people-outline',
        link: 'admin/user-management',
      }
    ],
  },
  {
    title: 'Services',
    icon: 'copy-outline',
    expanded: false,
    children: [
      {
        title: 'On Boarding',
        icon: 'people-outline',
        link: 'admin/on-boarding-users',
      },
      {
        title: 'Off Boarding',
        icon: 'people-outline',
        link: 'admin/off-boarding-users',
      },
      {
        title: 'Expatriation',
        icon: 'people-outline',
        link: 'admin/expatriation-users',
      }
    ],
  },
  {
    title: 'Demandes',
    icon: 'monitor-outline',
    expanded: false,
    children: [
      {
        title: 'Lancer une demande',
        icon: 'plus-outline',
        link: 'request/new',
      },
      {
        title: 'Mes demandes',
        icon: 'list-outline',
        link: 'request',
      },
    ],
  },
];

