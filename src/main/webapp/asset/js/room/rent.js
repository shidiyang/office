
$('input').focus(function(){
    $("#mes").html("");
});

$('#save').click(function () {
    save_check();
});

function save_check(){
    var rentor = $("#rentor").val().trim();
    var roomUses = $("#roomUses").val().trim();
    if(rentor ==''){
        $("#mes").html("请填写'租借人'后再提交...").css("color","red");
        return false;
    }

    if(roomUses ==''){
        $("#mes").html("请填写'房屋用途'后再提交...").css("color","red");
        return false;
    }

    $.ajax({
        url:"/office/rrent/check",
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
    $.post("/office/rrent/save",$('#add-form').serialize(), function (data) {
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

function rent(id,roomId) {
    $('#add-modal').modal('show');
    $('#id').val(id);
    $('#roomId').val(roomId);
    $('#rentor').val("");
    $('#roomUses').val("");
    $("#mes").html("");
}

function recover(id) {
    if (confirm('确定收回 ? ')) {
        $.post('/office/rrent/recover', {"id":id}, function (data) {
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
