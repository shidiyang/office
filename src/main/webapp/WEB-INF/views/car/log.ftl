<#include "../common/html.ftl"/>
<@html title="车位基础信息">
<div class="widget-box">
    <div class="widget-title"> <span class="icon"><i class="icon-th"></i></span>
        <h5>车位基础信息</h5>
    </div>
    <div id="search">
        <form method="POST" action="/office/cbash/list">
        <input type="text" placeholder="编号或地址" name="keyword" id="keyword" value="${keyword!}"/>
        <button type="submit" id="submit" class="tip-bottom" title="Search" ><i class="icon-search icon-white"></i></button>
        </form>
    </div>
    <div class="widget-content nopadding">
        <table class="table table-bordered data-table">
            <thead>
            <tr>
                <th>车位编号</th>
                <th>车位地址</th>
                <th>车位类型 </th>
                <th>状态</th>
                <th>计价方式</th>
                <th>费用(元)</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
                <#if page?? && page.rows?? && page.rows?size gt 0>
                    <#list page.rows as row>
                    <tr class="gradeA">
                        <td>${(row.parkingId)!}</td>
                        <td>${(row.adress)!}</td>
                        <td>${(row.type)!}</td>
                        <td>
                        <#if row.status == 1>
                            已租出
                        <#elseif row.status == 2>
                            未租出
                        </#if></td>
                        <td>
                            <#if row.princeType == 1>
                                按小时收费
                            <#elseif row.princeType == 2>
                                按天收费
                            <#elseif row.princeType == 3>
                                按月收费
                            <#elseif row.princeType == 4>
                                按年收费
                            </#if></td>
                        <td>${(row.prince)!}</td>
                        <td style="text-align:center" ><span style="cursor: pointer;" onclick="updat(${(row.id)!},${(row.parkingId)!},'${(row.adress)!}','${(row.type)!}','${(row.princeType)!}','${(row.prince)!}')" class="badge badge-info"><i class="fa fa-edit"></i>修改</span>
                            <span style="cursor: pointer;" onclick="del(${row.id})" class="label label-info"><i class="badge badge-info"></i>删除</span></td>
                    </tr>
                    </#list>
                <#else>
                <div align="center" style="font-size:16px;display: table-cell , overflow: hidden"><b>暂无数据 !</b></div>
                </#if>
            </tbody>
        </table>
    </div>
    <#if page?? && page.rows?? && page.rows?size gt 0>
        <@pagination formUrl="/office/cbash/list?page=%d&keyword=${keyword!}" currentPage=page.page totalPage=page.totalPage totalCount=page.total/>
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
                        <h4 class="modal-title">车位新增/修改</h4>
                    </div>
                    <div class="modal-body">
                        <table style="width:100%;" align="center">
                            <input id="id" type="hidden" name="id" "/>
                            <tr>
                                <td class="text-right" width="40%" height="34px">
                                    <span style="padding-right:25px;">车位编号:</span>
                                </td>
                                <td>
                                    <span><input id="parkingId" type="text" name="parkingId" "/> </span>
                                </td>
                            </tr>
                            <tr>
                                <td class="text-right" width="40%" height="34px">
                                    <span style="padding-right:25px;">车位地址:</span>
                                </td>
                                <td>
                                    <span><input  id="adress" type="text" name="adress" /></span>
                                </td>
                            </tr>
                            <tr>
                                <td class="text-right" width="40%" height="34px">
                                    <span style="padding-right:25px;">车位类型:</span>
                                </td>
                                <td>
                                    <select name="type" id="type">
                                        <option value='1'>小型车位</option>
                                        <option value='2'>中型车位</option>
                                        <option value='3'>大型车位</option>
                                        <option value='4'>超大型车位</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td class="text-right" width="40%" height="34px">
                                    <span style="padding-right:25px;">计价方式:</span>
                                </td>
                                <td>
                                    <select name="princeType" id="princeType">
                                        <option value='1'>按小时收费</option>
                                        <option value='2'>按天收费</option>
                                        <option value='3'>按月收费</option>
                                        <option value='4'>按年收费</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td class="text-right" width="40%" height="34px">
                                    <span style="padding-right:25px;">费用:</span>
                                </td>
                                <td>
                                    <span><input  id="prince"type="text" name="prince" /></span>
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
<script src="/office/asset/js/car/rent.js"></script>
</@html>