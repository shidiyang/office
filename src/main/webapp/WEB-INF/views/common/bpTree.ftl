<div id="sidebar"><a href="#" class="visible-phone"><i class="icon icon-home"></i> Dashboard</a>
    <ul>
    <!-- 递归  宏定义 -->
<#macro bpTree children>
    <#if children?? && children?size gt 0>
        <#list children as child>
            <#if child.children?? && child.children?size gt 0>
                <li class="submenu"> <a href="" ><i class="icon ${(child.imageUrl)!}"></i> <span>${(child.text)!}</span> <span class="label label-important">${(child.children?size)!}</span></a>
                    <ul class="treeview-menu">
                        <@bpTree children=child.children />
                    </ul>
                </li>
            <#else>
                <li><a href="/office${child.url!}" target="content"><i class="icon ${(child.imageUrl)!}"></i> ${(child.text)!}</a></li>
            </#if>
        </#list>
    </#if>
</#macro>
    <!-- 调用宏 生成递归树 -->
<@bpTree children=treeMenu />
</ul>
</div>
