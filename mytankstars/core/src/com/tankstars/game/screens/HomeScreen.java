package com.tankstars.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tankstars.game.tankstars;

import java.io.Serializable;

public class HomeScreen implements Screen, Serializable {
    private Texture background;
    private tankstars gg;

    public HomeScreen(tankstars gg) {
        this.gg = gg;
        background = new Texture("HomeScreen.png");
    }


    public void handleInput() {
        if(Gdx.input.justTouched()){
            gg.setScreen(new PlayScreen(this.gg));
        }
    }


    public void update(float dt) {
        handleInput();
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        update(delta);
        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gg.batch.begin();
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
        background.dispose();
    }
}
