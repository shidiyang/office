<#include "../common/html.ftl"/>
<@html title="菜单管理">
<div class="widget-box">
    <div class="widget-title"> <span class="icon"><i class="icon-th"></i></span>
        <h5>菜单管理</h5>
    </div>
    <div id="search">
        <form method="POST" action="/office/menu/list">
        <input type="text" placeholder="父ID" name="keyword" id="keyword" value="${keyword!}"/>
        <button type="submit" id="submit" class="tip-bottom" title="Search" ><i class="icon-search icon-white"></i></button>
        </form>
    </div>
    <div class="widget-content nopadding">
        <table class="table table-bordered data-table">
            <thead>
            <tr>
                <th>菜单ID</th>
                <th>菜单名称</th>
                <th>父ID</th>
                <th>排序</th>
                <th>图标</th>
                <th>URL</th>
                <th>权限名称</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
                <#if page?? && page.rows?? && page.rows?size gt 0>
                    <#list page.rows as row>
                    <tr class="gradeA">
                        <td>${(row.menuId)!}</td>
                        <td>${(row.text)!}</td>
                        <td>${(row.parentId)!}</td>
                        <td>${(row.orderId)!}</td>
                        <td><i class="${(row.imageUrl)!}"></td>
                        <td>${(row.url)!}</td>
                        <td>${(row.permission.description)!}</td>
                        <td style="text-align:center" ><span style="cursor: pointer;" onclick="updat(${(row.id)!},${(row.menuId)!},'${(row.text)!}',${(row.parentId)!},${(row.orderId)!},'${(row.imageUrl)!}','${(row.url)!}','${(row.permission.description)!}')" class="label label-info"><i class="fa fa-edit"></i>修改</span>
                            <span style="cursor: pointer;" onclick="del(${row.id})" class="label label-info"><i class="fa fa-edit"></i>删除</span></td>
                    </tr>
                    </#list>
                <#else>
                <div align="center" style="font-size:16px;display: table-cell , overflow: hidden"><b>暂无数据 !</b></div>
                </#if>
            </tbody>
        </table>
    </div>
    <#if page?? && page.rows?? && page.rows?size gt 0>
        <@pagination formUrl="/office/menu/list?page=%d&keyword=${keyword!}" currentPage=page.page totalPage=page.totalPage totalCount=page.total/>
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
                        <h4 class="modal-title">菜单新增/修改</h4>
                    </div>
                    <div class="modal-body">
                        <table style="width:100%;" align="center">
                            <input id="id" type="hidden" name="id" "/>
                            <tr>
                                <td class="text-right" width="40%" height="34px">
                                    <span style="padding-right:25px;">菜单ID:</span>
                                </td>
                                <td>
                                    <span><input id="menuId" type="text" name="menuId" "/> </span>
                                </td>
                            </tr>
                            <tr>
                                <td class="text-right" width="40%" height="34px">
                                    <span style="padding-right:25px;">菜单名称:</span>
                                </td>
                                <td>
                                    <span><input  id="text" type="text" name="text" /></span>
                                </td>
                            </tr>
                            <tr>
                                <td class="text-right" width="40%" height="34px">
                                    <span style="padding-right:25px;">父ID:</span>
                                </td>
                                <td>
                                    <span><input  id="parentId"type="text" name="parentId" /></span>
                                </td>
                            </tr>
                            <tr>
                                <td class="text-right" width="40%" height="34px">
                                    <span style="padding-right:25px;">排序:</span>
                                </td>
                                <td>
                                    <span><input  id="orderId"type="text" name="orderId" /></span>
                                </td>
                            </tr>
                            <tr>
                                <td class="text-right" width="40%" height="34px">
                                    <span style="padding-right:25px;">图标:</span>
                                </td>
                                <td>
                                        <select name="imageUrl" id="imageUrl">
                                            <option value='icon-adjust'>icon-adjust</option>
                                            <option value='icon-asterisk'>icon-asterisk</option>
                                            <option value='icon-ban-circle'>icon-ban-circle</option>
                                            <option value='icon-bar-chart'>icon-bar-chart</option>
                                            <option value='icon-barcode'>icon-barcode</option>
                                            <option value='icon-beaker'>icon-beaker</option>
                                            <option value='icon-bell'>icon-bell</option>
                                            <option value='icon-bolt'>icon-bolt</option>
                                            <option value='icon-book'>icon-book</option>
                                            <option value='icon-bookmark'>icon-bookmark</option>
                                            <option value='icon-bookmark-empty'>icon-bookmark-empty</option>
                                            <option value='icon-briefcase'>icon-briefcase</option>
                                            <option value='icon-bullhorn'>icon-bullhorn</option>
                                            <option value='icon-calendar'>icon-calendar</option>
                                            <option value='icon-camera'>icon-camera</option>
                                            <option value='icon-camera-retro'>icon-camera-retro</option>
                                            <option value='icon-certificate'>icon-certificate</option>
                                            <option value='icon-check'>icon-check</option>
                                            <option value='icon-check-empty'>icon-check-empty</option>
                                            <option value='icon-cloud'>icon-cloud</option>
                                            <option value='icon-cog'>icon-cog</option>
                                            <option value='icon-cogs'>icon-cogs</option>
                                            <option value='icon-comment'>icon-comment</option>
                                            <option value='icon-comment-alt'>icon-comment-alt</option>
                                            <option value='icon-comments'>icon-comments</option>
                                            <option value='icon-comments-alt'>icon-comments-alt</option>
                                            <option value='icon-credit-card'>icon-credit-card</option>
                                            <option value='icon-dashboard'>icon-dashboard</option>
                                            <option value='icon-download'>icon-download</option>
                                            <option value='icon-download-alt'>icon-download-alt</option>
                                            <option value='icon-edit'>icon-edit</option>
                                            <option value='icon-envelope'>icon-envelope</option>
                                            <option value='icon-envelope-alt'>icon-envelope-alt</option>
                                            <option value='icon-exclamation-sign'>icon-exclamation-sign</option>
                                            <option value='icon-external-link'>icon-external-link</option>
                                            <option value='icon-eye-close'>icon-eye-close</option>
                                            <option value='icon-eye-open'>icon-eye-open</option>
                                            <option value='icon-facetime-video'>icon-facetime-video</option>
                                            <option value='icon-film'>icon-film</option>
                                            <option value='icon-filter'>icon-filter</option>
                                            <option value='icon-fire'>icon-fire</option>
                                            <option value='icon-flag'>icon-flag</option>
                                            <option value='icon-folder-close'>icon-folder-close</option>
                                            <option value='icon-folder-open'>icon-folder-open</option>
                                            <option value='icon-gift'>icon-gift</option>
                                            <option value='icon-glass'>icon-glass</option>
                                            <option value='icon-globe'>icon-globe</option>
                                            <option value='icon-group'>icon-group</option>
                                            <option value='icon-hdd'>icon-hdd</option>
                                            <option value='icon-headphones'>icon-headphones</option>
                                            <option value='icon-heart'>icon-heart</option>
                                            <option value='icon-heart-empty'>icon-heart-empty</option>
                                            <option value='icon-home'>icon-home</option>
                                            <option value='icon-inbox'>icon-inbox</option>
                                            <option value='icon-info-sign'>icon-info-sign</option>
                                            <option value='icon-key'>icon-key</option>
                                            <option value='icon-leaf'>icon-leaf</option>
                                            <option value='icon-legal'>icon-legal</option>
                                            <option value='icon-lemon'>icon-lemon</option>
                                            <option value='icon-lock'>icon-lock</option>
                                            <option value='icon-unlock'>icon-unlock</option>
                                            <option value='icon-magic'>icon-magic</option>
                                            <option value='icon-magnet'>icon-magnet</option>
                                            <option value='icon-map-marker'>icon-map-marker</option>
                                            <option value='icon-minus'>icon-minus</option>
                                            <option value='icon-minus-sign'>icon-minus-sign</option>
                                            <option value='icon-money'>icon-money</option>
                                            <option value='icon-move'>icon-move</option>
                                            <option value='icon-music'>icon-music</option>
                                            <option value='icon-off'>icon-off</option>
                                            <option value='icon-ok'>icon-ok</option>
                                            <option value='icon-ok-circle'>icon-ok-circle</option>
                                            <option value='icon-ok-sign'>icon-ok-sign</option>
                                            <option value='icon-pencil'>icon-pencil</option>
                                            <option value='icon-picture'>icon-picture</option>
                                            <option value='icon-plane'>icon-plane</option>
                                            <option value='icon-plus'>icon-plus</option>
                                            <option value='icon-plus-sign'>icon-plus-sign</option>
                                            <option value='icon-print'>icon-print</option>
                                            <option value='icon-pushpin'>icon-pushpin</option>
                                            <option value='icon-qrcode'>icon-qrcode</option>
                                            <option value='icon-question-sign'>icon-question-sign</option>
                                            <option value='icon-random'>icon-random</option>
                                            <option value='icon-refresh'>icon-refresh</option>
                                            <option value='icon-remove'>icon-remove</option>
                                            <option value='icon-remove-circle'>icon-remove-circle</option>
                                            <option value='icon-remove-sign'>icon-remove-sign</option>
                                            <option value='icon-reorder'>icon-reorder</option>
                                            <option value='icon-resize-horizontal'>icon-resize-horizontal</option>
                                            <option value='icon-resize-vertical'>icon-resize-vertical</option>
                                            <option value='icon-retweet'>icon-retweet</option>
                                            <option value='icon-road'>icon-road</option>
                                            <option value='icon-rss'>icon-rss</option>
                                            <option value='icon-screenshot'>icon-screenshot</option>
                                            <option value='icon-search'>icon-search</option>
                                            <option value='icon-share'>icon-share</option>
                                            <option value='icon-share-alt'>icon-share-alt</option>
                                            <option value='icon-shopping-cart'>icon-shopping-cart</option>
                                            <option value='icon-signal'>icon-signal</option>
                                            <option value='icon-signin'>icon-signin</option>
                                            <option value='icon-signout'>icon-signout</option>
                                            <option value='icon-sitemap'>icon-sitemap</option>
                                            <option value='icon-sort'>icon-sort</option>
                                            <option value='icon-sort-down'>icon-sort-down</option>
                                            <option value='icon-sort-up'>icon-sort-up</option>
                                            <option value='icon-star'>icon-star</option>
                                            <option value='icon-star-empty'>icon-star-empty</option>
                                            <option value='icon-star-half'>icon-star-half</option>
                                            <option value='icon-tag'>icon-tag</option>
                                            <option value='icon-tags'>icon-tags</option>
                                            <option value='icon-tasks'>icon-tasks</option>
                                            <option value='icon-thumbs-down'>icon-thumbs-down</option>
                                            <option value='icon-thumbs-up'>icon-thumbs-up</option>
                                            <option value='icon-time'>icon-time</option>
                                            <option value='icon-tint'>icon-tint</option>
                                            <option value='icon-trash'>icon-trash</option>
                                            <option value='icon-trophy'>icon-trophy</option>
                                            <option value='icon-truck'>icon-truck</option>
                                            <option value='icon-umbrella'>icon-umbrella</option>
                                            <option value='icon-upload'>icon-upload</option>
                                            <option value='icon-upload-alt'>icon-upload-alt</option>
                                            <option value='icon-user'>icon-user</option>
                                            <option value='icon-user-md'>icon-user-md</option>
                                            <option value='icon-volume-off'>icon-volume-off</option>
                                            <option value='icon-volume-down'>icon-volume-down</option>
                                            <option value='icon-volume-up'>icon-volume-up</option>
                                            <option value='icon-warning-sign'>icon-warning-sign</option>
                                            <option value='icon-wrench'>icon-wrench</option>
                                            <option value='icon-zoom-in'>icon-zoom-in</option>
                                            <option value='icon-zoom-out'>icon-zoom-out</option>
                                            <option value='icon-file'>icon-file</option>
                                            <option value='icon-cut'>icon-cut</option>
                                            <option value='icon-copy'>icon-copy</option>
                                            <option value='icon-paste'>icon-paste</option>
                                            <option value='icon-save'>icon-save</option>
                                            <option value='icon-undo'>icon-undo</option>
                                            <option value='icon-repeat'>icon-repeat</option>
                                            <option value='icon-paper-clip'>icon-paper-clip</option>
                                            <option value='icon-text-height'>icon-text-height</option>
                                            <option value='icon-text-width'>icon-text-width</option>
                                            <option value='icon-align-left'>icon-align-left</option>
                                            <option value='icon-align-center'>icon-align-center</option>
                                            <option value='icon-align-right'>icon-align-right</option>
                                            <option value='icon-align-justify'>icon-align-justify</option>
                                            <option value='icon-indent-left'>icon-indent-left</option>
                                            <option value='icon-indent-right'>icon-indent-right</option>
                                            <option value='icon-font'>icon-font</option>
                                            <option value='icon-bold'>icon-bold</option>
                                            <option value='icon-italic'>icon-italic</option>
                                            <option value='icon-strikethrough'>icon-strikethrough</option>
                                            <option value='icon-underline'>icon-underline</option>
                                            <option value='icon-link'>icon-link</option>
                                            <option value='icon-columns'>icon-columns</option>
                                            <option value='icon-table'>icon-table</option>
                                            <option value='icon-th-large'>icon-th-large</option>
                                            <option value='icon-th'>icon-th</option>
                                            <option value='icon-th-list'>icon-th-list</option>
                                            <option value='icon-list'>icon-list</option>
                                            <option value='icon-list-ol'>icon-list-ol</option>
                                            <option value='icon-list-ul'>icon-list-ul</option>
                                            <option value='icon-list-alt'>icon-list-alt</option>
                                            <option value='icon-arrow-down'>icon-arrow-down</option>
                                            <option value='icon-arrow-left'>icon-arrow-left</option>
                                            <option value='icon-arrow-right'>icon-arrow-right</option>
                                            <option value='icon-arrow-up'>icon-arrow-up</option>
                                            <option value='icon-chevron-down'>icon-chevron-down</option>
                                            <option value='icon-circle-arrow-down'>icon-circle-arrow-down</option>
                                            <option value='icon-circle-arrow-left'>icon-circle-arrow-left</option>
                                            <option value='icon-circle-arrow-right'>icon-circle-arrow-right</option>
                                            <option value='icon-circle-arrow-up'>icon-circle-arrow-up</option>
                                            <option value='icon-chevron-left'>icon-chevron-left</option>
                                            <option value='icon-caret-down'>icon-caret-down</option>
                                            <option value='icon-caret-left'>icon-caret-left</option>
                                            <option value='icon-caret-right'>icon-caret-right</option>
                                            <option value='icon-caret-up'>icon-caret-up</option>
                                            <option value='icon-chevron-right'>icon-chevron-right</option>
                                            <option value='icon-hand-down'>icon-hand-down</option>
                                            <option value='icon-hand-left'>icon-hand-left</option>
                                            <option value='icon-hand-right'>icon-hand-right</option>
                                            <option value='icon-hand-up'>icon-hand-up</option>
                                            <option value='icon-chevron-up'>icon-chevron-up</option>
                                            <option value='icon-play-circle'>icon-play-circle</option>
                                            <option value='icon-play'>icon-play</option>
                                            <option value='icon-pause'>icon-pause</option>
                                            <option value='icon-stop'>icon-stop</option>
                                            <option value='icon-step-backward'>icon-step-backward</option>
                                            <option value='icon-fast-backward'>icon-fast-backward</option>
                                            <option value='icon-backward'>icon-backward</option>
                                            <option value='icon-forward'>icon-forward</option>
                                            <option value='icon-fast-forward'>icon-fast-forward</option>
                                            <option value='icon-step-forward'>icon-step-forward</option>
                                            <option value='icon-eject'>icon-eject</option>
                                            <option value='icon-fullscreen'>icon-fullscreen</option>
                                            <option value='icon-resize-full'>icon-resize-full</option>
                                            <option value='icon-resize-small'>icon-resize-small</option>
                                            <option value='icon-phone'>icon-phone</option>
                                            <option value='icon-phone-sign'>icon-phone-sign</option>
                                            <option value='icon-facebook'>icon-facebook</option>
                                            <option value='icon-facebook-sign'>icon-facebook-sign</option>
                                            <option value='icon-twitter'>icon-twitter</option>
                                            <option value='icon-twitter-sign'>icon-twitter-sign</option>
                                            <option value='icon-github'>icon-github</option>
                                            <option value='icon-github-sign'>icon-github-sign</option>
                                            <option value='icon-linkedin'>icon-linkedin</option>
                                            <option value='icon-linkedin-sign'>icon-linkedin-sign</option>
                                            <option value='icon-pinterest'>icon-pinterest</option>
                                            <option value='icon-pinterest-sign'>icon-pinterest-sign</option>
                                            <option value='icon-google-plus'>icon-google-plus</option>
                                            <option value='icon-google-plus-sign'>icon-google-plus-sign</option>
                                            <option value='icon-sign-blank'>icon-sign-blank</option>
                                        </select>
                                </td>
                            </tr>
                            <tr>
                                <td class="text-right" width="40%" height="34px">
                                    <span style="padding-right:25px;">URL:</span>
                                </td>
                                <td>
                                    <span><input  id="url" type="text" name="url" /></span>
                                </td>
                            </tr>
                            <tr>
                                <td class="text-right" width="40%" height="34px">
                                    <span style="padding-right:25px;">权限名称:</span>
                                </td>
                                <td>
                                    <span><input  id="permissionName" type="text" name="permissionName" /></span>
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
<script src="/office/asset/js/sys/menu.js"></script>
</@html>