import { Component, OnInit } from '@angular/core';
import { WarehouseService } from '../service/warehouse.service';
import { PageModel } from 'src/interfaces/PageModel';
import { Warehouse } from 'src/interfaces/Warehouse';
import { TableModule } from "primeng/table";
import { Checkbox } from "primeng/checkbox";
import { FormsModule } from '@angular/forms';

@Component({
    selector: 'app-warehouses',
    imports: [TableModule, Checkbox, FormsModule],
    templateUrl: './warehouses.html',
    styleUrl: './warehouses.scss'
})
export class Warehouses implements OnInit {
    data: PageModel<Warehouse> | undefined;
    constructor(private service: WarehouseService) {}

    ngOnInit(): void {
        this.service.find().subscribe((model) => {
            this.data = model;
        });
    }
}
