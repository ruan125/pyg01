//订单服务层
app.service('OrderService',function($http){
    //订单列表
    this.findOrderList=function(status){
        return $http.get('order/userSeletOrder.do?status='+status);
    }

    //搜索
    this.search=function(page,rows,status){
        return $http.post('../order/search.do?page='+page+"&rows="+rows+"&status="+status);
    }
});