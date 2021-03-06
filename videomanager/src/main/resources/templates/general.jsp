<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
	
	<div class="" ng-controller="GeneralController">
	  <div class="panel-group" id="accordiongeneral">
	    <div class="panel panel-default">
	      <div class="panel-heading">
	        <h4 class="panel-title">
	          <a data-toggle="collapse" data-parent="#accordiongeneral" href="#fileslist">Folder List ...</a>
	        </h4>
	      </div>
	      <div id="fileslist" class="panel-collapse collapse in">
	        <div class="panel-body folderparent">
	        	<div class="folderitemsparent filefoldercontainer">
					<ul class="folderparent">
					<c:forEach items="${folders}" var="folderitem" varStatus="status">
						<li class="folderitem" data-path="${folderitem.getPath()}" ng-click="loadFiles(${folderitem.getId()})">${folderitem.getName()} </li>
					</c:forEach>
					</ul>
	        	
	        	</div>
	        	<div class="fileitemsparent filefoldercontainer" watchtocompilefilelist>
	        		
				</div>			
			</div>
	      </div>
	    </div>
	    <div class="panel panel-default">
	      <div class="panel-heading">
	        <h4 class="panel-title">
	          <a data-toggle="collapse" data-parent="#accordiongeneral" href="#subtitleslist">Subtitle List ...</a>
	        </h4>
	      </div>
	      <div id="subtitleslist" class="panel-collapse collapse">
	        <div class="panel-body folderparent">
	        	<div class="folderitemsparent filefoldercontainer">
					<ul class="folderparent">
					<c:forEach items="${folders}" var="folderitem" varStatus="status">
						<li class="folderitem" data-path="${folderitem.getPath()}" ng-click="loadSubtitles(${folderitem.getId()})">${folderitem.getName()} </li>
					</c:forEach>
					</ul>
	        	
	        	</div>
	        	<div class="subtitleitemsparent filefoldercontainer" >
	        		
				</div>			
			</div>
	      </div>
	    </div>
	    <div class="panel panel-default">
	      <div class="panel-heading">
	        <h4 class="panel-title">
	          <a data-toggle="collapse" data-parent="#accordiongeneral" href="#general3">Collapsible Group 3</a>
	        </h4>
	      </div>
	      <div id="general3" class="panel-collapse collapse">
	        <div class="panel-body">Collapsible Group 3</div>
	      </div>
	    </div>
	  </div> 
	  
	  <script type="text/ng-template" id="getsubtitle.html">

    <md-dialog aria-label="Zip File Url ..." aria-title="Zip File Url ..." style="width:700px;">
		<md-toolbar>
    		<div class="md-toolbar-tools">
      		<md-button aria-label="Close ..." ng-click="cancel()" class="md-icon-button">
        		<md-icon md-svg-src="/jvideo/resources/images/close_48.svg" class="fa fa-times fa-2x"></md-icon>
      		</md-button>
      		<h2 flex="flex">Choose Zip File Url ...</h2>
    		</div>
  		</md-toolbar>

    	<div layout-padding layout="column">
      		<md-input-container>
        		<label>Subtitle Zip File Url ...</label>
        		<input ng-model="subtitlezipurl" type="text" md-autofocus autofocus id="txtsubtitlezipurl" />
        	</md-input-container>
        
			<md-dialog-actions layout="row">
        		<md-button aria-label="Cancel ..." ng-click="cancel()" >Cancel</md-button>
				<md-button aria-label="Save ..." ng-click="addSubtitle()" class="md-primary">Save</md-button>
			</md-dialog-actions>

      </div>
    </md-dialog>
  </script>
  
	  
	  <script type="text/ng-template" id="addsubtitleproc.html">

    <md-dialog aria-label="Add To Subtitle Process?" aria-title="Add To Subtitle Process?" style="width:700px;">
		<md-toolbar>
    		<div class="md-toolbar-tools">
      		<md-button aria-label="Close ..." ng-click="cancel()" class="md-icon-button">
        		<md-icon md-svg-src="/jvideo/resources/images/close_48.svg" class="fa fa-times fa-2x"></md-icon>
      		</md-button>
      		<h2 flex="flex">Add To Subtitle Process?</h2>
    		</div>
  		</md-toolbar>

    	<div layout-padding layout="column">
      		<md-input-container>
        		<label>Language</label>
				<md-select ng-model="subtitlelang">
					<md-option ng-repeat="option in languagelist" ng-value="option.value">
						{{option.title}}
					</md-option>
				</md-select>
        	</md-input-container>
        
		<md-dialog-actions layout="row">
       	<md-button ng-click="cancel()" >Cancel</md-button>
			<md-button aria-label="Add ..." ng-click="addSubtitle()" class="md-primary">Add</md-button>
 		</md-dialog-actions>

      </div>
    </md-dialog>
  </script>
  
	  
	  <script type="text/ng-template" id="editsubtitle.html">

    <md-dialog aria-label="Edit Subtitle ..." aria-title="Edit Subtitle ..." style="width:600px; height:500px;">
		<md-toolbar>
    		<div class="md-toolbar-tools">
      		<md-button aria-label="Close ..." ng-click="close()" class="md-icon-button">
        		<md-icon md-svg-src="/jvideo/resources/images/close_48.svg" class="fa fa-times fa-2x"></md-icon>
      		</md-button>
      		<h2 flex="flex">Edit Subtitle ...</h2>
    		</div>
  		</md-toolbar>

		<div layout-padding layout="column">
      		<md-input-container style="border-bottom:solid gray 1px; padding:5px; margin-bottom: 10px; margin-top: 10px;">
				<md-button aria-label="Convert to UTF-8 From Ascii ..." ng-click="convertFromAscci($event)" style="border:1px solid gray; background:lightgreen; ">Conver From Ascii</md-button>
				<md-button aria-label="Convert to UTF-8 From Unicode ..." ng-click="convertFromUnicode($event)" style="border:1px solid gray; background:lightgreen; ">Conver From Unicode</md-button>
        	</md-input-container>
        
      		<md-input-container style="border-bottom:solid gray 1px;">
        		<label>Add Seconds: </label>
        		<input ng-model="addseconds" type="number" style="width:70%; float:left;" />
				<md-button aria-label="Add Seconds ..." ng-click="addsecs()" style="border:1px solid gray; background:lightgreen; float:right;">Add</md-button>
				<div style="clear:both;"></div>
        	</md-input-container>
        
			<md-dialog-actions layout="row" style="top:430px;position:absolute;width: 95%;">
        		<md-button aria-label="Close ..." class="md-primary" style="float:  right;" ng-click="close()" >Close</md-button>
			</md-dialog-actions>

		</div>

    </md-dialog>
  </script>
	  
	</div>	
