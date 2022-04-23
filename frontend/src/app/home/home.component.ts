import {Component, OnInit, ViewChild} from '@angular/core';
import {ChartService} from "../shared/chart.service";
import {OhlcvModelProvider} from "../shared/model/OhlcvModelProvider";
import {ChartComponent, NgApexchartsModule} from "ng-apexcharts";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  @ViewChild('ohlcvChart') ohlcvChart : ChartComponent;

  public chartCandleOptions!: any;

  constructor(private chartService: ChartService) {
    this.ohlcvChart = new ChartComponent();
  }

  onValChange(value: any) {
    let data = this.chartCandleOptions.series[0].data;
    switch (value) {
      case "1M": {
        this.ohlcvChart.zoomX(data[data.length-30][0],  data[data.length-1][0]);
        break;
      }
      case "6M": {
        this.ohlcvChart.zoomX(data[Math.round(data.length-data.length/2)][0],  data[data.length-1][0]);
        break;
      }
      case "1Y": {
        this.ohlcvChart.zoomX(data[0][0],  data[data.length-1][0]);
        break;
      }
    }
  }



  ngOnInit(): void {

    this.chartService.getOhlcvChart().subscribe({
      next: (data) => {
        this.chartCandleOptions = OhlcvModelProvider.getChartData(data);
      },
      error: err => {
        console.log(err)
      }
    })







    // this.chartBarOptions = {
    //   series: [
    //     {
    //       name: "volume",
    //       data: seriesDataLinear
    //     }
    //   ],
    //   chart: {
    //     height: 160,
    //     type: "bar",
    //     brush: {
    //       enabled: true,
    //       target: "candles"
    //     },
    //     selection: {
    //       enabled: true,
    //       xaxis: {
    //         min: new Date(1484870400000).getTime(),
    //         max: new Date("10 Dec 2017").getTime()
    //       },
    //       fill: {
    //         color: "#ccc",
    //         opacity: 0.4
    //       },
    //       stroke: {
    //         color: "#0D47A1"
    //       }
    //     }
    //   },
    //   dataLabels: {
    //     enabled: false
    //   },
    //   plotOptions: {
    //     bar: {
    //       columnWidth: "80%",
    //       colors: {
    //         ranges: [
    //           {
    //             from: -1000,
    //             to: 0,
    //             color: "#F15B46"
    //           },
    //           {
    //             from: 1,
    //             to: 10000,
    //             color: "#FEB019"
    //           }
    //         ]
    //       }
    //     }
    //   },
    //   stroke: {
    //     width: 0
    //   },
    //   xaxis: {
    //     type: "datetime",
    //     axisBorder: {
    //       offsetX: 13
    //     }
    //   },
    //   yaxis: {
    //     labels: {
    //       show: false
    //     }
    //   }
    // };















    // this.chartOptions = {
    //   series: [
    //     {
    //       name: "My-series",
    //       data: [10, 41, 35, 51, 49, 62, 69, 91, 148, 100, 410, 350, 501, 409, 620, 609, 91, 1048, 100, 41, 350, 51, 49, 620, 69, 91, 148, 10, 41, 35, 51, 49, 62, 69, 91, 148]
    //     }
    //   ],
    //   chart: {
    //     height: 350,
    //     type: "area",
    //     stacked: false,
    //     toolbar: {
    //       show: false
    //     },
    //     zoom: {
    //       type: "x",
    //       enabled: true,
    //       autoScaleYaxis: true
    //     }
    //   },
    //   dataLabels: {
    //     enabled: false
    //   },
    //   markers: {
    //     size: 0
    //   },
    //   fill: {
    //     type: "gradient",
    //     gradient: {
    //       shadeIntensity: 1,
    //       inverseColors: false,
    //       opacityFrom: 0.5,
    //       opacityTo: 0,
    //       stops: [0, 90, 100]
    //     }
    //   } ,
    //   xaxis: {
    //     categories: ["Jan", "Feb",  "Mar",  "Apr",  "May",  "Jun",  "Jul",  "Aug", "Sep", "Jan", "Feb",  "Mar",  "Apr",  "May",  "Jun",  "Jul",  "Aug", "Sep", "Jan", "Feb",  "Mar",  "Apr",  "May",  "Jun",  "Jul",  "Aug", "Sep", "Jan", "Feb",  "Mar",  "Apr",  "May",  "Jun",  "Jul",  "Aug", "Sep"]
    //   },
    //   stroke: {
    //     width: 1
    //   }
    // };

  }

}
