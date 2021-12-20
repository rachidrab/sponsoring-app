import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { IWeek, Week } from '../week.model';
import { WeekService } from '../service/week.service';

@Component({
  selector: 'jhi-week-update',
  templateUrl: './week-update.component.html',
})
export class WeekUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    start: [],
    end: [],
  });

  constructor(protected weekService: WeekService, protected activatedRoute: ActivatedRoute, protected fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ week }) => {
      this.updateForm(week);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const week = this.createFromForm();
    if (week.id !== undefined) {
      this.subscribeToSaveResponse(this.weekService.update(week));
    } else {
      this.subscribeToSaveResponse(this.weekService.create(week));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IWeek>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(week: IWeek): void {
    this.editForm.patchValue({
      id: week.id,
      start: week.start,
      end: week.end,
    });
  }

  protected createFromForm(): IWeek {
    return {
      ...new Week(),
      id: this.editForm.get(['id'])!.value,
      start: this.editForm.get(['start'])!.value,
      end: this.editForm.get(['end'])!.value,
    };
  }
}
