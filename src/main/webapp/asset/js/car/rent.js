
$('input').focus(function(){
    $("#mes").html("");
});

$('#save').click(function () {
    save_check();
});

function save_check(){
    var rentor = $("#rentor").val().trim();
    if(rentor ==''){
        $("#mes").html("请填写'租借人'后再提交...").css("color","red");
        return false;
    }

    $.ajax({
        url:"/office/crent/check",
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
    $.post("/office/crent/save",$('#add-form').serialize(), function (data) {
        $('#add-modal').modal('hide');
        if(data==true){
            location.reload();
            $.isLoading({text:"数据加载中，请稍后..."});
            alert("租出成功!");
        }else{
            alert("租出失败!")
        }
    }, 'json');
}

function rent(id,parkingId) {
    $('#add-modal').modal('show');
    $('#id').val(id);
    $('#parkingId').val(parkingId);
    $('#rentor').val("");
    $("#mes").html("");
}

function recover(id,total) {
    if (confirm('确定收回 ？ \n 收回前请收取 '+total+" 元.")) {
        $.post('/office/crent/recover', {"id":id,"total":total}, function (data) {
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

function showInfo(userName, sex, phone, email) {
    $('#info-modal').modal('show');
    $('#iuserName').val(userName);
    $('#isex').val(sex);
    $('#iphone').val(phone);
    $("#iemall").val(email);
}
