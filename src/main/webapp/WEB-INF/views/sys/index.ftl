<#include "../common/head.ftl"/>
<#macro html title="">
<!DOCTYPE html>
<html lang="zh-CN">
    <@head title = title/>
<body class="">
    <#include "../common/header_manu.ftl"/>
    <#include "../common/bpTree.ftl"/>
    <div id="content">
    <div id="content-header">
        <div id="breadcrumb"> <a href="ind.html" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a></div>
    </div>
    <div class="container-fluid" style="padding: 0px;">
    <iframe src="" name="content" width="100%"  height="800px" frameborder="0" scrolling="auto" marginheight="0" marginwidth="0"></iframe>
    </div>
    </div>
    <#include "../common/footer.ftl"/>
<script type="text/javascript">
    startInit('mainFrame', 560);
</script>
</body>

</#macro>
<@html title="首页" />