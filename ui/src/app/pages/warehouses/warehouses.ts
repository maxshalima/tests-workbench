import { Component, OnInit } from '@angular/core';
import { WarehouseService } from '../service/warehouse.service';
import { PageModel } from 'src/interfaces/page-model';
import { Warehouse } from 'src/interfaces/warehouse';
import { TableModule } from 'primeng/table';
import { Checkbox } from 'primeng/checkbox';
import { FormsModule } from '@angular/forms';
import { DialogService } from 'primeng/dynamicdialog';
import { WarehouseEditor } from '@/components/warehouse-editor/warehouse-editor';
import { Button } from "primeng/button";
import { Tooltip } from "primeng/tooltip";
import { InputText } from "primeng/inputtext";

@Component({
    selector: 'app-warehouses',
    imports: [TableModule, Checkbox, FormsModule, Button, Tooltip, InputText],
    templateUrl: './warehouses.html',
    styleUrl: './warehouses.scss',
    providers: [DialogService]
})
export class Warehouses implements OnInit {
    data: PageModel<Warehouse> | undefined;
    query: string;
    constructor(
        private service: WarehouseService,
        private dialogService: DialogService
    ) {
        this.query = '';
    }

    ngOnInit(): void {
        this.loadWarehouses();
    }

    loadWarehouses() {
        this.service.find(this.query).subscribe((model) => {
            this.data = model;
        });
    }

    switch(warehouseId: number) {
        this.service.switchWarehouse(warehouseId).subscribe(()=>{
            this.loadWarehouses();
        })
    }

    edit(warehouseId?: number) {
        this.dialogService.open(WarehouseEditor, {
            width: '50vw',
            modal:true,
            breakpoints: {
                '960px': '75vw',
                '640px': '90vw'
            },
            data: {
                warehouseId
            }
        }).onClose.subscribe((res)=>{
            if(res) {
                this.loadWarehouses();
            }
        });
    }
}
