import { Component, OnInit } from '@angular/core';
import { Chart } from 'node_modules/chart.js';
import { HomeService } from '../services/home.service';
import { Expense } from '../model/expense';

@Component({
  selector: 'app-chart',
  templateUrl: './chart.component.html',
  styleUrls: ['./chart.component.css']
})
export class ChartComponent implements OnInit {

  constructor(private homeService: HomeService) { }

  expenses: Expense[];
  monthNames = ["Január", "Február", "Március", "Április", "Május", "Június", "Július", "Augusztus", "Szeptember", "Október", "November", "December"];

  ngOnInit(): void {
    this.homeService.getExpenses().subscribe(
      data => {
        this.expenses = data;
        console.log(this.expenses);
        this.loadChart();
      }
    );
  }

  loadChart() {
    var myChart = new Chart('expenseChart', {
      type: 'bar',
      data: {
        labels: this.getLastSixMonths(),
        datasets: [{
          label: this.expenses[0].name,
          backgroundColor: 'rgba(54, 162, 235, 0.2)',
          stack: 'Stack 0',
          data: this.expenses[0].data
        }, {
          label: this.expenses[1].name,
          backgroundColor: 'rgba(255, 99, 132, 0.2)',
          stack: 'Stack 1',
          data: this.expenses[1].data
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

}
