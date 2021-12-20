import { Pipe, PipeTransform } from '@angular/core';

@Pipe({ name: 'findLanguageFromKey' })
export class FindLanguageFromKeyPipe implements PipeTransform {
  private languages: { [key: string]: { name: string; rtl?: boolean } } = {
    al: { name: 'Shqip' },
    'ar-ly': { name: 'العربية', rtl: true },
    hy: { name: 'Հայերեն' },
    bn: { name: 'বাংলা' },
    cs: { name: 'Český' },
    nl: { name: 'Nederlands' },
    en: { name: 'English' },
    fr: { name: 'Français' },
    de: { name: 'Deutsch' },
    hi: { name: 'हिंदी' },
    in: { name: 'Bahasa Indonesia' },
    it: { name: 'Italiano' },
    'pt-pt': { name: 'Português' },
    ro: { name: 'Română' },
    sk: { name: 'Slovenský' },
    sr: { name: 'Srpski' },
    es: { name: 'Español' },
    sv: { name: 'Svenska' },
    tr: { name: 'Türkçe' },
    ua: { name: 'Українська' },
    // jhipster-needle-i18n-language-key-pipe - JHipster will add/remove languages in this object
  };

  transform(lang: string): string {
    return this.languages[lang].name;
  }

  isRTL(lang: string): boolean {
    return Boolean(this.languages[lang].rtl);
  }
}
