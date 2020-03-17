<button id="article-fuctions-share-button" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--icon">
    <i class="material-icons" role="presentation">share</i>
    <span class="visuallyhidden">share</span>
</button>
<ul class="mdl-menu mdl-menu--bottom-right mdl-js-menu mdl-js-ripple-effect" for="article-fuctions-share-button">
    <!-- Share Weibo -->
    <#if (options.theme_material_sns_share_weibo!'true')=='true'>
    <a class="post_share-link" href="http://service.weibo.com/share/share.php?appkey=&title=${post.postTitle}&url=${options.blog_url}/archives/${post.postUrl}&pic=&searchPic=false&style=simple" target="_blank">
        <li class="mdl-menu__item">
            分享到Weibo
        </li>
    </a>
    </#if>
    <!-- Share QQ -->
    <#if (options.theme_material_sns_share_qq!'true')=='true'>
    <a class="post_share-link" href="http://connect.qq.com/widget/shareqq/index.html?site=${options.blog_title!}&title=${post.postTitle}&summary=${post.postSummary!}&pics=&url=${options.blog_url}/archives/${post.postUrl}" target="_blank">
        <li class="mdl-menu__item">
            分享到QQ
        </li>
    </a>
    </#if>

</ul>
