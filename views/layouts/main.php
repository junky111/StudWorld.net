<?php
use yii\helpers\Html;

        use app\components\SocialMenu;
/* @var $this \yii\web\View */
/* @var $content string */
$this->beginPage() ?>
<html lang="<?= Yii::$app->language ?>">
<head>
    <meta charset="<?= Yii::$app->charset ?>"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="google-site-verification" content="ZVwcGoy_XA71yzNfc_cz6zB6hChG-lfXu-RnX3QZzak" />
    <?= Html::csrfMetaTags() ?>
    <title><?= Html::encode($this->title) ?></title>
    <?php $this->head() ?>
    <? foreach($this->context->scripts as $script) { ?>
    <script src="<?= $script ?>"></script>
    <? } ?>
    <? foreach($this->context->styles as $style) { ?>
    <link rel="stylesheet" type="text/css" href="<?= $style ?>">
    <? } ?>
    <script><?= $this->context->scriptLines ?></script>
<script>$(function() {$('body').perfectScrollbar();});</script>
</head>
<body>

<?php $this->beginBody() ?>
    <? /*<div class="wrap">

        <div class="container">
            <?= Breadcrumbs::widget([
                'links' => isset($this->params['breadcrumbs']) ? $this->params['breadcrumbs'] : [],
            ]) ?>
            
        </div>
    </div>

    <footer class="footer">
        <div class="container">
            <p class="pull-left">&copy; My Company <?= date('Y') ?></p>
            <p class="pull-right"><?= Yii::powered() ?></p>
        </div>
    </footer>*/ ?>
<?
switch($this->context->leftMenu) {
    case 'social':
        echo HelloWidget2::widget();
        break;
}
?>
<div id="main-container"><?= $content ?></div>

<?php $this->endBody() ?>
</body>
</html>
<?php $this->endPage() ?>
