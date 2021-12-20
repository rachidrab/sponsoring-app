import { IUser } from 'app/entities/user/user.model';

export interface IAddress {
  id?: number;
  streetLine1?: string | null;
  streetLine2?: string | null;
  city?: string | null;
  stateOrProvince?: string | null;
  postalCode?: string | null;
  country?: string | null;
  users?: IUser[] | null;
}

export class Address implements IAddress {
  constructor(
    public id?: number,
    public streetLine1?: string | null,
    public streetLine2?: string | null,
    public city?: string | null,
    public stateOrProvince?: string | null,
    public postalCode?: string | null,
    public country?: string | null,
    public users?: IUser[] | null
  ) {}
}

export function getAddressIdentifier(address: IAddress): number | undefined {
  return address.id;
}
