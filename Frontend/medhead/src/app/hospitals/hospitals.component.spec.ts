import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { HospitalsComponent } from './hospitals.component';

describe('HospitalsComponent', () => {
  let component: HospitalsComponent;
  let fixture: ComponentFixture<HospitalsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HospitalsComponent ],
      imports: [ HttpClientTestingModule, ReactiveFormsModule ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HospitalsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should load hospitals on init', () => {
    spyOn(component, 'loadHospitals');
    component.ngOnInit();
    expect(component.loadHospitals).toHaveBeenCalled();
  });

  it('should load specializations on init', () => {
    spyOn(component, 'loadSpecializations');
    component.ngOnInit();
    expect(component.loadSpecializations).toHaveBeenCalled();
  });
});
