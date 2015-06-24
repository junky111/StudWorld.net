<?php

namespace app\models;
use \yii\db\ActiveRecord;
class Cell extends ActiveRecord {

    public function getlinks()
    {
        return $this->hasMany(Link::className(), ['cellid' => 'id'])
            ->orderBy('sorting');
    }
    public static function tableName()
    {
        return 'cells';
    }
}
