package com.tankstars.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tankstars.game.tankstars;

import java.io.Serializable;

public class SavedGame implements Screen, Serializable {
    private Texture loadback;
    private tankstars gg;

    protected SavedGame(tankstars gg) {
        this.gg = gg;
        loadback = new Texture("loadback.png");
    }

    protected void handleInput() {
        if(Gdx.input.justTouched() && Gdx.input.getX()>=59 && Gdx.input.getX()<=217 && Gdx.input.getY()>=57 && Gdx.input.getY()<=114){
            gg.setScreen(new MenuScreen(this.gg));
        }
    }

    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(float delta) {
        update(delta);
        gg.batch.begin();
        gg.batch.draw(loadback,0,0, 1200, 600);
        gg.batch.end();

    }

    @Override
    public void show() {

    }


    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        loadback.dispose();
    }
}
