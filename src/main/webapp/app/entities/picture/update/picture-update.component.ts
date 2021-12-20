import { Component, OnInit, ElementRef } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { IPicture, Picture } from '../picture.model';
import { PictureService } from '../service/picture.service';
import { AlertError } from 'app/shared/alert/alert-error.model';
import { EventManager, EventWithContent } from 'app/core/util/event-manager.service';
import { DataUtils, FileLoadError } from 'app/core/util/data-util.service';
import { IGift } from 'app/entities/gift/gift.model';
import { GiftService } from 'app/entities/gift/service/gift.service';

@Component({
  selector: 'jhi-picture-update',
  templateUrl: './picture-update.component.html',
})
export class PictureUpdateComponent implements OnInit {
  isSaving = false;

  giftsSharedCollection: IGift[] = [];

  editForm = this.fb.group({
    id: [],
    image: [],
    imageContentType: [],
    title: [],
    gift: [],
  });

  constructor(
    protected dataUtils: DataUtils,
    protected eventManager: EventManager,
    protected pictureService: PictureService,
    protected giftService: GiftService,
    protected elementRef: ElementRef,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ picture }) => {
      this.updateForm(picture);

      this.loadRelationshipsOptions();
    });
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(base64String: string, contentType: string | null | undefined): void {
    this.dataUtils.openFile(base64String, contentType);
  }

  setFileData(event: Event, field: string, isImage: boolean): void {
    this.dataUtils.loadFileToForm(event, this.editForm, field, isImage).subscribe({
      error: (err: FileLoadError) =>
        this.eventManager.broadcast(new EventWithContent<AlertError>('parrainageApp.error', { ...err, key: 'error.file.' + err.key })),
    });
  }

  clearInputImage(field: string, fieldContentType: string, idInput: string): void {
    this.editForm.patchValue({
      [field]: null,
      [fieldContentType]: null,
    });
    if (idInput && this.elementRef.nativeElement.querySelector('#' + idInput)) {
      this.elementRef.nativeElement.querySelector('#' + idInput).value = null;
    }
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const picture = this.createFromForm();
    if (picture.id !== undefined) {
      this.subscribeToSaveResponse(this.pictureService.update(picture));
    } else {
      this.subscribeToSaveResponse(this.pictureService.create(picture));
    }
  }

  trackGiftById(index: number, item: IGift): number {
    return item.id!;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPicture>>): void {
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

  protected updateForm(picture: IPicture): void {
    this.editForm.patchValue({
      id: picture.id,
      image: picture.image,
      imageContentType: picture.imageContentType,
      title: picture.title,
      gift: picture.gift,
    });

    this.giftsSharedCollection = this.giftService.addGiftToCollectionIfMissing(this.giftsSharedCollection, picture.gift);
  }

  protected loadRelationshipsOptions(): void {
    this.giftService
      .query()
      .pipe(map((res: HttpResponse<IGift[]>) => res.body ?? []))
      .pipe(map((gifts: IGift[]) => this.giftService.addGiftToCollectionIfMissing(gifts, this.editForm.get('gift')!.value)))
      .subscribe((gifts: IGift[]) => (this.giftsSharedCollection = gifts));
  }

  protected createFromForm(): IPicture {
    return {
      ...new Picture(),
      id: this.editForm.get(['id'])!.value,
      imageContentType: this.editForm.get(['imageContentType'])!.value,
      image: this.editForm.get(['image'])!.value,
      title: this.editForm.get(['title'])!.value,
      gift: this.editForm.get(['gift'])!.value,
    };
  }
}
