import { TestBed } from '@angular/core/testing';

import { GuardRegisterService } from './guard-register.service';

describe('GuardRegisterService', () => {
  let service: GuardRegisterService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GuardRegisterService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
