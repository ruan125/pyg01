//购物车控制层
app.controller('loginController',function($scope,$controller,cartService,uploadService,loginService){


    $controller('baseController',{$scope:$scope});

    $scope.showName=function(){
        loginService.showName().success(
            function(response){
                $scope.loginName=response;
            }
        );
    }



    $scope.upfile = function(){
        // 调用uploadService的方法完成文件的上传
        uploadService.uploadFile().success(function(response){
            if(response.success){
                // 获得url
                alert(response.message);
               // $scope.image_entity.url =  response.message;
            }else{
                alert(response.message);
            }
        });
    }


    //用户信息回显
    $scope.findUser=function(){
        loginService.findUser().success(
            function(response){
                $scope.userInfo=response;

                var day = moment(response.birthday);

                $scope.year = day.get('year');
                $scope.month = day.get('month')+1;
                $scope.day = day.get('date');


                $("#select_year2").attr("rel", day.get('year'));
                $("#select_month2").attr("rel", day.get('month')+1);
                $("#select_day2").attr("rel", day.get('date'));

                // if (response.sex=="1"){
                // 	$scope.mysex="checked";
                // 	$scope.mysex1="";
                // }
                // if (response.sex=="2"){
                //     $scope.mysex="";
                //     $scope.mysex1="checked";
                // }

                $.ms_DatePicker({
                    YearSelector: "#select_year2",
                    MonthSelector: "#select_month2",
                    DaySelector: "#select_day2"
                });

                var radios = $("input[name='gender']");
                for(var i=0;i<radios.size();i++) {
                    if($(radios[i]).val() == response.sex){
                        $(radios[i]).parent().addClass("checked");
                        $(radios[i]).attr("checked", true);
                    }
                }
                // 处理图片列表，因为图片信息保存的是JSON的字符串，让前台识别为JSON格式对象
                // $scope.headPic = JSON.parse( $scope.headPic);
            }
        );
    }


    //个人信息保存修改

    $scope.updateUser = function(){
        console.log($scope.userInfo);

        //拼接年月日
        var year=  $("#select_year2").val();
        var month= $("#select_month2").val();
        //月份小于10的时候,前面加0替补
        if(month<10){
            month='0'+month ;
        }
        var day = $("#select_day2").val();
        //日期小于10的时候,前面加0替补
        if(day<10){
            day='0'+day;
        }

        var birthday=year+'-' + month+'-' + day;

        $scope.userInfo.birthday=birthday;

        loginService.updateUser( $scope.userInfo).success(
            function(response){
                if(response.success){
                    // 重新查询
                    // $scope.reloadList();//重新加载
                    // $scope.reloadList();
                    //  location.href="home-setting-address.html";

                }else{
                    alert(response.message);
                }
            }
        );
    }

    // 查询一级分类列表:
    $scope.selectItemCat1List = function(){
        cartService.findByParentId(0).success(function(response){
            $scope.provinceList = response;
        });
    }

    // 查询二级分类列表:
    $scope.$watch("entity.provinceId",function(newValue,oldValue){
        cartService.findByParentId2(newValue).success(function(response){
            $scope.cityList = response;
        });
    });

    // 查询三级分类列表:
    $scope.$watch("entity.cityId",function(newValue,oldValue){
        cartService.findByParentId3(newValue).success(function(response){
            $scope.areaList = response;
        });
    });
	
});