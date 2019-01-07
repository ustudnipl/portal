import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';

import {SignupComponent} from "./signup/signup.component";
import {SigninComponent} from "./signin/signin.component";
import {AuthRoutingModule} from "./auth-routing.module";
import {AuthService} from "./auth.service";
import {CommonModule} from "@angular/common";
import {AuthGuard} from "./auth.guard";

@NgModule({
  declarations: [
    SigninComponent,
    SignupComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    AuthRoutingModule
  ],
  providers: [
    AuthService,
    AuthGuard
  ]
})
export class AuthModule {
}
