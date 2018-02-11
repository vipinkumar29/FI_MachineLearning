<%@ page language="java" pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0
	response.setDateHeader("Expires", 0); //prevent caching at the proxy server
%>

   <!-- <nav class="navbar navbar-default navbar-static-top box-shadow" role="navigation" style="margin-bottom: 0; background:#fff;"> -->
           <!--  <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index.html"></a>
            </div> -->
            <!-- /.navbar-header -->

            <%-- <ul class="nav navbar-top-links navbar-right">
                
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                       ${username} &nbsp;  <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                        <li><a href="${pageContext.request.contextPath}/logout"><i class="fa fa-sign-out"></i>&nbsp; Sign Out</a>
                        </li>
                    </ul>
                    <!-- /.dropdown-user -->
                </li>
            </ul> --%>
            <!-- /.navbar-top-links -->

            <div class="navbar-default sidebar" role="navigation">
            <div class="sidebar-nav navbar-collapse" style="font-weight: bold; color:#ffffff;">
                    <ul class="nav" id="side-menu">
                        <!--<li class="sidebar-search">
                            <div class="input-group custom-search-form">
                                <input type="text" class="form-control" placeholder="Search...">
                                <span class="input-group-btn">
                                <button class="btn btn-default" type="button">
                                    <i class="fa fa-search"></i>
                                </button>
                            </span>
                            </div>
                            <!-- /input-group 
                        </li>-->
                        <br/>
                        <%-- <div align="center"><img src="${pageContext.request.contextPath}/static/images/beep.png"/></div>
                        <div class="clearfix"><br/></div> --%>
                        
                        <li><div align="center" style="padding: 20px;"><img src="${pageContext.request.contextPath}/static/images/D.png" width="100" height="100"/></div></li>
                        <li></li>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<P>"Do That Matter"<P><br>
                        <p>"Innovate Contribute and Make an Impact"</p>
                        <%-- <li>
                        <p style="font-size:12px; color:#ffffff; padding:7px 5px 0 20px;">NAVIGATIONS</p>
                    	</li>

                       <li>
                             <a style="font-size:12px; color:#ffffff; padding:7px 5px 0 20px;" href="${pageContext.request.contextPath}/load/Dashboard">DASHBOARD</a>

                        </li> --%>
                                             
                     <li>
                        <%-- <a href="#">SETUP<span class="fa arrow"></span></a>
                    	<!-- <p style="font-size:12px; color:#ffffff; padding:7px 5px 0 20px;">SETUP</p> -->
                    	
                    	 <ul class="nav nav-second-level">
                    	 	<li>
                       			 <a href="${pageContext.request.contextPath}/backOfficeUser/createUser">User</a>
                    		</li>
 							<li>
		                        <a href="${pageContext.request.contextPath}/nfcReader/addNewCardReaderMaster">NFC Reader Master</a>
		                    </li>
		                    
		                      <li>
		                        <a href="${pageContext.request.contextPath}/terminal/terminalMaster.html">Terminal Master</a>
		                    </li>
		                    <li>
		                        <a href="${pageContext.request.contextPath}/nfcCardMasterDetails.html">NFC Card Master</a>
		                    </li>
		                     <li>
		                        <a href="${pageContext.request.contextPath}/fareDenomination">Denomination Master</a>
		                    </li>
		                     <li>
		                        <a href="${pageContext.request.contextPath}/seasonTicket/seasonTicketType">SeasonTicket Type</a>
		                    </li>
		                     </li> --%>
		                     <%--  <li>
		                        <a href="${pageContext.request.contextPath}/marqueText/loadMarqueText">Marquee Text</a>
		                    </li>
		                     <li>
		                        <a href="${pageContext.request.contextPath}/marqueText/loadList">Passing List</a>
		                    </li>
		                  <li>
		                        <a href="${pageContext.request.contextPath}/master/stationMaster">Station Master</a>
		                     --%><%-- </li>
		                    
		                    <li>
		                        <a href="${pageContext.request.contextPath}/routeAndStation/routeAndStationConfiguration">Route and Station Configuration</a>
		                    </li>
 						</ul>
 						</li>
                    
                     <li>
                        <a href="#">AGENT MANAGEMENT<span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">    
                    <li>
                        <a href="${pageContext.request.contextPath}/agentEnrollment/agentenrollment.htm">Agent Enrollment</a>
                    </li>
                     <li>
                        <a href="${pageContext.request.contextPath}/terminalAndNFCAllocation.htm">Terminal & NFC Allocation</a>
                    </li>
                   <!--  <li>
                        <a href="agentcredit.html">Agent Credit</a>
                    </li> -->
                  
                     <li>
                    	<a href="${pageContext.request.contextPath}/agentReport/transHistory.html">Agent Record Report</a>
                     </li>
                      <li>
                    	<a href="${pageContext.request.contextPath}/cardAllocation/allocationHistory.html">Card Allocation Report</a>
                     </li>
                   <!--  <li>
                        <a href="card.html">Card Allocation</a>
                    </li> -->
                     </ul>                
                    </li>
                    
                        
                    <li>
                        <a href="#">VEHICLE TERMINAL MANAGEMENT<span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                     <li>
                        <a href="${pageContext.request.contextPath}/vehicle/vehicleEnrollment">Vehicle Enrollment</a>
                    </li>                   
                    
                     <li>
                        <a href="${pageContext.request.contextPath}/terminalAndNFCAllocation.htm">Terminal & NFC Allocation</a>
                    </li>
                     <li>
                    <a href="${pageContext.request.contextPath}/vehicleRouteConfiguration.html">Vehicle Route Configuration</a>
                    </li>
                      <li>
                        <a href="${pageContext.request.contextPath}/fareCollection/transactionHistory.html">Fare Collection Report</a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/hourlyReport/transactionHistory.html">Hourly Report</a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/distanceFare/transaction.html">Distance Based Report</a>
                    </li>
                   <!--  <li>
                        <a href="jeepney.html">Jeepney Enrollment</a>
                    </li> -->
                  
                   <!--  <li>
                        <a href="jeepneyroute.html">Route & Fare Allocation</a>
                    </li> -->
                    </ul></li>
                    
                    <li>
                        
                        <a href="#">ROUTE & FARE MANAGEMENT<span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                     
                    <li>
                        <a href="${pageContext.request.contextPath}/routeStage.html">Route Stage</a>
                    </li>
                   
                    <li>
                        <a href="${pageContext.request.contextPath}/routeFare/routeAndFareConfigurationDetails.html">Route & Fare Configuration</a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/vehicleRoute/vehicleRouteConfiguration.html">Vehicle Route Configuration</a>
                    </li>
                   <!--  <li>
                        <div style="border:1px solid #07080b;"></div>
                    	<p style="font-size:12px; color:#ffffff; padding:7px 5px 0 20px;">
                    	<a href="settlement.html">SETTLEMENT</a></p>
                    </li> -->
                   <li>
                             <a href="${pageContext.request.contextPath}/flipApp/loadFlipApp">FlipApp</a>
 --%>
                        </li>
                    
                    
                    </ul>
                </div>
                <!-- /.sidebar-collapse -->
            </div>
            <!-- /.navbar-static-side -->
        </nav>
