import { IUser } from 'app/entities/user/user.model';
import { IGift } from 'app/entities/gift/gift.model';

export interface ICampaign {
  id?: number;
  isWeek?: boolean | null;
  isMonth?: boolean | null;
  participants?: IUser[] | null;
  gift?: IGift | null;
}

export class Campaign implements ICampaign {
  constructor(
    public id?: number,
    public isWeek?: boolean | null,
    public isMonth?: boolean | null,
    public participants?: IUser[] | null,
    public gift?: IGift | null
  ) {
    this.isWeek = this.isWeek ?? false;
    this.isMonth = this.isMonth ?? false;
  }
}

export function getCampaignIdentifier(campaign: ICampaign): number | undefined {
  return campaign.id;
}
