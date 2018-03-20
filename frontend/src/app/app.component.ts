import { Component } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Demo';
  greeting = {};

  constructor(private http: HttpClient) {
    http.get('http://localhost:8080/').subscribe(data => this.greeting = data);
  }
}
