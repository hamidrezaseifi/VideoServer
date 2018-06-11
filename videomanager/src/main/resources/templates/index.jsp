<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="en" ng-app="myApp">
<head>
	<title>JVideo ...</title>

	<spring:url value="/resources/css/index.css" var="IndexCSS" />
	<link href="${IndexCSS}" rel="stylesheet" />

	<spring:url value="/resources/angular/angular-material.min.css" var="AngularJsMaterialCSS" />
	<link href="${AngularJsMaterialCSS}" rel="stylesheet" />


	<spring:url value="/resources/angular/angular.min.js" var="AngularJs" />
	<spring:url value="/resources/angular/angular-animate.min.js" var="AngularJsAnimate" />
	<spring:url value="/resources/angular/angular-aria.min.js" var="AngularJsAria" />
	<spring:url value="/resources/angular/angular-messages.min.js" var="AngularJsMessages" />
	<spring:url value="/resources/angular/angular-material.min.js" var="AngularJsMaterial" />
	<script src="${AngularJs}"></script>
	<script src="${AngularJsAnimate}"></script>
	<script src="${AngularJsAria}"></script>
	<script src="${AngularJsMessages}"></script>
	<script src="${AngularJsMaterial}"></script>

  
	<spring:url value="/resources/js/index.js" var="IndexJs" />
	<script src="${IndexJs}"></script>

	<spring:url value="/resources/js/general.js" var="GeneralJs" />
	<script src="${GeneralJs}"></script>

	<spring:url value="/resources/js/process.js" var="ProcessJs" />
	<script src="${ProcessJs}"></script>

	<spring:url value="/resources/js/tools.js" var="ToolsJs" />
	<script src="${ToolsJs}"></script>

	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>

<body ng-controller="BodyController">
	
	<div class="main-container">
		<ul id="tabul" class="nav nav-tabs">
		<c:forEach items="${tabs}" var="tabitem" varStatus="status">
			<li ${status.first ? 'class="active"' : ''}>
				<a data-toggle="tab" href="#maintab${status.index}">${tabitem.getName()}</a>
			</li>
		</c:forEach>
		</ul>
		
		<div class="tab-content">
		<c:forEach items="${tabs}" var="tabitem" varStatus="status">
			<div id="maintab${status.index}" watchtocompile class="tab-pane content-container fade ${status.first ? 'in active' : ''}">
			
			<script type="text/javascript">loadUrlList.push("${tabitem.getUrl()}");</script>
			</div>
			
		</c:forEach>
		</div>
	
	</div>
	
	<div id="dvloading" class="loadingover" ng-show="showloading"></div>
	  	
</body>
</html>