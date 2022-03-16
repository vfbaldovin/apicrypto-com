import {Component, OnInit, ViewChild} from '@angular/core';
import {ChartComponent} from "ng-apexcharts";
import {ChartOptions} from "./model/ChartOptions";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {


  // public chart: ChartComponent;
  public chartOptions: Partial<ChartOptions>;


  constructor() {
    this.chartOptions = {
      series: [
        {
          name: "My-series",
          data: [10, 41, 35, 51, 49, 62, 69, 91, 148, 100, 410, 350, 501, 409, 620, 609, 91, 1048, 100, 41, 350, 51, 49, 620, 69, 91, 148, 10, 41, 35, 51, 49, 62, 69, 91, 148]
        }
      ],
      chart: {
        height: 350,
        type: "area",
        stacked: false,
        toolbar: {
          show: false
        },
        zoom: {
          type: "x",
          enabled: true,
          autoScaleYaxis: true
        }
      },
      dataLabels: {
          enabled: false
      },
      markers: {
        size: 0
      },
      fill: {
        type: "gradient",
        gradient: {
          shadeIntensity: 1,
          inverseColors: false,
          opacityFrom: 0.5,
          opacityTo: 0,
          stops: [0, 90, 100]
        }
      } ,
      xaxis: {
        categories: ["Jan", "Feb",  "Mar",  "Apr",  "May",  "Jun",  "Jul",  "Aug", "Sep", "Jan", "Feb",  "Mar",  "Apr",  "May",  "Jun",  "Jul",  "Aug", "Sep", "Jan", "Feb",  "Mar",  "Apr",  "May",  "Jun",  "Jul",  "Aug", "Sep", "Jan", "Feb",  "Mar",  "Apr",  "May",  "Jun",  "Jul",  "Aug", "Sep"]
      },
      stroke: {
        width: 1
      }
    };
  }
  // public initChartData(): void {
  //   let ts2 = 1484418600000;
  //   let dates = [];
  //   for (let i = 0; i < 120; i++) {
  //     ts2 = ts2 + 86400000;
  //     dates.push([ts2, dataSeries[1][i].value]);
  //   }
  //
  //   this.series = [
  //     {
  //       name: "XYZ MOTORS",
  //       data: dates
  //     }
  //   ];
  //   this.chart = {
  //     type: "area",
  //     stacked: false,
  //     height: 350,
  //     zoom: {
  //       type: "x",
  //       enabled: true,
  //       autoScaleYaxis: true
  //     },
  //     toolbar: {
  //       autoSelected: "zoom"
  //     }
  //   };
  //   this.dataLabels = {
  //     enabled: false
  //   };
  //   this.markers = {
  //     size: 0
  //   };
  //   this.title = {
  //     text: "Stock Price Movement",
  //     align: "left"
  //   };
  //   this.fill = {
  //     type: "gradient",
  //     gradient: {
  //       shadeIntensity: 1,
  //       inverseColors: false,
  //       opacityFrom: 0.5,
  //       opacityTo: 0,
  //       stops: [0, 90, 100]
  //     }
  //   };
  //   this.yaxis = {
  //     labels: {
  //       formatter: function(val) {
  //         return (val / 1000000).toFixed(0);
  //       }
  //     },
  //     title: {
  //       text: "Price"
  //     }
  //   };
  //   this.xaxis = {
  //     type: "datetime"
  //   };
  //   this.tooltip = {
  //     shared: false,
  //     y: {
  //       formatter: function(val) {
  //         return (val / 1000000).toFixed(0);
  //       }
  //     }
  //   };
  // }
  ngOnInit(): void {
  }

}
