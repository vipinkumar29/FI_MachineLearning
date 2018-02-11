<html>
	<head>
		<!-- <link rel="stylesheet" type='text/css' href="./css/bootstrap.css" /> -->
		<!-- <link rel="stylesheet" type='text/css' href="./css/styles.css" /> -->
		<link rel="stylesheet" type='text/css' href="${pageContext.request.contextPath}/static/css/styles.css" >
		<link rel="stylesheet" type='text/css' href="${pageContext.request.contextPath}/static/css/bootstraps.css" rel="stylesheet">
		<title>DPearl</title>
	</head>
	<body>
		<header>
			<div class="bct-header clearfix">
				<div class="col-sm-2">
					<div class="title">
						<a href="https://www2.deloitte.com/global/en/pages/about-deloitte/articles/about-deloitte.html">
						<img src="<%=request.getContextPath()%>/static/images/deloitte-logo.svg" alt="Home" title="Home"/>
							<!-- <img src="./images/deloitte-logo.svg" alt="Home" title="Home"/> -->
						</a>
					</div>
				</div>
				<div class="col-sm-8 centerText">
					<span style="margin-right: 24px;">Human Capital</span>
				</div>
				<div class="col-sm-2">
					<a href="javascript:;">
						<div class="logo"></div>
					</a>
				</div>
				<div class="col-xs-12 bct-menu-holder icon-holder-unauth clearfix">
					<span>Deloitte Pension Accounting and Reporting Logic</span>
					<div class="pull-right">
						<a href="javascript:;" class="helpIcon"><img src="<%=request.getContextPath()%>/static/images/icons/help.png" alt="Help" title="Help"/></a>
						<a href="javascript:;" class="profileIcon"><img src="<%=request.getContextPath()%>/static/images/icons/userProfile.png" alt="Profile" title="Profile"/></a>
					</div>
				</div>
			</div>
		</header>
		<div style="min-height: 570px">
			<div id="view" class="unauth-page" style="margin-top: 135px;">
				<div class="aaa_image">
					<img src="<%=request.getContextPath()%>/static/images/Login.gif" />
				</div>
				<div class="public-wrap login-page">
					<div class="panel">
						<div class="panel-heading">
							<h4>Welcome to DPearl</h4>
							<h5>Authorization is required to login</h5>
							<h5>Please enter your identify details below</h5>
						</div>
						<div class="panel-body">
							<div class="row">
								<div class="col-sm-12">
									<form method="POST" commandName="loginForm" action="${pageContext.request.contextPath}/fi/landingPage" id="login-form" role="form" class="">
										<div class="form-group row">
											<label for="email" class="control-label col-md-4">Identity</label>
											<div class="col-md-8">
												<input id="email" novalidate type="email" name="email" class="form-control" placeholder="Email" ng-model="email" />
											</div>
										</div>
										<div class="form-group row">
											<label for="password" class="control-label col-md-4">Password</label>
											<div class="col-md-8">
												<input id="password" type="password" name="password" class="form-control" placeholder="Password" ng-model="password" />
											</div>
										</div>
										<div class="form-group row">
											<label for="role" class="control-label col-md-4">Connect As</label>
											<div class="col-md-8">
												<select path ="userType" style="color: black;">
												<form:options items=[userType,] itemLabel="fullRouteCode" itemValue="routeCode"/>
													<!-- <option itemValue="userType">Select One</option> 
													<option itemValue="userType">Super User</option> 
													<option itemValue="userType">Actuary User</option>
													<option itemValue="userType">Client User</option> -->
												</select>
											</div>
										</div>
										<div class="form-group pull-left remember-me">
											<input path ="rememberMe" type="checkbox" name="rememberMe"  />
											<label id="rememberMe" for="rememberMe">Remember Me</label>
										</div>
										<div class="form-group pull-right">
											<button id="loginBtn" ng-click="login()" ng-disabled="loggingIn" class="form-control btn btn-primary">Log In</button>
										</div>
									</form>
								</div>
							</div>
						</div>
						<div class="panel-footer">
							<div class="form-group">
								<a href="javascript:;">Forgot Password?</a>
								<a href="javascript:;" class="pull-right">Resend Activation Mail</a>
							</div>
						</div>
					</div>
					<!-- <form method='post' action='#/home'></form> -->
				</div>
			</div>
		</div>
		<footer class="clearfix">
			<p class="pull-left"><a href="javascript:;">Home</a> / <a href="javascript:;">Terms of Use</a> / <a href="javascript:;">Privacy</a></p>
		   	<p class="pull-right">&copy; 2016. See Terms of Use for more information</p>
			<p class="clearfix">Certain information, such as Protected Health Information that is regulated under US law, should not be uploaded to Deloitte Pearl. 
			Please direct any questions to your legal advisor, your Engagement Partner or, for practitioners of the US Firms, to the Office of the Chief Confidentiality Officer.</p>
			<p>Deloitte refers to one or more of Deloitte Touche Tohmatsu Limited, a UK private company limited by guarantee ('DTTL'), its network of member firms, 
			and their related entities. DTTL and each of its member firms are legally separate and independent entities. 
			DTTL (also referred to as 'Deloitte Global') does not provide services to clients. Please see <a href="http://www.deloitte.com/about">www.deloitte.com/about</a> for a more detailed description of DTTL and its member firms.</p>
		</footer>
	</body>
</html>