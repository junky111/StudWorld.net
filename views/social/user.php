<div class="user-profile">
    <div class="leftpart">
        <div class="avatar">
            <img src="">
        </div>
        <h1 id="name"></h1>
        <a href="#" id="personal"></a>
        <a href="#" id="pm"></a>
        <div id="subscribers menu"></div>
    </div>
    <div class="rightpart">
        <div class="photos menu"></div>
        <div class="wall menu"></div>
        <div class="writewall menu">
            <div class="textarea">
                <div class="send"></div>
                <h4 class="header"></h4>
                <textarea id="posttext"></textarea>
            </div>
        </div>
    </div>
</div>
<? if(Yii::$app->request->get('id')!="") { ?>
<script>
StudWorld.ready(function() {
    StudWorld.openUser("<?= Yii::$app->request->get('id') ?>");
});
</script>
<? } ?>