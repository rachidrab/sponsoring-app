import { IUser } from 'app/entities/user/user.model';

export interface ICategory {
  id?: number;
  category?: string | null;
  users?: IUser[] | null;
}

export class Category implements ICategory {
  constructor(public id?: number, public category?: string | null, public users?: IUser[] | null) {}
}

export function getCategoryIdentifier(category: ICategory): number | undefined {
  return category.id;
}
