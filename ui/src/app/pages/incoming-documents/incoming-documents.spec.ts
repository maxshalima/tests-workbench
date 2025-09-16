import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IncomingDocuments } from './incoming-documents';

describe('IncomingDocuments', () => {
  let component: IncomingDocuments;
  let fixture: ComponentFixture<IncomingDocuments>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [IncomingDocuments]
    })
    .compileComponents();

    fixture = TestBed.createComponent(IncomingDocuments);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
