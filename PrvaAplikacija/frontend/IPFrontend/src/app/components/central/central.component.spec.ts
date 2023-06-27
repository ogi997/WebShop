import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CentralComponent } from './central.component';

describe('AllProductsComponent', () => {
  let component: CentralComponent;
  let fixture: ComponentFixture<CentralComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CentralComponent]
    });
    fixture = TestBed.createComponent(CentralComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
