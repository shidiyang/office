
$(document).ready(function(){

	var login = $('#loginform');
	var recover = $('#recoverform');
	var speed = 400;

	$('#to-recover').click(function(){
		
		$("#loginform").slideUp();
		$("#recoverform").fadeIn();
	});
	$('#to-login').click(function(){
		
		$("#recoverform").hide();
		$("#loginform").fadeIn();
	});
	
	
	$('#to-login').click(function(){

	});

	$('#submit').click(function () {
	    login_check();
    });
    $('input').focus(function(){
        $("#mes").html("");
        $("#fine_mes").html("");
    });

    $('#find_passwd').click(function() {
        find_check();
    });

    $('.img_pos').click(function () {
        chageImg();
    });

    function chageImg() {
        var img =$(".img_pos");
        var src = img.attr("src");
        img.attr("src",src+"?");
    }


    
    if($.browser.msie == true && $.browser.version.slice(0,3) < 10) {
        $('input[placeholder]').each(function(){ 
       
        var input = $(this);       
       
        $(input).val(input.attr('placeholder'));
               
        $(input).focus(function(){
             if (input.val() == input.attr('placeholder')) {
                 input.val('');
             }
        });
       
        $(input).blur(function(){
            if (input.val() == '' || input.val() == input.attr('placeholder')) {
                input.val(input.attr('placeholder'));
            }
        });
    });

    }

    function login_check(){
        if($("#userName").val().trim() ==''){
            $("#mes").html("请输入用户名.").css("color","red");
            return false;
        }
        if($("#password").val().trim() ==''){
            $("#mes").html("请输入密码.").css("color","red");
            return false;
        }
        if($("#code").val().trim() ==''){
            $("#mes").html("请输入验证码.").css("color","red");
            return false;
        }
        $.ajax({
            url:"/office/login/check",
            type:'post',
            data:login.serialize(),
            success:function(data){
                if(data.state==0) {
                    location.href = "/office/main/index";
                }else{
                    $("#mes").html(data.mes).css("color","red");
                    chageImg();
                    return false;
                }
            },
            dataType:"json"
        });
    }
    function find_check(){
        var mail_name=$("#mail_name").val().trim();
        var find_code=$("#find_code").val().trim();
        if( mail_name==''){
            $("#fine_mes").html("请输入邮箱.").css("color","red");
            return false;
        }
        if( find_code ==''){
            $("#fine_mes").html("请输入验证码.").css("color","red");
            return false;
        }
        $.ajax({
            url:"/office/login/check_pass",
            type:'post',
            data:{"mail_name":mail_name,"find_code":find_code},
            success:function(data){
                if(data.state==0) {
                    sent_email();
                }else{
                    $("#fine_mes").html(data.mes).css("color","red");
                    chageImg();
                    return false;
                }
            },
            dataType:"json"
        });
    }
    
    function sent_email() {
        var mail_name=$("#mail_name").val().trim();
        $.post("/office/login/sent",{"mail_name":mail_name}, function (data) {
            if(data==true){
                alert("邮件已发送,请查收.");
                location.reload();
                $.isLoading({text:"数据加载中，请稍后..."});
            }else{
                alert("邮件发送失败!")
            }
        }, 'json');
    }
});