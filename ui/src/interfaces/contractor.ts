import { BaseDto } from 'src/interfaces/base-dto';
export interface Contractor extends BaseDto {
    taxNumber: string;
    title: string;
    shortTitle: string;
    address?: string;
    phone?: string;
    email?: string;
    contractPerson: string;
}
