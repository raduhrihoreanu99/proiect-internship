import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PatientControllerService } from 'src/app/api/services';

@Component({
  selector: 'app-patients',
  templateUrl: './patients.component.html',
  styleUrls: ['./patients.component.scss']
})
export class PatientsComponent implements OnInit {

  constructor(
    private http: HttpClient,
    private router: Router,
    private patients: PatientControllerService) { }

  ngOnInit(): void {
    // this.patients.getAllPatientsUsingGET().subscribe(p => console.log(p));
  }

  loadPatients() {
    this.patients.getAllPatientsUsingGET().subscribe(p => console.log(p));
  }

  logout() {
    this.http.post("http://localhost:8001/logout", {}, {withCredentials: true}).subscribe(() => {
      console.log('Logout Success!');
      this.router.navigate(['/']);
    },
    () => {
      this.router.navigate(['/']);
    });
  }

}
