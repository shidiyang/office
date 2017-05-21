<#include "../common/html.ftl"/>
<@html title="房屋基础信息">
<div class="widget-box">
    <div class="widget-title"> <span class="icon"><i class="icon-th"></i></span>
        <h5>房屋基础信息</h5>
    </div>
    <div id="search">
        <form method="POST" action="/office/room/list">
        <input type="text" placeholder="编号或名称" name="keyword" id="keyword" value="${keyword!}"/>
        <button type="submit" id="submit" class="tip-bottom" title="Search" ><i class="icon-search icon-white"></i></button>
        </form>
    </div>
    <div class="widget-content nopadding">
        <table class="table table-bordered data-table">
            <thead>
            <tr>
                <th>房屋编号</th>
                <th>房屋名称</th>
                <th>面积(平方米) </th>
                <th>状态</th>
                <th>备注</th>
                <th>操作人</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
                <#if page?? && page.rows?? && page.rows?size gt 0>
                    <#list page.rows as row>
                    <tr class="gradeA">
                        <td>${(row.roomId)!}</td>
                        <td>${(row.roomName)!}</td>
                        <td>${(row.roomArea)!}</td>
                        <td>
                        <#if row.status == 1>
                            已租出
                        <#elseif row.status == 2>
                            未租出
                        </#if></td>
                        <td>${(row.remarks)!}</td>
                        <td>${(row.operator)!}</td>
                        <td style="text-align:center" ><span style="cursor: pointer;" onclick="updat(${(row.id)!},${(row.roomId)!},'${(row.roomName)!}','${(row.roomArea)!}','${(row.status)!}','${(row.remarks)!}')" class="badge badge-info"><i class="fa fa-edit"></i>修改</span>
                            <span style="cursor: pointer;" onclick="del(${row.id})" class="badge badge-info"><i class="fa fa-edit"></i>删除</span></td>
                    </tr>
                    </#list>
                <#else>
                <div align="center" style="font-size:16px;display: table-cell , overflow: hidden"><b>暂无数据 !</b></div>
                </#if>
            </tbody>
        </table>
    </div>
    <#if page?? && page.rows?? && page.rows?size gt 0>
        <@pagination formUrl="/office/room/list?page=%d&keyword=${keyword!}" currentPage=page.page totalPage=page.totalPage totalCount=page.total/>
    </#if>

    <div class="box margin-lb-10">
        <#--<button type="button" class="btn btn-flat btn-primary"  id="add" onclick="return add()">新增</button>-->
        <button class="btn btn-info btn-flat btn-primary" id="add" type="button">新增</button>
    </div>
    <div class="modal fade hide"  data-keyboard="false" id="add-modal" aria-hidden="true" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog">
            <div class="modal-content">
                <form method="post" id="add-form" autocomplete="off" class="form-horizontal">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title">房屋新增/修改</h4>
                    </div>
                    <div class="modal-body">
                        <table style="width:100%;" align="center">
                            <input id="id" type="hidden" name="id" "/>
                            <tr>
                                <td class="text-right" width="40%" height="34px">
                                    <span style="padding-right:25px;">房屋编号:</span>
                                </td>
                                <td>
                                    <span><input id="roomId" type="text" name="roomId" "/> </span>
                                </td>
                            </tr>
                            <tr>
                                <td class="text-right" width="40%" height="34px">
                                    <span style="padding-right:25px;">房屋名称:</span>
                                </td>
                                <td>
                                    <span><input  id="roomName" type="text" name="roomName" /></span>
                                </td>
                            </tr>
                            <tr>
                                <td class="text-right" width="40%" height="34px">
                                    <span style="padding-right:25px;">房屋面积:</span>
                                </td>
                                <td>
                                    <span><input  id="roomArea" type="text" name="roomArea" /></span>
                                </td>
                            </tr>
                            <tr>
                                <td class="text-right" width="40%" height="34px">
                                    <span style="padding-right:25px;">状态:</span>
                                </td>
                                <td>
                                    <select name="status" id="status">
                                        <option value='1'>已出租</option>
                                        <option value='2'>未出租</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td class="text-right" width="40%" height="34px">
                                    <span style="padding-right:25px;">备注:</span>
                                </td>
                                <td>
                                    <textarea class="span3" name="remarks" id="remarks"></textarea>
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
<script src="/office/asset/js/room/room.js"></script>
</@html>