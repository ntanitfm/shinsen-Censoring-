package com.mygdx.game.title;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.item.Config;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.rotateBy;
import static com.mygdx.game.item.Config.*;
/**
 * TitleScreenの部品生成、状態管理などの
 * 裏方の仕事を行う。
 * これにより、TitleScreen側からは
 * 部品の取得、登録、描画、遷移といった抽象的な命令で
 * コーディング出来る。
 *
 * Created by ntani on 2017/11/01.
 */

class TitleEnvironment {
    private String TAG = TitleEnvironment.class.getSimpleName();
    String GAMEMODE;

    TitleEnvironment() {
        Gdx.app.log(TAG, "Construct in titleEnv");
        GAMEMODE = Config.NO_SLCT;
    }

    // タイトル回転牌
    Image getTitlePai() {
        Image ttlPai = new Image(new Texture("icon/normal/p1.png"));
        ttlPai.setSize(TTL_PAI_WIDTH, TTL_PAI_HEIGHT);
        ttlPai.setPosition(SCRN_WIDTH_CTR - TTL_PAI_WIDTH_CTR, SCRN_HEIGHT_CTR - TTL_PAI_HEIGHT_CTR);
        ttlPai.setOrigin(TTL_PAI_WIDTH_CTR, TTL_PAI_HEIGHT_CTR);
        ttlPai.addAction(Actions.forever(rotateBy(TTL_PAI_ROTATE_SPD)));
        return ttlPai;
    }

    // テキストボタン
    TextButton getTitleTextButton(String label, float y_from_center) {
        TextButton txtBtn = new TextButton(label, skin);
        txtBtn.setSize(TXTBTN_WIDTH_L, TXTBTN_HEIGHT);
        txtBtn.setPosition(SCRN_WIDTH_CTR - TXTBTN_WIDTH_L_CTR, SCRN_HEIGHT_CTR - y_from_center);
        setBtnListener(txtBtn);
        return txtBtn;
    }

    // licence表示ボタン
    ImageButton getInfoButton() {
        float width = 30f;
        float height = 30f;
        Texture texture = new Texture(Gdx.files.internal("icon/button/info.png"));
        TextureRegion txRegion = new TextureRegion(texture);
        TextureRegionDrawable txrDrawable = new TextureRegionDrawable(txRegion);
        ImageButton imgBtn = new ImageButton(txrDrawable);
        imgBtn.setBounds(10, Config.SCRN_HEIGHT - height - 10, width, height);
        setBtnListener(imgBtn);
        return imgBtn;
    }

    // リスナー設定
    private void setBtnListener(final TextButton txtBtn) {
        txtBtn.addListener(new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                GAMEMODE = txtBtn.getText().toString();
                Gdx.app.log(TAG, "GAMEMODE = " + GAMEMODE);
                return true;
            }
        });
    }
    private void setBtnListener(final ImageButton txtBtn) {
        txtBtn.addListener(new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                GAMEMODE = Config.LICE;
                Gdx.app.log(TAG, "GAMEMODE = " + GAMEMODE);
                return true;
            }
        });
    }

}
