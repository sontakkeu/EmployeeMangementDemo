import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing-module';
import { App } from './app';
import { HttpClientModule } from '@angular/common/http';

import { FormsModule } from '@angular/forms';
import { NavabarComponent } from './Component/navabar-component/navabar-component';
import { AddEmployeeComponent } from './Component/add-employee-component/add-employee-component';
import { UpdateComponent } from './Component/update-component/update-component';
import { CommonModule } from '@angular/common';
import { EmployeeComponent } from './Component/employee-component/employee-component';
import { AdminComponent } from './Component/admin-component/admin-component';
import { UserComponent } from './Component/user-component/user-component';
import { LoginComponent } from './Component/login-component/login-component';


@NgModule({
  declarations: [
    App,
    NavabarComponent,
    AddEmployeeComponent,
    UpdateComponent,
    AdminComponent,
    UserComponent,
    LoginComponent,


  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    CommonModule,
    EmployeeComponent,
    
    
    

  ],
  providers: [],
  bootstrap: [App]
})
export class AppModule { }
