import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { IUser, User } from 'app/admin/user-management/user-management.model';
import { UserService } from 'app/entities/user/user.service';

@Component({
  selector: 'jhi-sponsored-by',
  templateUrl: './sponsored-by.component.html',
  styleUrls: ['./sponsored-by.component.scss']
})
export class SponsoredByComponent implements OnInit {

  user!: IUser;
  firstForm: FormGroup;
  secondForm: FormGroup;
  thirdForm: FormGroup;
  code!: string | null;
  editForm = this.fb.group({
    id: [],
    login: [
      '',
      [
        Validators.required,
        Validators.minLength(1),
        Validators.maxLength(50),
        Validators.pattern('^[a-zA-Z0-9!$&*+=?^_`{|}~.-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$|^[_.@A-Za-z0-9-]+$'),
      ],
    ],
    firstName: ['', [Validators.maxLength(50)]],
    lastName: ['', [Validators.maxLength(50)]],
    email: ['', [Validators.minLength(5), Validators.maxLength(254), Validators.email]],
    langKey: [],
    code: [''],
    phoneNumber: [''],
    sexe: [''],
    password: [''],
    confirmPassword: [''],
    birthDate: ['']
  });


  constructor(private userService: UserService, private activatedRoute: ActivatedRoute, private fb: FormBuilder) { 
    this.firstForm = this.fb.group({
      firstCtrl: ['', Validators.required],
    });

    this.secondForm = this.fb.group({
      secondCtrl: ['', Validators.required],
    });

    this.thirdForm = this.fb.group({
      thirdCtrl: ['', Validators.required],
    });
  }

  ngOnInit(): void {
    this.code = this.activatedRoute.snapshot.paramMap.get('code');
    this.editForm.get("code")?.patchValue(this.code);
    this.userService.getSponsoredBy(this.activatedRoute.snapshot.paramMap.get('code')).subscribe(
      data => {
        this.user = data;
      });
      
  }



  onFirstSubmit() {
    this.firstForm.markAsDirty();
  }

  onSecondSubmit() {
    this.secondForm.markAsDirty();
  }

  onThirdSubmit() {
    this.thirdForm.markAsDirty();
  }
 

}
