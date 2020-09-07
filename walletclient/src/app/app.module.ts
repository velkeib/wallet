import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';

import {ChartsModule}  from 'ng2-charts';

import { HomeService } from './services/home.service';
import { AppComponent } from './app.component';
import { ExpenseFormComponent } from './expense-form/expense-form.component';


@NgModule({
  declarations: [
    AppComponent,
    ExpenseFormComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    ReactiveFormsModule,
    ChartsModule
  ],
  providers: [HomeService],
  bootstrap: [AppComponent]
})
export class AppModule { }
