import { Routes } from '@angular/router';
import { Documentation } from './documentation/documentation';
import { Crud } from './crud/crud';
import { Empty } from './empty/empty';
import { Warehouses } from './warehouses/warehouses';

export default [
    { path: 'documentation', component: Documentation },
    { path: 'crud', component: Crud },
    { path: 'empty', component: Empty },
    { path: 'warehouses', component: Warehouses },
    { path: '**', redirectTo: '/notfound' }
] as Routes;
