// import { HttpClient } from '@angular/common/http';
// import { Component } from '@angular/core';

// @Component({
//   selector: 'app-registration',
//   templateUrl: './registration.component.html',
//   styleUrls: ['./registration.component.css']
// })
// export class RegistrationComponent {
//   user = {
//     loginId:Number,
//     userName: '',
//     firstName: '',
//     lastName: '',
//     password: '',
//     mobileNumber: '',
//     email: '',
//     role:''
//   };

//   constructor(private http: HttpClient) {}

//   onSubmit() {
//     // Send registration data to your backend using HttpClient
//     this.http.post('http://localhost:9090/register', this.user).subscribe(
//       (response) => {
//         console.log('Registration successful', response);
//         // Optionally, you can redirect to a login page or show a success message.
//       },
//       (error) => {
//         console.error('Registration failed', error);
//         // Handle registration failure, show an error message, etc.
//       }
//     );
//   }
// }

//-o--------
// import { Component } from '@angular/core';
// import { HttpClient } from '@angular/common/http';

// @Component({
//   selector: 'app-registration',
//   templateUrl: './registration.component.html',
//   styleUrls: ['./registration.component.css']
// })
// export class RegistrationComponent {
//   user = {
//     loginId: null,
//     userName: '',
//     firstName: '',
//     lastName: '',
//     password: '',
//     mobileNumber: '',
//     email: '',
//     role: ''
//   };

//   // Add error variables for each field
//   loginIdError: string | null = null;
//   userNameError: string | null = null;
//   firstNameError: string | null = null;
//   lastNameError: string | null = null;
//   passwordError: string | null = null;
//   mobileNumberError: string | null = null;
//   emailError: string | null = null;
//   roleError: string | null = null;

//   constructor(private http: HttpClient) {}

//   // Validation function for Login ID
//   validateLoginId(): void {
//     this.loginIdError = this.user.loginId !== null ? '' : 'Login ID is required';
//   }

//   // Validation function for User Name
//   validateUserName(): void {
//     this.userNameError = this.user.userName ? '' : 'User Name is required';
//   }

//   // Validation function for First Name
//   validateFirstName(): void {
//     this.firstNameError = this.user.firstName ? '' : 'First Name is required';
//   }

//   // Validation function for Last Name
//   validateLastName(): void {
//     this.lastNameError = this.user.lastName ? '' : 'Last Name is required';
//   }

//   // Validation function for Password
//   validatePassword(): void {
//     this.passwordError = this.user.password ? '' : 'Password is required';
//   }

//   // Validation function for Mobile Number
//   validateMobileNumber(): void {
//     this.mobileNumberError =
//       this.user.mobileNumber && /^[6-9]\d{9}$/.test(this.user.mobileNumber)
//         ? ''
//         : 'Invalid mobile number';
//   }

//   // Validation function for Email
//   validateEmail(): void {
//     this.emailError =
//       this.user.email && /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/.test(this.user.email)
//         ? ''
//         : 'Invalid email format';
//   }

//   // Validation function for Role
//   validateRole(): void {
//     this.roleError = this.user.role ? '' : 'Role is required';
//   }

//   // Function to check form validity
//   isFormValid(): boolean {
//     return (
//       this.user.loginId !== null &&
//       !!this.user.userName &&
//       !!this.user.firstName &&
//       !!this.user.lastName &&
//       !!this.user.password &&
//       !!this.user.mobileNumber &&
//       !!this.user.email &&
//       !!this.user.role &&
//       !this.loginIdError &&
//       !this.userNameError &&
//       !this.firstNameError &&
//       !this.lastNameError &&
//       !this.passwordError &&
//       !this.mobileNumberError &&
//       !this.emailError &&
//       !this.roleError
//     );
//   }

//   onSubmit(): void {
//     if (!this.isFormValid()) {
//       console.error('Form is not valid');
//       // Handle invalid form, e.g., show an error message
//       return;
//     }

//     // Form is valid, proceed with submitting the data to the server
//     // Replace 'http://localhost:9090/register' with your actual backend endpoint
//     this.http.post('http://localhost:9090/register', this.user).subscribe(
//       (response) => {
//         console.log('Registration successful', response);
//         // Optionally, you can redirect to a success page or show a success message.
//       },
//       (error) => {
//         console.error('Registration failed', error);
//         // Handle registration failure, show an error message, etc.
//       }
//     );
//   }
// }




//-oooo----






import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent {
  user = {
   // loginId: null,
    userName: '',
    firstName: '',
    lastName: '',
    password: '',
    mobileNumber: '',
    email: '',
    role: 'ROLE_CUSTOMER'
  };

  // Add error variables for each field
  //loginIdError: string | null = null;
  userNameError: string | null = null;
  firstNameError: string | null = null;
  lastNameError: string | null = null;
  passwordError: string | null = null;
  mobileNumberError: string | null = null;
  emailError: string | null = null;
  roleError: string | null = null;

  constructor(private http: HttpClient,private router:Router) {}

  // Validation function for Login ID
  // validateLoginId(): void {
  //   this.loginIdError = this.user.loginId !== null ? '' : 'Login ID is required';
  // }

  // Validation function for User Name
  validateUserName(): void {
    this.userNameError = this.user.userName ? '' : 'User Name is required';
  }

  // Validation function for First Name
  validateFirstName(): void {
    this.firstNameError = /^[a-zA-Z]+$/.test(this.user.firstName)
      ? ''
      : 'First Name should only contain alphabets';
  }

  // Validation function for Last Name
  validateLastName(): void {
    this.lastNameError = /^[a-zA-Z]+$/.test(this.user.lastName)
      ? ''
      : 'Last Name should only contain alphabets';
  }

  // Validation function for Password
  validatePassword(): void {
    this.passwordError =
      this.user.password && this.user.password.length >= 6 && this.user.password.length <= 10
        ? ''
        : 'Password must contain 6 to 10 characters';
  }

  // Validation function for Mobile Number
  validateMobileNumber(): void {
    this.mobileNumberError =
      this.user.mobileNumber && /^[6-9]\d{9}$/.test(this.user.mobileNumber)
        ? ''
        : 'Invalid mobile number';
  }

  // Validation function for Email
  validateEmail(): void {
    this.emailError =
      this.user.email && /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/.test(this.user.email)
        ? ''
        : 'Invalid email format';
  }

  // Validation function for Role
  validateRole(): void {
    this.roleError = this.user.role ? '' : 'Role is required';
  }

  // Function to check form validity
  isFormValid(): boolean {
    return (
     // this.user.loginId !== null &&
      !!this.user.userName &&
      !!this.user.firstName &&
      !!this.user.lastName &&
      !!this.user.password &&
      !!this.user.mobileNumber &&
      !!this.user.email &&
      !!this.user.role &&
     // !this.loginIdError &&
      !this.userNameError &&
      !this.firstNameError &&
      !this.lastNameError &&
      !this.passwordError &&
      !this.mobileNumberError &&
      !this.emailError &&
      !this.roleError
    );
  }

  onSubmit(): void {
    if (!this.isFormValid()) {
      console.error('Form is not valid');
      // Handle invalid form, e.g., show an error message
      return;
    }

    // Form is valid, proceed with submitting the data to the server
    // Replace 'http://localhost:9090/register' with your actual backend endpoint
    this.http.post('http://localhost:9090/register', this.user).subscribe(
      (response) => {
        alert('Registration successful');
        console.log('Registration successful', response);
        this.router.navigate(['/']);
        // Optionally, you can redirect to a success page or show a success message.
      },
      (error) => {
        alert(error.error.message)
        console.error('Registration failed', error);
        // Handle registration failure, show an error message, etc.
      }
    );
  }
}
