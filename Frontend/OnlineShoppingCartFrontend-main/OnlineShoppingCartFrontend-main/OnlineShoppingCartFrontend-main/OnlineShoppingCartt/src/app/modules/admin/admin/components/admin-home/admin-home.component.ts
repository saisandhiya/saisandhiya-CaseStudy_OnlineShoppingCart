import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { AdminService } from './admin.service';

@Component({
  selector: 'app-admin-home',
  templateUrl: './admin-home.component.html',
  styleUrls: ['./admin-home.component.css']
})
export class AdminHomeComponent {


  constructor(private adminService:AdminService){}
  productForm: FormGroup;


  ngOnInit() {
    this.productForm = new FormGroup({
      productType: new FormControl('', [Validators.required]),
      productName: new FormControl('',[Validators.required]),
      category: new FormControl('', [Validators.required]),
      rating: new FormControl('', [Validators.required ,Validators.pattern('[1-5]')]),
      review: new FormControl('', [Validators.required]),
      image: new FormControl('',[Validators.required]),
      price: new FormControl('', [Validators.required, Validators.pattern(/^\d+(\.\d{1,2})?$/)]),
      description: new FormControl('', [Validators.required]),
      specification: new FormControl('', [Validators.required]),
    });
  }


  onSubmit() {
    if (this.productForm.valid) {
      this.adminService.addProduct(this.productForm.value).subscribe(
        res => {
          alert("Product addedd");
          location.reload();
        },
        err => {
          console.log(err);
          
        }
      )
      
    } else {
      // Handle form validation errors
      console.log('Form is invalid. Please fill all the required fields.');
    }
  }

}
