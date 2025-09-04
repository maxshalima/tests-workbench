import { map } from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BaseDto } from 'src/interfaces/base-dto';

export abstract class AbstractCrudService<T extends BaseDto> {
    constructor(protected http: HttpClient) {}

    protected abstract entityUrl(): string;

    public findById(id: number): Observable<T> {
        return this.http.get<T>(`${this.entityUrl()}/${id}`);
    }

    public create(entity: T): Observable<number> {
        return this.http.post<number>(`${this.entityUrl()}`, entity);
    }

    public update(entityId: number, entity: T): Observable<void> {
        return this.http.put<void>(`${this.entityUrl()}/${entityId}`, entity);
    }

    public delete(entityId: number): Observable<void> {
        return this.http.delete<void>(`${this.entityUrl()}/${entityId}`);
    }
}
