import {Component, OnInit, ViewChild} from '@angular/core';
import {ChartService} from "../shared/chart.service";
import {OhlcvModelProvider} from "../shared/model/OhlcvModelProvider";
import {ChartComponent, NgApexchartsModule} from "ng-apexcharts";
import {AppConstants} from "../shared/constants/AppConstants";

export interface Tile {
  color: string;
  cols: number;
  rows: number;
  text: string;
}


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  @ViewChild('ohlcvChart') ohlcvChart : ChartComponent;

  public chartCandleOptions!: any;
  public chartOptions!: any
  public tickerOptions!: any
  public logoUrl!: string

  tiles!: Tile[];
  tiles2!: Tile[];

  constructor(private chartService: ChartService) {
    this.ohlcvChart = new ChartComponent();
    this.logoUrl = AppConstants.LOGO_ENDPOINT + "64x64/1";
    this.tiles = [
      {text: 'One', cols: 3, rows: 1, color: 'lightblue'},
      {text: 'Two', cols: 1, rows: 2, color: 'lightgreen'},
      {text: 'Three', cols: 1, rows: 1, color: 'lightpink'},
      {text: 'Four', cols: 2, rows: 1, color: '#DDBDF1'},
    ];
    this.tiles2 = [
      {text: 'One', cols: 2, rows: 1, color: 'lightblue'},
      {text: 'One', cols: 2, rows: 1, color: 'lightblue'},
      {text: 'One', cols: 4, rows: 1, color: 'lightblue'},
    ];
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


    this.chartService.getTickerData( 1).subscribe({
      next: (data) => {
        this.tickerOptions = data;
        console.log(this.tickerOptions)
      },
      error: err => {
        console.log(err)
      }
    })

    this.chartOptions = {
      series: [
        {
          name: "My-series",
          data: [10, 41, 35, 51, 49, 62, 69, 91, 148, 100, 410, 350, 501, 409, 620, 609, 91, 1048, 100, 41, 350, 51, 49, 620, 69, 91, 148, 10, 41, 35, 51, 49, 62, 69, 91, 148]
        }
      ],
      chart: {
        height: 400,
        width: 500,
        type: "area",
        stacked: false,
        toolbar: {
          show: true,
          offsetX: 0,
          offsetY: 0,
          tools: {
            download: true,
            selection: true,
            zoom: true,
            zoomin: true,
            zoomout: true,
            pan: true,
            reset: true,
            // customIcons: [{
            // // <i class="pi pi-linkedin"></i>
            //   icon: '<i class="pi pi-linkedin"></i>',
            //   appendTo: 'left',
            //   title: '1 year',
            //   class: 'custom-icon',
            //   click: function () {
            //     console.log("cliked")
            //   }
            // }]
          },
          export: {
            csv: {
              filename: undefined,
              columnDelimiter: ',',
              headerCategory: 'category',
              headerValue: 'value'
            },
            svg: {
              filename: undefined,
            },
            png: {
              filename: undefined,
            }
          },
          autoSelected: 'zoom'
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

}
