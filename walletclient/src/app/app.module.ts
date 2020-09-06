import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
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
    HttpClientModule
  ],
  providers: [HomeService],
  bootstrap: [AppComponent]
})
export class AppModule { }
