import { DialogService } from 'primeng/dynamicdialog';
import { TableLazyLoadEvent, TableModule } from 'primeng/table';
import { Component, OnInit } from '@angular/core';
import { ContractorService } from '../service/contractor.service';
import { Contractor } from 'src/interfaces/contractor';
import { FormsModule } from '@angular/forms';
import { Button } from "primeng/button";
import { ContractorEditorComponent } from '@/components/contractor-editor/contractor-editor.component';
import { InputText } from "primeng/inputtext";

@Component({
    selector: 'app-contractors',
    templateUrl: './contractors.component.html',
    styleUrls: ['./contractors.component.css'],
    imports: [TableModule, FormsModule, Button, InputText],
    providers: [DialogService]
})
export class ContractorsComponent {
    contractors!: Contractor[];
    totalRows!: number;
    query = '';
    first = 0;
    pageSize = 10;

    constructor(private service: ContractorService,
        private dialogService: DialogService
    ) {}

    loadData(event: TableLazyLoadEvent) {
        if (event.first !== undefined) {
            this.first = event.first;
        }
        if (event.rows != null && event.rows !== 0) {
            this.pageSize = event.rows;
        }
        const pageNumber = this.first / this.pageSize;
        this.service.find(this.query, pageNumber, this.pageSize).subscribe((res) => {
            this.contractors = res.content;
            this.totalRows = res.page.totalElements;
            if (event.forceUpdate) event.forceUpdate();
        });
    }
    edit(contractorId?: number) {
        this.dialogService.open(ContractorEditorComponent, {
            width: '50vw',
            modal:true,
            breakpoints: {
                '960px': '75vw',
                '640px': '90vw'
            },
            data: {
                contractorId
            }
        }).onClose.subscribe((res)=>{
            if(res) {
                this.loadData({});
            }
        });

    }

    delete(id: number) {
        this.service.delete(id).subscribe(() => {
            let event = {};
            this.loadData(event);
        });
    }
}
