package com.tankstars.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tankstars.game.tankstars;

import java.io.Serializable;

public class MenuScreen implements Screen, Serializable {
    private Texture playscreen;
    private Texture newGame;
    private Texture loadGame;
    private Texture exitGame;
    private tankstars gg;
    protected MenuScreen(tankstars gg) {
        this.gg = gg;
        playscreen = new Texture("PlayScreen.png");
        newGame = new Texture("newGame.png");
        loadGame= new Texture("loadGame.png");
        exitGame = new Texture("Exit.png");
    }

    protected void handleInput() {
        if(Gdx.input.justTouched()){
            if(Gdx.input.isTouched() && Gdx.input.getX()>=35 && Gdx.input.getX()<=280 && Gdx.input.getY()>=360 && Gdx.input.getY()<=420){
                gg.setScreen(new ChooseMode(this.gg));
            }
            if(Gdx.input.isTouched() && Gdx.input.getX()>=35 && Gdx.input.getX()<=280 && Gdx.input.getY()>=430 && Gdx.input.getY()<=480){
                gg.setScreen(new SavedGame(this.gg));
            }
            if(Gdx.input.isTouched() && Gdx.input.getX()>=35 && Gdx.input.getX()<=280 && Gdx.input.getY()>=500 && Gdx.input.getY()<=550){
                gg.setScreen(new ExitScreen(this.gg));
            }
        }
    }

    public void update(float dt) {
        handleInput();
//        System.out.println(Gdx.input.getX()+" "+ Gdx.input.getY());
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);

        gg.batch.begin();
        gg.batch.draw(playscreen,0,0, 1200, 600);
        gg.batch.draw(newGame,35,168,245,80);
        gg.batch.draw(loadGame,35,97,245,80);
        gg.batch.draw(exitGame,35,31,247,80);
        gg.batch.end();
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
        playscreen.dispose();
        newGame.dispose();
        loadGame.dispose();
        exitGame.dispose();
    }
}
