export interface Page {
    size: number;
    number: number;
    totalElements: number;
    totalPages: number;
}

export interface PageModel<T> {
    content: T[];
    page: Page;
}
