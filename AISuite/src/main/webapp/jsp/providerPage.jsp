<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%-- <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> --%>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Created Threads</title>

    <!-- Bootstrap Core CSS -->
    <link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="${pageContext.request.contextPath}/static/css/plugins/metisMenu/metisMenu.min.css" rel="stylesheet">
    
      <!-- Custom Fonts -->
    <link href="${pageContext.request.contextPath}/static/font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/backbuttondisable.js"></script>
    <!-- Display message js -->
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/displaymessage.js"></script>
   
    <style type="text/css">
		        .fullscreen {
		  position: fixed;
		  top: 0;
		  left: 0;
		  bottom: 0;
		  right: 0;
		  overflow: auto;
		  background: ; /* Just to visualize the extent */
		  
		}
		
</style>
<style>
table, th, td {
    border: 1px solid black;
}
</style>
<style>
#wrapper {
  display:table;
  width:750px;
  
}
#row {
  display:table-row;
}
#createThread {
  display:table-cell;
 
  background-color:;
  width:500px;
  margin-left: 160px;
    border-left: medium;
    border-bottom: medium;
    border-right: solid;
    border-top: medium;
    min-height: 350px;
    padding: 15px;
    border-color: #000000;
}
#existingThread {
  display:table-cell;
  background-color:;
  width:500px;
  margin-left: 160px;
    border-left: solid;
    border-bottom: medium;
    border-right: medium;
    border-top: medium;
    min-height: 350px;
    padding: 15px;
    border-color: #000000;
}

</style>
</head>

<body>

 <div class="fullscreen">

        <!-- <p style="font-size:50px; color:#33bbff; padding:7px 5px 0 20px;">Threads</p> -->
            	<%-- <div style="margin-top:60px;"><img src="${pageContext.request.contextPath}/static/images/threads.png" width: 20%; height: 20%/></div> --%>
                  <div class="panel-body">
                    <form:form method="GET" commandName="spocValidatorForm" action="${pageContext.request.contextPath}/fiprovider/existingThreads">
                    <p style="font-size:25px; padding:7px 5px 0 20px;">Provider's Page</p><b>
                      <div class="panel panel-default">
                        <div class="panel-heading">
                        </div>
                        <div class="panel-body">
                            <div class="row">
                            <div class="col-lg-6">
                   			 <div id="success" class="alert alert-success" role="alert">${result}</div>
                            <div id="failure" class="alert alert-danger" role="alert" >${result}</div>
							</div>
				   </div>
						    <div class="table-responsive">
						    <p align="left" style="font-size:15px; font-style:italic; padding:7px 5px 0 20px;">Create / Edit Thread</p><i><br>
                            <div id="wrapper">
							  <div id="row">
							    <div id="createThread">
							    <button type="button" id="FIProvider" class="button button1" onclick="javascript:window.location.href = '${pageContext.request.contextPath}/fiprovider/createThread'">Create New Thread</button>
                                </div>
							    <div id="existingThread">
							    <fieldset>
							    <div class="form-group input-group" >
				                    <div class="form-group"><b>
				                                    <label>SPOC</label>
				                                    <form:input path="spoc" id ="spoc" class="form-control" placeholder="your Id (eg. abc@deloitte.com)"/>
				                                    <form:errors class="help-block" style="color:#F00;" path="spoc" />
				                                    </div> <br><br><br>
				                                    <div>
				                                     <input type="submit"  class="btn btn-primary" value="Show my created threads"/>
				                                    
				                                    </div>
				                                 
				                    </div>
				                    </fieldset>
                                <br><br></div>
							  </div>
							</div>
					  
				   </div>
				   </div>
				   </div>
				   
				   <button type="button" id="back" class="button button3" onclick="javascript:window.location.href = '${pageContext.request.contextPath}/fi/welcome'">Back</button>
                        </form:form>
                    </div>
                    
        
            
 
 

    <!-- jQuery Version 1.11.0 -->
    <script src="${pageContext.request.contextPath}/static/js/jquery-1.11.0.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
    
     
    <!-- Metis Menu Plugin JavaScript -->
    <script src="${pageContext.request.contextPath}/static/js/plugins/metisMenu/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="${pageContext.request.contextPath}/static/js/sb-admin-2.js"></script>
</div>

<style>
		.button1 {
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
		
	
</style>

	 <script>
	    $(document).ready(function() {
	        $('#dataTables-example').dataTable();
	    });
    </script>


</body>
<script type = "text/javascript" >
var statusCode = '${resultCode}';
displayAlert(statusCode);
disableBackButton();
</script>
</html>
