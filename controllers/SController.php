<?php


namespace app\controllers;

namespace app\controllers;
use Yii;
use app\components\LangRequest;
use app\models\LangT;
use yii\web\Controller;

class SController extends Controller {
    public $scripts = ['/js/jquery-1.11.2.min.js','/js/translations/ru.js','/js/components.js','/js/easing.js','/js/rhinoslider-1.05.min.js','/js/snabbt.min.js','/js/main.js','/js/studworld.js','/js/globalauth.js','/js/packets.js','/js/perfect-scrollbar.jquery.min.js'];
    public $scriptLines = "";
    public $styles = ['/css/site.css','/css/font-awesome.css','/css/rhinoslider-1.05.css','/css/social.css','/css/perfect-scrollbar.min.css'];
    public $leftMenu = null;
    public function beforeAction($event)
    {
        $l = LangT::findOne(LangRequest::$lang);
        if($l==null) {
            return $this->redirect("/".Yii::$app->Lang->current.LangRequest::$_lang_url);
        } else {
            Yii::$app->Lang->setLang(LangRequest::$lang);
        }
        return parent::beforeAction($event);
    }
}
