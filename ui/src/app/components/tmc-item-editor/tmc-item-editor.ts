import {DynamicDialogRef, DynamicDialogConfig, DialogService} from 'primeng/dynamicdialog';
import { IncomingDocumentsService } from '@/pages/service/incoming-documents.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { IncomingDocument } from 'src/interfaces/incoming-document';

import { FloatLabel } from "primeng/floatlabel";
import { InputText } from "primeng/inputtext";
import { Button } from "primeng/button";
import {DatePicker} from "primeng/datepicker";
import {AutoComplete, AutoCompleteCompleteEvent} from "primeng/autocomplete";
import {ContractService} from "@/pages/service/contract.service";
import {ContractorService} from "@/pages/service/contractor.service";
import {SelectListItemDto} from "../../../interfaces/select-list-item-dto";
import { TableModule } from 'primeng/table';
import {WarehouseItemTypeService} from "@/pages/service/warehouse-item-type.service";


@Component({
    selector: 'tmc-item-editor-editor',
    templateUrl: './tmc-item-editor.html',
    styleUrl: './tmc-item-editor.scss',
    imports: [FormsModule, ReactiveFormsModule, FloatLabel, InputText, Button, DatePicker, AutoComplete, TableModule],
    providers: [DialogService]

})
export class TmcItemEditor implements OnInit {
    formGroup: FormGroup;
    warehouseItemTypes!: SelectListItemDto[];


    tmcTable = [
        {
            code: 'P001',
            name: 'Wireless Mouse',
            category: 'Electronics',
            quantity: 25
        },
        {
            code: 'P002',
            name: 'Notebook A5',
            category: 'Stationery',
            quantity: 100
        },
        {
            code: 'P003',
            name: 'Coffee Mug',
            category: 'Kitchenware',
            quantity: 40
        },
        {
            code: 'P004',
            name: 'USB-C Cable',
            category: 'Accessories',
            quantity: 60
        },
        {
            code: 'P005',
            name: 'Desk Lamp',
            category: 'Furniture',
            quantity: 15
        }
    ];


    constructor(
        private service: IncomingDocumentsService,
        private ref: DynamicDialogRef,
        private config: DynamicDialogConfig,
        private warehouseItemTypeService: WarehouseItemTypeService,
        private fb: FormBuilder,
        private dialogService: DialogService

    ) {
        this.formGroup = fb.group({
            contractor: [undefined, Validators.required],
            contract: [undefined, Validators.required],
            documentNumber: [undefined, Validators.required],
            documentDate: [undefined, Validators.required],
            warehouse: [undefined]
        });
    }

    ngOnInit(): void {
        // this.incomingDocumentId = this.config.data.incomingDocumentId;
        // if (this.incomingDocumentId) {
        //     this.service.findById(this.incomingDocumentId).subscribe((incomingDocumentData) => {
        //         if (incomingDocumentData.documentDate) {
        //             incomingDocumentData.documentDate = new Date(incomingDocumentData.documentDate); // Convert string to Date
        //         }
        //         this.formGroup.patchValue(incomingDocumentData);
        //     });
        // }
    }

    save() {
        // let incomingDocument = this.formGroup.value as IncomingDocument;
        // incomingDocument.enabled = true;
        // if (this.incomingDocumentId) {
        //     this.service.update(this.incomingDocumentId, incomingDocument).subscribe(()=>{
        //         this.ref.close(true);
        //     });
        // } else {
        //     this.service.create(incomingDocument).subscribe((res)=>{
        //         this.ref.close(true);
        //     })
        // }
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

    removeTmcItem() {
    }

    addTmcItem(incomingDocumentId?: number) {
        this.dialogService.open(TmcItemEditor, {
            width: '100vw',
            modal:true,
            breakpoints: {
                '960px': '75vw',
                '640px': '90vw'
            },
            data: {
                incomingDocumentId
            }
        }).onClose.subscribe((res)=>{
            if(res) {
                this.updateTmsArray();
            }
        });
    }

    updateTmsArray() {

    }

}
