import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OglasiComponent } from './oglasi.component';

describe('OglasiComponent', () => {
  let component: OglasiComponent;
  let fixture: ComponentFixture<OglasiComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [OglasiComponent]
    });
    fixture = TestBed.createComponent(OglasiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
