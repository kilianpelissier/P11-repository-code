import { TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing'; // Ajouté pour les tests HTTP
import { AppComponent } from './app.component';

describe('AppComponent', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        RouterTestingModule,
        HttpClientTestingModule // Ajouté pour les tests HTTP
      ],
      declarations: [
        AppComponent
      ],
    }).compileComponents();
  });

  it('should create the app', () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    expect(app).toBeTruthy();
  });

  it(`should have as title 'medhead'`, () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    expect(app.title).toEqual('medhead');
  });

  it('should contain the navbar element', () => {
    const fixture = TestBed.createComponent(AppComponent);
    fixture.detectChanges();  // This ensures that the DOM is updated with the component's template
    const compiled = fixture.nativeElement;
    const navElement = compiled.querySelector('app-navbar');
    expect(navElement).toBeTruthy();  // toBeTruthy checks that the element exists
  });
});
