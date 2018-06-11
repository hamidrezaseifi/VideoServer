<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
	
	<div class="" ng-controller="ProcessController">
		<div class="panel-body processparent" ng-init="loadProcess()">
	        	
	       	<div class="processitemsparent filefoldercontainer" watchtocompilefilelist>
	       		{{filesList}}
			</div>			
		</div>
	  
	  
  
	  
	</div>	
