import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IncomingDocumentEditor } from './incoming-document-editor';

describe('IncomingDocumentEditor', () => {
  let component: IncomingDocumentEditor;
  let fixture: ComponentFixture<IncomingDocumentEditor>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [IncomingDocumentEditor]
    })
    .compileComponents();

    fixture = TestBed.createComponent(IncomingDocumentEditor);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
