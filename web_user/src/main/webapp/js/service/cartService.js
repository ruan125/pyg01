//购物车服务层
app.service('cartService',function($http){
	//购物车列表
	this.findCartList=function(){
		return $http.get('cart/findCartList.do');
	}
	
	//添加商品到购物车
	this.addGoodsToCartList=function(itemId,num){
		return $http.get('cart/addGoodsToCartList.do?itemId='+itemId+'&num='+num);
	}
	
	//求合计数
	this.sum=function(cartList){
		var totalValue={totalNum:0,totalMoney:0 };
			
		for(var i=0;i<cartList.length ;i++){
			var cart=cartList[i];//购物车对象
			for(var j=0;j<cart.orderItemList.length;j++){
				var orderItem=  cart.orderItemList[j];//购物车明细
				totalValue.totalNum+=orderItem.num;//累加数量
				totalValue.totalMoney+=orderItem.totalFee;//累加金额				
			}			
		}
		return totalValue;

	}


	//添加地址
    this.save = function(entity){
        return $http.post("../address/add.do",entity);
    }

    //修改地址
    this.update=function(entity){
        return $http.post("../address/update.do",entity);
    }

    //查询一个地址(回显)
    this.findById=function(id){
        return $http.get("../address/findOne.do?id="+id);
    }

    //删除地址
    this.dele = function(id){
        return $http.get("../address/delete.do?id="+id);
    }

    //查询省
    this.findByParentId = function(parentId){
        return $http.get("../address/findByParentId.do?parentId="+parentId);
    }

    //查询市
    this.findByParentId2 = function(parentId2){
        return $http.get("../address/findByParentId2.do?parentId2="+parentId2);
    }
    //查询地方
    this.findByParentId3 = function(parentId3){
        return $http.get("../address/findByParentId3.do?parentId3="+parentId3);
    }



    this.updateStatus = function(id){
        return $http.get("../address/updateStatus.do?id="+id);
    }

	//获取当前登录账号的收货地址
	this.findAddressList=function(){
		return $http.get('address/findListByLoginUser.do');
	}
	
	//提交订单
	this.submitOrder=function(order){
		return $http.post('order/add.do',order);		
	}



	
});