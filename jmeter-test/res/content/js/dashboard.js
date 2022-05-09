/*
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
var showControllersOnly = false;
var seriesFilter = "";
var filtersOnlySampleSeries = true;

/*
 * Add header in statistics table to group metrics by category
 * format
 *
 */
function summaryTableHeader(header) {
    var newRow = header.insertRow(-1);
    newRow.className = "tablesorter-no-sort";
    var cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 1;
    cell.innerHTML = "Requests";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 3;
    cell.innerHTML = "Executions";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 7;
    cell.innerHTML = "Response Times (ms)";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 1;
    cell.innerHTML = "Throughput";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 2;
    cell.innerHTML = "Network (KB/sec)";
    newRow.appendChild(cell);
}

/*
 * Populates the table identified by id parameter with the specified data and
 * format
 *
 */
function createTable(table, info, formatter, defaultSorts, seriesIndex, headerCreator) {
    var tableRef = table[0];

    // Create header and populate it with data.titles array
    var header = tableRef.createTHead();

    // Call callback is available
    if(headerCreator) {
        headerCreator(header);
    }

    var newRow = header.insertRow(-1);
    for (var index = 0; index < info.titles.length; index++) {
        var cell = document.createElement('th');
        cell.innerHTML = info.titles[index];
        newRow.appendChild(cell);
    }

    var tBody;

    // Create overall body if defined
    if(info.overall){
        tBody = document.createElement('tbody');
        tBody.className = "tablesorter-no-sort";
        tableRef.appendChild(tBody);
        var newRow = tBody.insertRow(-1);
        var data = info.overall.data;
        for(var index=0;index < data.length; index++){
            var cell = newRow.insertCell(-1);
            cell.innerHTML = formatter ? formatter(index, data[index]): data[index];
        }
    }

    // Create regular body
    tBody = document.createElement('tbody');
    tableRef.appendChild(tBody);

    var regexp;
    if(seriesFilter) {
        regexp = new RegExp(seriesFilter, 'i');
    }
    // Populate body with data.items array
    for(var index=0; index < info.items.length; index++){
        var item = info.items[index];
        if((!regexp || filtersOnlySampleSeries && !info.supportsControllersDiscrimination || regexp.test(item.data[seriesIndex]))
                &&
                (!showControllersOnly || !info.supportsControllersDiscrimination || item.isController)){
            if(item.data.length > 0) {
                var newRow = tBody.insertRow(-1);
                for(var col=0; col < item.data.length; col++){
                    var cell = newRow.insertCell(-1);
                    cell.innerHTML = formatter ? formatter(col, item.data[col]) : item.data[col];
                }
            }
        }
    }

    // Add support of columns sort
    table.tablesorter({sortList : defaultSorts});
}

$(document).ready(function() {

    // Customize table sorter default options
    $.extend( $.tablesorter.defaults, {
        theme: 'blue',
        cssInfoBlock: "tablesorter-no-sort",
        widthFixed: true,
        widgets: ['zebra']
    });

    var data = {"OkPercent": 100.0, "KoPercent": 0.0};
    var dataset = [
        {
            "label" : "FAIL",
            "data" : data.KoPercent,
            "color" : "#FF6347"
        },
        {
            "label" : "PASS",
            "data" : data.OkPercent,
            "color" : "#9ACD32"
        }];
    $.plot($("#flot-requests-summary"), dataset, {
        series : {
            pie : {
                show : true,
                radius : 1,
                label : {
                    show : true,
                    radius : 3 / 4,
                    formatter : function(label, series) {
                        return '<div style="font-size:8pt;text-align:center;padding:2px;color:white;">'
                            + label
                            + '<br/>'
                            + Math.round10(series.percent, -2)
                            + '%</div>';
                    },
                    background : {
                        opacity : 0.5,
                        color : '#000'
                    }
                }
            }
        },
        legend : {
            show : true
        }
    });

    // Creates APDEX table
    createTable($("#apdexTable"), {"supportsControllersDiscrimination": true, "overall": {"data": [0.8815, 500, 1500, "Total"], "isController": false}, "titles": ["Apdex", "T (Toleration threshold)", "F (Frustration threshold)", "Label"], "items": [{"data": [0.7, 500, 1500, "authenticate"], "isController": false}, {"data": [0.87, 500, 1500, "create education detail entry API"], "isController": false}, {"data": [0.925, 500, 1500, "get education-study-field API"], "isController": false}, {"data": [0.93, 500, 1500, "get employee-type API"], "isController": false}, {"data": [0.96, 500, 1500, "update education entry API"], "isController": false}, {"data": [0.97, 500, 1500, "update professional qualifications API"], "isController": false}, {"data": [0.87, 500, 1500, "create professional qualification entry API"], "isController": false}, {"data": [0.875, 500, 1500, "create specific skill entry API"], "isController": false}, {"data": [0.855, 500, 1500, "create work experience entry API"], "isController": false}, {"data": [0.86, 500, 1500, "get degree level API"], "isController": false}]}, function(index, item){
        switch(index){
            case 0:
                item = item.toFixed(3);
                break;
            case 1:
            case 2:
                item = formatDuration(item);
                break;
        }
        return item;
    }, [[0, 0]], 3);

    // Create statistics table
    createTable($("#statisticsTable"), {"supportsControllersDiscrimination": true, "overall": {"data": ["Total", 1000, 0, 0.0, 306.71700000000004, 4, 2283, 231.0, 742.5999999999997, 915.9499999999999, 1478.8300000000002, 205.4653790836244, 100.00385247585781, 115.97557530306143], "isController": false}, "titles": ["Label", "#Samples", "FAIL", "Error %", "Average", "Min", "Max", "Median", "90th pct", "95th pct", "99th pct", "Transactions/s", "Received", "Sent"], "items": [{"data": ["authenticate", 100, 0, 0.0, 577.7000000000002, 126, 1942, 565.0, 971.2000000000007, 1330.1999999999991, 1941.4499999999998, 28.506271379703534, 22.66025869441277, 16.006939495438996], "isController": false}, {"data": ["create education detail entry API", 100, 0, 0.0, 377.3, 32, 1579, 317.0, 856.4000000000001, 1108.2999999999993, 1575.4499999999982, 23.91200382592061, 9.363978060736489, 16.649666726446675], "isController": false}, {"data": ["get education-study-field API", 100, 0, 0.0, 222.23999999999995, 4, 1139, 37.5, 773.9000000000008, 912.1499999999999, 1137.5499999999993, 22.3463687150838, 9.536487430167599, 10.976780726256985], "isController": false}, {"data": ["get employee-type API", 100, 0, 0.0, 207.47, 5, 903, 102.5, 590.2000000000002, 665.8499999999999, 902.1599999999996, 22.3563603845294, 12.99026799687011, 10.80702967806841], "isController": false}, {"data": ["update education entry API", 100, 0, 0.0, 184.82000000000002, 36, 1108, 81.5, 432.70000000000005, 589.2999999999998, 1105.0699999999986, 22.21235006663705, 9.631136161705909, 11.995536705908485], "isController": false}, {"data": ["update professional qualifications API", 100, 0, 0.0, 164.20999999999998, 25, 855, 47.5, 398.9, 581.6999999999995, 854.4699999999997, 22.281639928698752, 9.682939226827093, 12.163512422014259], "isController": false}, {"data": ["create professional qualification entry API", 100, 0, 0.0, 349.25000000000006, 30, 2170, 251.0, 808.9, 1080.1499999999999, 2160.8899999999953, 23.041474654377883, 9.023077476958525, 13.343353974654379], "isController": false}, {"data": ["create specific skill entry API", 100, 0, 0.0, 350.5999999999998, 29, 2283, 319.5, 898.3000000000001, 1058.0499999999997, 2274.959999999996, 23.089355806972986, 9.04182781112907, 13.528919418148233], "isController": false}, {"data": ["create work experience entry API", 100, 0, 0.0, 343.1700000000002, 32, 1213, 332.5, 842.8000000000004, 975.3499999999995, 1211.7499999999993, 22.168033695411218, 8.681036632675681, 15.045686931944136], "isController": false}, {"data": ["get degree level API", 100, 0, 0.0, 290.41, 5, 2048, 23.5, 846.8, 953.9999999999991, 2043.799999999998, 22.33638597274961, 14.06930561760107, 10.775561201697565], "isController": false}]}, function(index, item){
        switch(index){
            // Errors pct
            case 3:
                item = item.toFixed(2) + '%';
                break;
            // Mean
            case 4:
            // Mean
            case 7:
            // Median
            case 8:
            // Percentile 1
            case 9:
            // Percentile 2
            case 10:
            // Percentile 3
            case 11:
            // Throughput
            case 12:
            // Kbytes/s
            case 13:
            // Sent Kbytes/s
                item = item.toFixed(2);
                break;
        }
        return item;
    }, [[0, 0]], 0, summaryTableHeader);

    // Create error table
    createTable($("#errorsTable"), {"supportsControllersDiscrimination": false, "titles": ["Type of error", "Number of errors", "% in errors", "% in all samples"], "items": []}, function(index, item){
        switch(index){
            case 2:
            case 3:
                item = item.toFixed(2) + '%';
                break;
        }
        return item;
    }, [[1, 1]]);

        // Create top5 errors by sampler
    createTable($("#top5ErrorsBySamplerTable"), {"supportsControllersDiscrimination": false, "overall": {"data": ["Total", 1000, 0, null, null, null, null, null, null, null, null, null, null], "isController": false}, "titles": ["Sample", "#Samples", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors"], "items": [{"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}]}, function(index, item){
        return item;
    }, [[0, 0]], 0);

});
