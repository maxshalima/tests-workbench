import { ContractService } from '@/pages/service/contract.service';
import { ContractorService } from '@/pages/service/contractor.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AutoCompleteCompleteEvent, AutoComplete } from 'primeng/autocomplete';
import { DynamicDialogConfig, DynamicDialogRef } from 'primeng/dynamicdialog';
import { Contract } from 'src/interfaces/contract';
import { SelectListItemDto } from 'src/interfaces/select-list-item-dto';
import { FloatLabel } from "primeng/floatlabel";
import { InputText } from "primeng/inputtext";
import { Button } from "primeng/button";
import { DatePicker } from "primeng/datepicker";
import { EditorModule } from 'primeng/editor';
@Component({
    selector: 'app-contract-editor',
    templateUrl: './contract-editor.component.html',
    styleUrls: ['./contract-editor.component.css'],
    imports: [FloatLabel, InputText, Button, FormsModule, ReactiveFormsModule, AutoComplete, DatePicker, EditorModule]
})
export class ContractEditorComponent implements OnInit {
    contractId!: number;
    formGroup: FormGroup;
    contractors!: SelectListItemDto[];

    constructor(
        private config: DynamicDialogConfig,
        private ref: DynamicDialogRef,
        private service: ContractService,
        private contractorService: ContractorService,
        private fb: FormBuilder
    ) {
        this.formGroup = fb.group({
            contractor: [undefined, Validators.required],
            contractNumber: [undefined, Validators.required],
            contractDate: [undefined, Validators.required],
            description: [undefined]
        });
    }

    ngOnInit() {
        this.contractId = this.config.data.contractId;
        if(this.contractId) {
            this.service.findById(this.contractId).subscribe(c=>{
                this.formGroup.patchValue(c);
            });
        }
    }

    save() {
        const c = this.formGroup.value as Contract;
        if (this.contractId) {
            this.service.update(this.contractId, c).subscribe(()=>{
                this.ref.close(true);
            });
        } else {
            this.service.create(c).subscribe(()=>{
                this.ref.close(true);
            });
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
