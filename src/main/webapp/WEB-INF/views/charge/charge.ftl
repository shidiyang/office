<#include "../common/html.ftl"/>
<@html title="收费管理">
<div class="widget-box">
    <div class="widget-title"> <span class="icon"><i class="icon-th"></i></span>
        <h5>收费管理</h5>
    </div>
    <div id="search">
        <form method="POST" action="/office/charge/list">
        <input type="text" placeholder="收费名称" name="keyword" id="keyword" value="${keyword!}"/>
        <button type="submit" id="submit" class="tip-bottom" title="Search" ><i class="icon-search icon-white"></i></button>
        </form>
    </div>
    <div class="widget-content nopadding">
        <table class="table table-bordered data-table">
            <thead>
            <tr>
                <th>收费名称</th>
                <th>收费方式</th>
                <th>收费单价(元)</th>
                <th>单位</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
                <#if page?? && page.rows?? && page.rows?size gt 0>
                    <#list page.rows as row>
                    <tr class="gradeA">
                        <td>${(row.chargeName)!}</td>
                        <td>
                        <#if row.chargeType == 1>
                            按小时收费
                        <#elseif row.chargeType == 2>
                            按天收费
                        <#elseif  row.chargeType == 3>
                            按月收费
                        <#elseif row.chargeType == 4>
                            按年收费
                        <#elseif row.chargeType == 5>
                            按数量收费
                        </#if>${(row.staffName)!}</td>
                        <td>${(row.prince)!}</td>
                        <td>${(row.unit)!}</td>
                        <td style="text-align:center" ><span style="cursor: pointer;" onclick="updat(${(row.id)!},'${(row.chargeName)!}','${(row.chargeType)!}','${(row.prince)!}','${(row.unit)!}')" class="badge badge-info"><i class="fa fa-edit"></i>修改</span>
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
        <@pagination formUrl="/office/charge/list?page=%d&keyword=${keyword!}" currentPage=page.page totalPage=page.totalPage totalCount=page.total/>
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
                        <h4 class="modal-title">收费新增/修改</h4>
                    </div>
                    <div class="modal-body">
                        <table style="width:100%;" align="center">
                            <input id="id" type="hidden" name="id" "/>
                            <tr>
                                <td class="text-right" width="40%" height="34px">
                                    <span style="padding-right:25px;">收费名称:</span>
                                </td>
                                <td>
                                    <span><input id="chargeName" type="text" name="chargeName" "/> </span>
                                </td>
                            </tr>
                            <tr>
                                <td class="text-right" width="40%" height="34px">
                                    <span style="padding-right:25px;">收费方式:</span>
                                </td>
                                <td>
                                    <select name="chargeType" id="chargeType">
                                        <option value="1">按小时收费</option>
                                        <option value="2">按天收费</option>
                                        <option value="3">按月收费</option>
                                        <option value="4">按年收费</option>
                                        <option value="5">按数量收费</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td class="text-right" width="40%" height="34px">
                                    <span style="padding-right:25px;">收费单价(元):</span>
                                </td>
                                <td>
                                    <span><input id="prince" type="text" name="prince" /> </span>
                                </td>
                            </tr>
                            <tr>
                                <td class="text-right" width="40%" height="34px">
                                    <span style="padding-right:25px;">单位:</span>
                                </td>
                                <td>
                                    <span><input id="unit" type="text" name="unit" /> </span>
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
<script src="/office/asset/js/charge/charge.js"></script>
</@html>