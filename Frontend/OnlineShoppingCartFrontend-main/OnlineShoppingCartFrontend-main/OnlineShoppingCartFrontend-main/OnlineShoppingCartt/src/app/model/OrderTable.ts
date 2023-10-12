// Order interface
export interface OrderTable {
    orderId: number;
    orderDate: string;
    customerName: string;
    amountPaid: number;
    modeOfPayment: string;
    orderStatus: string;
    quantity: number;
    address: {
      customerId: number;
      customerName: string;
      fullName: string;
      mobileNumber: string;
      flatNumber: number;
      city: string;
      pincode: number;
      state: string;
    };
    cart: {
      cartid: number;
      totalPrice: number;
      product: any; // Change this to the appropriate type
    };
    productId: number;
  }
  
 
  