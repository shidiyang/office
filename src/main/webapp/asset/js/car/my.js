

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
    var parkingId = $("#parkingId").val().trim();
    var adress = $("#adress").val().trim();
    var type = $("#type").val().trim();
    var princeType = $("#princeType").val().trim();
    var prince = $("#prince").val().trim();
    if(parkingId ==''){
        $("#mes").html("请填写'车位编号'后再提交...").css("color","red");
        return false;
    }

    var n = Number(parkingId);
    if (isNaN(n)){
        $("#mes").html("'车位编号'应为数据类型,请修改后再提交...").css("color","red");
        return false;
    }

    if(adress ==''){
        $("#mes").html("请填写'车位地址'后再提交...").css("color","red");
        return false;
    }
    if(prince ==''){
        $("#mes").html("请填写'费用'后再提交...").css("color","red");
        return false;
    }

    var n = Number(prince);
    if (isNaN(n)){
        $("#mes").html("'费用'应为数据类型,请修改后再提交...").css("color","red");
        return false;
    }

    $.ajax({
        url:"/office/cbash/check",
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
    $.post("/office/cbash/save",$('#add-form').serialize(), function (data) {
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
        $.post('/office/cbash/del', {id:id}, function (data) {
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

function updat(id,parkingId,adress,type,princeType,prince) {
    $('#add-modal').modal('show');
    $('#id').val(id);
    $('#parkingId').val(parkingId);
    $('#adress').val(adress);
    $('#type').val(type);
    $('#princeType').val(princeType);
    $('#prince').val(prince);
    $("#mes").html("");
}

