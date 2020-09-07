import { Component, OnInit } from '@angular/core';
import { HomeService } from '../services/home.service';
import { FormGroup, FormControl } from '@angular/forms';

import { Expense } from '../classes/expense'

@Component({
  selector: 'app-expense-form',
  templateUrl: './expense-form.component.html',
  styleUrls: ['./expense-form.component.css']
})
export class ExpenseFormComponent implements OnInit {

  expenseForm = new FormGroup({
    name: new FormControl(''),
    amount: new FormControl(''),
    description: new FormControl(''),
  });

  expenses:Expense[];

  constructor(private homeService: HomeService) { }

  ngOnInit() {
  }

  onSubmit() {

    this.homeService.setExpenses(this.expenseForm.value).subscribe(
      data=>{
        this.expenses = data;
      }
    );

  }

}
