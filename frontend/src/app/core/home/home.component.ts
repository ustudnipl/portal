import { Component, OnInit } from '@angular/core';
import {Store} from "@ngrx/store";
import * as fromApp from "../../store/app.reducers";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  constructor(private store: Store<fromApp.AppState>) { }

  appState: fromApp.AppState;

  ngOnInit() {
    this.store.take(1).subscribe(s => this.appState = s);
  }

}
