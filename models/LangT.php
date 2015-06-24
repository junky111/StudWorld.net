<?php
namespace app\models;
use \yii\db\ActiveRecord;
class LangT extends ActiveRecord {

    public static function tableName()
    {
        return 'langs';
    }
}
