import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SponsoredByComponent } from './sponsored-by.component';

describe('SponsoredByComponent', () => {
  let component: SponsoredByComponent;
  let fixture: ComponentFixture<SponsoredByComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SponsoredByComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SponsoredByComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
