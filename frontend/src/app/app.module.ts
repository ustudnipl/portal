import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {HttpClientModule} from "@angular/common/http";

import {NgbModule} from '@ng-bootstrap/ng-bootstrap';

import {StoreModule} from '@ngrx/store';
// import {EffectsModule} from '@ngrx/effects';

// import {AuthModule} from "./auth/auth.module";
import {AppRoutingModule} from "./app-routing.module";
import {CoreModule} from "./core/core.module";

import {AppComponent} from './app.component';

import * as fromApp from './store/app.reducers';
// import {AuthEffects} from './auth/store/auth.effects';
import { RegisterComponent } from './register/register.component';

@NgModule({
  declarations: [
    AppComponent,
    RegisterComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    CoreModule,
    // AuthModule,
    AppRoutingModule,
    StoreModule.forRoot(fromApp.reducers),
    // EffectsModule.forRoot([AuthEffects]),
    NgbModule.forRoot()
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
