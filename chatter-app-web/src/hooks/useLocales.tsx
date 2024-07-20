import { useTranslation } from 'react-i18next';
import useSettings from './useSettings';
import { enUS, frFR, zhCN, viVN, arSD } from '@mui/material/locale';

export const allLangs = [
    {
      label: 'English',
      value: 'en',
      systemValue: enUS,
      icon: '/assets/icons/flags/ic_flag_en.svg',
    },
    {
      label: 'French',
      value: 'fr',
      systemValue: frFR,
      icon: '/assets/icons/flags/ic_flag_fr.svg',
    },
    {
      label: 'Vietnamese',
      value: 'vn',
      systemValue: viVN,
      icon: '/assets/icons/flags/ic_flag_vn.svg',
    },
    {
      label: 'Chinese',
      value: 'cn',
      systemValue: zhCN,
      icon: '/assets/icons/flags/ic_flag_cn.svg',
    },
    {
      label: 'Arabic (Sudan)',
      value: 'ar',
      systemValue: arSD,
      icon: '/assets/icons/flags/ic_flag_sa.svg',
    },
  ];
  
  export const defaultLang = allLangs[0];

export default function useLocales() {
  const { i18n, t: translate } = useTranslation();

  const { onChangeDirectionByLang } = useSettings();

  const langStorage = localStorage.getItem('i18nextLng');

  const currentLang = allLangs.find((_lang) => _lang.value === langStorage) || defaultLang;

  const handleChangeLanguage = (newlang: any) => {
    i18n.changeLanguage(newlang);
    onChangeDirectionByLang(newlang);
  };

  return {
    onChangeLang: handleChangeLanguage,
    translate: (text: any, options: any) => translate(text, options),
    currentLang,
    allLangs,
  };
}
