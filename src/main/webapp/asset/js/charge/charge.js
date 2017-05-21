
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
    var chargeName = $("#chargeName").val().trim();
    var prince = $("#prince").val().trim();
    var unit = $("#unit").val().trim();
    if(chargeName ==''){
        $("#mes").html("请填写'收费名称'后再提交...").css("color","red");
        return false;
    }


    if(prince ==''){
        $("#mes").html("请填写'收费单价'后再提交...").css("color","red");
        return false;
    }

    var n = Number(prince);
    if (isNaN(n)){
        $("#mes").html("'收费单价'应为数据类型,请修改后再提交...").css("color","red");
        return false;
    }

    if(unit ==''){
        $("#mes").html("请填写'单位'后再提交...").css("color","red");
        return false;
    }

    $.ajax({
        url:"/office/charge/check",
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
    $.post("/office/charge/save",$('#add-form').serialize(), function (data) {
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
        $.post('/office/charge/del', {id:id}, function (data) {
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

function updat(id,chargeName,chargeType,prince,unit) {
    $('#add-modal').modal('show');
    $('#id').val(id);
    $('#chargeName').val(chargeName);
    $('#chargeType').val(chargeType);
    $('#prince').val(prince);
    $('#unit').val(unit);
    $("#mes").html("");
}

