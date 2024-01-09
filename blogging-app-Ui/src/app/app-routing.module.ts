import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppHeaderComponent } from './app-header/app-header.component';
import { AppFooterComponent } from './app-footer/app-footer.component';
import { AppSignupComponent } from './pages/app-signup/app-signup.component';
import { AppLoginComponent } from './pages/app-login/app-login.component';
import { AppDemoComponent } from './app-demo/app-demo.component';
import { AppHomeComponent } from './app-home/app-home.component';

const routes: Routes = [
  {path: '',   redirectTo: '/login', pathMatch: 'full'},
  {path:"signup", component:AppSignupComponent, title:"Signup"},
  {path:"login", component:AppLoginComponent, title:"Login"},
  {path:"feed", component:AppDemoComponent, title:"demoFeed"},
  {path:"home", component:AppHomeComponent, title:"Home"},
  
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
