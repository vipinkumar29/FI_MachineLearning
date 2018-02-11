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

    <title>Threads</title>

    <!-- Bootstrap Core CSS -->
    <link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="${pageContext.request.contextPath}/static/css/plugins/metisMenu/metisMenu.min.css" rel="stylesheet">
    <!-- DataTables CSS -->
    <link href="${pageContext.request.contextPath}/static/css/plugins/dataTables.bootstrap.css" rel="stylesheet">

    <!-- Custom CSS -->
    <%-- <link href="${pageContext.request.contextPath}/static/css/beep.css" rel="stylesheet"> --%>


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

</head>

<body>

 <div class="fullscreen">
<!-- <div id="wrapper">
 <div id="page-wrapper"> -->
        <!-- <p style="font-size:50px; color:#33bbff; padding:7px 5px 0 20px;">Threads</p> -->
            	<%-- <div style="margin-top:60px;"><img src="${pageContext.request.contextPath}/static/images/threads.png" width: 20%; height: 20%/></div> --%>
                  <div class="panel-body">
                    <form:form commandName="fiThreadsForm" action="">
                     <div class="panel panel-default">
                        <div class="panel-heading">
                        </div>
                        <div class="panel-body">
                            <div class="row">
                            <div class="col-lg-6">
                   			 <div id="success" class="alert alert-success" role="alert">${result}</div>
                            <div id="failure" class="alert alert-danger" role="alert" >${result}</div>
							</div>
				   </div><!-- 33bbff -->
						    <div class="table-responsive">
						    <p style="font-size:25px; padding:7px 5px 0 20px;">Your Threads:</p>
                            <!-- <label align="left">Threads :- </label> -->
										<table id="dataTables-example" class="table table-striped table-bordered table-hover">
											<thead>
									             <tr bgcolor="#e6ecff"><!-- 1ac6ff -->
									                  <th>Thread Name</th>
									                  <th>Thread Type</th>  
									                  <th>Description</th>
									                  <th>Skills</th>  
									                  <th>SPOC</th> 
									                  <th>Actions</th>
									                  <!-- <th>Interested ?</th>  -->
									              </tr>
									         </thead>
									        <tbody>
                				<c:choose>
									<c:when test="${fiThreadsForm.status == 'Success'}">
										<c:forEach items="${fiThreadsFormList}" var="fiThreads" begin="0">
										<tr bgcolor="ffd9b3"><!-- ffe6e6 -->
										    <td class="gradeA"><a href="${pageContext.request.contextPath}/fiseeker/seekersThread/${fiThreads.id}"><c:out value="${fiThreads.threadName}" /></a></td>
											<td class="gradeA"><c:out value="${fiThreads.threadType}" /></td>
											<td class="gradeA"><c:out value="${fiThreads.des}" /></td>
											<td class="gradeA"><c:out value="${fiThreads.skills}" /></td>
											<td class="gradeA"><c:out value="${fiThreads.spoc}" /></td>
											<td class="gradeA">
											        <label>
			                                            <button type="button" id="back" class="button button3" onclick="javascript:window.location.href = '${pageContext.request.contextPath}/fi/welcome'">Edit</button>
			                                            <button type="button" id="back" class="button button3" onclick="javascript:window.location.href = '${pageContext.request.contextPath}/fi/welcome'">Delete</button>
			                                            <button type="button" id="back" class="button button3" onclick="javascript:window.location.href = '${pageContext.request.contextPath}/fi/welcome'">Close</button>
			                                        </label>
			                                   </td>
										</tr>
									</c:forEach>
									</c:when>
								   <c:when test="${fiThreadsForm.status=='Failure'}">
								     <tr>
										<td colspan="9" align="center" style="border-bottom: none; color: blue" >No record found</td>
								 </tr>
								</c:when>
							 </c:choose>      
						</tbody>
					  </table>
				   </div>
				   </div>
				   </div>
				   <button type="button" id="back" class="button button3" onclick="javascript:window.location.href = '${pageContext.request.contextPath}/fi/welcome'">Back</button>
				   
                        </form:form>
                    </div>
                    
                   <!--  </div> -->
            
 
 

    <!-- jQuery Version 1.11.0 -->
    <script src="${pageContext.request.contextPath}/static/js/jquery-1.11.0.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
    
    <!-- DataTables JavaScript -->
    <script src="${pageContext.request.contextPath}/static/js/plugins/dataTables/jquery.dataTables.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/plugins/dataTables/dataTables.bootstrap.js"></script>
    
     <script>
    $(document).ready(function() {
        $('#dataTables-example').dataTable();
    });
    </script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="${pageContext.request.contextPath}/static/js/plugins/metisMenu/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="${pageContext.request.contextPath}/static/js/sb-admin-2.js"></script>
</div>
</body>
<script type = "text/javascript" >
var statusCode = '${resultCode}';
displayAlert(statusCode);
disableBackButton();
</script>
</html>
