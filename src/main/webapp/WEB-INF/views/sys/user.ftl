<#include "../common/html.ftl"/>
<@html title="用户管理">
<div class="widget-box">
    <div class="widget-title"> <span class="icon"><i class="icon-th"></i></span>
        <h5>用户管理</h5>
    </div>
    <div id="search">
        <form method="POST" action="/office/user/list">
        <input type="text" placeholder="用户名" name="keyword" id="keyword" value="${keyword!}"/>
        <button type="submit" id="submit" class="tip-bottom" title="Search" ><i class="icon-search icon-white"></i></button>
        </form>
    </div>
    <div class="widget-content nopadding">
        <table class="table table-bordered data-table">
            <thead>
            <tr>
                <th>用户名</th>
                <th>密码</th>
                <th>性别</th>
                <th>电话</th>
                <th>邮箱</th>
                <th>角色</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
                <#if page?? && page.rows?? && page.rows?size gt 0>
                    <#list page.rows as row>
                    <tr class="gradeA">
                        <td>${(row.userName)!}</td>
                        <td>${(row.password)!}</td>
                        <td>
                        <#if row.sex == 1>
                            男
                        <#else>
                            女
                        </#if></td>
                        <td>${(row.phone)!}</td>
                        <td>${(row.emall)!}</td>
                        <td>${(row.userRolesKey.role.description)!}</td>
                        <td style="text-align:center" ><span style="cursor: pointer;" onclick="updat(${(row.userId)!},'${(row.userName)!}','${(row.password)!}',${(row.sex)!},'${(row.phone)!}','${(row.emall)!}')" class="badge badge-info"><i class="fa fa-edit"></i>修改</span>
                            <span style="cursor: pointer;" onclick="del(${row.userId!})" class="badge badge-info"><i class="fa fa-edit"></i>删除</span></td>
                    </tr>
                    </#list>
                <#else>
                <div align="center" style="font-size:16px;display: table-cell , overflow: hidden"><b>暂无数据 !</b></div>
                </#if>
            </tbody>
        </table>
    </div>
    <#if page?? && page.rows?? && page.rows?size gt 0>
        <@pagination formUrl="/office/user/list?page=%d&keyword=${keyword!}" currentPage=page.page totalPage=page.totalPage totalCount=page.total/>
    </#if>

    <div class="box margin-lb-10">
        <button class="btn btn-info btn-flat btn-primary" id="add" type="button">新增</button>
    </div>
    <div class="modal fade hide"  data-keyboard="false" id="add-modal" aria-hidden="true" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog">
            <div class="modal-content">
                <form method="post" id="add-form" autocomplete="off" class="form-horizontal">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title">用户新增/修改</h4>
                    </div>
                    <div class="modal-body">
                        <table style="width:100%;" align="center">
                            <input id="userId" type="hidden" name="userId" "/>
                            <tr>
                                <td class="text-right" width="40%" height="34px">
                                    <span style="padding-right:25px;">用户名:</span>
                                </td>
                                <td>
                                    <span><input id="userName" type="text" name="userName" "/> </span>
                                </td>
                            </tr>
                            <tr>
                                <td class="text-right" width="40%" height="34px">
                                    <span style="padding-right:25px;">密码:</span>
                                </td>
                                <td>
                                    <span><input  id="password" type="text" name="password" /></span>
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
                                    <span><input  id="phone"type="text" name="phone" /></span>
                                </td>
                            </tr>
                            <tr>
                                <td class="text-right" width="40%" height="34px">
                                    <span style="padding-right:25px;">邮箱:</span>
                                </td>
                                <td>
                                    <span><input  id="emall"type="text" name="emall" /></span>
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
                        <button type="button" class="btn btn-flat btn-default" data-dismiss="modal"><i
                                class="fa fa-close"></i> 取消
                        </button>
                        <button  id="save" type="button" class="btn btn-flat btn-primary"><i
                                class="fa fa-save"></i> 确定
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script src="/office/asset/js/sys/user.js"></script>
</@html>