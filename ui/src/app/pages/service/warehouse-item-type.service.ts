import { AbstractCrudService } from 'src/services/AbstractCrudService';
import { Injectable } from '@angular/core';
import { WarehouseItemType } from 'src/interfaces/warehouse-item-type';
import { HttpClient, HttpParams } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { PageModel } from 'src/interfaces/page-model';

@Injectable({
    providedIn: 'root'
})
export class WarehouseItemTypeService extends AbstractCrudService<WarehouseItemType> {
    readonly _subUrl: string;
    constructor(protected override http: HttpClient) {
        super(http);
        this._subUrl = environment.apiBaseUrl + '/item-types';
    }

    protected override entityUrl(): string {
        return this._subUrl;
    }

    public find(query = '', page?: number, size?:number) {
        let params = new HttpParams();
        if(query) {
            params = params.append('query', query);
        }
        if (page!==undefined) {
            params = params.append('page', page);
        }
        if (size!==undefined) {
            params = params.append('size', size);
        } else {
            params = params.append('size', 100);
        }

        return this.http.get<PageModel<WarehouseItemType>>(this._subUrl, {
            params
        });
    }
}
