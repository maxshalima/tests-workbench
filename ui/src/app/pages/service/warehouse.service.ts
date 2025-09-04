import { environment } from 'src/environments/environment';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AbstractCrudService } from 'src/services/AbstractCrudService';
import { Warehouse } from 'src/interfaces/Warehouse';
import { Observable } from 'rxjs';
import { PageModel } from 'src/interfaces/PageModel';
@Injectable({
    providedIn: 'root'
})
export class WarehouseService extends AbstractCrudService<Warehouse> {
    readonly _subUrl: string;

    constructor(protected override http: HttpClient) {
        super(http);
        this._subUrl = `${environment.apiBaseUrl}/warehouses`;
    }

    protected override entityUrl(): string {
        return this._subUrl;
    }

    find(page?: number, size?: number): Observable<PageModel<Warehouse>> {
        let params = new HttpParams();
        if (page) {
            params.set('page', page);
        }
        if (size) {
            params.set('size', size);
        } else {
            params.set('size', 1000);
        }

        return this.http.get<PageModel<Warehouse>>(this._subUrl, {
            params
        });
    }
}
