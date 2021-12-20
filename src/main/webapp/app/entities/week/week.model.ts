import * as dayjs from 'dayjs';

export interface IWeek {
  id?: number;
  start?: dayjs.Dayjs | null;
  end?: dayjs.Dayjs | null;
}

export class Week implements IWeek {
  constructor(public id?: number, public start?: dayjs.Dayjs | null, public end?: dayjs.Dayjs | null) {}
}

export function getWeekIdentifier(week: IWeek): number | undefined {
  return week.id;
}
