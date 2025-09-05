import { ContractorService } from '@/pages/service/contractor.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { DynamicDialogConfig, DynamicDialogRef } from 'primeng/dynamicdialog';
import { Contractor } from 'src/interfaces/contractor';
import { Button } from 'primeng/button';
import { FloatLabel } from 'primeng/floatlabel';
import { InputText } from 'primeng/inputtext';

@Component({
    selector: 'app-contractor-editor',
    templateUrl: './contractor-editor.component.html',
    styleUrls: ['./contractor-editor.component.css'],
    imports: [FormsModule, ReactiveFormsModule, Button, FloatLabel, InputText]
})
export class ContractorEditorComponent implements OnInit {
    contractorId!: number;
    fromGroup: FormGroup;
    constructor(
        private config: DynamicDialogConfig,
        private ref: DynamicDialogRef,
        private service: ContractorService,
        private fb: FormBuilder
    ) {
        this.fromGroup = fb.group({
            taxNumber: [undefined, Validators.required],
            title: [undefined, Validators.required],
            shortTitle: [undefined, Validators.required],
            address: [undefined],
            email: [undefined, Validators.email],
            phone: [undefined],
            contactPerson: [undefined, Validators.required]
        });
    }

    ngOnInit() {
        this.contractorId = this.config.data.contractorId;
        if (this.contractorId) {
            this.service.findById(this.contractorId).subscribe((c) => {
                this.fromGroup.patchValue(c);
            });
        }
    }

    save() {
        const c = this.fromGroup.value as Contractor;
        if (this.contractorId) {
            this.service.update(this.contractorId, c).subscribe((res) => {
                this.ref.close(true);
            });
        } else {
            this.service.create(c).subscribe((res) => {
                this.ref.close(true);
            });
        }
    }

    cancel() {
        this.ref.close(false);
    }
}
