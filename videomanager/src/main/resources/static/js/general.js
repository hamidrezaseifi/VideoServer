/**
 * 
 */

var isGeneralLoaded = false;



brainApp.controller('GeneralController', ['$scope', '$http', '$sce', '$element', '$compile', '$mdDialog' ,'$httpParamSerializerJQLike', function ($scope, $http, $sce, $element, $compile, $mdDialog, $httpParamSerializerJQLike) {

	//alert("load general");

	if(!isGeneralLoaded){
		isGeneralLoaded = true;
		$scope.myMessage = "list of files ...";
		$scope.isGeneral = true;
		
		
		$scope.loadFiles = function(id) {
	        
			$scope.$parent.showloading = true;
	        $http.get("/jvideo/filelist/" + id).then(function(response) {
	        	
	        	var parent = angular.element(document.querySelector('.fileitemsparent'));
	        	parent.html("");
	        	var element = "";
	            for(index in response.data){
	            	var item = response.data[index];
	            	element += "<div class='fileitem";
	            	if(item.isConverted || item.hasConverted){
	            		element += " done";
	            	}
	            	else{
	            		element += " ready";
	            	}
	            	element += "' >";
	            	element += item.Name;
            		element += "<a class='fileitembutton' href='localexplorer:" + item.FolderPath + "' target='_self'>Folder</a>";
	            	if(!item.hasSubtitle){
	            		element += "<a class='fileitembutton findsub' href='" + item.SubtitleUrl + "' target='_blank'>Find Subtitle</a>";
	            		element += '<a class="fileitembutton addsub" href="#" ng-click="addSubtitle($event, \'' + item.Base64SubtitlePath + '\', \'' + item.Base64Path + '\')">Add Subtitle</a>';
	            	}
	            	else{
	            		element += '<a class="fileitembutton addsubproc" href="#" ng-click="addSubtitleProcess($event, \'' + item.Base64Path + '\')">Add Subtitle Process</a>';
	            	}
	            		
	            	element += "</div>";
	            	
	            }
	            
	            var $el = parent.append(element);
	                        	
            	$compile($el)($scope);
	            
	            
	            $scope.$parent.showloading = false;
	        });
	    };
	    
		$scope.loadSubtitles = function(id) {
	        
			$scope.$parent.showloading = true;
	        $http.get("/jvideo/sublist/" + id).then(function(response) {
	        	
	        	var parent = angular.element(document.querySelector('.subtitleitemsparent'));
	        	parent.html("");
	        	var element = "";
	            for(index in response.data){
	            	var item = response.data[index];
	            	element += "<div class='fileitem'";
	            	
	            	element += " >";
	            	element += item.Name;
	            	element += "<a class='fileitembutton' href='localexplorer:" + item.FolderPath + "' target='_self'>Folder</a>";
            		element += '<a class="fileitembutton addsubproc" href="#" ng-click="editSubtitle($event, \'' + item.Base64Path + '\')">Edit</a>';
	            		
	            	element += "</div>";
	            	
	            }
	            
	            var $el = parent.append(element);
	                        	
            	$compile($el)($scope);
	            
	            
	            $scope.$parent.showloading = false;
	        });
	    };
	    
	    $scope.doAddSubtitle = function(){
	    	
		    $mdDialog.cancel();
	    };

	    $scope.editSubtitle = function(ev, base64path){
	    	
	    	$mdDialog.show({
	            targetEvent: ev,
	            templateUrl: 'editsubtitle.html',
	            controller: EditSubtitleController,
	            locals: { base64path: base64path, targetEvent: ev}
	          });
	    	
	    	
		    
	    };
	    
	    $scope.addSubtitle = function(ev, ibase64subpath, ibase64path){
	    	
	    	$mdDialog.show({
	            targetEvent: ev,
	            templateUrl: 'getsubtitle.html',
	            controller: FindSubtitleController,
	            locals: { ibase64subpath: ibase64subpath, ibase64path:ibase64path, 
	            	linkbutton: ev.currentTarget,targetEvent: ev, general: $scope}
	          });
	        
	        
	       
	        
	        
	    	

	    };

	    $scope.addSubtitleProcess = function(ev, ibase64path){
	    	
	    	 $mdDialog.show({
		            targetEvent: ev,
		            templateUrl: 'addsubtitleproc.html',
		            controller: AddSubtitleProcessController,
		            locals: { ibase64path:ibase64path, general: $scope }
		          });
	        
	        
	       
	        
	        
	    	

	    };
	    
	    $scope.loadProc = function(){
	    	
	    	for(var cs = $scope.$parent.$$childHead; cs; cs = cs.$$nextSibling) {
				 
				 if(typeof cs.isProcess != "undefined"){
					 
					 cs.loadProcess();
					 break;
				 }
			 }
	    }
	    
	    $scope.selectProcessTab = function(){
	    	
	    	$scope.$parent.selectProcessTab();
	    	
	    }
	    
	    
	    
	}
	
	function FindSubtitleController($scope, $mdDialog, $http, ibase64subpath, ibase64path, linkbutton, targetEvent, general) {
		
		$scope.subtitlezipurl = "";
		$scope.subtitlelang = "per";

		angular.element(document.querySelector('#txtsubtitlezipurl')).focus();
		
	    $scope.hide = function() {
	      $mdDialog.hide();
	    };

	    $scope.cancel = function() {
	      $mdDialog.cancel();
	    };

	    $scope.addSubtitle = function(){
	          
	    	if($scope.subtitlezipurl == ""){
	    		return;
	    	}
	    	else{
	    		
	    		var postdata = {path64: ibase64path, subpath64: ibase64subpath, zipurl: $scope.subtitlezipurl };
	    		
	    		$http({
	    		     method: 'POST',
	    		     url: 'addsubfromzip',	    		     
	    		     data: $httpParamSerializerJQLike(postdata) ,
	    		     headers: {'Content-Type': 'application/x-www-form-urlencoded'}
	    		 }).then(function(response){
	    			 
	    			 if(response.data.result == "ok"){
	    				 
	    				 $(linkbutton).parent().children(".addsub, .findsub").remove();
	    				 $mdDialog.hide();	
	    				 
	    				 $mdDialog.show({
	    			            targetEvent: targetEvent,
	    			            templateUrl: 'addsubtitleproc.html',
	    			            controller: AddSubtitleProcessController,
	    			            locals: { ibase64path:ibase64path, general: general }
	    			          });
	    				 
	    			 }
	    			 
	    		 }, function(response){
	    			 alert("error: " + response);
	    		 });
	    		
	    		
	    	}
		    
	    };
	    
	  }	
	
	
	function AddSubtitleProcessController($scope, $mdDialog, $http, ibase64path, general) {
		
		$scope.subtitlelang = "per";
		$scope.languagelist = [{title: "Persian", value: "per"}, {title: "English", value: "en"}, {title: "Deutsch", value: "ger"}, ];
		
		
	    $scope.hide = function() {
	      $mdDialog.hide();
	    };

	    $scope.cancel = function() {
	      $mdDialog.cancel();
	    };

	    $scope.addSubtitle = function(){
	          
	    		var postdata = {path64: ibase64path, lang: $scope.subtitlelang };
	    		
	    		$http({
	    		     method: 'POST',
	    		     url: 'addsubproc',	    		     
	    		     data: $httpParamSerializerJQLike(postdata) ,
	    		     headers: {'Content-Type': 'application/x-www-form-urlencoded'}
	    		 }).then(function(response){
	    			 
	    			 
	    			 if(response.data.result == "ok"){
	    				 
	    				 $mdDialog.hide();	
		    			 general.loadProc();
		    			 general.selectProcessTab();
		    			 
	    				 
	    			 }
	    			 else{
	    				 alert(response.data.result);
	    			 }
	    			 
	    		 }, function(response){
	    			 alert("error: " + response.data);
	    		 });
	    		
	    		
	    	
		    
	    };
	    
	  }	
	
	function EditSubtitleController($scope, $mdDialog, $http, $httpParamSerializerJQLike, base64path, targetEvent) {
		
		$scope.addseconds = 0;

	    $scope.hide = function() {
	      $mdDialog.hide();
	    };

	    $scope.close = function() {
	      $mdDialog.cancel();
	    };

	    $scope.addsecs = function(){
	          
	    	var postdata = {path64: base64path, secs: ($scope.addseconds + 0) };
	    		    	
	    	$http({
   		     method: 'POST',
   		     url: 'subtitleaddsecs',	    		     
   		     data: $httpParamSerializerJQLike(postdata) ,
   		     headers: {'Content-Type': 'application/x-www-form-urlencoded'}
   		 }).then(function(response){
   			 
    			alert("items: " + response.data.items);
       			alert("error: " + response.data.error);
   			 if(response.data.result == "ok"){
   				 
   				alert(response.data.result);
   				 
   			 }
   			 else{
   				alert(response.data.result);
   			 }
   			 
   		 }, function(response){
   			 alert("error: " + response.data);
   		 });
		    
	    };
	    
	    $scope.convertFromAscci = function(ev){
	    	
	    	var postdata = {path64: base64path, conv: "ascii" };
	    	
	    	$http({
   		     method: 'POST',
   		     url: 'conversubtitle',	    		     
   		     data: $httpParamSerializerJQLike(postdata) ,
   		     headers: {'Content-Type': 'application/x-www-form-urlencoded'}
   		 }).then(function(response){
   			 
   			 if(response.data.result == "ok"){
   				 
   				alert(response.data.result);
   				 
   			 }
   			 else{
   				alert(response.data.result);
   			 }
   			 
   		 }, function(response){
   			 alert("error: " + response.data);
   		 });
	    	
	    	
	    };

	    $scope.convertFromUnicode = function(ev){
	    	
	    	var postdata = {path64: base64path, conv: "unicode" };
	    	
	    	$http({
   		     method: 'POST',
   		     url: 'conversubtitle',	    		     
   		     data: $httpParamSerializerJQLike(postdata) ,
   		     headers: {'Content-Type': 'application/x-www-form-urlencoded'}
   		 }).then(function(response){
   			 
   			 if(response.data.result == "ok"){
   				 
   				alert(response.data.result);
   				 
   			 }
   			 else{
   				alert(response.data.result);
   			 }
   			 
   		 }, function(response){
   			 alert("error: " + response.data);
   		 });
	    	
		    
	    };	    
	    
	  }	
	
}]);



