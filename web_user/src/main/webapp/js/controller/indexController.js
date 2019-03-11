//首页控制器
app.controller('indexController',function($scope,$controller,loginService,userService){

    $controller('baseController',{$scope:$scope});//继承

	$scope.showName=function(){
			loginService.showName().success(
					function(response){
						$scope.loginName=response;
					}
			);
	}



/*
    // 查用户回显
    $scope.findOne = function(){
        loginService.findOne().success(function(response){
            // {id:xx,name:yy,firstChar:zz}
            $scope.entity = response;
        });
    }
*/


    $scope.prePay=function(status){
        $scope.searchEntity = {status:status}
        this.search(1, 10);
    }


    $scope.searchEntity={};

    // 假设定义一个查询的实体：searchEntity
    $scope.search = function(page,rows){
        // 向后台发送请求获取数据:
        userService.search(page,rows,$scope.searchEntity).success(function(response){
            $scope.paginationConf.totalItems = response.total;
            $scope.list = response.rows;
        });
    }
	//上传
    $scope.uploadFile = function(){
        // 调用uploadService的方法完成文件的上传
        uploadService.uploadFile().success(function(response){
            if(response.success){
                // 获得url
                $scope.image_entity.url =  response.message;
            }else{
                alert(response.message);
            }
        });
    }





/*
    // 保存个人信息的方法:
    $scope.save = function(){
        // 区分是保存还是修改
        var object;
        if($scope.entity.id != null){
            // 更新
            object = loginService.update($scope.entity);
        }else{
            // 保存
            object = loginService.save($scope.entity);
        }
        object.success(function(response){
            // {success:true,message:xxx}
            // 判断保存是否成功:
            if(response.success==true){
                // 保存成功
                alert(response.message);
                $scope.reloadList();
            }else{
                // 保存失败
                alert(response.message);
            }
        });
    }*/


});