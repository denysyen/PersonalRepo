import { TestBed } from '@angular/core/testing';

import { UserStotageService } from './user-stotage.service';

describe('UserStotageService', () => {
  let service: UserStotageService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UserStotageService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
