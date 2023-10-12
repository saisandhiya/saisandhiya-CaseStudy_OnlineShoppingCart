import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {
  private baseUrl = 'http://localhost:9090/customer/access'; // Replace with your actual backend API base URL

  constructor(private http: HttpClient) {}

  registerUser(user: any): Observable<any> {
    const registrationUrl = `${this.baseUrl}/your-registration-endpoint`; // Replace with your registration endpoint
    return this.http.post(registrationUrl, user);
  }
}
