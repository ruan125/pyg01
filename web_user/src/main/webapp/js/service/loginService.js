//服务层
app.service('loginService',function($http){
	//读取列表数据绑定到表单中
	this.showName=function(){
		return $http.get("../login/name.do");
	}

    //查询用户
    this.findUser=function(){
        return $http.get('login/findOne.do');
    }


    //添加地址
    this.save = function(entity){
        return $http.post("../address/add.do",entity);
    }

    //修改地址
    this.update=function(entity){
        return $http.post("../address/update.do",entity);
    }

    //查询用户信息
    this.findUser=function(){
        return $http.get('../login/findUser.do');
    }

    //个人信息修改
    this.updateUser=function(entity){
        return  $http.post('../login/updateUser.do',entity );
    }




});