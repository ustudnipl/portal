import { Injectable } from '@angular/core';
import {SigninCredentials} from "./login-input.model";
import {Observable} from 'rxjs/Observable';
import {Observer} from 'rxjs/Observer';

@Injectable()
export class AuthService {

  constructor() { }

  signin(authData: SigninCredentials): Observable<string> {
    return Observable.create(
      (observer: Observer<string>) => {

        // observer.next('123456789');
        if (authData.email === 'test@test' && authData.password === 'test') {
          observer.next('123456789');

          observer.complete();
        } else {
          observer.error('blad uwierzytelniania');
        }

      }
    );
  }

}
