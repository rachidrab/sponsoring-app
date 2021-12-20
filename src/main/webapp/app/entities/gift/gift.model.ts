import { IPicture } from 'app/entities/picture/picture.model';
import { ActiveNoiseCancellation } from 'app/entities/enumerations/active-noise-cancellation.model';
import { VolumeControl } from 'app/entities/enumerations/volume-control.model';
import { TouchScreen } from 'app/entities/enumerations/touch-screen.model';
import { Hooded } from 'app/entities/enumerations/hooded.model';

export interface IGift {
  id?: number;
  brandName?: string | null;
  material?: string | null;
  style?: string | null;
  season?: string | null;
  type?: string | null;
  clothingLength?: string | null;
  collar?: string | null;
  sleeveLength?: string | null;
  design?: string | null;
  gender?: string | null;
  occasion?: string | null;
  decoration?: string | null;
  closureType?: string | null;
  sleeveType?: string | null;
  color?: string | null;
  quality?: string | null;
  features?: string | null;
  activeNoiseCancellation?: ActiveNoiseCancellation | null;
  volumeControl?: VolumeControl | null;
  wirelessType?: string | null;
  functions?: string | null;
  packageList?: string | null;
  bluetoothVersion?: string | null;
  frequency?: string | null;
  modelNumber?: string | null;
  description?: string;
  ram?: string | null;
  suitableFor?: string | null;
  screenStyle?: string | null;
  weight?: string | null;
  rom?: string | null;
  battery?: string | null;
  touchScreen?: TouchScreen | null;
  hooded?: Hooded | null;
  madeIn?: string | null;
  shippingFrom?: string | null;
  sizee?: string | null;
  pictures?: IPicture[] | null;
}

export class Gift implements IGift {
  constructor(
    public id?: number,
    public brandName?: string | null,
    public material?: string | null,
    public style?: string | null,
    public season?: string | null,
    public type?: string | null,
    public clothingLength?: string | null,
    public collar?: string | null,
    public sleeveLength?: string | null,
    public design?: string | null,
    public gender?: string | null,
    public occasion?: string | null,
    public decoration?: string | null,
    public closureType?: string | null,
    public sleeveType?: string | null,
    public color?: string | null,
    public quality?: string | null,
    public features?: string | null,
    public activeNoiseCancellation?: ActiveNoiseCancellation | null,
    public volumeControl?: VolumeControl | null,
    public wirelessType?: string | null,
    public functions?: string | null,
    public packageList?: string | null,
    public bluetoothVersion?: string | null,
    public frequency?: string | null,
    public modelNumber?: string | null,
    public description?: string,
    public ram?: string | null,
    public suitableFor?: string | null,
    public screenStyle?: string | null,
    public weight?: string | null,
    public rom?: string | null,
    public battery?: string | null,
    public touchScreen?: TouchScreen | null,
    public hooded?: Hooded | null,
    public madeIn?: string | null,
    public shippingFrom?: string | null,
    public sizee?: string | null,
    public pictures?: IPicture[] | null
  ) {}
}

export function getGiftIdentifier(gift: IGift): number | undefined {
  return gift.id;
}
