import {Component} from '@angular/core';
import {HttpClient} from "@angular/common/http";

interface Message {
  id: String;
  content: String
}

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Demo';
  greeting: Message;

  constructor(private http: HttpClient) {
    http.get('http://localhost:8080/').subscribe((data: Message) => this.greeting = data);
  }
}
