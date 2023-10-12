export interface CartItem { 
    productId: number; 
    productName: string;
    quantity: number; 
    image: string;   // Add any other properties you need for a cart item 
    }
export interface Cart{  
    cartId: number;
    items: CartItem[]; // An array of cart items  
    totalPrice: number; // Total price of all items in the cart
}