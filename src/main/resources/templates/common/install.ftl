<!DOCTYPE html>
<html lang="zh">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
        <meta name="renderer" content="webkit">
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <title>jump-<@spring.message code='install.page.title'/></title>
        <link rel="stylesheet" href="/static/jump-backend/plugins/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="/static/jump-backend/css/AdminLTE.min.css">
        <link rel="stylesheet" href="/static/jump-backend/plugins/animate/animate.min.css">
        <link rel="stylesheet" href="/static/jump-backend/plugins/bootstrapvalidator/css/bootstrapValidator.min.css">
        <!--[if lt IE 9]>
        <script src="//oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="//oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->




        <style>
            body{
                background-color: #f5f5f5;
            }
            .container{
                max-width: 850px;
            }
            .form-horizontal .control-label{
                text-align: left;
            }
            .logo{font-size:56px;text-align:center;margin-bottom:25px;font-weight:500;color:#444;text-shadow:#b2baba .1em .1em .2em;}
        </style>





    </head>
    <body>

        <div class="container">
            <div class="row" style="padding-top: 50px">
                <div class="col-lg-12 col-xs-12">
                    <div class="logo animated fadeInUp">
                        Jump's Blog<small style="font-size: 14px;"><@spring.message code='install.page.title'/></small>
                    </div>
                    <#--这里判读是否已经安装过此软件，即是否已经安装-->
                    <#if isInstall==false>
                        <!-- 判断RequestParameters是否为空-->
                    <#if RequestParameters['lang']??>
                        <#assign lang ="${RequestParameters['lang']}">
                    </#if>
                        <#--执行安装 /install/do-->
                    <form method="post" action="/install/do" class="form-horizontal" id="installForm">
                        <div class="box box-solid animated" id="installFirst">
                            <div class="box-body" style="padding: 30px;">

                                <div class="form-group animated fadeInUp" style="animation-delay: 0.1s">

                                    <label for="blogLocale" class="col-sm-4 control-label"><@spring.message code='install.form.language'/></label>

                                    <div class="col-sm-8">
                                        <select class="form-control" id="blogLocale" name="blogLocale">
                                            <option value="zh_CN" <#if lang?default('zh_CN') = 'zh_CN'>selected</#if>>简体中文</option>
                                            <option value="en_US" <#if lang?default('zh_CN') = 'en_US'>selected</#if>>English</option>
                                        </select>
                                    </div>

                                </div>

                                <div class="form-group animated fadeInUp" style="animation-delay: 0.2s">
                                    <label for="blogTitle" class="col-sm-4 control-label"><@spring.message code='install.form.blogTitle'/></label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" id="blogTitle" name="blogTitle">
                                    </div>
                                </div>
                                <div class="form-group animated fadeInUp" style="animation-delay: 0.3s">
                                    <label for="blogUrl" class="col-sm-4 control-label"><@spring.message code='install.form.blogUrl'/></label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" id="blogUrl" name="blogUrl">
                                    </div>
                                </div>
                                <div class="form-group animated fadeInUp" style="animation-delay: 0.4s">
                                    <label for="userEmail" class="col-sm-4 control-label"><@spring.message code='install.form.userEmail'/></label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" id="userEmail" name="userEmail">
                                    </div>
                                </div>
                                <div class="form-group animated fadeInUp" style="animation-delay: 0.5s">
                                    <label for="userName" class="col-sm-4 control-label"><@spring.message code='install.form.userName'/></label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" id="userName" name="userName">
                                    </div>
                                </div>
                                <div class="form-group animated fadeInUp" style="animation-delay: 0.6s">
                                    <label for="userDisplayName" class="col-sm-4 control-label"><@spring.message code='install.form.userDisplayName'/></label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" id="userDisplayName" name="userDisplayName">
                                    </div>
                                </div>
                                <div class="form-group animated fadeInUp" style="animation-delay: 0.7s">
                                    <label for="userPwd" class="col-sm-4 control-label"><@spring.message code='install.form.userPwd'/></label>
                                    <div class="col-sm-8">
                                        <input type="password" class="form-control" id="userPwd" name="userPwd">
                                    </div>
                                </div>
                                <div class="form-group animated fadeInUp" style="animation-delay: 0.8s">
                                    <label for="userRePwd" class="col-sm-4 control-label"><@spring.message code='install.form.userRePwd'/></label>
                                    <div class="col-sm-8">
                                        <input type="password" class="form-control" id="userRePwd" name="userRePwd">
                                    </div>
                                </div>
                            </div>
                            <div class="box-footer" style="padding-right: 30px;">
                                <button type="submit" class="btn btn-primary btn-sm btn-flat pull-right animated fadeInUp" style="animation-delay: 1s"><@spring.message code='install.btn.submit'/></button>
                            </div>




                        </div>
                        <div class="box box-solid animated fadeInUp" style="display: none" id="installSuccess">
                            <div class="box-body">
                                <h2><@spring.message code='install.success.title'/></h2>
                                <h4><@spring.message code='install.success.message'/></h4>
                            </div>
                            <div class="box-footer" style="padding-right: 30px;">
                                <a class="btn btn-primary btn-sm btn-flat" href="/"><@spring.message code='install.success.btn.front'/></a>
                                <a class="btn btn-primary btn-sm btn-flat" href="/admin/login"><@spring.message code='install.success.btn.dashboard'/></a>
                            </div>
                        </div>
                        <div class="box box-solid animated fadeInUp" style="display: none" id="installError">
                            <div class="box-body">
                                <h2><@spring.message code='install.error.title'/></h2>
                                <h4><@spring.message code='install.error.message'/></h4>
                            </div>
                            <div class="box-footer" style="padding-right: 30px;">
                                <a class="btn btn-primary btn-sm btn-flat" href="/install"><@spring.message code='install.error.btn.return'/></a>
                            </div>
                        </div>
                    </form>
                        <#--已经安装过了-->
                    <#else >
                    <div class="animated fadeInUp" style="animation-delay: 0.1s">
                        <h4><@spring.message code='install.installed.message'/></h4>
                    </div>
                    </#if>
                </div>
            </div>
        </div>




    </body>
    <script src="/static/jump-common/jquery/jquery.min.js"></script>
    <script src="/static/jump-backend/plugins/bootstrap/js/bootstrap.min.js"></script>
    <script src="/static/jump-backend/plugins/bootstrapvalidator/js/bootstrapValidator.min.js"></script>
    <script src="/static/jump-backend/plugins/bootstrapvalidator/js/language/zh_CN.js"></script>
    <#--如果未安装才执行此段-->
    <#if isInstall==false>
    <script>
        /*host 属性是一个可读可写的字符串，可设置或返回当前 URL 的主机名称和端口号。 即服务器的公网ip和程序端口号*/
        var domain = window.location.host;
        $(function () {
            /*提示工具*/
            $('[data-toggle="tooltip"]').tooltip()
        });
        /* 当 DOM（document object model 文档对象模型）加载完毕且页面完全加载（包括图像）时发生 ready 事件。*/
        $(document).ready(function () {
            /* 返回 bologUrl的值为"http://"+domain */
            $('#blogUrl').val("http://"+domain);
            $('#installForm')
                .bootstrapValidator({
                    message: '安装表单验证失败',
                    feedbackIcons: {
                        valid: 'glyphicon glyphicon-ok',
                        invalid: 'glyphicon glyphicon-remove',
                        validating: 'glyphicon glyphicon-refresh'
                    },
                    fields: {
                        blogTitle: {
                            message: '网站标题验证失败',
                            validators: {
                                notEmpty: {
                                    message: '网站标题不能为空'
                                }
                            }
                        },
                        userEmail: {
                            message: '邮箱验证失败',
                            validators: {
                                notEmpty: {
                                    message: '邮箱不能为空'
                                },
                                emailAddress: {
                                    message: '邮箱格式有误'
                                }
                            }
                        },
                        userName: {
                            message: '用户名验证失败',
                            validators: {
                                notEmpty: {
                                    message: '用户名不能为空'
                                },
                                stringLength: {
                                    min: 1,
                                    max: 24,
                                    message: '用户名超出长度限制'
                                }
                            }
                        },
                        userPwd: {
                            message: '密码验证失败',
                            validators: {
                                notEmpty: {
                                    message: '密码不能为空'
                                },
                                stringLength: {
                                    min: 6,
                                    max: 18,
                                    message: '密码长度必须在6到18位之间'
                                }
                            }
                        },
                        userRePwd: {
                            message: '密码验证失败',
                            validators: {
                                notEmpty: {
                                    message: '确认密码不能为空'
                                },
                                identical: {
                                    field: 'userPwd',
                                    message: '两次输入的密码不相符'
                                }
                            }
                        }
                    }
                })
                /*当表单验证成功时会执行这个方法*/
                    .on('success.form.bv',function (e) {
                        /*固定搭配*/
                    e.preventDefault();
                    /*它返回哪个dom元素触发了事件 这里具体指的是表单*/
                    var $form = $(e.target);
                    var bv = $form.data('bootstrapValidator');
                    /* $form.attr('action') 指发送到后台的请求地址 $form.serialize() 指发送到后台的表单的数据 并且要序列化*/
                    $.post($form.attr('action'),$form.serialize(),function (data) {
                        if(data.code === 1){ /*则为提交成功 即注册安装成功 如果安装成功我要做什么*/
                            /*隐藏整个表单域*/
                            $('#installFirst').hide();
                            $('#installSuccess').show();
                        }else{
                            $('#installFirst').hide();
                            $('#installError').show();
                        }
                    },'JSON');
                });
        });
            /* on 是根据里面的事件元素来执行的 比如这里的change change是只要bloglocale标签值改变了就执行方法*/
        $('#blogLocale').on('change',function(){
            /*返回lang的值 即获取选择的值*/
            var lang = $(this).val();
            /*刷新页面到指定的url .href是获取当前页的完整地址*/
            window.location.href="/install?lang="+lang;
        });
    </script>
    <#else>
    <noscript>Not have Script!</noscript>
    </#if>
</html>
