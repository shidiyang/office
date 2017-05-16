

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
    var rolesName = $("#rolesName").val().trim();
    if(rolesName ==''){
        $("#mes").html("请填写'角色名称'后再提交...").css("color","red");
        return false;
    }

    $.ajax({
        url:"/office/role/check",
        type:'post',
        data:$('#add-form').serialize(),
        success:function(data){
            if(data.state==1) {
                alert(data.mes);
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
    $.post("/office/role/save",$('#add-form').serialize(), function (data) {
        $('#add-modal').modal('hide');
        if(data==true){
            location.reload();
            $.isLoading({text:"数据加载中，请稍后..."});
            alert("添加成功!");
        }else{
            alert("添加失败!")
        }
    }, 'json');
}

function del(roleId,permissionId) {
    if (confirm('确定删除 ？ ')) {
        $.post('/office/role/del', {"roleId":roleId,"permissionId":permissionId}, function (data) {
            if(data.state==0){
                location.reload();
                $.isLoading({text:"数据加载中，请稍后..."});
                alert(data.mes);
            }else{
                alert(data.mes);
            }
        }, 'json');
    }

}
