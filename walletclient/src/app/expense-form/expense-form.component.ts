import { Component, OnInit } from '@angular/core';
import { HomeService } from '../services/home.service';
import { Expensepost } from '../classes/expensepost';
import { Expense } from '../classes/expense'

@Component({
  selector: 'app-expense-form',
  templateUrl: './expense-form.component.html',
  styleUrls: ['./expense-form.component.css']
})
export class ExpenseFormComponent implements OnInit {

  name: string;
  expensepost: Expensepost;
  expenses:Expense[];

  constructor(private homeService: HomeService) { }

  ngOnInit() {
  }

  callFunction(event){
    console.log(<HTMLInputElement>document.getElementById("userName").value);
    
    console.log(<HTMLInputElement>document.getElementById("amount").value);
    
    console.log(<HTMLInputElement>document.getElementById("description").value);

    this.expensepost = new Expensepost();

    this.expensepost.userName = <string>(<HTMLInputElement>document.getElementById("userName").value);
    this.expensepost.amount = <number>(<HTMLInputElement>document.getElementById("amount").value);
    this.expensepost.description = <string>(<HTMLInputElement>document.getElementById("description").value);

    this.homeService.setExpenses(this.expensepost).subscribe(
      data=>{
        this.expenses = data;
      }
    );

  };

}
