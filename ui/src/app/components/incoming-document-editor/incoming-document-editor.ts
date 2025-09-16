import { DynamicDialogRef, DynamicDialogConfig } from 'primeng/dynamicdialog';
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

@Component({
    selector: 'app-incoming-document-editor',
    templateUrl: './incoming-document-editor.html',
    styleUrl: './incoming-document-editor.scss',
    imports: [FormsModule, ReactiveFormsModule, FloatLabel, InputText, Button, DatePicker, AutoComplete]
})
export class IncomingDocumentEditor implements OnInit {
    incomingDocumentId: number | undefined;
    formGroup: FormGroup;
    contractors!: SelectListItemDto[];
    contracts!: SelectListItemDto[];

    constructor(
        private service: IncomingDocumentsService,
        private ref: DynamicDialogRef,
        private config: DynamicDialogConfig,
        private contractService: ContractService,
        private contractorService: ContractorService,
        private fb: FormBuilder
    ) {
        this.formGroup = fb.group({
            contractor: [undefined, Validators.required],
            contract: [undefined, Validators.required],
            documentNumber: [undefined, Validators.required],
            documentDate: [undefined, Validators.required],
            warehouse: [undefined],
            description: [undefined]
        });
    }

    ngOnInit(): void {
        this.incomingDocumentId = this.config.data.incomingDocumentId;
        if (this.incomingDocumentId) {
            this.service.findById(this.incomingDocumentId).subscribe((w) => {
                this.formGroup.patchValue(w);
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

    clearSelection() {
        this.formGroup.controls["contractor"].setValue(undefined);
    }
}
