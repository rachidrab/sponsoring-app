import { IGift } from 'app/entities/gift/gift.model';

export interface IPicture {
  id?: number;
  imageContentType?: string | null;
  image?: string | null;
  title?: string | null;
  gift?: IGift | null;
}

export class Picture implements IPicture {
  constructor(
    public id?: number,
    public imageContentType?: string | null,
    public image?: string | null,
    public title?: string | null,
    public gift?: IGift | null
  ) {}
}

export function getPictureIdentifier(picture: IPicture): number | undefined {
  return picture.id;
}
