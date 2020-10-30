import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
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


  constructor(private homeService: HomeService, private router: Router) { }


  colors = [];
  sum = 0;
  myDoughnutChart;
  myChart;
  doughnutChartExpenses: Expense[];
  expenses: Expense[];
  monthNames = ["Január", "Február", "Március", "Április", "Május", "Június", "Július", "Augusztus", "Szeptember", "Október", "November", "December"];
  loading = false;


  ngOnInit() {

    this.loading = true;



    this.homeService.getExpenses(this.router.url).subscribe(
      data => {
        this.expenses = data;
        this.loadChart();
        this.loading = false;
      }
    );

  }

  loadChart() {


    var bar_ctx =  (<Chart> document.getElementById('expenseChart')).getContext('2d');

    var background_1 = bar_ctx.createLinearGradient(0, 0, 0, 300);
    background_1.addColorStop(0, '#FFE400');
    background_1.addColorStop(1, '#FF5733');

    var background_2 = bar_ctx.createLinearGradient(0, 0, 0, 300);
    background_2.addColorStop(0, '#00FFD1');
    background_2.addColorStop(1, '#00BDDA');



    this.colors.push('linear-gradient(to bottom right, #FFE400 , #FF5733)');
    this.colors.push('linear-gradient(to bottom right, #00FFD1 , #00BDDA)');

    this.myDoughnutChart = new Chart('doughnutChart', {
      type: 'doughnut',
      data: {
        labels: [this.expenses[0].name, this.expenses[1].name],
        datasets: [
          {
            backgroundColor: [background_1, background_2, "#3cba9f", "#e8c3b9", "#c45850"],
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
          backgroundColor:  background_1,
          stack: 'Stack 0',
          data: this.expenses[0].data
        }, {
          label: this.expenses[1].name,
          backgroundColor: background_2,
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
            position: 'right',
            gridLines: {
              color: "#000000",
              drawBorder: false,
              display: true,
              lineWidth: 3,
              maxTicksLimit: 3,
            },
            ticks: {
              beginAtZero: true,
              fontColor: '#000000',
              fontSize: 14,
              padding: 10
            }
          }],
          xAxes: [{
            gridLines: {
              display: false,
              color: "#BDBDBD",
            },
            ticks: {
              beginAtZero: true,
              fontColor: '#000000',
              fontStyle: 'bold',
              fontSize: 14
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
    this.sum = 0;

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
