
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
    });


    
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
        $.ajax({
            url:"/office/login/check",
            type:'post',
            data:login.serialize(),
            success:function(data){
                if(data.state==0) {
                    location.href = "/office/main/index";
                }else{
                    $("#mes").html(data.mes).css("color","red");
                    return false;
                }
            },
            dataType:"json"
        });
    }
});