<#include "../common/html.ftl"/>
<@html title="修改密码">
<div class="widget-box">
    <div class="widget-title"> <span class="icon"><i class="icon-th"></i></span>
        <h5>修改密码</h5>
    </div>
            <div class="modal-content">
                <form method="post" id="add-form" autocomplete="off" class="form-horizontal">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title">修改密码</h4>
                    </div>
                    <div class="modal-body">
                        <table style="width:100%;" align="center">
                            <input id="userId" type="hidden" name="userId" value="${userInfo.userId}"/>
                            <tr>
                                <td class="text-right" width="40%" height="34px">
                                    <span style="padding-right:25px;">原密码:</span>
                                </td>
                                <td>
                                    <span><input id="oldpass" type="password" name="userName" /> </span>
                                </td>
                            </tr>
                            <tr>
                                <td class="text-right" width="40%" height="34px">
                                    <span style="padding-right:25px;">新密码:</span>
                                </td>
                                <td>
                                    <span><input  id="newpass" type="password" name="phone"/></span>
                                </td>
                            </tr>
                            <tr>
                                <td class="text-right" width="40%" height="34px">
                                    <span style="padding-right:25px;">确认密码:</span>
                                </td>
                                <td>
                                    <span><input  id="confirm" type="password" name="confirm" /></span>
                                </td>
                            </tr>
                            <tr><td class="text-center" colspan="2"><span id="mes"></span></td></tr>
                        </table>
                    </div>
                    <div class="modal-footer">
                        <button  id="save" type="button" class="btn btn-flat btn-primary"><i
                                class="fa fa-save"></i> 提交
                        </button>
                    </div>
                </form>
            </div>
</div>
<script src="/office/asset/js/sys/alterps.js"></script>
</@html>