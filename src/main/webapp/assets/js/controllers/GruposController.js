app.controller('GruposController', [ '$scope', 'GruposFactory', function($scope, Grupos) {
	$scope.message = 'Hello from GruposController';
	$scope.blackp = false;
	$scope.wolv = false;
	$scope.activeGroup = {
		id : 0,
		name : '',
		cant : '',
		checked : false,
		clicked : false,
		personajes : [ '' ]
	};
	$scope.activeId = -1;

	$scope.grupos = [];

	Grupos.getPersonajes().success(function(data) {
		$scope.personajesList = data.personajes.name;
	})
	Grupos.getGrupos().success(function(data) {
		$scope.gruposList = data.grupos.name;
	})
	Grupos.addGrupo().success(function(data) {
		$scope.t = data.grupos;
	})

	$scope.newGroup = function() {
		addGrupo()
		$scope.grupos.push();
		$scope.lastId = $scope.lastId + 1;
	};

	/*
	 * $scope.addChar = function() { angular.forEach($scope.data,
	 * function(value, key){ if(value.id == activeId){ if(capi == true) {
	 * value.personajes.push('Capitain America'); value.cant = activeGroup.cant +
	 * 1; }; if(wolv == true) { value.personajes.push('Wolverine'); value.cant =
	 * activeGroup.cant + 1; }; if(blackp == true) {
	 * value.personajes.push('Black Panther'); value.cant = value.cant + 1; } } }
	 * });
	 */

	$scope.assign = function(myId) {
		activeId = myId;
	};

	$scope.chckedIndexs = [];

	$scope.checkedIndex = function(group) {
		if ($scope.chckedIndexs.indexOf(group) === -1) {
			$scope.chckedIndexs.push(group);
		} else {
			$scope.chckedIndexs.splice($scope.chckedIndexs.indexOf(group), 1);
		}
	}

	$scope.selectedGroups = function() {
		return $filter('filter')($scope.students, {
			checked : true
		});
	};

	$scope.remove = function(index) {
		angular.forEach($scope.chckedIndexs, function(value, index) {
			var index = $scope.data.indexOf(value);
			$scope.data.splice($scope.data.indexOf(value), 1);
		});
		$scope.chckedIndexs = [];
	};

} ]);
