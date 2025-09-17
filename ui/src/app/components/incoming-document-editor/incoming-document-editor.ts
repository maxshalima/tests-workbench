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
import {TmcItemEditor} from "@/components/tmc-item-editor/tmc-item-editor";
import {IncomingDocumentItemDto} from "../../../interfaces/tmc-list-item-dto";


@Component({
    selector: 'app-incoming-document-editor',
    templateUrl: './incoming-document-editor.html',
    styleUrl: './incoming-document-editor.scss',
    imports: [FormsModule, ReactiveFormsModule, FloatLabel, InputText, Button, DatePicker, AutoComplete, TableModule],
    providers: [DialogService]

})
export class IncomingDocumentEditor implements OnInit {
    incomingDocumentId: number | undefined;
    formGroup: FormGroup;
    contractors!: SelectListItemDto[];
    contracts!: SelectListItemDto[];
    warehouse!: SelectListItemDto;
    tmsItems: IncomingDocumentItemDto[] = [];

    constructor(
        private service: IncomingDocumentsService,
        private ref: DynamicDialogRef,
        private config: DynamicDialogConfig,
        private contractService: ContractService,
        private contractorService: ContractorService,
        private fb: FormBuilder,
        private dialogService: DialogService

    ) {
        this.formGroup = fb.group({
            contractor: [undefined, Validators.required],
            contract: [undefined, Validators.required],
            documentNumber: [undefined, Validators.required],
            documentDate: [undefined, Validators.required],
            warehouse: [undefined],
            tmsItems: this.fb.array([])
        });
    }

    ngOnInit(): void {
        this.incomingDocumentId = this.config.data.incomingDocumentId;

        if (this.incomingDocumentId) {
            this.service.findById(this.incomingDocumentId).subscribe((incomingDocumentData) => {
                if (incomingDocumentData.documentDate) {
                    incomingDocumentData.documentDate = new Date(incomingDocumentData.documentDate); // Convert string to Date
                }

                this.tmsItems = incomingDocumentData.items || [];

                //✅ Initialize FormArray for tmsItems
                const itemsArray = this.fb.array(
                    this.tmsItems.map(item => this.fb.group({
                        id: [item.id],
                        warehouseItemTypeId: [item.warehouseItemTypeId],
                        // warehouseItemTypeTitle: [item.warehouseItemTypeTitle],
                        name: [item.name],
                        quantity: [item.quantity],
                        price: [item.price],
                        sum: [item.sum]
                    }))
                );
                this.formGroup.setControl('tmsItems', itemsArray);

                this.formGroup.patchValue(incomingDocumentData);
            });
        }
    }

    save() {
        let incomingDocument = this.formGroup.value as IncomingDocument;
        incomingDocument.enabled = true;
        if (this.incomingDocumentId) {
            this.service.update(this.incomingDocumentId, incomingDocument).subscribe(()=>{
                this.ref.close(true);
            });
        } else {
            this.service.create(incomingDocument).subscribe((res)=>{
                this.ref.close(true);
            })
        }
    }

     cancel() {
        this.ref.close(false);
     }


    completeContractors(event: AutoCompleteCompleteEvent) {
        this.contractorService.findSimple(event.query).subscribe(res=>{
            this.contractors = res;
        });
    }

    completeContracts(event: AutoCompleteCompleteEvent) {
        this.contractService.findSimple(event.query).subscribe(res=>{
            this.contracts = res;
        });
    }

    clearSelection() {
        this.formGroup.controls["contractor"].setValue(undefined);
    }
    clearContractSelection() {
        this.formGroup.controls["contract"].setValue(undefined);
    }

    /////////////////////////////////////



    removeTmcItem() {
    }

    addTmcItemDialog() {
        this.dialogService.open(TmcItemEditor, {
            width: '100vw',
            modal: true,
            breakpoints: {
                '960px': '75vw',
                '640px': '90vw'
            },
            data: {
                // pass any initial data if needed
            }
        }).onClose.subscribe((res: IncomingDocumentItemDto | null) => {
            if (res) {
                this.updateTmsArray(res);
            }
        });
    }

    updateTmsArray(newItem: IncomingDocumentItemDto) {
        this.tmsItems = [...this.tmsItems, newItem]; // ✅ triggers change detection
    }

    calculateSum(quantity: string, price: string): string {
        const q = parseFloat(quantity);
        const p = parseFloat(price);
        if (!isNaN(q) && !isNaN(p) && q > 0 && p > 0) {
            return (q * p).toFixed(2);
        }
        return '0.00';
    }


}
