 //控制层 
app.controller('seckillGoodsController' ,function($scope,$controller,$location,typeTemplateService ,itemCatService,uploadService,seckillGoodsService){
	
	$controller('baseController',{$scope:$scope});//继承
	
    //读取列表数据绑定到表单中  
	$scope.findAll=function(){
        seckillGoodsService.findAll().success(
			function(response){
				$scope.list=response;
			}			
		);
	}    
	
	//分页
	$scope.findPage=function(page,rows){
        seckillGoodsService.findPage(page,rows).success(
			function(response){
				$scope.list=response.rows;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}
	
	//查询实体 
	$scope.findOne=function(){	
		var id = $location.search()['id'];
		// alert(id);
		seckillGoodsService.findOne(id).success(
			function(response){
				$scope.entity= response;
				//处理

				// 处理图片列表
				$scope.entity.smallPic = JSON.parse( $scope.entity.smallPic );

			}
		);				
	}
	
	$scope.checkAttributeValue = function(specName,optionName){
		var items = $scope.entity.goodsDesc.specificationItems;
		var object = $scope.searchObjectByKey(items,"attributeName",specName);
		if(object != null){
			if(object.attributeValue.indexOf(optionName)>=0){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	
	//保存 
	$scope.save=function(){	
		// 再添加之前，获得富文本编辑器中的内容。
		var serviceObject;//服务层对象  				
		if($scope.entity.id!=null){//如果有ID
			serviceObject=seckillGoodsService.update( $scope.entity ); //修改
		}else{
			serviceObject=seckillGoodsService.add( $scope.entity  );//增加
		}				
		serviceObject.success(
			function(response){
				if(response.success){
					//重新查询 
		        	alert(response.message);
		        	location.href="seckillGoods.html";
				}else{
					alert(response.message);
				}
			}		
		);				
	}
	
	 
	//批量删除 
	$scope.dele=function(){			
		//获取选中的复选框			
		seckillGoodsService.dele( $scope.selectIds ).success(
			function(response){
				if(response.success){
					$scope.reloadList();//刷新列表
					$scope.selectIds = [];
				}						
			}		
		);				
	}
	
	$scope.searchEntity={};//定义搜索对象 

    //搜索
    $scope.search=function(page,rows){
        seckillGoodsService.search(page,rows,$scope.searchEntity).success(
            function(response){
                $scope.list=response.rows;
                $scope.paginationConf.totalItems=response.total;//更新总记录数
            }
        );
    }
    
	// $scope.entity={goods:{},goodsDesc:{},itemList:[]}
	
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
	
	// 获得了image_entity的实体的数据{"color":"褐色","url":"http://192.168.209.132/group1/M00/00/00/wKjRhFn1bH2AZAatAACXQA462ec665.jpg"}
	$scope.entity={goods:{},goodsDesc:{itemImages:[],specificationItems:[]}};
	
	$scope.add_image_entity = function(){
		$scope.entity.goodsDesc.itemImages.push($scope.image_entity);
	}
	
	$scope.remove_iamge_entity = function(index){
		$scope.entity.goodsDesc.itemImages.splice(index,1);
	}
	
	$scope.updateSpecAttribute = function($event,name,value){
		// 调用封装的方法判断 勾选的名称是否存在:
		var object = $scope.searchObjectByKey($scope.entity.goodsDesc.specificationItems,"attributeName",name);
	
		if(object != null){
			// 找到了
			if($event.target.checked){
				object.attributeValue.push(value);
			}else{
				object.attributeValue.splice(object.attributeValue.indexOf(value),1);
			}
			
			if(object.attributeValue.length == 0){
				var idx = $scope.entity.goodsDesc.specificationItems.indexOf(object);
				$scope.entity.goodsDesc.specificationItems.splice(idx,1);
			}
		}else{
			// 没找到
			$scope.entity.goodsDesc.specificationItems.push({"attributeName":name,"attributeValue":[value]});
		}
	}

	
	addColumn = function(list,columnName,columnValues){
		// 定义一个集合用于保存生成的每行的数据:
		var newList = [];
		// 遍历该集合的数据:
		for(var i=0;i<list.length;i++){
			var oldRow = list[i];
			for(var j=0;j<columnValues.length;j++){
				// 对oldRow数据进行克隆:
				var newRow = JSON.parse( JSON.stringify(oldRow) );
				newRow.spec[columnName]=columnValues[j];
				// 将newRow存入到newList中
				newList.push(newRow);
			}
			
		}
		
		return newList;
	}
	
	// 显示状态
	$scope.status = ["未审核","审核通过","审核未通过","关闭"];
	
	$scope.itemCatList = [];
	// 显示分类:
	$scope.findItemCatList = function(){
		itemCatService.findAll().success(function(response){
			//遍历分类表所有数据
			for(var i=0;i<response.length;i++){
				//response[i] 获取分类数据的某一条
				//response[i].id获取分类数据某一条的id属性值
				//$scope.itemCatList[4] = 网络原创
				//$scope.itemCatList[1] = 图书、音像、电子书刊
				//itemCatList[分类id] = 分类名称
				//{{itemCatList[entity.category1Id]}} 相当于itemCatList[分类id] 通过分类索引号直接取分类的名称
				$scope.itemCatList[response[i].id] = response[i].name;
			}
		});
	}
});	
