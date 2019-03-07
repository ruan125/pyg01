app.service("contentService",function($http){
	this.findByCategoryId = function(categoryId){
		return $http.get("content/findByCategoryId.do?categoryId="+categoryId);
	}


    this.findByFloorId = function(floorId){
        return $http.get("content/findByFloorId.do?floorId="+floorId);
    }
});