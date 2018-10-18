import {Action} from '@ngrx/store';
import {SigninCredentials} from "../login-input.model";

export const TRY_SIGNUP = 'TRY_SIGNUP';
export const SIGNUP = 'SIGNUP';
export const SIGNIN = 'SIGNIN';
export const SIGNIN_SUCCESS = 'SIGNIN_SUCCESS';
export const SIGNIN_FAILURE = 'SIGNIN_FAILURE';
export const LOGOUT = 'LOGOUT';
export const SET_TOKEN = 'SET_TOKEN';

export class TrySignup implements Action {
  readonly type = TRY_SIGNUP;

  constructor(public payload: { username: string, password: string }) {
  }
}

export class Signup implements Action {
  readonly type = SIGNUP;
}

export class Signin implements Action {
  readonly type = SIGNIN;

  constructor(public payload: SigninCredentials) {
  }
}

export class SigninSuccess implements Action {
  readonly type = SIGNIN_SUCCESS;
}

export class SigninFailure implements Action {
  readonly type = SIGNIN_FAILURE;
}


export class Logout implements Action {
  readonly type = LOGOUT;
}

export class SetToken implements Action {
  readonly type = SET_TOKEN;

  constructor(public payload: string) {
  }
}

export type AuthActions
  = Signup
  | SigninSuccess
  | SigninFailure
  | Logout
  | SetToken
  | TrySignup
  | Signin;
