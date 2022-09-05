import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, Subject } from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'login-app';
  showRegister = false;
  showError = false;

  username: String = '';
  password: String = '';

  constructor(private http: HttpClient, private router: Router) {}

  login() {
    const formData = new FormData();
    formData.append('username', this.username.toString());
    formData.append('password', this.password.toString());

    this.http.post('http://localhost:8001/login', formData, { responseType: 'text', observe: 'response', withCredentials: true }).subscribe((r) => {
      console.log("Login Success!");
      this.router.navigate(['/patients']);
    },
    error => {
      
      this.showError = true;
    });
  }

  showLogin() {
    return !window.location.href.includes('patients');
  }
}
