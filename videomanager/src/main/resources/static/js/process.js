
var isProcessLoaded = false;

brainApp.controller('ProcessController', ['$scope', '$http', '$sce', '$element', '$compile', '$interval' ,'$httpParamSerializerJQLike', function ($scope, $http, $sce, $element, $compile, $interval, $httpParamSerializerJQLike) {

	//alert("load general");

	if(!isProcessLoaded){
		isProcessLoaded = true;
		
		$scope.isProcess = true;
		$scope.intervalStop = false;
		$scope.progValue = {};		
		$scope.isRunning = {};		
		
		$scope.loadProcess = function(id) {
	        
			
			$scope.$parent.showloading = true;
			
			if ($scope.intervalStop){
			     $interval.cancel($scope.intervalStop);
			}
			$scope.intervalStop = false;
			
			$http.get("/proclist").then(function(response) {
	        	
	        	var parent = angular.element(document.querySelector('.processitemsparent'));
	        	parent.html("");
	        	var element = "";
	        	
	            for(index in response.data){
	            	
	            	var item = response.data[index];
	            	element += "<div class='processitems' data-out64='" + item.base64OutputFilePath + "'";
	            	
	            	element += " >";
	            	element += item.outputFileName;
	            	element += "<span class='proctype cell'>" + item.processType + "</span>";
	            	element += "<span class='status cell'>" + item.status + "</span>";
	            	element += "<span class='percentproc cell'><span class='percent'>" + item.percent + "</span><md-progress-linear md-mode='determinate' value='{{progValue[\"" + item.base64OutputFilePath + "\"]}}' ></md-progress-linear></span>";
            		element += "<a class='fileitembutton' href='localexplorer:" + item.folderPath + "' target='_self'>Folder</a>";
	            	if(!item.isRunning){
	            		//element += '<a class="fileitembutton startproc" href="#" ng-click="startProc($event, \'' + item.Base64OutputFilePath + '\')">Start</a>';
	            		
	            	}
	            	else{
	            		//element += '<a class="fileitembutton stopproc" href="#" ng-click="stopProc($event, \'' + item.Base64OutputFilePath + '\')">Stop</a>';
	            		
	            	}
	            	element += '<a class="fileitembutton startproc" href="#" ng-hide="isRunning[\'' + item.base64OutputFilePath + '\']" ng-click="startProc($event, \'' + item.base64OutputFilePath + '\')">Start</a>';
	            	element += '<a class="fileitembutton stopproc" href="#" ng-show="isRunning[\'' + item.base64OutputFilePath + '\']" ng-click="stopProc($event, \'' + item.base64OutputFilePath + '\')">Stop</a>';
	            	element += '<a class="fileitembutton delproc" href="#" ng-hide="isRunning[\'' + item.base64OutputFilePath + '\']" ng-click="delProc($event, \'' + item.base64OutputFilePath + '\')">Remove</a>';
	            		
	            	element += "</div>";
	            	
	            	$scope.progValue[item.base64OutputFilePath] = item.percent;
	            	$scope.isRunning[item.base64OutputFilePath] = item.isRunning;
	            }
	            $scope.intervalStop = $interval(function() {$scope.checkProcess()}, 500);
	            
	            
	            //alert(parent.parent().html());  	
	            var $el = parent.append(element);
	            //alert(parent.html());  	
            	$compile($el)($scope);
	            
	            
	            $scope.$parent.showloading = false;
	        });
	    };
	    
	    
	    $scope.startProc = function(ev, ibase64path){
	    	
	    	var postdata = {path64: ibase64path, start:1 };
    		
    		$http({
    		     method: 'POST',
    		     url: 'changeproc',	    		     
    		     data: $httpParamSerializerJQLike(postdata) ,
    		     headers: {'Content-Type': 'application/x-www-form-urlencoded'}
    		 }).then(function(response){
    			 
    			 if(response.data.found == "ok" && response.data.act == "start"){
    				 
    				 $scope.loadProcess();
    				 
    			 }
    			 else{
    				 alert(response.data.found + ", " + response.data.act);
    			 }
    			 
    		 }, function(response){
    			 alert("error: " + response);
    		 });
	    };
	    
	    $scope.stopProc = function(ev, ibase64path){
	    	
	    	var postdata = {path64: ibase64path, start:2 };
    		
    		$http({
    		     method: 'POST',
    		     url: 'changeproc',	    		     
    		     data: $httpParamSerializerJQLike(postdata) ,
    		     headers: {'Content-Type': 'application/x-www-form-urlencoded'}
    		 }).then(function(response){
    			 
    			 if(response.data.found == "ok" && response.data.act == "stop"){
    				 
    				 $scope.loadProcess();
    				 
    			 }
    			 else{
    				 alert(response.data.found + ", " + response.data.act);
    			 }
    			 
    		 }, function(response){
    			 alert("error: " + response);
    		 });
	    	

	    };

	    
	    $scope.delProc = function(ev, ibase64path){
	    	
	    	var postdata = {path64: ibase64path, start:3 };
    		
    		$http({
    		     method: 'POST',
    		     url: 'changeproc',	    		     
    		     data: $httpParamSerializerJQLike(postdata) ,
    		     headers: {'Content-Type': 'application/x-www-form-urlencoded'}
    		 }).then(function(response){
    			 
    			 if(response.data.found == "ok" && response.data.act == "del"){
    				 
    				 $scope.loadProcess();
    				 
    			 }
    			 else{
    				 alert(response.data.found + ", " + response.data.act);
    			 }
    			 
    		 }, function(response){
    			 alert("error: " + response);
    		 });
	    	

	    };

	    $scope.checkProcess = function(){
	    	
			$http.get("/proclist").then(function(response) {
	        	
	            for(index in response.data){
	            	var item = response.data[index];
	            	
   				 	var parent = angular.element(document.querySelector('.processitems[data-out64="' + item.base64OutputFilePath + '"]'));
				 
					$(parent).children(".status").html(item.status);
					$(parent).children(".percentproc").children(".percent").html(item.percentString + " %");
					$scope.progValue[item.base64OutputFilePath] = item.percent;
					
					$scope.isRunning[item.base64OutputFilePath] = item.isRunning;
	            }

	        }, function(response){
   			 alert("error: " + response);
   		 	});	    	
    		
	    	
    		
    		
	    }
	    
	}
	
	
	
}]);