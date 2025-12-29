import { AddressModel } from "./address-model";

export interface EmployeeModel {
    id?:number;
    name:string;
    email:string;
    number:string;
    password:string;
     accountNo: string;
     image? : string;
     role? : string;
     addresses: AddressModel[]

}
