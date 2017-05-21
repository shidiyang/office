
$('#alter').click(function () {
    $('input,select').removeAttr("disabled");
    $('#save').show();
    $('#alter').hide();
    $("#roleId").attr("disabled","disabled");
    $("#userName").attr("disabled","disabled");
});

$('input').focus(function(){
    $("#mes").html("");
});

$('#save').click(function () {
    save_check();
});

function save_check(){
    var userName = $("#userName").val().trim();
    var phone = $("#phone").val().trim();
    var emall = $("#emall").val().trim();
    if(userName ==''){
        $("#mes").html("请填写'用户名'后再提交...").css("color","red");
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
            alert("修改成功!");
        }else{
            alert("修改失败!")
        }
    }, 'json');
}
