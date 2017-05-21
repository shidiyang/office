
$('#add').click(function () {
    $('#add-modal').modal('show');
    $('input').val("");
    $('textarea').val("");
});

// $('.date').datepicker({
//     language: 'zh-CN',//显示中文
//     format: 'yyyy-mm-dd',//显示格式
//     minView: "month",//设置只显示到月份
//     initialDate: new Date(),//初始化当前日期
//     autoclose: true,//选中自动关闭
//     todayBtn: true//显示今日按钮
// });

$('#birthday').datepicker({
    show: true,
    format: 'yyyy-mm-dd',
    language: 'zh-CN',
    weekStart: 1,
    autoclose: true,
    orientation:'right',
    todayBtn:'linked'

});

$('input').focus(function(){
    $("#mes").html("");
});

$('#save').click(function () {
    save_check();
});

function save_check(){
    var staffId = $("#staffId").val().trim();
    var staffName = $("#staffName").val().trim();
    var identityId = $("#identityId").val().trim();
    var phone = $("#phone").val().trim();
    var email = $("#email").val().trim();
    var nation = $("#nation").val().trim();
    var currentAdress = $("#currentAdress").val().trim();
    var nativePlace = $("#nativePlace").val().trim();
    var politicalBackground = $("#politicalBackground").val().trim();
    if(staffId ==''){
        $("#mes").html("请填写'员工编号'后再提交...").css("color","red");
        return false;
    }
    if(staffName ==''){
        $("#mes").html("请填写'员工姓名'后再提交...").css("color","red");
        return false;
    }
    if(identityId ==''){
        $("#mes").html("请填写'身份证号'后再提交...").css("color","red");
        return false;
    }
    if(phone ==''){
        $("#mes").html("请填写'联机方式'后再提交...").css("color","red");
        return false;
    }
    if(email ==''){
        $("#mes").html("请填写'邮箱'后再提交...").css("color","red");
        return false;
    }
    if(nation ==''){
        $("#mes").html("请填写'名族'后再提交...").css("color","red");
        return false;
    }
    if(currentAdress ==''){
        $("#mes").html("请填写'现住地址'后再提交...").css("color","red");
        return false;
    }
    if(nativePlace ==''){
        $("#mes").html("请填写'籍贯'后再提交...").css("color","red");
        return false;
    }if(politicalBackground ==''){
        $("#mes").html("请填写'政治背景'后再提交...").css("color","red");
        return false;
    }

    $.ajax({
        url:"/office/staff/check",
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
    $.post("/office/staff/save",$('#add-form').serialize(), function (data) {
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
        $.post('/office/staff/del', {id:id}, function (data) {
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

