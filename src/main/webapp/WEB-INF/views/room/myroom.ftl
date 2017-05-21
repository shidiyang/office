<#include "../common/html.ftl"/>
<@html title="我的房屋">
<div class="widget-box">
    <div class="widget-title"> <span class="icon"><i class="icon-th"></i></span>
        <h5>我的房屋</h5>
    </div>
    <div id="search">
        <form method="POST" action="/office/myroom/list">
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
                <th>房屋面积</th>
                <th>房屋用途 </th>
                <th>起租时间 </th>
                <th>操作人</th>
                <th>备注</th>
            </tr>
            </thead>
            <tbody>
                <#if page?? && page.rows?? && page.rows?size gt 0>
                    <#list page.rows as row>
                    <tr class="gradeA">
                        <td>${(row.roomId)!}</td>
                        <td>${(row.roomName)!}</td>
                        <td>${(row.roomArea)!}</td>
                        <td>${(row.roomUse)!}</td>
                        <td>${(row.rentTime?datetime)!}</td>
                        <td>${(row.operator)!}</td>
                        <td>${(row.remarks)!}</td>
                    </tr>
                    </#list>
                <#else>
                <div align="center" style="font-size:16px;display: table-cell , overflow: hidden"><b>暂无数据 !</b></div>
                </#if>
            </tbody>
        </table>
    </div>
    <#if page?? && page.rows?? && page.rows?size gt 0>
        <@pagination formUrl="/office/myroom/list?page=%d&keyword=${keyword!}" currentPage=page.page totalPage=page.totalPage totalCount=page.total/>
    </#if>
</div>
</@html>