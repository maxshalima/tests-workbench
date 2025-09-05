import { BaseDto } from "./base-dto";
import { SelectListItemDto } from "./select-list-item-dto";

export interface Contract extends BaseDto {
    contractDate: Date;
    contractNumber: number;
    description?: string;
    contractor: SelectListItemDto;
}
