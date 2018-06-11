<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>


	
	<div class="" ng-controller="ToolsController">
	  <div class="panel-group" id="accordiontools">
	    
	    <div class="panel panel-default">
	      <div class="panel-heading">
	        <h4 class="panel-title">
	          <a data-toggle="collapse" data-parent="#accordiontools" href="#pathsub">Path Subtitle List ...</a>
	        </h4>
	      </div>
	      <div id="pathsub" class="panel-collapse collapse in">
			<div class="panel-body processparent" ng-init="loadPathSubtitle()">
		        <div class="btn-group btn-group-sm" role="group" aria-label="..." style="width:80%; margin-bottom:10px;">
					<button type="button" class="btn btn-secondary" ng-click="addPathSubtitle()"><span class="glyphicon glyphicon-plus"></span></button>
				</div>
		       	<div class="pathsubitemsparent filefoldercontainer">
		       		
				</div>			
			</div>
	      </div>
	    </div>
	    <div class="panel panel-default">
	      <div class="panel-heading">
	        <h4 class="panel-title">
	          <a data-toggle="collapse" data-parent="#accordiontools" href="#tools2">Group 2</a>
	        </h4>
	      </div>
	      <div id="tools2" class="panel-collapse collapse">
	        <div class="panel-body">Collapsible Group 2</div>
	      </div>
	    </div>

	    <div class="panel panel-default">
	      <div class="panel-heading">
	        <h4 class="panel-title">
	          <a data-toggle="collapse" data-parent="#accordiontools" href="#tools3">Group 3</a>
	        </h4>
	      </div>
	      <div id="tools3" class="panel-collapse collapse">
	        <div class="panel-body">Collapsible Group 3</div>
	      </div>
	    </div>
	  </div>

<script type="text/ng-template" id="addpathsubtitle.html">

    <md-dialog aria-label="Add Path / Subtitle ..." aria-title="Add Path / Subtitle ..." style="width:600px; height:350px;">
		<md-toolbar>
    		<div class="md-toolbar-tools">
      		<md-button aria-label="Close ..." ng-click="close()" class="md-icon-button">
        		<md-icon md-svg-src="/jvideo/resources/images/close_48.svg" class="fa fa-times fa-2x"></md-icon>
      		</md-button>
      		<h2 flex="flex">Add Path / Subtitle ...</h2>
    		</div>
  		</md-toolbar>

		<div layout-padding layout="column">
        
      		<md-input-container>
        		<label>Subtitle Url ...</label>
        		<input ng-model="subtitleurl" type="text" md-autofocus autofocus id="txtsubtitlezipurl" />
        	</md-input-container>
        
      		<md-input-container>
        		<label>Local Path ...</label>
        		<input ng-model="localpath" type="text" id="txtlocalpath" />
        	</md-input-container>
        
			<md-dialog-actions layout="row">
        		<md-button aria-label="Cancel ..." ng-click="close()" >Cancel</md-button>
				<md-button aria-label="Save ..." ng-click="addPathSubtitle()" class="md-primary">Add</md-button>
			</md-dialog-actions>

		</div>

    </md-dialog>
</script>