<#include "head.ftl"/>
<#include "page.ftl"/>
<#macro html title="">
<!DOCTYPE html>
<html lang="zh-CN">
    <@head title = title/>
<body class="">
<div id="content" style="margin: 0px;padding-bottom: 40px">
    <div class="container-fluid">
        <#nested />
    </div>
</div>
</body>
</#macro>