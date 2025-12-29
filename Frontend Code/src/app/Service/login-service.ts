import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  
  private baseUrl = 'http://localhost:8080/auth';

  constructor(private http: HttpClient) {}

  register(user: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/register`, user, {
      responseType: 'text'
    });
  }

  login(data: any) {
    return this.http.post<any>(`${this.baseUrl}/login`, {
      email: data.email,
      password: data.password
    });
  }

  isAdmin(): boolean {
    return localStorage.getItem('role') === 'ADMIN';
  }

  isEmployee(): boolean {
    return localStorage.getItem('role') === 'USER';
  }

  logout() {
    localStorage.clear();
  }
}