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

    var data = {"OkPercent": 49.312714776632305, "KoPercent": 50.687285223367695};
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
    createTable($("#apdexTable"), {"supportsControllersDiscrimination": true, "overall": {"data": [0.07689003436426117, 500, 1500, "Total"], "isController": false}, "titles": ["Apdex", "T (Toleration threshold)", "F (Frustration threshold)", "Label"], "items": [{"data": [0.0, 500, 1500, "/product-desc"], "isController": false}, {"data": [0.08333333333333333, 500, 1500, "User"], "isController": false}, {"data": [0.07, 500, 1500, "/list"], "isController": false}, {"data": [0.13, 500, 1500, "/merchant"], "isController": false}, {"data": [0.0, 500, 1500, "/search"], "isController": false}, {"data": [0.08333333333333333, 500, 1500, "Dashboard"], "isController": false}, {"data": [0.0, 500, 1500, "Login"], "isController": false}, {"data": [0.10666666666666667, 500, 1500, "/recomendation"], "isController": false}, {"data": [0.15166666666666667, 500, 1500, "/personalized-ads"], "isController": false}, {"data": [0.0, 500, 1500, "help"], "isController": false}, {"data": [0.0, 500, 1500, "Query CEO"], "isController": false}, {"data": [0.09666666666666666, 500, 1500, "/history"], "isController": false}, {"data": [0.095, 500, 1500, "/promo"], "isController": false}, {"data": [0.13666666666666666, 500, 1500, "/review"], "isController": false}, {"data": [0.0, 500, 1500, "DB Query"], "isController": false}, {"data": [0.065, 500, 1500, "/order"], "isController": false}, {"data": [0.055, 500, 1500, "/top-ads"], "isController": false}, {"data": [0.0, 500, 1500, "/category"], "isController": false}]}, function(index, item){
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
    createTable($("#statisticsTable"), {"supportsControllersDiscrimination": true, "overall": {"data": ["Total", 2328, 1180, 50.687285223367695, 7123.8685567010425, 235, 32967, 1559.5, 29862.7, 30420.199999999997, 30616.620000000003, 68.29983863869738, 620.7776457294265, 12.153755088381986], "isController": false}, "titles": ["Label", "#Samples", "FAIL", "Error %", "Average", "Min", "Max", "Median", "90th pct", "95th pct", "99th pct", "Transactions/s", "Received", "Sent"], "items": [{"data": ["/product-desc", 150, 50, 33.333333333333336, 18105.186666666676, 2365, 32967, 17959.5, 30540.0, 30585.45, 32438.13000000001, 4.548486870034568, 55.87143915072169, 0.8084224710413003], "isController": false}, {"data": ["User", 6, 5, 83.33333333333333, 538.6666666666666, 235, 1356, 269.5, 1356.0, 1356.0, 1356.0, 0.205761316872428, 0.2873089741941015, 0.037307634602194786], "isController": false}, {"data": ["/list", 100, 76, 76.0, 803.8999999999997, 236, 13828, 249.0, 1571.3, 2977.8499999999995, 13745.199999999957, 3.9928129367139147, 18.53772584348173, 0.7096601117987622], "isController": false}, {"data": ["/merchant", 150, 75, 50.0, 2772.4133333333325, 236, 26619, 1047.5, 5309.800000000011, 20181.35, 26612.88, 5.070479667376533, 47.14667999780279, 0.9011985346313762], "isController": false}, {"data": ["/search", 200, 82, 41.0, 19225.829999999994, 2053, 30898, 19131.5, 30571.8, 30594.0, 30753.07, 6.37267397399949, 69.5247217431653, 1.1326432258475656], "isController": false}, {"data": ["Dashboard", 6, 5, 83.33333333333333, 7712.166666666666, 237, 24309, 530.5, 24309.0, 24309.0, 24309.0, 0.2086738775084339, 0.14930507251417244, 0.04272651984140785], "isController": false}, {"data": ["Login", 6, 5, 83.33333333333333, 11145.0, 2015, 30558, 9751.5, 30558.0, 30558.0, 30558.0, 0.19634792852935404, 0.24763998993716865, 0.053241479318018196], "isController": false}, {"data": ["/recomendation", 150, 82, 54.666666666666664, 1818.7333333333336, 239, 24573, 877.0, 2514.4000000000005, 5854.149999999992, 22618.680000000037, 5.152868430092752, 43.60957091635177, 0.9158418498797665], "isController": false}, {"data": ["/personalized-ads", 300, 145, 48.333333333333336, 3814.286666666666, 239, 27957, 1262.0, 16680.700000000033, 20861.8, 25955.71000000001, 10.156408693885842, 97.46780101056267, 1.8051429514523665], "isController": false}, {"data": ["help", 8, 0, 0.0, 6734.5, 6107, 7130, 7049.0, 7130.0, 7130.0, 7130.0, 1.1220196353436185, 0.40432152875175315, 0.18298562412342217], "isController": false}, {"data": ["Query CEO", 1, 1, 100.0, 249.0, 249, 249, 249.0, 249.0, 249.0, 249.0, 4.016064257028112, 1.8158571787148594, 0.8981237449799196], "isController": false}, {"data": ["/history", 300, 170, 56.666666666666664, 2589.65, 237, 26595, 907.5, 5649.600000000002, 20148.049999999992, 24158.350000000006, 10.198878123406425, 82.65095136409995, 1.8126912289648138], "isController": false}, {"data": ["/promo", 300, 183, 61.0, 1215.4866666666667, 236, 24304, 299.5, 2183.200000000002, 2756.3499999999995, 20139.720000000034, 10.232621597653319, 74.98446453543897, 1.818688604270414], "isController": false}, {"data": ["/review", 150, 63, 42.0, 3514.060000000001, 236, 25920, 1307.0, 16707.600000000013, 20888.8, 25765.47, 4.928374293599685, 52.88758455242147, 0.8759415248390064], "isController": false}, {"data": ["DB Query", 1, 0, 0.0, 5516.0, 5516, 5516, 5516.0, 5516.0, 5516.0, 5516.0, 0.18129079042784627, 0.036293566443074694, 0.0], "isController": false}, {"data": ["/order", 100, 59, 59.0, 4491.1799999999985, 236, 30580, 1055.0, 18507.400000000005, 30182.649999999998, 30579.13, 3.031497256494983, 23.300875392199956, 0.5388012701973505], "isController": false}, {"data": ["/top-ads", 100, 71, 71.0, 954.8899999999999, 236, 20352, 255.5, 1890.2000000000003, 2676.8999999999987, 20204.839999999924, 3.834502856704628, 21.236293150139957, 0.6815229686721116], "isController": false}, {"data": ["/category", 300, 108, 36.0, 19069.33666666666, 2021, 31941, 18925.0, 30561.4, 30588.9, 31217.770000000004, 9.392317084624777, 110.88376322751323, 1.669337606837607], "isController": false}]}, function(index, item){
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
    createTable($("#errorsTable"), {"supportsControllersDiscrimination": false, "titles": ["Type of error", "Number of errors", "% in errors", "% in all samples"], "items": [{"data": ["503/Service Temporarily Unavailable", 13, 1.1016949152542372, 0.5584192439862543], "isController": false}, {"data": ["502/Bad Gateway", 1158, 98.13559322033899, 49.74226804123711], "isController": false}, {"data": ["422/Unprocessable Entity", 3, 0.2542372881355932, 0.12886597938144329], "isController": false}, {"data": ["401/Unauthorized", 2, 0.1694915254237288, 0.0859106529209622], "isController": false}, {"data": ["404/Not Found", 4, 0.3389830508474576, 0.1718213058419244], "isController": false}]}, function(index, item){
        switch(index){
            case 2:
            case 3:
                item = item.toFixed(2) + '%';
                break;
        }
        return item;
    }, [[1, 1]]);

        // Create top5 errors by sampler
    createTable($("#top5ErrorsBySamplerTable"), {"supportsControllersDiscrimination": false, "overall": {"data": ["Total", 2328, 1180, "502/Bad Gateway", 1158, "503/Service Temporarily Unavailable", 13, "404/Not Found", 4, "422/Unprocessable Entity", 3, "401/Unauthorized", 2], "isController": false}, "titles": ["Sample", "#Samples", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors"], "items": [{"data": ["/product-desc", 150, 50, "502/Bad Gateway", 50, null, null, null, null, null, null, null, null], "isController": false}, {"data": ["User", 6, 5, "502/Bad Gateway", 3, "401/Unauthorized", 1, "404/Not Found", 1, null, null, null, null], "isController": false}, {"data": ["/list", 100, 76, "502/Bad Gateway", 72, "503/Service Temporarily Unavailable", 4, null, null, null, null, null, null], "isController": false}, {"data": ["/merchant", 150, 75, "502/Bad Gateway", 75, null, null, null, null, null, null, null, null], "isController": false}, {"data": ["/search", 200, 82, "502/Bad Gateway", 82, null, null, null, null, null, null, null, null], "isController": false}, {"data": ["Dashboard", 6, 5, "502/Bad Gateway", 3, "401/Unauthorized", 1, "404/Not Found", 1, null, null, null, null], "isController": false}, {"data": ["Login", 6, 5, "422/Unprocessable Entity", 3, "502/Bad Gateway", 1, "404/Not Found", 1, null, null, null, null], "isController": false}, {"data": ["/recomendation", 150, 82, "502/Bad Gateway", 80, "503/Service Temporarily Unavailable", 2, null, null, null, null, null, null], "isController": false}, {"data": ["/personalized-ads", 300, 145, "502/Bad Gateway", 145, null, null, null, null, null, null, null, null], "isController": false}, {"data": [], "isController": false}, {"data": ["Query CEO", 1, 1, "404/Not Found", 1, null, null, null, null, null, null, null, null], "isController": false}, {"data": ["/history", 300, 170, "502/Bad Gateway", 170, null, null, null, null, null, null, null, null], "isController": false}, {"data": ["/promo", 300, 183, "502/Bad Gateway", 180, "503/Service Temporarily Unavailable", 3, null, null, null, null, null, null], "isController": false}, {"data": ["/review", 150, 63, "502/Bad Gateway", 63, null, null, null, null, null, null, null, null], "isController": false}, {"data": [], "isController": false}, {"data": ["/order", 100, 59, "502/Bad Gateway", 59, null, null, null, null, null, null, null, null], "isController": false}, {"data": ["/top-ads", 100, 71, "502/Bad Gateway", 67, "503/Service Temporarily Unavailable", 4, null, null, null, null, null, null], "isController": false}, {"data": ["/category", 300, 108, "502/Bad Gateway", 108, null, null, null, null, null, null, null, null], "isController": false}]}, function(index, item){
        return item;
    }, [[0, 0]], 0);

});
