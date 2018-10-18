import {Component, OnDestroy, OnInit} from '@angular/core';
import {NgForm} from '@angular/forms';
import {Store} from '@ngrx/store';

import * as fromApp from '../../store/app.reducers';
import * as AuthActions from "../store/auth.actions";
import {Actions} from "@ngrx/effects";
import {Subject} from "rxjs";
import 'rxjs/add/operator/takeUntil';
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.scss']
})
export class SigninComponent implements OnInit, OnDestroy {

  destroyed$ = new Subject<boolean>();
  error: Boolean = false;
  returnUrl: string = null;

  constructor(private store: Store<fromApp.AppState>,
              private actions$: Actions,
              private router: Router,
              private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.actions$
      .ofType(AuthActions.SIGNIN_FAILURE, AuthActions.SIGNIN_SUCCESS)
      .takeUntil(this.destroyed$)
      .subscribe(action => {
        switch (action.type) {
          case AuthActions.SIGNIN_FAILURE:
            this.error = true;
            break;
          case AuthActions.SIGNIN_SUCCESS:
            this.error = false;
            this.router.navigate([this.returnUrl != null ? this.returnUrl : '/']);
        }
      });
    this.route.queryParams.subscribe(params => {
      this.returnUrl = params['returnUrl'];
    });
  }

  onSubmit(form: NgForm) {
    this.store.dispatch(new AuthActions.Signin({
      email: form.value.email,
      password: form.value.password
    }));
  }

  ngOnDestroy(): void {
    this.destroyed$.next(true);
    this.destroyed$.complete();
  }

}
