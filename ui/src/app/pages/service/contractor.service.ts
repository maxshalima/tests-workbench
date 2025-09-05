import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Contractor } from 'src/interfaces/contractor';
import { PageModel } from 'src/interfaces/page-model';
import { SelectListItemDto } from 'src/interfaces/select-list-item-dto';
import { AbstractCrudService } from 'src/services/AbstractCrudService';

@Injectable({
    providedIn: 'root'
})
export class ContractorService extends AbstractCrudService<Contractor> {
    private readonly _subUrl: string;
    constructor(protected override http: HttpClient) {
        super(http);
        this._subUrl = environment.apiBaseUrl + '/contractors';
    }
    protected override entityUrl(): string {
        return this._subUrl;
    }

    public find(query = '', page?: number, size?: number): Observable<PageModel<Contractor>> {
        let params = new HttpParams();
        if (query) {
            params = params.set('query', query);
        }
        if (page) {
            params = params.append('page', page);
        }
        if (size) {
            params = params.append('size', size);
        } else {
            params = params.append('size', 1000);
        }

        return this.http.get<PageModel<Contractor>>(this._subUrl, { params });
    }

    public findSimple(query = ''): Observable<SelectListItemDto[]> {
        let params = new HttpParams();
        if (query) {
            params = params.set('query', query);
        }
        return this.http.get<SelectListItemDto[]>(this._subUrl + '/simple', { params });
    }
}
