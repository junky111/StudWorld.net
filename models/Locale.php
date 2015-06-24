<?php

namespace app\models;
use Yii;
use \yii\db\ActiveRecord;
class Locale extends ActiveRecord {
    public static function tableName()
    {
        return 'local_'.Yii::$app->Lang->current;
    }
}
