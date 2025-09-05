import { Routes } from '@angular/router';
import { Documentation } from './documentation/documentation';
import { Crud } from './crud/crud';
import { Empty } from './empty/empty';
import { Warehouses } from './warehouses/warehouses';
import { ItemTypesComponent } from './item-types/item-types.component';
import { ContractorsComponent } from './contractors/contractors.component';
import { ContractsComponent } from './contracts/contracts.component';

export default [
    { path: 'documentation', component: Documentation },
    { path: 'crud', component: Crud },
    { path: 'empty', component: Empty },
    { path: 'warehouses', component: Warehouses },
    { path: 'item-types', component: ItemTypesComponent },
    { path: 'contractors', component: ContractorsComponent},
    { path: 'contracts', component: ContractsComponent},
    { path: '**', redirectTo: '/notfound' }
] as Routes;
