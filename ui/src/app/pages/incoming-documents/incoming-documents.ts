import { Component, OnInit } from '@angular/core';
import { PageModel } from 'src/interfaces/page-model';
import { TableModule } from 'primeng/table';
import { Checkbox } from 'primeng/checkbox';
import { FormsModule } from '@angular/forms';
import { DialogService } from 'primeng/dynamicdialog';
import { Button } from "primeng/button";
import { Tooltip } from "primeng/tooltip";
import { InputText } from "primeng/inputtext";
import {IncomingDocumentsService} from "@/pages/service/incoming-documents.service";
import {IncomingDocument} from "../../../interfaces/incoming-document";
import {IncomingDocumentEditor} from "@/components/incoming-document-editor/incoming-document-editor";

@Component({
    selector: 'app-incoming-documents',
    imports: [TableModule, Checkbox, FormsModule, Button, Tooltip, InputText],
    templateUrl: './incoming-documents.html',
    styleUrl: './incoming-documents.scss',
    providers: [DialogService]
})
export class IncomingDocuments implements OnInit {
    data: PageModel<IncomingDocument> | undefined;
    query: string;
    constructor(
        private service: IncomingDocumentsService,
        private dialogService: DialogService
    ) {
        this.query = '';
    }

    ngOnInit(): void {
        this.loadIncomingDocuments();
    }

    loadIncomingDocuments() {
        this.service.find(this.query).subscribe((model) => {
            this.data = model;
        });
    }

    switch(incomingDocumentId: number) {
        this.service.switchWarehouse(incomingDocumentId).subscribe(()=>{
            this.loadIncomingDocuments();
        })
    }

    edit(incomingDocumentId?: number) {
        this.dialogService.open(IncomingDocumentEditor, {
            width: '50vw',
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
                this.loadIncomingDocuments();
            }
        });
    }
}
