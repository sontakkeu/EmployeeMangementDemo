import { Component } from '@angular/core';
import { LoginService } from '../../Service/login-service';
import { Router } from '@angular/router';



@Component({
  selector: 'app-login-component',
  standalone: false,
  templateUrl: './login-component.html',
  styleUrl: './login-component.css',
})
export class LoginComponent {
 
  
  showPassword = false;

  constructor( private loginservice:LoginService, private router:Router) {}
  isLogin = true;

  // Login
  loginData = {
    email: '',
    password: ''
  };

  // Register
  registerData = {
    name: '',
    email: '',
    password: ''
  };

  toggleForm() {
    this.isLogin = !this.isLogin;
  }
  togglePassword() {
    this.showPassword = !this.showPassword;
  }

  login() {
    this.loginservice.login(this.loginData).subscribe({
      next: (res) => {
        localStorage.setItem('role', res.role);
        localStorage.setItem('userId', res.id);
  
        if (res.role === 'ADMIN') {
          this.router.navigate(['/admin']);
        } else {
          this.router.navigate(['/employee']);
        }
      },
      error: () => alert('Invalid Credentials')
    });
  }
  

  
 
}
