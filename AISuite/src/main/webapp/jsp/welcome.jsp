<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Deloitte's Firm Initiative</title>
    
    <!-- slide show -->
    <link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">

    <!-- Bootstrap Core CSS -->
    <link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="${pageContext.request.contextPath}/static/css/plugins/metisMenu/metisMenu.min.css" rel="stylesheet">
	<%-- <!-- DataTables CSS -->
    <link href="${pageContext.request.contextPath}/static/css/plugins/dataTables.bootstrap.css" rel="stylesheet">
 --%>
    <!-- Custom CSS -->
    <link href="${pageContext.request.contextPath}/static/css/beep.css" rel="stylesheet">
	<!-- Custom Fonts -->
    <link href="${pageContext.request.contextPath}/static/font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
  
 	<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
 	<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/backbuttondisable.js"></script>
 	
 	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
        <script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.8.23/jquery-ui.min.js"></script>
        <link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.23/themes/smoothness/jquery-ui.css" rel="stylesheet" type="text/css" media="all" />

        <script type="text/javascript">
            $(document).ready(function() {
            	
            	$('#chooseFile').dialog({
            	    autoOpen: false,
            	    title: 'Basic Dialog'
            	});
            	$('#opener').click(function() {
            	    $('#chooseFile').dialog('open');
            	    return false;
            	});
            	/* $('#chooseFile').dialog({
                	autoOpen: false,
                    title: 'Basic Dialog'
                });
              $('#opener').click(function() {
                    $('#chooseFile').dialog('open');
                  return false;
                }); */
            });
        </script>
        
<style>
.mySlides {display:none;
height: 10em;
width: 10em}
</style>

</head>
<body>
    <div id="wrapper">
        <!-- Navigation -->
       <jsp:include page="leftmenu.jsp"></jsp:include>
        <div id="page-wrapper">
            <div class="row"> 
                <div><!-- class="col-lg-12" -->
                <%-- <img src="<%=request.getContextPath()%>/static/images/Deloitte.png" width="500" height="50" /></a> --%>
                <p style="font-size:50px; color:#D4AC0D; padding:7px 5px 0 20px;">Firm Initiative</p>
                    <!-- <h1>Chose to log in as : </h1> -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- <div class="row"> -->
                <div><!-- class="col-lg-12" -->
                   <form:form method="GET" commandName="fiThreadsForm" action="">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                        </div>
                        <div class="panel-body">
                            <div class="row">
                            <div class="col-lg-6">
                            <%-- <div id="success" class="alert alert-success" role="alert">${result}</div>
                            <div id="failure" class="alert alert-danger" role="alert" >${result}</div>
                            --%>  	   		
                                        <%-- <img src="<%=request.getContextPath()%>/static/images/Innovation.png" style="float: left; width: 49%; margin-right: 1%; margin-bottom: 0.5em;" width="300" height="100" />
								        <img src="<%=request.getContextPath()%>/static/images/Recruitment.png" style="float: left; width: 49%; margin-right: 1%; margin-bottom: 0.5em;" width="300" height="100"/>
								        <img src="<%=request.getContextPath()%>/static/images/SINS.png" style="float: left; width: 49%; margin-right: 1%; margin-bottom: 0.5em;" width="300" height="100"/>
								        <img src="<%=request.getContextPath()%>/static/images/Impactday.png" style="float: left; width: 49%; margin-right: 1%; margin-bottom: 0.5em;" width="300" height="100"/> --%>
								
								<div class="w3-content w3-section" style="max-width:500px">
								  <img class="mySlides" src="<%=request.getContextPath()%>/static/images/Innovation.png" style="width:100%">
								  <img class="mySlides" src="<%=request.getContextPath()%>/static/images/Recruitment.png" style="width:100%">
								  <img class="mySlides" src="<%=request.getContextPath()%>/static/images/SINS.png" style="width:100%">
								  <img class="mySlides" src="<%=request.getContextPath()%>/static/images/Impactday.png" style="width:100%">
								</div>
								
								<p style="clear: both;">
								<br>
								<br>
								<p style="clear: both;">Go further as - </p>
								<button type="button" id="FISeeker" class="button button2" onclick="javascript:window.location.href = '${pageContext.request.contextPath}/fiseeker/threads'">FI Seeker</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <button type="button" id="FIProvider" class="button button3" onclick="javascript:window.location.href = '${pageContext.request.contextPath}/fiprovider/providerPage'">FI Provider</button>
                                
								<button type="button" class="button button4" id="opener">Open the dialog</button>
								 <div id="chooseFile">
								    <p>Some txt goes here</p>
								</div>
								
                              </div>
                                </div>
                                </div>
                                </div>
                                
                            <!-- </table> -->
                          
                            </form:form>
                            </div>
                             <!-- /.col-lg-6 (nested) -->
                            </div>
                            <!-- /.row (nested) -->
                        </div><!-- 
                         /.panel-body-->
                    
                    <!-- /.panel -->
               <!--  </div>
                /.col-lg-12
            </div>
            /.row
        </div>
        /#page-wrapper

    </div> -->
    <!-- /#wrapper -->
<!-- jQuery Version 1.11.0 -->
    <script src="${pageContext.request.contextPath}/static/js/jquery-1.11.0.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="${pageContext.request.contextPath}/static/js/plugins/metisMenu/metisMenu.min.js"></script>

    <%-- <!-- DataTables JavaScript -->
    <script src="${pageContext.request.contextPath}/static/js/plugins/dataTables/jquery.dataTables.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/plugins/dataTables/dataTables.bootstrap.js"></script>
 --%>
    <!-- Custom Theme JavaScript -->
    <script src="${pageContext.request.contextPath}/static/js/sb-admin-2.js"></script>

    <!-- Page-Level Demo Scripts - Tables - Use for reference -->
    <script>
        disableBackButton();
    </script>
    
    <script>
var myIndex = 0;
carousel();

function carousel() {
    var i;
    var x = document.getElementsByClassName("mySlides");
    for (i = 0; i < x.length; i++) {
       x[i].style.display = "none";  
    }
    myIndex++;
    if (myIndex > x.length) {myIndex = 1}    
    x[myIndex-1].style.display = "block";  
    setTimeout(carousel, 2000); // Change image every 2 seconds
}
</script>
    
    <style>
		.button {
		    background-color: #4CAF50; /* Green */
		    border: none;
		    color: white;
		    padding: 15px 32px;
		    text-align: center;
		    text-decoration: none;
		    display: inline-block;
		    font-size: 16px;
		    margin: 4px 2px;
		    cursor: pointer;
		}
		
		.button2 {background-color: #008CBA;} /* Blue */
		.button3 {background-color: #f44336;} /* Red */ 
		.button4 {background-color: #e7e7e7; color: black;} /* Gray */ 
		.button5 {background-color: #555555;} /* Black */
</style>

</body>

</html>
