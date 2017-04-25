<#include "head.ftl"/>
<#macro html title="">
<!DOCTYPE html>
<html lang="zh-CN">
    <@head title = title/>
<body class="">
<div class="" style="position:static;">
    <div class="">
        <section class="">
            <h1>
                <i class=""></i> Home
                <small> ${title!}</small>
            </h1>
        </section>
        <section class="">
            <#nested />
        </section>
    </div>
</div>
</body>

</#macro>