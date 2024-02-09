// src/app/register/register.component.ts

import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  user = { username: '', password: '', role: 'ROLE_USER' }; // assuming 'ROLE_USER' is the default role

  constructor(private http: HttpClient, private router: Router) {}

  register() {
    this.http.post<any>('localhost:8080/api/auth/register', this.user)
      .subscribe(response => {
        this.router.navigate(['/login']);
      }, error => {
        console.error('Registration failed', error);
      });
  }
}
