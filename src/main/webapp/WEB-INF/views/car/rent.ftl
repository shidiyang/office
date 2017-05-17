<#include "../common/html.ftl"/>
<@html title="车位租借">
<div class="widget-box">
    <div class="widget-title"> <span class="icon"><i class="icon-th"></i></span>
        <h5>车位租借</h5>
    </div>
    <div id="search">
        <form method="POST" action="/office/crent/list">
            <select name="keyType" id="keyType" style="width: 80px">
                <option value="0">全部</option>
                <option value="1">已出租</option>
                <option value="2">未出租</option>
            </select>
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
                <th>登记日期</th>
                <th>租借人</th>
                <th>计价方式</th>
                <th>费用(元)</th>
                <th>应支付</th>
                <th>操作人</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
                <#if page?? && page.rows?? && page.rows?size gt 0>
                    <#list page.rows as row>
                    <tr class="gradeA">
                        <td>${(row.parkingId)!}</td>
                        <td>${(row.adress)!}</td>
                        <td>
                            <#if row.type==1>
                                小型车位
                            <#elseif row.type==2>
                                中型车位
                            <#elseif row.type==3>
                                大型车位
                            <#elseif row.type==4>
                                超大型车位
                            </#if></td>
                        <#if row.status == 1>
                            <td style="color: #ff0000"> 已租出</td>
                        <#elseif row.status == 2>
                            <td style="color: #00ff00"> 未租出</td>
                        </#if>
                        <td>${(row.rentTime?datetime)!}</td>
                        <td>
                            <#if row.status==1>
                                <span style="cursor: pointer;" onclick="showInfo('${(row.user.userName)!}',${(row.user.sex)!},'${(row.user.phone)!}','${(row.user.emall)!}')" class="badge badge-info"><i class="fa fa-edit"></i>${(row.user.userName)!}</span>
                            <#else >
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
                        <td>
                        <#if row.status == 1>
                            ${(row.total)!}
                        </#if></td>
                        <td>${(row.oprator)!}</td>
                        <td style="text-align:center">
                        <#if row.status==2>
                            <span style="cursor: pointer;" onclick="rent(${(row.id)!},${(row.parkingId)!})" class="badge badge-success"><i class="fa fa-edit"></i>租出</span>
                        <#elseif row.status==1>
                            <span style="cursor: pointer;" onclick="recover(${row.id!},${(row.total)!})" class="badge badge-warning"><i class="fa fa-edit"></i>收回</span>
                        </#if></td>
                    </tr>
                    </#list>
                <#else>
                <div align="center" style="font-size:16px;display: table-cell , overflow: hidden"><b>暂无数据 !</b></div>
                </#if>
            </tbody>
        </table>
    </div>
    <#if page?? && page.rows?? && page.rows?size gt 0>
        <@pagination formUrl="/office/crent/list?page=%d&keyword=${keyword!}&keyType=${keyType!}" currentPage=page.page totalPage=page.totalPage totalCount=page.total/>
    </#if>

    <div class="modal fade hide"  data-keyboard="false" id="add-modal" aria-hidden="true" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog">
            <div class="modal-content">
                <form method="post" id="add-form" autocomplete="off" class="form-horizontal">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title">租出</h4>
                    </div>
                    <div class="modal-body">
                        <table style="width:100%;" align="center">
                            <input id="id" type="hidden" name="id" "/>
                            <tr>
                                <td class="text-right" width="40%" height="34px">
                                    <span style="padding-right:25px;">车位编号:</span>
                                </td>
                                <td>
                                    <span><input id="parkingId" type="text" name="parkingId"  disabled="disabled""/> </span>
                                </td>
                            </tr>
                            <tr>
                                <td class="text-right" width="40%" height="34px">
                                    <span style="padding-right:25px;">租借人:</span>
                                </td>
                                <td>
                                    <span><input  id="rentor" type="text" name="rentor" /></span>
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

    <div class="modal fade hide"  data-keyboard="false" id="info-modal" aria-hidden="true" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog">
            <div class="modal-content">
                <form method="post" id="add-form" autocomplete="off" class="form-horizontal">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title">用户信息</h4>
                    </div>
                    <div class="modal-body">
                        <table style="width:100%;" align="center">
                            <tr>
                                <td class="text-right" width="40%" height="34px">
                                    <span style="padding-right:25px;">租借人:</span>
                                </td>
                                <td>
                                    <span><input id="iuserName" type="text" disabled="disabled"/> </span>
                                </td>
                            </tr>
                            <tr>
                                <td class="text-right" width="40%" height="34px">
                                    <span style="padding-right:25px;">性别:</span>
                                </td>
                                <td>
                                    <select id="isex" disabled="disabled">
                                        <option value='0'>女</option>
                                        <option value='1'>男</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td class="text-right" width="40%" height="34px">
                                    <span style="padding-right:25px;">电话:</span>
                                </td>
                                <td>
                                    <span><input  id="iphone"type="text" disabled="disabled"/></span>
                                </td>
                            </tr>
                            <tr>
                                <td class="text-right" width="40%" height="34px">
                                    <span style="padding-right:25px;">邮箱:</span>
                                </td>
                                <td>
                                    <span><input  id="iemall"type="text" disabled="disabled"/></span>
                                </td>
                            </tr>
                        </table>
                    </div>
                    <div class="modal-footer">
                        <button  type="button" class="btn btn-flat btn-primary" data-dismiss="modal"><i
                                class="fa fa-close"></i> 确定
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $('#keyType').val(${keyType!});
</script>
<script src="/office/asset/js/car/rent.js"></script>
</@html>