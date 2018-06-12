/**
 * 
 */

var loadUrlList = new Array();

var brainApp = angular.module('myApp', ['ngMaterial']);

var dynamicWatcher = null;

brainApp.service('myService', function($http) {
    return {
        getHTML: function(inurl, element, $compile, $scope) {
			return $http({
				method: 'GET',
				url: inurl
			}).then(function(response){
				//alert(response.data);
				element.html("");
				
				
				
				var $el = element.append(response.data);
            	
            	$compile($el)($scope);	
				
			});
        }
    };
});


brainApp.controller('BodyController', ['$scope', '$http', '$sce', '$element', 'myService', '$compile', function ($scope, $http, $sce, $element, myService, $compile) {

	$scope.showloading = false;
	
	for (var i = 0; i < loadUrlList.length; i++) {
		
		var target = angular.element(document.querySelector('#maintab' + i));
		myService.getHTML(loadUrlList[i], target, $compile, $scope);
	}
	

    $scope.selectProcessTab = function(){
    	
    	$("#tabul a[href='#maintab1']").tab('show');
    	
    }

}]);
