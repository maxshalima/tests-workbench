import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TmcItemEditor } from './tmc-item-editor';

describe('TmcItemEditor', () => {
  let component: TmcItemEditor;
  let fixture: ComponentFixture<TmcItemEditor>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TmcItemEditor]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TmcItemEditor);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
