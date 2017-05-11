

$('#submit').click(function () {
    var keyword = $("#keyword").val().trim();
    var n = Number(keyword);
    if (!isNaN(n) || keyword == "") {
        return true;
    } else {
        alert("搜索输入有误。。。");
        return false;
    }

});

$('#add').click(function () {
    $('#add-modal').modal('show');
    $('input').val("");
});

$('input').focus(function(){
    $("#mes").html("");
});

$('#save').click(function () {
    save_check();
});

function save_check(){
    var menuId = $("#menuId").val().trim();
    var text = $("#text").val().trim();
    var parentId = $("#parentId").val().trim();
    var orderId = $("#orderId").val().trim();
    var url = $("#url").val().trim();
    var permissionName = $("#permissionName").val().trim();
    if(menuId ==''){
        $("#mes").html("请填写'菜单ID'后再提交...").css("color","red");
        return false;
    }

    var n = Number(menuId);
    if (isNaN(n)){
        $("#mes").html("'菜单ID'应为数据类型,请修改后再提交...").css("color","red");
        return false;
    }

    if(text ==''){
        $("#mes").html("请填写'菜单名称'后再提交...").css("color","red");
        return false;
    }
    if(parentId ==''){
        $("#mes").html("请填写'父ID'后再提交...").css("color","red");
        return false;
    }

    var n = Number(parentId);
    if (isNaN(n)){
        $("#mes").html("'父ID'应为数据类型,请修改后再提交...").css("color","red");
        return false;
    }

    if(orderId ==''){
        $("#mes").html("请填写'排序'后再提交...").css("color","red");
        return false;
    }

    var n = Number(orderId);
    if (isNaN(n)){
        $("#mes").html("'排序'应为数据类型,请修改后再提交...").css("color","red");
        return false;
    }

    if(url ==''){
        $("#mes").html("请填写'URL'后再提交...").css("color","red");
        return false;
    }
    if(permissionName ==''){
        $("#mes").html("请填写'权限名称'后再提交...").css("color","red");
        return false;
    }
    $.ajax({
        url:"/office/menu/check",
        type:'post',
        data:$('#add-form').serialize(),
        success:function(data){
            if(data.state==1) {
                alert(data.mes);
                $('#add-modal').modal('hide');
                return false;
            }else{
                save();
            }
        },
        dataType:"json"
    });
    return false;
}

function save(){
    $.post("/office/menu/save",$('#add-form').serialize(), function (data) {
        $('#add-modal').modal('hide');
        if(data==true){
            location.reload();
            $.isLoading({text:"数据加载中，请稍后..."});
            alert("添加/修改成功!");
        }else{
            alert("添加/修改失败!")
        }
    }, 'json');
}