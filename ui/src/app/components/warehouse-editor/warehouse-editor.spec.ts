import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WarehouseEditor } from './warehouse-editor';

describe('WarehouseEditor', () => {
  let component: WarehouseEditor;
  let fixture: ComponentFixture<WarehouseEditor>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [WarehouseEditor]
    })
    .compileComponents();

    fixture = TestBed.createComponent(WarehouseEditor);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
