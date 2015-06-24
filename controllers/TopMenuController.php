<?php
namespace app\controllers;

class TopMenuController extends SController {
    public function actionIndex()
    {
        return $this->renderPartial('index');
    }
}
