<?php
use yii\helpers\Html;
use yii\helpers\Url;

/* @var $this yii\web\View */
$this->title = 'About';
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="site-about">
    <h1><?= Html::encode($this->title) ?></h1>

    <p>
        Cached locale
    </p>
    <? /*Yii::$app->Lang->flushCache();*/ ?>
    <code><?= Yii::$app->Lang->getText("test") ?></code>
    <code><?= Url::toRoute(['/site/about', 'id' => 105]); ?></code>
    
</div>
