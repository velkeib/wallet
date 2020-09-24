import { Component, OnInit } from '@angular/core';
import { HomeService } from '../services/home.service';
import { User } from '../model/user';
import { FormGroup, FormControl } from '@angular/forms';
import { ChartComponent } from '../chart/chart.component';

@Component({
  selector: 'app-expense-form',
  templateUrl: './expense-form.component.html',
  styleUrls: ['./expense-form.component.css']
})
export class ExpenseFormComponent implements OnInit {

  constructor(private homeService: HomeService, private chartComponent: ChartComponent) { }

  expenseForm = new FormGroup({
    name: new FormControl(''),
    amount: new FormControl(''),
    description: new FormControl(''),
    date: new FormControl(''),
  });


  expenses:Object[];
  

  name: string;
  users: User[];

  ngOnInit(): void {
    this.homeService.getUsers().subscribe(
      data => {
        this.users = data;
        console.log(this.users);
      }
    )
  }

  onSubmit() {
    console.log(this.expenseForm.value);
    
    this.homeService.setExpenses(this.expenseForm.value).subscribe(
      data => {
        this.expenses = data;

        this.homeService.getExpenses().subscribe(
          data => {
            this.chartComponent.expenses = data;
            this.chartComponent.updateChart();
          }
        )

      }
    );

  }

}
