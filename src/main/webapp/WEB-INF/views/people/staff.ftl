<#include "../common/html.ftl"/>
<@html title="员工管理">
<div class="widget-box">
    <div class="widget-title"> <span class="icon"><i class="icon-th"></i></span>
        <h5>员工管理</h5>
    </div>
    <div id="search">
        <form method="POST" action="/office/staff/list">
        <input type="text" placeholder="员工编号或姓名" name="keyword" id="keyword" value="${keyword!}"/>
        <button type="submit" id="submit" class="tip-bottom" title="Search" ><i class="icon-search icon-white"></i></button>
        </form>
    </div>
    <div class="widget-content nopadding">
        <table class="table table-bordered data-table">
            <thead>
            <tr>
                <th>员工号</th>
                <th>员工姓名</th>
                <th>性别</th>
                <th>电话</th>
                <th>邮箱</th>
                <th>所属部门</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
                <#if page?? && page.rows?? && page.rows?size gt 0>
                    <#list page.rows as row>
                    <tr class="gradeA">
                        <td>${(row.staffId)!}</td>
                        <td>${(row.staffName)!}</td>
                        <td>
                        <#if row.sex == 0>
                            女
                        <#elseif row.sex == 1 >
                            男
                        </#if></td>
                        <td>${(row.phone)!}</td>
                        <td>${(row.email)!}</td>
                        <td>${(row.department.departmentName)!}</td>
                        <td style="text-align:center" ><span style="cursor: pointer;" onclick="updat(${(row.id)!},'${(row.departmentName)!}','${(row.description)!}')" class="badge badge-info"><i class="fa fa-edit"></i>修改</span>
                            <span style="cursor: pointer;" onclick="del(${row.id})" class="badge badge-info"><i class="fa fa-edit"></i>删除</span></td>
                    </tr>
                    </#list>
                <#else>
                <div align="center" style="font-size:16px;display: table-cell , overflow: hidden"><b>暂无数据 !</b></div>
                </#if>
            </tbody>
        </table>
    </div>
    <#if page?? && page.rows?? && page.rows?size gt 0>
        <@pagination formUrl="/office/staff/list?page=%d&keyword=${keyword!}" currentPage=page.page totalPage=page.totalPage totalCount=page.total/>
    </#if>

    <div class="box margin-lb-10">
        <#--<button type="button" class="btn btn-flat btn-primary"  id="add" onclick="return add()">新增</button>-->
        <button class="btn btn-info btn-flat btn-primary" id="add" type="button">新增</button>
    </div>
    <div class="modal fade hide"  data-keyboard="false" id="add-modal" aria-hidden="true" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog">
            <div class="modal-content">
                <form method="post" id="add-form" autocomplete="off" class="form-horizontal">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title">员工新增/修改</h4>
                    </div>
                    <div class="modal-body">
                        <table style="width:100%;" align="center">
                            <input id="id" type="hidden" name="id" "/>
                            <tr>
                                <td class="text-right" width="40%" height="34px">
                                    <span style="padding-right:25px;">员工编号:</span>
                                </td>
                                <td>
                                    <span><input id="staffId" type="text" name="staffId" "/> </span>
                                </td>
                            </tr>
                            <tr>
                                <td class="text-right" width="40%" height="34px">
                                    <span style="padding-right:25px;">员工姓名:</span>
                                </td>
                                <td>
                                    <span><input id="staffName" type="text" name="staffName" "/> </span>
                                </td>
                            </tr>
                            <tr>
                                <td class="text-right" width="40%" height="34px">
                                    <span style="padding-right:25px;">性别:</span>
                                </td>
                                <td>
                                    <select name="sex" id="sex">
                                        <option value="0">女</option>
                                        <option value="1" selected="selected">男</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td class="text-right" width="40%" height="34px">
                                    <span style="padding-right:25px;">身份证号:</span>
                                </td>
                                <td>
                                    <span><input id="identityId" type="text" name="identityId" /> </span>
                                </td>
                            </tr>
                            <tr>
                                <td class="text-right" width="40%" height="34px">
                                    <span style="padding-right:25px;">生日:</span>
                                </td>
                                <td>
                                    <div  data-date="12-02-2012" class="input-append date datepicker" id="birthday">
                                        <input type="text" value="12-02-2012"  data-date-format="mm-dd-yyyy" class="span11"  >
                                        <span class="add-on"><i class="icon-th"></i></span> </div>
                                </td>
                            </tr>
                            <tr>
                                <td class="text-right" width="40%" height="34px">
                                    <span style="padding-right:25px;">联系方式:</span>
                                </td>
                                <td>
                                    <span><input id="phone" type="text" name="phone" /> </span>
                                </td>
                            </tr>
                            <tr>
                                <td class="text-right" width="40%" height="34px">
                                    <span style="padding-right:25px;">邮箱:</span>
                                </td>
                                <td>
                                    <span><input id="email" type="text" name="email" /> </span>
                                </td>
                            </tr>
                            <tr>
                                <td class="text-right" width="40%" height="34px">
                                    <span style="padding-right:25px;">民族:</span>
                                </td>
                                <td>
                                    <span><input id="nation" type="text" name="nation" /> </span>
                                </td>
                            </tr>
                            <tr>
                                <td class="text-right" width="40%" height="34px">
                                    <span style="padding-right:25px;">现居地:</span>
                                </td>
                                <td>
                                    <span><input id="currentAdress" type="text" name="currentAdress" /> </span>
                                </td>
                            </tr>
                            <tr>
                                <td class="text-right" width="40%" height="34px">
                                    <span style="padding-right:25px;">所在部门:</span>
                                </td>
                                <td>
                                    <select name="departmentId" id="departmentId">
                                        <#if departments?? && departments?size gt 0>
                                            <#list departments as department>
                                                <option value="${department.id}">${department.departmentName}</option>
                                            </#list>
                                        </#if>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td class="text-right" width="40%" height="34px">
                                    <span style="padding-right:25px;">籍贯:</span>
                                </td>
                                <td>
                                    <span><input id="nativePlace" type="text" name="nativePlace" /> </span>
                                </td>
                            </tr>
                            <tr>
                                <td class="text-right" width="40%" height="34px">
                                    <span style="padding-right:25px;">政治面貌:</span>
                                </td>
                                <td>
                                    <span><input id="politicalBackground" type="text" name="politicalBackground" /> </span>
                                </td>
                            </tr>
                            <tr>
                                <td class="text-right" width="40%" height="34px">
                                    <span style="padding-right:25px;">入职时间:</span>
                                </td>
                                <td>
                                    <#--<input type="text" data-date="01-02-2013" data-date-format="dd-mm-yyyy" value="01-02-2013" class="datepicker span11">-->
                                </td>
                            </tr>
                            <tr><td class="text-center" colspan="2"><span id="mes"></span></td></tr>
                        </table>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-flat btn-default" data-dismiss="modal"><i
                                class="fa fa-close"></i> 取消
                        </button>
                        <button  id="save" type="button" class="btn btn-flat btn-primary"><i
                                class="fa fa-save"></i> 确定
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script src="/office/asset/js/people/department.js"></script>
</@html>