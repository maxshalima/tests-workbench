/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { WarehouseItemTypeService } from './warehouse-item-type.service';

describe('Service: WarehouseItemType', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [WarehouseItemTypeService]
    });
  });

  it('should ...', inject([WarehouseItemTypeService], (service: WarehouseItemTypeService) => {
    expect(service).toBeTruthy();
  }));
});
