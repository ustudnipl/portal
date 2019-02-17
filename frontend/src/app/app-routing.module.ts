import {NgModule} from '@angular/core';
import {PreloadAllModules, RouterModule, Routes} from '@angular/router';

import {HomeComponent} from "./core/home/home.component";
import {RegisterComponent} from "./register/register.component";
import {AuthGuard} from "./auth/auth.guard";
import {EditionsComponent} from "./core/editions/editions.component";

const routes: Routes = [
  {path: '', component: HomeComponent, canActivate: [AuthGuard]},
  {path: 'editions', component: EditionsComponent, canActivate: [AuthGuard]},
  {path: 'register', component: RegisterComponent}
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, {preloadingStrategy: PreloadAllModules})
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
