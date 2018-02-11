<html>
	<head>
		<title>Google Pie Chart Demo</title>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/dashboard/common.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/dashboard/amcharts.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/dashboard/serial.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/dashboard/jquery-ui.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/dashboard/jquery.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/dashboard/jquery-1.10.2.min.js"></script>
		<link href="${pageContext.request.contextPath}/static/css/dashboard/style.css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/static/css/dashboard/jquery-ui.css" rel="stylesheet">
		<script src="${pageContext.request.contextPath}/static/js/dataTables/jquery-1.10.2.min.js"></script>
		<script src="${pageContext.request.contextPath}/static/js/dataTables/jquery.dataTables.js"></script>
	<!--for Datepicker -->
		<link href="${pageContext.request.contextPath}/static/css/jquery-1.11.0-ui.css" rel="stylesheet">
		<script src="${pageContext.request.contextPath}/static/js/jquery-1.11.0-ui.js"></script>
	
    <!-- Bootstrap Core CSS -->
    <link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="${pageContext.request.contextPath}/static/css/plugins/metisMenu/metisMenu.min.css" rel="stylesheet">
	<!-- DataTables CSS -->
    <link href="${pageContext.request.contextPath}/static/css/plugins/dataTables.bootstrap.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="${pageContext.request.contextPath}/static/css/beep.css" rel="stylesheet">
	<!-- Custom Fonts -->
    <link href="${pageContext.request.contextPath}/static/font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	
    <!-- Bootstrap Core JavaScript -->
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="${pageContext.request.contextPath}/static/js/plugins/metisMenu/metisMenu.min.js"></script>

    <!-- DataTables JavaScript -->
    <script src="${pageContext.request.contextPath}/static/js/plugins/dataTables/jquery.dataTables.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/plugins/dataTables/dataTables.bootstrap.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="${pageContext.request.contextPath}/static/js/sb-admin-2.js"></script>
  

		<script type="text/javascript">

	   var dashboard_jsonValue=[];
		var dashboard_jsonNumber=[];
		var result = $.parseJSON('${pie}');	
		function drawingDashboard() { 
			
			if(result.length == 0 && result ==''){
				/* alert("0"); */
				$("#msg").show();
			}else{
				$("#msg").hide();
			}
				dashboard_jsonNumber = new Array();
					 for (i = 0; i < result.length; i++){
						 dashboard_jsonNumber.push(result[i].transaction);
						 
					   }			 
				
				 var chart =	AmCharts.makeChart("numberChart",{
					"type": "serial",
			   	    "categoryField": "timeFrom",
			   	    "columnWidth": 0.25,
			   	 	"color":"#888e8e",
					"startDuration": 1,					
					"startEffect": "easeOutSine",
					"dataProvider": result,
					"categoryAxis": {
							"gridPosition": "start",
							"gridThickness": 0,
							"labelRotation": 0
							},
					"trendLines": [],
					"graphs": [{
							
							"fillAlphas": 1,
							"fillColors": "#fbae42",
							"lineColor" : "#fbae42",
							"id": "AmGraph-1",
							"title": "transaction",
							"type": "column",
							"valueField": "transaction"
					}],
					"guides": [],
					"valueAxes": [{
						    "unit": "%",
							"id": "ValueAxis-1",
							"title": "Per cent"
					}],
					"allLabels": [],
					"balloon": {},
					
					"titles": [{
							"id": "Title-1",
							"size": 15,
							"text": ""
					}]
				}); 
				 /* chart.dataProvider = dashboard_jsonNumber;
				 chart.categoryField = "transaction";
				 chart.startDuration = 1; */
				// chart.addListener("clickGraphItem", handleClick);
		
		}
		<%-- function handleClick(event){			
			var time = event.item.category;
			var url = "<%=request.getContextPath()%>/hourlyReport/fareCollectionTransactionHistory/";
			window.location.href = url+time;
		} --%>
	</script>
	
	</head>
	<body>
	
<%
    response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
    response.setHeader("Pragma", "no-cache"); //HTTP 1.0
    response.setDateHeader("Expires", 0); //prevents caching at the proxy server
%>
   

            <br/>
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                          <!--   Kindly Enter NFC Card Master Details -->
                        </div>
                        <div class="panel-body">
                            <div class="row">
                            <div class="col-lg-6">
                            <h4>${result}</h4>
                                    <form:form method="POST" commandName="amChartForm" action="${pageContext.request.contextPath}/fi/amChart">
                                     
                                        
                                        
                                   </form:form>
                            </div>
                             <!-- /.col-lg-6 (nested) -->
                            </div>                            
                            <!-- /.row (nested) -->
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->     
     <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Hourly Fare Report
                        </div>
                        <!-- /.panel-heading -->
	<div class="panel-body">
	    <div class="table-responsive"> 	
		    <div class="row">
				<div class="col-xs-12">
					<span id="msg" class="msgErr" style="color:#F00;">No Records Found</span>
					<div id="numberChart" class="leftdiv" style="width:100%; height: 300px; background-color: #FFFFFF;" ></div>
				</div>
			</div>
		 </div>
         <!--  /.table-responsive -->
    </div>
	      <!-- /.panel-body -->
	            </div>
                   <!--  /.panel -->
                </div>
               <!--  /.col-lg-12 -->
            </div>
           

		<script type="text/javascript">
	    $(document).ready(function() {
	    	
	    	drawingDashboard();
	        
	    });
    </script>
	</body>
</html>