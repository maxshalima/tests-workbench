import {DynamicDialogRef, DynamicDialogConfig, DialogService} from 'primeng/dynamicdialog';
import { IncomingDocumentsService } from '@/pages/service/incoming-documents.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormsModule, ReactiveFormsModule } from '@angular/forms';

import { FloatLabel } from "primeng/floatlabel";
import { InputText } from "primeng/inputtext";
import { Button } from "primeng/button";
import {DatePicker} from "primeng/datepicker";
import {AutoComplete, AutoCompleteCompleteEvent} from "primeng/autocomplete";
import {SelectListItemDto} from "../../../interfaces/select-list-item-dto";
import { TableModule } from 'primeng/table';
import {WarehouseItemTypeService} from "@/pages/service/warehouse-item-type.service";
import {InputNumber} from "primeng/inputnumber";


@Component({
    selector: 'tmc-item-editor-editor',
    templateUrl: './tmc-item-editor.html',
    styleUrl: './tmc-item-editor.scss',
    imports: [FormsModule, ReactiveFormsModule, FloatLabel, InputText, Button, DatePicker, AutoComplete, TableModule, InputNumber],
    providers: [DialogService]

})
export class TmcItemEditor implements OnInit {
    formGroup: FormGroup;
    warehouseItemTypes!: SelectListItemDto[];
    constructor(
        private ref: DynamicDialogRef,
        private warehouseItemTypeService: WarehouseItemTypeService,
        private fb: FormBuilder,

    ) {
        this.formGroup = fb.group({
            warehouseItemType: [undefined, Validators.required],
            quantity: [
                undefined,
                [
                    Validators.required,
                    Validators.pattern(/^[1-9]\d*$/) // ✅ Only natural numbers > 0
                ]
            ],
            price: [
                undefined,
                [
                    Validators.required,
                    Validators.pattern(/^(?!0*$)\d+(\.\d{1,2})?$/)
                ]
            ],
            totalPrice: [{ value: '', disabled: true }]
        });
    }

    ngOnInit(): void {
        this.formGroup.get('quantity')?.valueChanges.subscribe(() => this.updateTotalPrice());
        this.formGroup.get('price')?.valueChanges.subscribe(() => this.updateTotalPrice());
    }

    save() {
        if (this.formGroup.valid) {
            const formValue = this.formGroup.value;

            const newItem = {
                warehouseItemTypeTitle: formValue.warehouseItemType?.title,
                warehouseItemTypeId: formValue.warehouseItemType?.id ,
                quantity: formValue.quantity.toString(),
                price: formValue.price.toString()
            };
            this.ref.close(newItem);
        }
    }

     cancel() {
        this.ref.close(false);
     }


    completeWarehouseItemTypes(event: AutoCompleteCompleteEvent) {
        this.warehouseItemTypeService.findSimple(event.query).subscribe(res=>{
            this.warehouseItemTypes = res;
        });
    }

    clearSelection() {
        this.formGroup.controls["warehouseItemType"].setValue(undefined);
    }

    updateTotalPrice(): void {
        const quantity = parseFloat(this.formGroup.get('quantity')?.value);
        const price = parseFloat(this.formGroup.get('price')?.value);

        if (!isNaN(quantity) && quantity > 0 && !isNaN(price) && price > 0) {
            const total = quantity * price;
            this.formGroup.get('totalPrice')?.setValue(total.toFixed(2), { emitEvent: false });
        } else {
            this.formGroup.get('totalPrice')?.setValue('', { emitEvent: false });
        }
    }

}
