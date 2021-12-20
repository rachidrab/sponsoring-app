import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPicture } from '../picture.model';
import { DataUtils } from 'app/core/util/data-util.service';

@Component({
  selector: 'jhi-picture-detail',
  templateUrl: './picture-detail.component.html',
})
export class PictureDetailComponent implements OnInit {
  picture: IPicture | null = null;

  constructor(protected dataUtils: DataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ picture }) => {
      this.picture = picture;
    });
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(base64String: string, contentType: string | null | undefined): void {
    this.dataUtils.openFile(base64String, contentType);
  }

  previousState(): void {
    window.history.back();
  }
}
