jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { WeekService } from '../service/week.service';
import { IWeek, Week } from '../week.model';

import { WeekUpdateComponent } from './week-update.component';

describe('Component Tests', () => {
  describe('Week Management Update Component', () => {
    let comp: WeekUpdateComponent;
    let fixture: ComponentFixture<WeekUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let weekService: WeekService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [WeekUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(WeekUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(WeekUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      weekService = TestBed.inject(WeekService);

      comp = fixture.componentInstance;
    });

    describe('ngOnInit', () => {
      it('Should update editForm', () => {
        const week: IWeek = { id: 456 };

        activatedRoute.data = of({ week });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(week));
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', () => {
        // GIVEN
        const saveSubject = new Subject<HttpResponse<Week>>();
        const week = { id: 123 };
        jest.spyOn(weekService, 'update').mockReturnValue(saveSubject);
        jest.spyOn(comp, 'previousState');
        activatedRoute.data = of({ week });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: week }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(weekService.update).toHaveBeenCalledWith(week);
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', () => {
        // GIVEN
        const saveSubject = new Subject<HttpResponse<Week>>();
        const week = new Week();
        jest.spyOn(weekService, 'create').mockReturnValue(saveSubject);
        jest.spyOn(comp, 'previousState');
        activatedRoute.data = of({ week });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: week }));
        saveSubject.complete();

        // THEN
        expect(weekService.create).toHaveBeenCalledWith(week);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it('Should set isSaving to false on error', () => {
        // GIVEN
        const saveSubject = new Subject<HttpResponse<Week>>();
        const week = { id: 123 };
        jest.spyOn(weekService, 'update').mockReturnValue(saveSubject);
        jest.spyOn(comp, 'previousState');
        activatedRoute.data = of({ week });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error('This is an error!');

        // THEN
        expect(weekService.update).toHaveBeenCalledWith(week);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).not.toHaveBeenCalled();
      });
    });
  });
});
