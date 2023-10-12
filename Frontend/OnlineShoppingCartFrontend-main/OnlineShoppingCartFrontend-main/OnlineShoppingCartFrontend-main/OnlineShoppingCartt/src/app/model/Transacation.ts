export interface Transaction {
    transactionId: number;
    cartId: number;
    userName: string;
    amount: number;
    transactionStatus: string;
    paymentMode: string;
  }