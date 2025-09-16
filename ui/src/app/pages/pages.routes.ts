import { Routes } from '@angular/router';
import { Empty } from './empty/empty';
import { Warehouses } from './warehouses/warehouses';
import { ItemTypesComponent } from './item-types/item-types.component';
import { ContractorsComponent } from './contractors/contractors.component';
import { ContractsComponent } from './contracts/contracts.component';
import {IncomingDocuments} from "./incoming-documents/incoming-documents";

export default [
    { path: 'empty', component: Empty },
    { path: 'warehouses', component: Warehouses },
    { path: 'item-types', component: ItemTypesComponent },
    { path: 'contractors', component: ContractorsComponent},
    { path: 'contracts', component: ContractsComponent},
    { path: 'incoming-documents', component: IncomingDocuments},
    { path: '**', redirectTo: '/notfound' }
] as Routes;
