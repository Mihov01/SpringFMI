// src/app/login/login.component.ts

import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  credentials = { username: '', password: '' };

  constructor(private http: HttpClient, private router: Router) {}

  login() {
    this.http.post<any>('localhost:8080/api/auth/login', this.credentials)
      .subscribe(response => {
        localStorage.setItem('token', response.token);
        this.router.navigate(['/']);
      }, error => {
        console.error('Login failed', error);
      });
  }
}
