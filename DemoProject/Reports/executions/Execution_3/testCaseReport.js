$(document).ready(function(){var plot1 = $.jqplot('holder', [[['0 : Passed Test Cases',0],['0 : Failed Test Cases',0],['2 : Skipped Test Cases',2]]], {gridPadding: {top:0,             bottom:0,             left:0,            right:0        },        seriesDefaults:{            renderer:$.jqplot.PieRenderer,            trendline:{                show:false            },            rendererOptions: {                padding: 29,                showDataLabels: true,                dataLabelThreshold:1,                dataLabelNudge: 62,                seriesColors: ['#A2BF2F', '#BF2F2F','#2F69BF']                ,                startAngle:165,                shadowOffset:1,                shadowAlpha:0.02,                sliceMargin:1.2            }        }, grid: { background: '#ffffff'},        legend:{            show:true,            location:'e'        }          });});
