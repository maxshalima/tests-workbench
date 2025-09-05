import { Component, OnInit } from '@angular/core';
import { ContractorService } from '../service/contractor.service';
import { PageModel } from 'src/interfaces/page-model';
import { Contractor } from 'src/interfaces/contractor';

@Component({
    selector: 'app-contractors',
    templateUrl: './contractors.component.html',
    styleUrls: ['./contractors.component.css']
})
export class ContractorsComponent implements OnInit {
    data: PageModel<Contractor>|undefined;
    query = '';
    constructor(private service: ContractorService) {}

    ngOnInit() {}

    loadContractors() {

    }
}
