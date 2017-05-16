<#include "../common/html.ftl"/>
<@html title="角色管理">
<div class="widget-box">
    <div class="widget-title"> <span class="icon"><i class="icon-th"></i></span>
        <h5>用户管理</h5>
    </div>
    <div id="search">
        <form method="POST" action="/office/role/list">
        <input type="text" placeholder="角色名称" name="keyword" id="keyword" value="${keyword!}"/>
        <button type="submit" id="submit" class="tip-bottom" title="Search" ><i class="icon-search icon-white"></i></button>
        </form>
    </div>
    <div class="widget-content nopadding">
        <table class="table table-bordered data-table">
            <thead>
            <tr>
                <th>角色名称</th>
                <th>权限名称</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
                <#if page?? && page.rows?? && page.rows?size gt 0>
                    <#list page.rows as row>
                    <tr class="gradeA">
                        <td>${(row.roles.description)!}</td>
                        <td>${(row.permission.description)!}</td>
                        <td style="text-align:center" > <span style="cursor: pointer;" onclick="del(${row.roleId!},${row.permissionId!})" class="label label-info"><i class="fa fa-edit"></i>删除</span></td>
                    </tr>
                    </#list>
                <#else>
                <div align="center" style="font-size:16px;display: table-cell , overflow: hidden"><b>暂无数据 !</b></div>
                </#if>
            </tbody>
        </table>
    </div>
    <#if page?? && page.rows?? && page.rows?size gt 0>
        <@pagination formUrl="/office/role/list?page=%d&keyword=${keyword!}" currentPage=page.page totalPage=page.totalPage totalCount=page.total/>
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
                        <h4 class="modal-title">新增角色</h4>
                    </div>
                    <div class="modal-body">
                        <table style="width:100%;" align="center">
                            <tr>
                                <td class="text-right" width="40%" height="34px">
                                    <span style="padding-right:25px;">角色名称:</span>
                                </td>
                                <td>
                                    <span><input id="rolesName" type="text" name="roles.description" "/> </span>
                                </td>
                            </tr>
                            <tr>
                                <td class="text-right" width="40%" height="34px">
                                    <span style="padding-right:25px;">权限名称:</span>
                                </td>
                                <td>
                                    <select name="permissionId" id="permissionId">
                                        <#if permissions?? && permissions?size gt 0>
                                            <#list permissions as permission>
                                                <option value='${permission.permissionId}'>${permission.description}</option>
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
<script src="/office/asset/js/sys/roles.js"></script>
</@html>