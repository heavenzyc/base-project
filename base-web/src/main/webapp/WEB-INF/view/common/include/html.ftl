<#macro html>
<!DOCTYPE html>
<html lang="en">
    <#nested/>
</html>
</#macro>

<#macro html nosidebar=false title="平台" js=[] css=[]>
<head>
    <meta charset="utf-8" />
    <title>后台管理</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <!-- basic styles -->
    <link href="/assets/css/bootstrap.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="/assets/css/font-awesome.min.css" />

    <!--[if IE 7]>
    <link rel="stylesheet" href="/assets/css/font-awesome-ie7.min.css" />
    <![endif]-->
    <!-- ace styles -->
    <link rel="stylesheet" href="/assets/css/jquery-ui-1.10.3.full.min.css" />
    <link rel="stylesheet" href="/assets/css/ui.jqgrid.css"/>
    <link rel="stylesheet" href="/assets/css/chosen.css" />
    <link rel="stylesheet" href="/assets/css/datepicker.css" />
    <link rel="stylesheet" href="/assets/css/bootstrap-timepicker.css" />
    <link rel="stylesheet" href="/assets/css/daterangepicker.css" />
    <link rel="stylesheet" href="/assets/css/colorpicker.css" />



    <link rel="stylesheet" href="/assets/css/ace.min.css" />
    <link rel="stylesheet" href="/assets/css/ace-rtl.min.css" />
    <link rel="stylesheet" href="/assets/css/ace-skins.min.css" />
    <!--[if lte IE 8]>
    <link rel="stylesheet" href="/assets/css/ace-ie.min.css" />
    <![endif]-->

    <#--<script src="/assets/js/jquery-2.0.3.min.js"></script>-->
    <script src="/assets/js/ace-extra.min.js"></script>
    <!--[if lt IE 9]>
    <script src="/assets/js/html5shiv.js"></script>
    <script src="/assets/js/respond.min.js"></script>


    <![endif]-->

    <!--[if !IE]> -->

    <script type="text/javascript">
    window.jQuery || document.write("<script src='/assets/js/jquery-2.0.3.min.js'>"+"<"+"script>");
</script>

    <!-- <![endif]-->

    <!--[if IE]>
    <script type="text/javascript">
    window.jQuery || document.write("<script src='/assets/js/jquery-1.10.2.min.js'>"+"<"+"script>");
</script>
    <![endif]-->

    <script type="text/javascript">
    if("ontouchend" in document) document.write("<script src='/assets/js/jquery.mobile.custom.min.js'>"+"<"+"script>");
</script>
    <script src="/assets/js/bootstrap.min.js"></script>
    <script src="/assets/js/typeahead-bs2.min.js"></script>
<#--############################################################-->
    <script src="/assets/js/jquery-ui-1.10.3.custom.min.js"></script>
    <script src="/assets/js/jquery.ui.touch-punch.min.js"></script>
    <script src="/assets/js/chosen.jquery.min.js"></script>
    <script src="/assets/js/fuelux/fuelux.spinner.min.js"></script>
    <script src="/assets/js/date-time/bootstrap-datepicker.min.js"></script>
    <script src="/assets/js/date-time/bootstrap-timepicker.min.js"></script>
    <script src="/assets/js/date-time/moment.min.js"></script>
    <script src="/assets/js/date-time/daterangepicker.min.js"></script>
    <script src="/assets/js/bootstrap-colorpicker.min.js"></script>
    <script src="/assets/js/jquery.knob.min.js"></script>
    <script src="/assets/js/jquery.autosize.min.js"></script>
    <script src="/assets/js/jquery.inputlimiter.1.3.1.min.js"></script>
    <script src="/assets/js/jquery.maskedinput.min.js"></script>
    <script src="/assets/js/bootstrap-tag.min.js"></script>
<#--############################################################-->
    <!--[if lte IE 8]>
    <script src="/assets/js/excanvas.min.js"></script>
    <![endif]-->
    <script src="/assets/js/jqGrid/jquery.jqGrid.min.js"></script>
    <script src="/assets/js/jqGrid/i18n/grid.locale-en.js"></script>

    <script src="/assets/js/ace-elements.min.js"></script>
    <script src="/assets/js/ace.min.js"></script>


</head>
</#macro>

    <#macro body>

    <body>
    <div class="navbar navbar-default" id="navbar">
        <script type="text/javascript">
				try{ace.settings.check('navbar' , 'fixed')}catch(e){}
			</script>

        <div class="navbar-container" id="navbar-container">
            <div class="navbar-header pull-left">
                <a href="#" class="navbar-brand">
                    <small>
                        <i class="icon-leaf"></i>
                        XXOO管理系统
                    </small>
                </a><!-- /.brand -->
            </div><!-- /.navbar-header -->
            <div class="navbar-menu pull-left" id="menu1">
                <#list menuList1 as menu1>
                    <a href="${menu1.index_url}" <#if currentmenu1.id=menu1.id>class="current"</#if>>${menu1.name}</a>
                </#list>
            </div>
            <div class="navbar-header pull-right" role="navigation">
                <ul class="nav ace-nav">
                    <li class="light-blue">
                        <a data-toggle="dropdown" href="#" class="dropdown-toggle">
                            <img class="nav-user-photo" src="${static_path}/backstage/assets/avatars/user.jpg" alt="" />
								<span class="user-info">
									<small>${current_user.name!?html}</small>
									<span id="currentAreaName">${(current_area.name)!?html}</span>
								</span>

                            <i class="icon-caret-down"></i>
                        </a>

                        <ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
                            <li>
                                <a href="javascript:changepwd();">
                                    <i class="icon-cog"></i>
                                    修改密码
                                </a>
                            </li>

                            <li class="divider"></li>
                            <#list g_areaList as area>
                                <li>
                                    <a href="javascript:;" class="btnAreaSelect" __area_id="${area.id}">
                                        <i class="icon-user"></i>
                                    ${area.name!?html}
                                    </a>
                                </li>
                            </#list>
                            <li class="divider"></li>

                            <li>
                                <a href="/logout">
                                    <i class="icon-off"></i>
                                    退出
                                </a>
                            </li>
                        </ul>
                    </li>
                </ul><!-- /.ace-nav -->
            </div><!-- /.navbar-header -->
        </div><!-- /.container -->
    </div>

    <div class="main-container" id="main-container">
        <script type="text/javascript">
            try{ace.settings.check('main-container' , 'fixed')}catch(e){}
        </script>

        <div class="main-container-inner" >
            <a class="menu-toggler" id="menu-toggler" href="#">
                <span class="menu-text"></span>
            </a>

            <#if nosidebar>
            <#else>
                <div class="sidebar" id="sidebar">
                    <script type="text/javascript">
						try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
					</script>

                    <div class="sidebar-shortcuts" id="sidebar-shortcuts">
                        <!--
                        <div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
                            <button class="btn btn-success">
                                <i class="icon-signal"></i>
                            </button>

                            <button class="btn btn-info">
                                <i class="icon-pencil"></i>
                            </button>

                            <button class="btn btn-warning">
                                <i class="icon-group"></i>
                            </button>

                            <button class="btn btn-danger">
                                <i class="icon-cogs"></i>
                            </button>
                        </div>-->

                        <div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
                            <span class="btn btn-success"></span>

                            <span class="btn btn-info"></span>

                            <span class="btn btn-warning"></span>

                            <span class="btn btn-danger"></span>
                        </div>
                    </div><!-- #sidebar-shortcuts -->

                    <ul class="nav nav-list">
                        <#list menuList2 as menu2>
                            <li <#if currentmenu2.id=menu2.id>class="active"</#if>>
                                <#if menu2.menuList3?has_content>
                                    <a href="#" class="dropdown-toggle">
                                        <i class="${menu2.icon_class}"></i>
                                        <span class="menu-text">${menu2.name}</span>
                                        <b class="arrow icon-angle-down"></b>
                                    </a>
                                    <ul class="submenu">
                                        <#list menu2.menuList3 as menu3>
                                            <li>
                                                <a href="${menu3.index_url}">
                                                    <i class="icon-double-angle-right"></i>
                                                ${menu3.name}
                                                </a>
                                            </li>
                                        </#list>
                                    </ul>
                                <#else>
                                    <a href="${menu2.index_url}">
                                        <i class="${menu2.icon_class}"></i>
                                        <span class="menu-text">${menu2.name}</span>
                                    </a>
                                </#if>
                            </li>
                        </#list>
                    </ul><!-- /.nav-list -->

                    <div class="sidebar-collapse" id="sidebar-collapse">
                        <i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
                    </div>

                    <script type="text/javascript">
						try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}
					</script>
                </div>
            </#if>

            <div class="main-content <#if nosidebar>no-margin</#if>">
                <#nested>
            </div><!-- /.main-content -->

        </div><!-- /.main-container-inner -->

        <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
            <i class="icon-double-angle-up icon-only bigger-110"></i>
        </a>
    </div><!-- /.main-container -->
    </body>
</html>
</#macro>
