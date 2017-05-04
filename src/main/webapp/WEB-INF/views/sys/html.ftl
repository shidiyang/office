<#include "head.ftl"/>
<#macro html title="">
<!DOCTYPE html>
<html lang="zh-CN">
    <@head title = title/>
<body class="">
    <#include "header_manu.ftl"/>
    <#include "bpTree.ftl"/>
    <div id="content">
    <div id="content-header">
        <div id="breadcrumb"> <a href="index.html" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a></div>
    </div>
    <div class="container-fluid">
    <iframe src="" name="content" width="100%" frameborder="0"></iframe>
    </div>
    </div>
    <#include "footer.ftl"/>
</body>

</#macro>
<@html title="首页" />