import { Injectable } from '@angular/core';
import {SigninCredentials} from "./login-input.model";
import {Observable} from 'rxjs/Observable';
import {Observer} from 'rxjs/Observer';
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";

@Injectable()
export class AuthService {

  constructor(private http: HttpClient) { }

  signin(authData: SigninCredentials): Observable<string> {
    return Observable.create(
      (observer: Observer<string>) => {

        let formData = new FormData();
        formData.append('username', authData.email);
        formData.append('password', authData.password);

        this.http.post(environment.backendUrl + "/login", formData, {observe: 'response'})
          .subscribe(
            response => {
              observer.next(response.headers.get('Authorization'));
              observer.complete();
            },
            response => {
              observer.error('authentication error');
            }
          );
      }
    );
  }

}
