<#include "../common/html.ftl"/>
<@html title="我的车位">
<div class="widget-box">
    <div class="widget-title"> <span class="icon"><i class="icon-th"></i></span>
        <h5>我的车位</h5>
    </div>
    <div id="search">
        <form method="POST" action="/office/cmy/list">
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
                <th>起租时间 </th>
                <th>计价方式</th>
                <th>费用(元)</th>
                <th>应付款(元)</th>
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
                        <td>${(row.rentTime?datetime)!}</td>
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
                        <td>${(row.total)!}</td>
                    </tr>
                    </#list>
                <#else>
                <div align="center" style="font-size:16px;display: table-cell , overflow: hidden"><b>暂无数据 !</b></div>
                </#if>
            </tbody>
        </table>
    </div>
    <#if page?? && page.rows?? && page.rows?size gt 0>
        <@pagination formUrl="/office/cmy/list?page=%d&keyword=${keyword!}" currentPage=page.page totalPage=page.totalPage totalCount=page.total/>
    </#if>
</div>
</@html>