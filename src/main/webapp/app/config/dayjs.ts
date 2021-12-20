import * as dayjs from 'dayjs';
import * as customParseFormat from 'dayjs/plugin/customParseFormat';
import * as duration from 'dayjs/plugin/duration';
import * as relativeTime from 'dayjs/plugin/relativeTime';

// jhipster-needle-i18n-language-dayjs-imports - JHipster will import languages from dayjs here
import 'dayjs/locale/fr';
import 'dayjs/locale/sq';
import 'dayjs/locale/ar-ly';
import 'dayjs/locale/hy-am';
import 'dayjs/locale/bn';
import 'dayjs/locale/cs';
import 'dayjs/locale/nl';
import 'dayjs/locale/en';
import 'dayjs/locale/de';
import 'dayjs/locale/hi';
import 'dayjs/locale/id';
import 'dayjs/locale/it';
import 'dayjs/locale/pt';
import 'dayjs/locale/ro';
import 'dayjs/locale/sk';
import 'dayjs/locale/sr';
import 'dayjs/locale/es';
import 'dayjs/locale/sv';
import 'dayjs/locale/tr';
import 'dayjs/locale/uk';

// DAYJS CONFIGURATION
dayjs.extend(customParseFormat);
dayjs.extend(duration);
dayjs.extend(relativeTime);
