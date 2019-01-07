import {Actions, Effect} from '@ngrx/effects';
import {Injectable} from '@angular/core';

import * as AuthActions from './auth.actions';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/switchMap';
import 'rxjs/add/operator/mergeMap';

import {SigninCredentials} from "../login-input.model";
import {AuthService} from "../auth.service";
import {catchError, mergeMap} from "rxjs/operators";

@Injectable()
export class AuthEffects {

  constructor(private actions$: Actions,
              private authService: AuthService) {
  }

  @Effect()
  authSignin = this.actions$
    .ofType(AuthActions.SIGNIN)
    .map((action: AuthActions.Signin) => action.payload as SigninCredentials)
    .switchMap(credentials =>
      this.authService.signin(credentials)
        .pipe(
          mergeMap((token: string) => {
            return [
              new AuthActions.SigninSuccess(),
              new AuthActions.SetToken(token)
            ];
          }),
          catchError((error: string) => {
            return [
              new AuthActions.SigninFailure()
            ];
          })
      )
    );

}
