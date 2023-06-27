import { TestBed } from '@angular/core/testing';

import { GuardActivateAccountService } from './guard-activate-account.service';

describe('GuardActivateAccountService', () => {
  let service: GuardActivateAccountService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GuardActivateAccountService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
