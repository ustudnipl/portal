import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import {Store} from "@ngrx/store";
import * as fromApp from "../store/app.reducers";
import 'rxjs/add/operator/take';
import * as AuthActions from "./store/auth.actions";

@Injectable()
export class AuthGuard implements CanActivate {

  constructor(private router: Router,
              private store: Store<fromApp.AppState>) {
    if (localStorage["token"] != null) {
      this.store.dispatch(new AuthActions.SigninSuccess());
      this.store.dispatch(new AuthActions.SetToken(localStorage["token"]));
    }
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    let appState: fromApp.AppState;
    this.store.take(1).subscribe(s => appState = s);

    if (appState.auth.authenticated) {
      return true;
    }

    this.router.navigate(['/signin'], { queryParams: { returnUrl: state.url }});
    return false;
  }

}
