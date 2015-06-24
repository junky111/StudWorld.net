<?php
namespace app\controllers;

use Yii;

class SocialController extends SController {
    public function beforeAction($event)
    {
        $this->scripts[] = "/js/social.js";
        return parent::beforeAction($event);
    }
    public function actionIndex()
    {
        if (Yii::$app->request->isAjax) {
            return $this->renderPartial('auth');
        } else {
            return $this->render('auth');
        }
    }
    public function actionUser()
    {
        if (Yii::$app->request->isAjax) {
            return $this->renderPartial('user');
        } else {
            return $this->render('user');
        }
    }
    public function actionPms()
    {
        if (Yii::$app->request->isAjax) {
            return $this->renderPartial('pms');
        } else {
            return $this->render('pms');
        }
    }
    public function actionLeftmenu()
    {
        return $this->renderPartial('leftmenu');
    }
    public function actionSubscribers()
    {
        return $this->renderPartial('subscribers');
    }
    public function actionPosts()
    {
        return $this->renderPartial('posts');
    }
    public function actionPhotos()
    {
        return $this->renderPartial('photos');
    }
}
