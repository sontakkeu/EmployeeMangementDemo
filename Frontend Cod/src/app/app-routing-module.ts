import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EmployeeComponent } from './Component/employee-component/employee-component';
import { AddEmployeeComponent } from './Component/add-employee-component/add-employee-component';
import { UpdateComponent } from './Component/update-component/update-component';
import { adminGuard } from './guard/admin-guard';
import { authGuard } from './guard/auth-guard-guard';
import { LoginComponent } from './Component/login-component/login-component';
import { AdminComponent } from './Component/admin-component/admin-component';
import { NavabarComponent } from './Component/navabar-component/navabar-component';
const routes: Routes = [


 // { path: '', redirectTo: '/employee', pathMatch: 'full' },
 
   { path: '', redirectTo: '/login', pathMatch: 'full' },


   { path: 'login', component: LoginComponent },
   
    
  {
    path: 'admin',
    component: NavabarComponent,
    canActivate: [authGuard, adminGuard],
    children: [

      //{ path: '', redirectTo: 'employees', pathMatch: 'full' },
      
      { path: '', component: AdminComponent },

      { path: 'employees', component: EmployeeComponent },
      { path: 'add', component: AddEmployeeComponent },
      { path: 'update', component: UpdateComponent }

    ]
  }
];
 


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
