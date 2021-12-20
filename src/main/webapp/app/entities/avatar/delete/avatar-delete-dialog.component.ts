import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IAvatar } from '../avatar.model';
import { AvatarService } from '../service/avatar.service';

@Component({
  templateUrl: './avatar-delete-dialog.component.html',
})
export class AvatarDeleteDialogComponent {
  avatar?: IAvatar;

  constructor(protected avatarService: AvatarService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.avatarService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
