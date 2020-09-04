import { Component } from '@angular/core';
import { HomeService } from './services/home.service';
import { Expense } from './classes/expense'

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app';

  constructor(private homeService: HomeService) { }

  expenses:Expense[];

  ngOnInit(){
    this.homeService.getExpenses().subscribe(
      data=>{
        this.expenses = data;
      }
    );
  }

}
