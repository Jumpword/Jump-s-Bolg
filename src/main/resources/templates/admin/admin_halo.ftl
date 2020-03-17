<#compress >
<#include "module/_macro.ftl">
<@head><@spring.message code="admin.jump.title" /></@head>
<div class="content-wrapper">
    <style type='text/css'>
        blockquote{
            border-left: 4px solid #dddddd;
            padding: 0 15px;
            color: #777777;
            font-size: 16px;
        }
    </style>
    <section class="content-header" id="animated-header">
        <h1 style="display: inline-block;"><@spring.message code='admin.jump.title' /></h1>
        <ol class="breadcrumb">
            <li>
                <a data-pjax="true" href="/admin"><i class="fa fa-dashboard"></i> <@spring.message code='admin.index.bread.index' /></a>
            </li>
            <li class="active"><@spring.message code='admin.jump.bread.active' /></li>
        </ol>
    </section>
    <section class="content container-fluid" id="animated-content">
        <div id='write' class='is-mac'>
            <blockquote style="font-size: 14px;">
                <p><@spring.message code='admin.jump.content.p1' />😉</p>
                <p><@spring.message code='admin.jump.content.p2' /></p>
            </blockquote>
            <p><@spring.message code='admin.jump.content.p3' /></p>
        </div>
    </section>
</div>
<@footer>
<script type="application/javascript" id="footer_script"></script>
</@footer>
</#compress>
