<#include "../common/html.ftl"/>
<@html title="用户信息">
<div class="widget-box">
    <div class="widget-title"> <span class="icon"><i class="icon-th"></i></span>
        <h5>用户信息</h5>
    </div>
            <div class="modal-content">
                <form method="post" id="add-form" autocomplete="off" class="form-horizontal">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title">用户信息</h4>
                    </div>
                    <div class="modal-body">
                        <table style="width:100%;" align="center">
                            <input id="userId" type="hidden" name="userId" value="${userInfo.userId}"/>
                            <tr>
                                <td class="text-right" width="40%" height="34px">
                                    <span style="padding-right:25px;">用户名:</span>
                                </td>
                                <td>
                                    <span><input id="userName" type="text" name="userName" value="${userInfo.userName}" /> </span>
                                </td>
                            </tr>
                            <tr>
                                <td class="text-right" width="40%" height="34px">
                                    <span style="padding-right:25px;">性别:</span>
                                </td>
                                <td>
                                    <select name="sex" id="sex">
                                            <option value='0'>女</option>
                                            <option value='1' selected="selected">男</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td class="text-right" width="40%" height="34px">
                                    <span style="padding-right:25px;">电话:</span>
                                </td>
                                <td>
                                    <span><input  id="phone"type="text" name="phone" value="${userInfo.phone}"/></span>
                                </td>
                            </tr>
                            <tr>
                                <td class="text-right" width="40%" height="34px">
                                    <span style="padding-right:25px;">邮箱:</span>
                                </td>
                                <td>
                                    <span><input  id="emall"type="text" name="emall" value="${userInfo.emall}"/></span>
                                </td>
                            </tr>
                            <tr>
                                <td class="text-right" width="40%" height="34px">
                                    <span style="padding-right:25px;">角色:</span>
                                </td>
                                <td>
                                    <select name="roleId" id="roleId">
                                        <#if roles?? && roles?size gt 0>
                                            <#list roles as role>
                                                <option value='${role.roleId}'>${role.description}</option>
                                            </#list>
                                        </#if>
                                    </select>
                                </td>
                            </tr>
                            <tr><td class="text-center" colspan="2"><span id="mes"></span></td></tr>
                        </table>
                    </div>
                    <div class="modal-footer">
                        <button  id="alter" type="button" class="btn btn-flat btn-primary"><i
                                class="fa"></i> 修改
                        </button>
                        <button  id="save" type="button" class="btn btn-flat btn-primary" style="display: none"><i
                                class="fa fa-save"></i> 提交
                        </button>
                    </div>
                </form>
            </div>
</div>
<script type="text/javascript">
    $('#sex').val(${userInfo.sex});
    $('#roleId').val(${userInfo.userRolesKey.roleId});
    $('input,select').attr("disabled","disabled");
</script>
<script src="/office/asset/js/sys/info.js"></script>
</@html>