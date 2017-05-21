
$('input').focus(function(){
    $("#mes").html("");
});

$('#save').click(function () {
    save_check();
});

function save_check(){
    var userId = $("#userId").val().trim();
    var oldpass = $("#oldpass").val().trim();
    var newpass = $("#newpass").val().trim();
    var confirm = $("#confirm").val().trim();
    if(oldpass ==''){
        $("#mes").html("请填写'原密码'后再提交...").css("color","red");
        return false;
    }

    if(newpass ==''){
        $("#mes").html("请填写'新密码'后再提交...").css("color","red");
        return false;
    }

    if(confirm ==''){
        $("#mes").html("请填写'确认密码'后再提交...").css("color","red");
        return false;
    }

    if(confirm != newpass){
        $("#mes").html("两次输入的密码不同...").css("color","red");
        return false;
    }

    if(oldpass == newpass){
        $("#mes").html("新旧密码不能相同...").css("color","red");
        return false;
    }

    $.ajax({
        url:"/office/user/checkps",
        type:'post',
        data:{"userId":userId,"password":oldpass},
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
    var userId = $("#userId").val().trim();
    var newpass = $("#newpass").val().trim();
    $.post("/office/user/saveps",{"userId":userId,"password":newpass}, function (data) {
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
