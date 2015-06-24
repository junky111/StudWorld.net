<?php
/* @var $this yii\web\View */
/* @var $table app\models\Table */
use \yii\helpers\Url;
$this->title = 'StudWorld - Главная';
$cells = json_encode($table->asArray());
$this->context->scriptLines .=  "
    var StudWorld;
    jQuery(document).ready(function ($) {
        $('#slider').rhinoslider({
            effect: 'fade',
            effectTime: 500,
            easing: 'linear',
            controlsMousewheel: false,
            controlsKeyboard: false,
            controlsPrevNext: false,
            controlsPlayPause: false,
            animateActive: false,
            showBullets: 'never',
            showControls: 'never'
        });
        var cells = {$cells};
        StudWorld = new System();
        StudWorld.main = new MainComponent(cells);
        StudWorld.main.forceCellURL(cells[0][0].url);
    });
    ";
    $currentcell = $table->cells[0][0];
?>
<div class="interface-right">
    <a href="<?=Url::toRoute("/browse")?>" class="browse"><i class="fa fa-th-large"></i></a>
    <a href="<?=Url::toRoute("/user")?>" class="social"><i class="fa fa-user"></i></a>
</div>
<div id="main-container">
    <?/*<div class="caption">
        <div class="links">
            <?foreach ($currentcell->links as $link) { ?>
            <a href="<?= $link->url?>" <?if($link->type===0) { ?> OnClick="StudWorld.main.forceSlide('<?= $link->url?>');"<? } ?>>
            <?=$link->name?>
            </a>
            <? } ?>
        </div>
    </div>
    <div id="slider">
        <? $i = 0; ?>
        <?foreach ($currentcell->links as $link) { ?>
        <?if($link->type!==0) {continue;} ?>
        <li style="background-image: url(<?=$link->image?>); background-size: cover;">
            <p><?= $currentcell->links[0]->text ?></p>
        </li>
        <? } ?>
    </div>*/?>
</div>