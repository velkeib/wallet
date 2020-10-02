import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ChartComponent } from './chart/chart.component'; 
import { LoginComponent } from './login/login.component';
import { AuthGuard } from './_helper/auth.guard';

const routes: Routes = [{ path: '', pathMatch: 'full', redirectTo: 'login'},
{ path: 'chart', component: ChartComponent, canActivate: [AuthGuard]  },
{ path: 'login', component: LoginComponent}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
