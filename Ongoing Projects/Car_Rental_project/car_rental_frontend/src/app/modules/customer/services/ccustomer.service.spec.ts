import { TestBed } from '@angular/core/testing';

import { CcustomerService } from './ccustomer.service';

describe('CcustomerService', () => {
  let service: CcustomerService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CcustomerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
