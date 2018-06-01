import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';

import {SignupComponent} from "./signup/signup.component";
import {SigninComponent} from "./signin/signin.component";
import {AuthRoutingModule} from "./auth-routing.module";
import {AuthService} from "./auth.service";

@NgModule({
  declarations: [
    SigninComponent,
    SignupComponent
  ],
  imports: [
    FormsModule,
    AuthRoutingModule
  ],
  providers: [AuthService]
})
export class AuthModule {
}
