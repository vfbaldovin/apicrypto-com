export class OhlcvModelProvider {

  private static chartCandleOptions:  any;

  static getChartData(seriesData: []) : any {
    this.chartCandleOptions =     {
      series: [
        {
          name: "candlestick",
          data: seriesData
        }
      ],
      tooltip: {
        x: {
          format: "dd MMM yyyy"
        }
      },
      chart: {
        type: "candlestick",
        height: 500,
        id: "candles",

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
          autoScaleYaxis: true
        }
      },
      title: {
        text: "Bitcoin Candlestick",
        align: 'center',
        margin: 10,
        offsetX: 0,
        offsetY: 0,
        floating: false,
        style: {
          // fontSize:  '14px',
          // fontWeight:  'bold',
          // fontFamily:  undefined,
          // color:  '#263238'
        }},
      plotOptions: {
        candlestick: {
          colors: {
            upward: "#228B22",
            downward: "#FF0000"
          }
        }
      },

      xaxis: {
        type: "datetime",
        labels: {
          format: 'dd MMM \'yy',
        }
      }
    };

    return this.chartCandleOptions;

  }
}
