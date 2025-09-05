import { WarehouseItemTypeService } from './../service/warehouse-item-type.service';
import { Component, OnInit } from '@angular/core';
import { TableModule } from 'primeng/table';
import { PageModel } from 'src/interfaces/page-model';
import { WarehouseItemType } from 'src/interfaces/warehouse-item-type';
import { Button } from "primeng/button";
import { FormsModule } from '@angular/forms';
import { InputText } from "primeng/inputtext";

@Component({
    selector: 'app-item-types',
    templateUrl: './item-types.component.html',
    styleUrls: ['./item-types.component.css'],
    imports: [TableModule, Button, FormsModule, InputText]
})
export class ItemTypesComponent implements OnInit {
    data: PageModel<WarehouseItemType>|undefined;
    query = '';
    constructor(private service: WarehouseItemTypeService) {}

    ngOnInit() {
        this.loadItemTypes();
    }

    loadItemTypes() {
        this.service.find(this.query).subscribe((res)=> {
            this.data = res;
        });
    }

    edit(id?:number) {

    }

    delete(id:number) {
        this.service.delete(id).subscribe(()=>{
            this.loadItemTypes();
        })
    }
}
