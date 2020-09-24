import { Component, OnInit, Input } from '@angular/core';
import { Chart } from 'node_modules/chart.js';
import { HomeService } from '../services/home.service';
import { Expense } from '../model/expense';
import { ExpenseFormComponent } from '../expense-form/expense-form.component';

@Component({
  selector: 'app-chart',
  templateUrl: './chart.component.html',
  styleUrls: ['./chart.component.css']
})
export class ChartComponent implements OnInit {


  constructor(private homeService: HomeService) { }

  sum = 0;
  myDoughnutChart;
  myChart;
  doughnutChartExpenses: Expense[];
  expenses: Expense[];
  monthNames = ["Január", "Február", "Március", "Április", "Május", "Június", "Július", "Augusztus", "Szeptember", "Október", "November", "December"];

  ngOnInit() {

    this.homeService.getExpenses().subscribe(
      data => {
        this.expenses = data;
        this.loadChart();
      }
    );

  }

  loadChart() {

    this.myDoughnutChart = new Chart('doughnutChart', {
      type: 'doughnut',
      data: {
        labels: [this.expenses[0].name, this.expenses[1].name],
        datasets: [
          {
            backgroundColor: ["#3e95cd", "#FF1493", "#3cba9f", "#e8c3b9", "#c45850"],
            data: [this.expenses[0].data[5], this.expenses[1].data[5]]
          }
        ]
      },
      options: {
        responsive: true,
        maintainAspectRatio: false,
        legend: {
          display: false,
        },
        title: {
          display: false,
          text: 'Kiadások ebben a hónapban',

        }
      }
    });

    this.myChart = new Chart('expenseChart', {
      type: 'bar',
      data: {
        labels: this.getLastSixMonths(),
        datasets: [{
          label: this.expenses[0].name,
          backgroundColor: '#3e95cd',
          stack: 'Stack 0',
          data: this.expenses[0].data
        }, {
          label: this.expenses[1].name,
          backgroundColor: '#FF1493',
          stack: 'Stack 1',
          data: this.expenses[1].data
        }]

      },
      options: {
        responsive: true,
        maintainAspectRatio: false,
        legend: {
          display: false,
        },
        scales: {
          yAxes: [{
            ticks: {
              beginAtZero: true
            }
          }]
        }
      }
    });

    this.sumOfData();

  }

  updateChart() {
    for (var i = 0; i < this.myChart.config.data.datasets.length; i++) {
      this.myChart.config.data.datasets[i].data = this.expenses[i].data;
      this.myChart.config.data.datasets[i].label = this.expenses[i].name;
    }

    for (var i = 0; i < this.myDoughnutChart.config.data.datasets[0].data.length; i++) {
      this.myDoughnutChart.config.data.datasets[0].data[i] = this.expenses[i].data[5]
    }

    this.myDoughnutChart.update();
    this.myChart.update();
    this.sumOfData();
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

  sumOfData() {

    this.doughnutChartExpenses = [];

    for (var i = 0; i < this.expenses.length; i++) {

      var sum = 0;

      for (var j = 0; j < this.expenses[i].data.length; j++) {
        sum = sum + this.expenses[i].data[j];
      }

      this.sum = this.sum + sum;

      this.doughnutChartExpenses.push(new Expense(this.expenses[i].name, sum));
    }

    console.log(this.doughnutChartExpenses);

  }

}
