import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AppDemoComponent } from './app-demo.component';

describe('AppDemoComponent', () => {
  let component: AppDemoComponent;
  let fixture: ComponentFixture<AppDemoComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AppDemoComponent]
    });
    fixture = TestBed.createComponent(AppDemoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
