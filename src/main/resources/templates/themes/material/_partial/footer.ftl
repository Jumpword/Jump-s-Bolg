<!--Footer-->
<footer class="mdl-mini-footer" id="bottom">
    <#if (options.theme_material_scheme!'Paradox') == "Paradox">
    <!-- Paradox Footer Left Section -->
    <#include "footer-left.ftl">

    <!--Copyright-->
    <div id="copyright">
        Copyright&nbsp;©&nbsp;2019&nbsp;-<script type="text/javascript">var fd = new Date();document.write("&nbsp;" + fd.getFullYear() + "&nbsp;");</script>${options.blog_title!}
        <br>
        ${options.blog_footer!}
    </div>
    </#if>
</footer>
