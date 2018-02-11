	    var responseText = []	
		var dashboard_jsonValue=[];
		var dashboard_jsonNumber=[];
		
		function drawingDashboard(selectIndexText,selectIndex) { 
				dashboard_jsonValue = new Array();
				dashboard_jsonNumber = new Array();
				document.getElementById("display").innerHTML="<h5><b>Snapshot  -&nbsp;"+ selectIndexText +" </b></h5>";
				if(selectIndex == 0)
					 {
					 for (i = 0; i < responseText.jeepneyDashboard.TillToday.length; i++){
						 dashboard_jsonNumber.push(responseText.jeepneyDashboard.TillToday[i]);
						 if(responseText.jeepneyDashboard.TillToday[i].category != "Issuance"){
							 dashboard_jsonValue.push(responseText.jeepneyDashboard.TillToday[i]);
						 }
					   }
					 }
				else if(selectIndex == 1)
					 {
					 for (i = 0; i < responseText.jeepneyDashboard.Last7Days.length; i++){
						 dashboard_jsonNumber.push(responseText.jeepneyDashboard.Last7Days[i]);
						 if(responseText.jeepneyDashboard.Last7Days[i].category != "Issuance"){
							 dashboard_jsonValue.push(responseText.jeepneyDashboard.Last7Days[i]);
						 }
					   }
					 }
				else if(selectIndex == 2)
					 {
					 for (i = 0; i < responseText.jeepneyDashboard.Last30Days.length; i++){
						 dashboard_jsonNumber.push(responseText.jeepneyDashboard.Last30Days[i]);
						 if(responseText.jeepneyDashboard.Last30Days[i].category != "Issuance"){
							 dashboard_jsonValue.push(responseText.jeepneyDashboard.Last30Days[i]);
						 }
					  }
				  }
				else if(selectIndex == 3)
				 {
					 for (i = 0; i < responseText.jeepneyDashboard.Last180Days.length; i++){
						 dashboard_jsonNumber.push(responseText.jeepneyDashboard.Last180Days[i]);
						 if(responseText.jeepneyDashboard.Last180Days[i].category != "Issuance"){
							 dashboard_jsonValue.push(responseText.jeepneyDashboard.Last180Days[i]);
						 }
					}
				 }
		 	
				var chart =	AmCharts.makeChart("numberChart",{
				    "type": "serial",
			   	    "categoryField": "category",
			   	    "columnWidth": 0.25,
			   	 "color":"#888e8e",
					"startDuration": 1,
					"startEffect": "easeOutSine",
					"dataProvider": dashboard_jsonNumber,
					"categoryAxis": {
							"gridPosition": "start",
							"gridThickness": 0,
							"labelRotation": 0
							},
					"trendLines": [],
					"graphs": [{
							"balloonText": "[[title]] of [[category]]s:<b>[[value]]</b>",
							"fillAlphas": 1,
							"fillColors": "#fbae42",
							"lineColor" : "#fbae42",
							"id": "AmGraph-1",
							"title": "Number",
							"type": "column",
							"valueField": "numbers"
							}
							],
					"guides": [],
					"valueAxes": [{
							"id": "ValueAxis-1",
							"title": ""
							}],
					"allLabels": [],
					"balloon": {},
					
					"titles": [{
							"id": "Title-1",
							"size": 15,
							"text": "NUMBERS"
							}]
							});
	
	 chart = AmCharts.makeChart("valueChart",{
		 	"type": "serial",
			"categoryField": "category",
			"startDuration": 1,
			"columnWidth": 0.20,
			"color":"#888e8e",
			"theme": "default",
			"startEffect": "easeOutSine",
			"dataProvider": dashboard_jsonValue,
			"categoryAxis": {
				"gridPosition": "start",
				"gridThickness": 0,
				"labelRotation": 0
			},	
				"trendLines": [],
				"graphs": [{
						"balloonText": "[[title]] of [[category]]:<b> ₱ [[value]]</b>",
						"fillAlphas": 1,
						"fillColors":"#03afaa",
						"lineColor" :"#03afaa",
						"id": "AmGraph-2",
						"title": "Value",
						"type": "column",
						"valueField": "value"
						}],
				"guides": [],
				"valueAxes": [{
						"id": "ValueAxis-1",
						"title": ""
						}],
				"allLabels": [],
				"balloon": {},
				
				"titles": [{
						"id": "Title-1",
						"size": 15,
						"text": "VALUES(in ₱)"
						}]
						});
             }
	
	function gettingDashboardReport(reqUrl,selectIndexText,selectIndex) {
			var url = reqUrl;
			var text = selectIndexText;
				$.ajax({
			    	type: "GET",
			        url: url,
			        data: "selectIndexText="+ text,
			        success: function(response) {
			        var jsonResponsetext=response;
			        responseText = new Array();
					responseText = JSON.parse(jsonResponsetext);
					drawingDashboard(text,selectIndex);
			        }
		      });
		    }

 	