import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {Edition} from "../../app-model";

@Component({
  selector: 'app-editions',
  templateUrl: './editions.component.html',
  styleUrls: ['./editions.component.scss']
})
export class EditionsComponent implements OnInit {

  constructor(private http: HttpClient) { }

  editions : Edition[] = null;

  ngOnInit(): void {
    this.http.get(environment.backendUrl + "/edition")
      .subscribe(
        response => {
          this.editions = response as Array<Edition>;
        }
      );
  }
}
