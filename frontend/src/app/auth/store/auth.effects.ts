import {Actions, Effect} from '@ngrx/effects';
import {Injectable} from '@angular/core';

import * as AuthActions from './auth.actions';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/switchMap';
import 'rxjs/add/operator/mergeMap';

import {fromPromise} from 'rxjs/observable/fromPromise';
import {Router} from '@angular/router';
import {SigninCredentials} from "../login-input.model";
import {Observable} from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';
import {Observer} from 'rxjs/Observer';
import {AuthService} from "../auth.service";
import {SetToken, SigninSuccess} from "./auth.actions";

@Injectable()
export class AuthEffects {

  @Effect()
  authSignin = this.actions$
    .ofType(AuthActions.SIGNIN)
    .map((action: AuthActions.Signin) => action.payload as SigninCredentials)
    .switchMap(credentials =>
      this.authService.signin(credentials)
    )
    .mergeMap((token: string) => {
      this.router.navigate(['/']);
      return [
        new AuthActions.SigninSuccess(),
        new AuthActions.SetToken(token)
      ];
    })
  ;

  constructor(private actions$: Actions,
              private router: Router,
              private authService: AuthService) {
  }
}

