<!DOCTYPE html>
<html lang="en">
    
<head>
        <title>Matrix Admin</title><meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link rel="stylesheet" href="/office/asset/matrix-admin/css/bootstrap.min.css" />
		<link rel="stylesheet" href="/office/asset/matrix-admin/css/bootstrap-responsive.min.css" />
        <link rel="stylesheet" href="/office/asset/matrix-admin/css/matrix-login.css" />
        <link href="/office/asset/matrix-admin/font-awesome/css/font-awesome.css" rel="stylesheet" />
		<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800' rel='stylesheet' type='text/css'>

    </head>
    <body>
        <div id="loginbox">            
            <form id="loginform" class="form-vertical" action="/office/login/success">
				 <div class="control-group normal_text"> <h3><img src="/office/asset/matrix-admin/img/logo.png" alt="Logo" /></h3></div>
                <div class="control-group">
                    <div class="controls">
                        <div class="main_input_box">
                            <span class="add-on bg_lg"><i class="icon-user"></i></span><input type="text" id="userName" name="userName" placeholder="用户名" />
                        </div>
                    </div>
                </div>
                <div class="control-group">
                    <div class="controls">
                        <div class="main_input_box">
                            <span class="add-on bg_ly"><i class="icon-lock"></i></span><input type="password" id="password" name="password" placeholder="密码" />
                        </div>
                    </div>
                </div>
                <div class="control-group">
                    <div class="controls">
                        <div class="main_input_box">
                            <span id="mes"></span>
                        </div>
                    </div>
                </div>
                <div class="form-actions">
                    <span class="pull-left"><a href="#" class="flip-link btn btn-info" id="to-recover">忘记密码?</a></span>
                    <span class="pull-right"><a href="#" type="submit" id="submit" class="btn btn-success" /> 登陆</a></span>
                </div>
            </form>
            <form id="recoverform" action="#" class="form-vertical">
				<p class="normal_text">请输入您的邮箱我们将通过邮件的方式帮您找回密码.</p>
				
                    <div class="controls">
                        <div class="main_input_box">
                            <span class="add-on bg_lo"><i class="icon-envelope"></i></span><input type="text" placeholder="邮箱" />
                        </div>
                    </div>
               
                <div class="form-actions">
                    <span class="pull-left"><a href="#" class="flip-link btn btn-success" id="to-login">&laquo; 返回 登陆</a></span>
                    <span class="pull-right"><a class="btn btn-info"/>找回</a></span>
                </div>
            </form>
        </div>
        
        <script src="/office/asset/matrix-admin/js/jquery.min.js"></script>
        <script src="/office/asset/matrix-admin/js/matrix.login.js"></script>
    </body>

</html>
