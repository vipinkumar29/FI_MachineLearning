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

    <!-- Custom CSS -->
    <link href="${pageContext.request.contextPath}/static/css/beep.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="${pageContext.request.contextPath}/static/font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/backbuttondisable.js"></script>
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">
		        .fullscreen {
		  position: fixed;
		  top: 0;
		  left: 0;
		  bottom: 0;
		  right: 0;
		  overflow: auto;
		  background: grey; /* Just to visualize the extent */
		  
		}
		
</style>
<style>
table, th, td {
    border: 1px solid black;
}
</style>


</head>

<body style="background:url(${pageContext.request.contextPath}/static/images/business.jpg) no-repeat center center fixed;
  -webkit-background-size: cover;
  -moz-background-size: cover;
  -o-background-size: cover;
  background-size: cover;">

 <div class="fullscreen">
<!-- <div id="wrapper"> -->
<!--  <div id="page-wrapper"> -->
        
            	<%-- <div style="margin-top:60px;"><img src="${pageContext.request.contextPath}/static/images/threads.png" width: 20%; height: 20%/></div> --%>
                  <div class="panel-body">
                    <form:form method="POST" commandName="fiThreadsForm" action="${pageContext.request.contextPath}/fiseeker/submitThread">
                     <div class="panel panel-default">
                        <div class="panel-heading">
                        </div>
                        <div class="panel-body">
                            <div class="row">
                            <div class="col-lg-6"><b>
                            <p style="font-size:50px; color:#33bbff; padding:7px 5px 0 20px;">Thread Overview</p>
                   			 <tbody>
                   			 <div class="form-group input-group" >
                   			 <fieldset>
                             
                             <form:input type="hidden" path="id"  id="id"  class="form-control" readonly="true"/>
                                 <label>Thread Name</label>
                             	<div class="form-group" >
                             	<form:input path="threadName"  id="threadName"  class="form-control"  readonly="true"/>
                           		</div><form:errors class="help-block" style="color:#F00;" path="threadName"  />
         						<p class="help-block" style="color:#F00;"></p>
                 				<div class="form-group">
                                    <label>Thread Type</label>
                 					<form:input class="form-control" path="threadType" id="threadType" readonly="true"/>
                 					<form:errors class="help-block" style="color:#F00;" path="threadType" />
                 				</div>
                 				<div class="form-group">
                                    <label>Description</label>
                 					<form:input class="form-control" path="des" id="des" readonly="true"/>
                 					<form:errors class="help-block" style="color:#F00;" path="des" />
                 				</div>
                 				<div class="form-group">
                                    <label>Skills</label>
                                   <form:textarea class="form-control" path="skills" id="skills" readonly="true"/>
                                    <form:errors class="help-block" style="color:#F00;" path="skills" />
                                </div>
                                <div class="form-group">
                                    <label>SPOC</label>
                 					<form:input class="form-control" path="spoc" id="spoc" readonly="true"/>
                 					<form:errors class="help-block" style="color:#F00;" path="spoc" />
                 				</div>
                 				<div class="form-group"><b>
                                    <label>Your Id</label>
                                    <form:input  path="seekerId" id ="seekerId" class="form-control" placeholder="Enter your Id (abc@deloitte.com)"/>
                                    <p class="help-block" style="color:#F00;"><form:errors path="seekerId" /></p>
                                 </div> 
                                        </fieldset>
                                        </div>
                                <br/>
                            </tbody>
                        <!-- /.panel-heading -->
							<!-- <div class="panel-body"> -->
							</div>
				   </div>
				   </div>
				   </div>
				   <input type="button" class="btn btn-primary" value="Back" onclick="javascript:window.location.href ='${pageContext.request.contextPath}/fiseeker/threads'"/>&nbsp;&nbsp;&nbsp;
				   <input type="submit"  class="btn btn-primary" value="Submit"/>
				        </form:form>
                    </div>
                    
                   <!--  </div> -->
            
 </div>
 

    <!-- jQuery Version 1.11.0 -->
    <script src="${pageContext.request.contextPath}/static/js/jquery-1.11.0.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="${pageContext.request.contextPath}/static/js/plugins/metisMenu/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="${pageContext.request.contextPath}/static/js/sb-admin-2.js"></script>

</body>
<script type = "text/javascript" >
 disableBackButton();
</script>
</html>
