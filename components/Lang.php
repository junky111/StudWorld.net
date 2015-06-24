<?php

namespace app\components;

use Yii;
use app\models\Locale;

class Lang extends \yii\base\Component{
    public $current, $default = "ru";
    private $cache = null;
    public function init() {
        $this->cache = Yii::$app->cache;
        if (!isset(Yii::$app->request->cookies['lang'])) {
            Yii::$app->response->cookies->add(new \yii\web\Cookie([
                'name' => 'lang',
                'value' => $this->default
            ]));
            $this->current = $this->default;
        } else {
            $this->current = Yii::$app->request->cookies['lang'];
        }
        parent::init();
    }
    public function getText($id) {
        $data = $this->cache->get($this->current."-".$id);
        if($data===false) {
            $loc = Locale::findOne($id);
            if($loc!=null) {
                $this->cache->set($this->current."-".$id, $loc->value, 86400);
                $data = $loc->value;
            } else {
                $data = "[".$this->current."-".$id."]";
            }
        }
        return $data;
    }
    public function setLang($lang) {
        $this->current = $lang;
        Yii::$app->response->cookies->add(new \yii\web\Cookie([
            'name' => 'lang',
            'value' => $this->current
        ]));
        
    }
    public function flushCache() {
        foreach(Locale::find()->all() as $loc) {
            $this->cache->delete($this->current."-".$loc->name);
        }
    }
}
