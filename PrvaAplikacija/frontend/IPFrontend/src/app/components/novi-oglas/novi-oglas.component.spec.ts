import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NoviOglasComponent } from './novi-oglas.component';

describe('NoviOglasComponent', () => {
  let component: NoviOglasComponent;
  let fixture: ComponentFixture<NoviOglasComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [NoviOglasComponent]
    });
    fixture = TestBed.createComponent(NoviOglasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
