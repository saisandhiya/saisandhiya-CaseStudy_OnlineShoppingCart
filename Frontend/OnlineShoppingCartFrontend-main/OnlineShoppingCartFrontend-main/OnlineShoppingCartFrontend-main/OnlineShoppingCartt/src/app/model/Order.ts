import { Address } from "./address";

export interface Order {
    orderDate: string;
    customerName: string;
    amountPaid: number;
    modeOfPayment: string;
    orderStatus: string;
    quantity: number;
    address: Address;
  }
  