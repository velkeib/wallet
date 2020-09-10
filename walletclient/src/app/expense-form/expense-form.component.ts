import { Component, OnInit } from '@angular/core';
import * as Chart from 'chart.js';
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
  expenses: number[][];
  monthNames = ["Január", "Február", "Március", "Április", "Május", "Június", "Július", "Augusztus", "Szeptember", "Október", "November", "December"];

  constructor(private homeService: HomeService) { }

  ngOnInit() {

    this.homeService.getExpenses().subscribe(
      data => {
        this.expenses = data;
        console.log(this.expenses);
        this.loadChart(this.expenses);
      }
    );

      

    
  }


  loadChart(data:number[][]){
    var myChart = new Chart('expenseChart', {
      type: 'bar',
      data: {
        labels: this.getLastSixMonths(),
        datasets: [{
          label: 'Dataset 1',
          backgroundColor: 'rgba(255, 99, 132, 0.2)',
          stack: 'Stack 0',
          data: this.expenses[0]
        }, {
          label: 'Dataset 2',
          backgroundColor: 'rgba(54, 162, 235, 0.2)',
          stack: 'Stack 1',
          data: this.expenses[1]
        }]

      },
      options: {
        scales: {
          yAxes: [{
            ticks: {
              beginAtZero: true
            }
          }]
        }
      }
    });
  }


  getCurrentMonth() {
    var d = new Date();
    return d.getMonth();
  }

  getLastSixMonths() {

    var months = [];

    for (var i = 0; i < 6; i++) {
      if (this.getCurrentMonth() - i < 0) {
        months.push(this.monthNames[12 + this.getCurrentMonth() - i]);
      } else {
        months.push(this.monthNames[this.getCurrentMonth() - i]);
      }
    }

    return months.reverse();

  }

  onSubmit() {

    this.homeService.setExpenses(this.expenseForm.value).subscribe(
      data => {
        this.expenses = data;
      }
    );

  }

}
