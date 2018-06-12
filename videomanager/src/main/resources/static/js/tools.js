/**
 * 
 */



brainApp.controller('ToolsController', ['$scope', '$http', '$sce', '$element', '$compile', '$mdDialog' ,'$httpParamSerializerJQLike', function ($scope, $http, $sce, $element, $compile, $mdDialog, $httpParamSerializerJQLike) {

	$scope.isTools = true;
	
	
	$scope.loadPathSubtitle = function(id) {
        
		
		$scope.$parent.showloading = true;
		$http.get("/pathsublist").then(function(response) {
        	
        	var parent = angular.element(document.querySelector('.pathsubitemsparent'));
        	parent.html("");
        	var element = "";

            for(index in response.data){
            	
            	var item = response.data[index];
            	element += '<div class="pathsubitems" >';
            	
            	element += item.path + " &nbsp;&nbsp; > &nbsp;&nbsp; " + item.suburl;
            		
            	element += '<button type="button" class="btn btn-default btn-sm" ng-click="delPathSubtitle($event, \'' + item.path + '\', \'' + item.suburl + '\', ' + item.id + ')"><span class="glyphicon glyphicon-trash"></span></button>';
            	element += "</div>";
            	
            	
            }
            
            
            var $el = parent.append(element);
        	$compile($el)($scope);
            
            
            $scope.$parent.showloading = false;
        });
    };
    
	
    
    $scope.addPathSubtitle = function(){
    	
    	$mdDialog.show({
            //targetEvent: ev,
            templateUrl: 'addpathsubtitle.html',
            controller: AddPathSubtitleController,
            locals: { parentscope: $scope}
          });
    	
    }
    
    $scope.delPathSubtitle = function(ev, path, sub, id){
    	
    	var confirm = $mdDialog.confirm()
        .title('Would you like to delete this Path/Subtitle?<br>' + path + '<br>' + sub)
        //.textContent('')
        //.ariaLabel('Lucky day')
        .targetEvent(ev)
        .ok('Delete')
        .cancel('Cancel');

		  $mdDialog.show(confirm).then(function() {

		    	var postdata = {psid: id };
		    	
		    	$http({
	   		     method: 'POST',
	   		     url: 'delpathsub',	    		     
	   		     data: $httpParamSerializerJQLike(postdata) ,
	   		     headers: {'Content-Type': 'application/x-www-form-urlencoded'}
	   		 }).then(function(response){
	   			 
	   			 if(response.data.result == "ok"){
	   				 
	   				
	   				$scope.loadPathSubtitle();
	   				$mdDialog.hide();
	   			 }
	   			 else{
	   				alert(response.data.result);
	   			 }
	   			 
	   		 }, function(response){
	   			 alert("error: " + response.data);
	   		 });
			  
			  
		  }, function() {
		    
		  });
    	
    }
	    
	    
	    
	function AddPathSubtitleController($scope, $mdDialog, $http, $httpParamSerializerJQLike, parentscope) {
		
		$scope.subtitleurl = "";
		$scope.localpath = "";

	    $scope.hide = function() {
	      $mdDialog.hide();
	    };

	    $scope.close = function() {
	      $mdDialog.cancel();
	    };

	    $scope.addPathSubtitle = function(){
	          
	    	var postdata = {path: $scope.localpath, suburl: $scope.subtitleurl };
	    		    	
	    	$http({
   		     method: 'POST',
   		     url: 'addpathsub',	    		     
   		     data: $httpParamSerializerJQLike(postdata) ,
   		     headers: {'Content-Type': 'application/x-www-form-urlencoded'}
   		 }).then(function(response){
   			 
   			 if(response.data.result == "ok"){
   				 
   				
   				parentscope.loadPathSubtitle();
   				$mdDialog.hide();
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



