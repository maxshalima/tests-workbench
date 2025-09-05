import { WarehouseItemTypeService } from './../service/warehouse-item-type.service';
import { Component } from '@angular/core';
import { TableLazyLoadEvent, TableModule, TablePageEvent } from 'primeng/table';
import { WarehouseItemType } from 'src/interfaces/warehouse-item-type';
import { Button } from 'primeng/button';
import { FormsModule } from '@angular/forms';
import { InputText } from 'primeng/inputtext';

@Component({
    selector: 'app-item-types',
    templateUrl: './item-types.component.html',
    styleUrls: ['./item-types.component.css'],
    imports: [TableModule, Button, FormsModule, InputText]
})
export class ItemTypesComponent {
    query = '';
    pageSize = 10;
    pageNumber = 0;
    first = 0;
    itemTypes!: WarehouseItemType[];
    totalRows!: number;
    constructor(private service: WarehouseItemTypeService) {}

    loadItemTypes(event: TableLazyLoadEvent) {
        if (event.first !== undefined) {
            this.first = event.first;
        }
        if (event.rows != null && event.rows !== 0) {
            this.pageSize = event.rows;
        }
        const pageNumber = this.first / this.pageSize;
        this.service.find(this.query, pageNumber, this.pageSize).subscribe((res) => {
            this.itemTypes = res.content;
            this.totalRows = res.page.totalElements;
            if (event.forceUpdate) event.forceUpdate();
        });
    }

    edit(id?: number) {}

    delete(id: number) {
        this.service.delete(id).subscribe(() => {
            let event = {};
            this.loadItemTypes(event);
        });
    }
}
