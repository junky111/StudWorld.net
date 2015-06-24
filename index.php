<?php
ini_set("display_errors", 1);
ini_set("track_errors", 1);
ini_set("html_errors", 1);
error_reporting(E_ALL);
// comment out the following two lines when deployed to production
defined('YII_DEBUG') or define('YII_DEBUG', true);
defined('YII_ENV') or define('YII_ENV', 'dev');

require(__DIR__ . '/../studworld-back/vendor/autoload.php');
require(__DIR__ . '/../studworld-back/vendor/yiisoft/yii2/Yii.php');

$config = require(__DIR__ . '/../studworld-back/config/web.php');

(new yii\web\Application($config))->run();
