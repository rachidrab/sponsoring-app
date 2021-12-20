import { IUser } from 'app/entities/user/user.model';

export interface IAvatar {
  id?: number;
  avatarContentType?: string | null;
  avatar?: string | null;
  title?: string | null;
  users?: IUser[] | null;
}

export class Avatar implements IAvatar {
  constructor(
    public id?: number,
    public avatarContentType?: string | null,
    public avatar?: string | null,
    public title?: string | null,
    public users?: IUser[] | null
  ) {}
}

export function getAvatarIdentifier(avatar: IAvatar): number | undefined {
  return avatar.id;
}
