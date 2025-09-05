import { DynamicDialogRef, DynamicDialogConfig } from 'primeng/dynamicdialog';
import { WarehouseService } from '@/pages/service/warehouse.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Warehouse } from 'src/interfaces/warehouse';
import { FloatLabel } from "primeng/floatlabel";
import { InputText } from "primeng/inputtext";
import { Button } from "primeng/button";

@Component({
    selector: 'app-warehouse-editor',
    templateUrl: './warehouse-editor.html',
    styleUrl: './warehouse-editor.scss',
    imports: [FormsModule, ReactiveFormsModule, FloatLabel, InputText, Button]
})
export class WarehouseEditor implements OnInit {
    warehouseId: number | undefined;
    formGroup: FormGroup;
    constructor(
        private service: WarehouseService,
        private ref: DynamicDialogRef,
        private config: DynamicDialogConfig,
        private fb: FormBuilder
    ) {
        this.formGroup = fb.group({
            title: ["", Validators.required],
            address: ["", Validators.required],
        });
    }

    ngOnInit(): void {
        this.warehouseId = this.config.data.warehouseId;
        if (this.warehouseId) {
            this.service.findById(this.warehouseId).subscribe((w) => {
                this.formGroup.patchValue(w);
            });
        }
    }

    save() {
        let warehouse = this.formGroup.value as Warehouse;
        warehouse.enabled = true;
        if (this.warehouseId) {
            this.service.update(this.warehouseId, warehouse).subscribe(()=>{
                this.ref.close(true);
            });
        } else {
            this.service.create(warehouse).subscribe((res)=>{
                this.ref.close(true);
            })
        }
    }

     cancel() {
        this.ref.close(false);
     }
}
