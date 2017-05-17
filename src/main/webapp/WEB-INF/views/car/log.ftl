<#include "../common/html.ftl"/>
<@html title="车位出租记录">
<div class="widget-box">
    <div class="widget-title"> <span class="icon"><i class="icon-th"></i></span>
        <h5>车位出租记录</h5>
    </div>
    <div id="search">
        <form method="POST" action="/office/clog/list">
            <select name="keyType" id="keyType" style="width: 80px">
                <option value="0">全部</option>
                <option value="1">租出</option>
                <option value="2">收回</option>
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
                <th>租借人</th>
                <th>操作时间</th>
                <th>费用(元)</th>
                <th>操作人</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
                <#if page?? && page.rows?? && page.rows?size gt 0>
                    <#list page.rows as row>
                    <tr class="gradeA">
                        <td>${(row.parkingId)!}</td>
                        <td>${(row.parkingAdress)!}</td>
                        <td>
                                <span style="cursor: pointer;" onclick="showInfo('${(row.rentor.userName)!}',${(row.rentor.sex)!},'${(row.rentor.phone)!}','${(row.rentor.emall)!}')" class="badge badge-info"><i class="fa fa-edit"></i>${(row.rentor.userName)!}</span>
                        </td>
                        <td>${(row.operateTime?datetime)!}</td>
                        <td>
                            <#if row.operateType == 2>
                            ${(row.account)!}
                        </#if></td>
                        <td>${(row.oprerator)!}</td>
                        <#if row.operateType == 1>
                            <td style="color: #ff0000"> 租出</td>
                        <#elseif row.operateType == 2>
                            <td style="color: #00ff00"> 收回</td>
                        </#if>
                    </tr>
                    </#list>
                <#else>
                <div align="center" style="font-size:16px;display: table-cell , overflow: hidden"><b>暂无数据 !</b></div>
                </#if>
            </tbody>
        </table>
    </div>
    <#if page?? && page.rows?? && page.rows?size gt 0>
        <@pagination formUrl="/office/clog/list?page=%d&keyword=${keyword!}&keyType=${keyType!}" currentPage=page.page totalPage=page.totalPage totalCount=page.total/>
    </#if>

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
<script src="/office/asset/js/car/log.js"></script>
</@html>