import { BaseDto } from './base-dto';
import {SelectListItemDto} from "./select-list-item-dto";
import {TmcTableItemDto} from "./tmc-list-item-dto";

export interface IncomingDocument extends BaseDto {
    documentDate: Date;
    documentNumber: number;
    contractor: SelectListItemDto;
    contract: SelectListItemDto;
    warehouse: SelectListItemDto;
    tmsItems: TmcTableItemDto[];

    enabled: boolean;
}
