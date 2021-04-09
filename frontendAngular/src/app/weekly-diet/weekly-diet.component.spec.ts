import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WeeklyDietComponent } from './weekly-diet.component';

describe('WeeklyDietComponent', () => {
  let component: WeeklyDietComponent;
  let fixture: ComponentFixture<WeeklyDietComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WeeklyDietComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WeeklyDietComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
