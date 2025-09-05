import { Component, OnInit } from '@angular/core';
import { DialogService } from 'primeng/dynamicdialog';
import { ContractService } from '../service/contract.service';
import { Contract } from 'src/interfaces/contract';
import { ContractEditorComponent } from '@/components/contract-editor/contract-editor.component';
import { TableLazyLoadEvent, TableModule } from 'primeng/table';
import { Button } from "primeng/button";
import { FormsModule } from '@angular/forms';
import { InputText } from "primeng/inputtext";
import { AutoCompleteCompleteEvent, AutoCompleteModule } from 'primeng/autocomplete';
import { SelectListItemDto } from 'src/interfaces/select-list-item-dto';
import { ContractorService } from '../service/contractor.service';
import { StyleClass } from "primeng/styleclass";

@Component({
    selector: 'app-contracts',
    templateUrl: './contracts.component.html',
    styleUrls: ['./contracts.component.css'],
    providers: [DialogService],
    imports: [Button, TableModule, FormsModule, InputText, AutoCompleteModule]
})
export class ContractsComponent {
    data!: Contract[];
    totalRows!: number;
    query = '';
    first = 0;
    pageSize = 10;
    contractor: SelectListItemDto|undefined;
    contractors!: SelectListItemDto[];
    constructor(
        private service: ContractService,
        private dialogService: DialogService,
        private contractorService: ContractorService
    ) {}

    loadData(event: TableLazyLoadEvent) {
        if (event.first !== undefined) {
            this.first = event.first;
        }
        if (event.rows != null && event.rows !== 0) {
            this.pageSize = event.rows;
        }
        const pageNumber = this.first / this.pageSize;
        this.service.find(this.query, this.contractor?.id, pageNumber, this.pageSize).subscribe((res) => {
            this.data = res.content;
            this.totalRows = res.page.totalElements;
            if (event.forceUpdate) event.forceUpdate();
        });
    }

    edit(contractId?: number) {
        this.dialogService
            .open(ContractEditorComponent, {
                width: '50vw',
                modal: true,
                breakpoints: {
                    '960px': '75vw',
                    '640px': '90vw'
                },
                data: {
                    contractId
                }
            })
            .onClose.subscribe((res) => {
                if (res) {
                    this.loadData({});
                }
            });
    }

    delete(id: number) {
        this.service.delete(id).subscribe(() => {
            this.loadData({});
        });
    }

    completeContractors(event: AutoCompleteCompleteEvent) {
        this.contractorService.findSimple(event.query).subscribe(res=>{
            this.contractors = res;
        });
    }

    clearSelection() {
        this.contractor = undefined;
        this.loadData({});
    }
}
