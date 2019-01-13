import { Component, OnInit } from '@angular/core';
import {Store} from "@ngrx/store";
import {Router} from '@angular/router';
import * as fromApp from "../../store/app.reducers";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  appState: fromApp.AppState;

  constructor(private store: Store<fromApp.AppState>,
              private router: Router) {
    router.events.subscribe(val => this.refresh());
  }

  ngOnInit() {
    this.refresh();
  }

  refresh() {
    this.store.take(1).subscribe(s => this.appState = s);
  }

}
