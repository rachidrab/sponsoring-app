export interface IUser {
  id?: number;
  login?: string;
  firstName?: string | null;
  lastName?: string | null;
  email?: string;
  activated?: boolean;
  langKey?: string;
  authorities?: string[];
  createdBy?: string;
  createdDate?: Date;
  lastModifiedBy?: string;
  lastModifiedDate?: Date;

  phoneNumber?: string;
  codeParrainage?: string;
  scoreTotal?: number;
  birthDate?: Date;
  sexe?: string;
  imageContentType?: string | null,
  image?: string | null
}

export class User implements IUser {
  constructor(
    public id?: number,
    public login?: string,
    public firstName?: string | null,
    public lastName?: string | null,
    public email?: string,
    public activated?: boolean,
    public langKey?: string,
    public authorities?: string[],
    public createdBy?: string,
    public createdDate?: Date,
    public lastModifiedBy?: string,
    public lastModifiedDate?: Date,

    public phoneNumber?: string,
    public codeParrainage?: string,
    public scoreTotal?: number,
    public birthDate?: Date,
    public sexe?: string,
    public imageContentType?: string | null,
    public image?: string | null
  ) {}
}
