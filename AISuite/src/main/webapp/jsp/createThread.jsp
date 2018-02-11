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
<!-- <div id="wrapper"> -->
<!--  <div id="page-wrapper"> -->
        
            	<%-- <div style="margin-top:60px;"><img src="${pageContext.request.contextPath}/static/images/threads.png" width: 20%; height: 20%/></div> --%>
                  <div class="panel-body">
                    <form:form method="POST" commandName="fiProviderThreadForm" action="${pageContext.request.contextPath}/fiprovider/submitProviderThread">
                     <div class="panel panel-default">
                        <div class="panel-heading">
                        </div>
                        <div class="panel-body">
                            <div class="row">
                            <div class="col-lg-6"><b>
                            <p style="font-size:50px; color:#33bbff; padding:7px 5px 0 20px;">Create Thread</p>
                   			 <tbody>
                   			 <div class="form-group input-group" >
                   			 <fieldset>
                             
                             <form:input type="hidden" path="id"  id="id"  class="form-control"/>
                                 <label>Thread Name*</label>
                             	<div class="form-group" >
                             	<form:input path="threadName"  id="threadName"  placeholder="Enter Thread Name" maxlength="100" class="form-control"/>
                           		</div><form:errors class="help-block" style="color:#F00;" path="threadName"  />
         						<p class="help-block" style="color:#F00;"></p>
                 				<div class="form-group">
                                    <label>Thread Type</label>
                 					<form:input class="form-control" placeholder="Enter Thread Type" maxlength="100" path="threadType" id="threadType"/>
                 					<form:errors class="help-block" style="color:#F00;" path="threadType" />
                 				</div>
                 				<div class="form-group">
                                    <label>Description</label>
                 					<form:input class="form-control" placeholder="Enter Thread Description" maxlength="100" path="des" id="des"/>
                 					<form:errors class="help-block" style="color:#F00;" path="des" />
                 				</div>
                 				<div class="form-group">
                                    <label>Skills required*</label>
                                   <form:textarea class="form-control" placeholder="Enter Skills" maxlength="100" path="skills" id="skills"/>
                                    <form:errors class="help-block" style="color:#F00;" path="skills" />
                                </div>
                                <div class="form-group">
                                    <label>SPOC*</label>
                 					<form:input class="form-control" placeholder="Enter SPOC's Id" maxlength="100" path="spoc" id="spoc"/>
                 					<form:errors class="help-block" style="color:#F00;" path="spoc" />
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
				   <input type="button" class="btn btn-primary" value="Back" onclick="javascript:window.location.href ='${pageContext.request.contextPath}/fiprovider/providerPage'"/>&nbsp;&nbsp;&nbsp;
				   <input type="submit"  class="btn btn-primary" value="Submit"/>&nbsp;&nbsp;&nbsp;
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
