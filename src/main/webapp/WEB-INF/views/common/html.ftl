<#include "head.ftl"/>
<#macro html title="">
<!DOCTYPE html>
<html lang="zh-CN">
    <@head title = title/>
<body class="">
<div id="content">
    <div id="content-header">
        <div id="breadcrumb"> <a href="index.html" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a></div>
    </div>
    <div class="container-fluid">
        <#nested />
    </div>
</div>
</body>
</#macro>