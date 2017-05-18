

$('#add').click(function () {
    $('#add-modal').modal('show');
    $('input').val("");
    $('textarea').val("");
});

$('input').focus(function(){
    $("#mes").html("");
});

$('#save').click(function () {
    save_check();
});

function save_check(){
    var name = $("#departmentName").val().trim();
    if(name ==''){
        $("#mes").html("请填写'部门名称'后再提交...").css("color","red");
        return false;
    }

    $.ajax({
        url:"/office/post/check",
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
    $.post("/office/post/save",$('#add-form').serialize(), function (data) {
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
        $.post('/office/post/del', {id:id}, function (data) {
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

function updat(id,departmentName,description) {
    $('#add-modal').modal('show');
    $('#id').val(id);
    $('#departmentName').val(departmentName);
    $('#description').val(description);
    $("#mes").html("");
}

