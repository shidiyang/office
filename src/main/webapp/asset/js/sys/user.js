

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
    var userName = $("#userName").val().trim();
    var password = $("#password").val().trim();
    var phone = $("#phone").val().trim();
    var emall = $("#emall").val().trim();
    if(userName ==''){
        $("#mes").html("请填写'用户名'后再提交...").css("color","red");
        return false;
    }

    if(password ==''){
        $("#mes").html("请填写'密码'后再提交...").css("color","red");
        return false;
    }
    if(phone ==''){
        $("#mes").html("请填写'电话'后再提交...").css("color","red");
        return false;
    }

    if(emall ==''){
        $("#mes").html("请填写'邮箱'后再提交...").css("color","red");
        return false;
    }

    $.ajax({
        url:"/office/user/check",
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
    $.post("/office/user/save",$('#add-form').serialize(), function (data) {
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

function del(id) {
    if (confirm('确定删除 ？ ')) {
        $.post('/office/user/del', {id:id}, function (data) {
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

function updat(userId,userName,password,sex,phone,emall) {
    $('#add-modal').modal('show');
    $('#userId').val(userId);
    $('#userName').val(userName);
    $('#password').val(password);
    $('#sex').val(sex);
    $('#phone').val(phone);
    $('#emall').val(emall);
    $("#mes").html("");
}

