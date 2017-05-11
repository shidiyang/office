<#macro pagination formUrl currentPage totalPage totalCount=0>
    <#assign startPage = currentPage-3/>
    <#assign endPage = currentPage+3/>
    <#if startPage lt 1>
        <#assign startPage = 1/>
    </#if>
    <#if endPage gt totalPage>
        <#assign endPage = totalPage/>
    </#if>
<div align="center" style="margin-top: 5px;" class="pagination alternate">
    <ul>
        <#if totalCount gte 0>
            <li class="disabled"><span>总计${totalCount}条记录</span></li>
        </#if>
        <#if startPage gt 1>
            <li <#if 1 == currentPage>class="disabled"</#if>>
                <a href="${formUrl?replace("%d","1")}" pageNum="1" onclick="return page()">1</a>
            </li>
            <#if startPage gt 1+1>
                <li class="disabled"><span>...</span></li>
            </#if>
        </#if>
        <#if endPage gt startPage>
            <#list startPage .. endPage as page>
                <li <#if page == currentPage>class="active"</#if>>
                    <a href="${formUrl?replace("%d",""+page)}" onclick="return page()" pageNum="${page}">${page}</a>
                </li>
            </#list>
        </#if>
        <#if endPage lt totalPage>
            <#if endPage lt totalPage -1>
                <li class="disabled"><span>...</span></li>
            </#if>
            <li <#if totalPage == currentPage>class="disabled"</#if>>
                <a href="${formUrl?replace("%d",""+totalPage)}" pageNum="${totalPage}">${totalPage}</a>
            </li>
        </#if>
    </ul>
</div>
</#macro>
