<?php
namespace app\models;
use app\models\Cell;
use \yii\helpers\Url;
use Yii;
class Table {
    public $cells = array();
    public function __construct() {
        
        //Yii::$app->db->beginCache(60);
        $cells = Cell::find()->with('links')->orderBy("sorting desc")->all();
        foreach ($cells as $cell) {
            $cell->name = Yii::$app->Lang->getText($cell->name);
            $cell->url = Url::toRoute($cell->url);
        }
        for($i = 0; $i < 3; $i++) {
            $row = array();
            for($q = 3*$i; $q < 3*$i+3; $q++) {
                $cell = count($cells)>0 ? array_pop($cells) : null;
                if($cell!=null) {
                    foreach ($cell->links as $link) {
                        $link->name = Yii::$app->Lang->getText($link->name);
                        $link->text = Yii::$app->Lang->getText($link->text);
                        $link->url = Url::toRoute($link->url);
                    }
                }
                $row[] = $cell;
            }
            $this->cells[] = $row;
        }
        //Yii::$app->db->endCache();
    }
    public function asArray() {
        $a = array();
        foreach ($this->cells as $row) {
            if($row==null) {continue;}
            $r = array();
            foreach ($row as $cells) {
                if($cells==null) {continue;}
                $cell = $cells->getAttributes(array('url','name','image'));
                $cell['links'] = array();
                foreach ($cells->links as $link) {
                if($link==null) {continue;}
                    $l = array();
                    $l['url'] = $link->url;
                    $l['image'] = $link->image;
                    $l['name'] = $link->name;
                    $l['type'] = $link->type;
                    $l['text'] = $link->text;
                    $cell['links'][] = $l;
                }
                $r[] = $cell;
            }
            $a[] = $r;
        }
        return $a;
    }
}
