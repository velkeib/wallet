import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import {NgbNav, NgbNavItem, NgbNavLink, NgbNavContent, NgbNavOutlet} from '@ng-bootstrap/ng-bootstrap'

import {ChartsModule}  from 'ng2-charts';

import { HomeService } from './services/home.service';
import { AppComponent } from './app.component';
import { ExpenseFormComponent } from './expense-form/expense-form.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';


@NgModule({
  declarations: [
    AppComponent,
    ExpenseFormComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    ReactiveFormsModule,
    ChartsModule,
    NgbModule,
    NgbNav, 
    NgbNavItem, 
    NgbNavLink, 
    NgbNavContent, 
    NgbNavOutlet
  ],
  providers: [HomeService],
  bootstrap: [AppComponent]
})
export class AppModule { }
