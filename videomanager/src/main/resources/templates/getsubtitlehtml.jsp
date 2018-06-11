<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
	
<div ng-controller="GeneralController as vm">
  <md-button class="md-primary md-raised" ng-click="vm.open($event)">
    Custom Dialog
  </md-button>
  <div ng-if="vm.showText" layout="column">
    {{vm.placeholder1}}
    <br>
    {{vm.placeholder2}}
  </div>

  <script type="text/ng-template" id="test.html">
    <md-dialog aria-label="Test">
    	<div layout-padding layout="column">
      	<md-input-container>
        	<label>Placeholder 1</label>
          <input ng-model="vm.placeholder1">
        </md-input-container>
        <md-input-container>
        	<label>Placeholder 2</label>
          <input ng-model="vm.placeholder2">
        </md-input-container>
        <md-button ng-click="vm.save()" class="md-primary">Save</md-button>
      </div>
    </md-dialog>
  </script>
</div>
