package com.tankstars.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tankstars.game.tankstars;

import java.io.Serializable;


public class Tank2 implements Screen, Serializable {
    private Texture backs;
    private Texture tank1;
    private Texture tank2;
    private Texture tank3;
    private Texture left;
    private Texture right;
    private Texture select;
    private Texture back;
    private tankstars gg;

    protected Tank2(tankstars gg) {
        this.gg = gg;
        tank1 = new Texture("Tank1.png");
        tank2 = new Texture("Tank2.png");
        tank3 = new Texture("Tank3.png");
        left = new Texture("left.png");
        right = new Texture("right.png");
        select = new Texture("select.png");
        backs = new Texture("backs.png");
        back = new Texture("Back.png");
    }

    protected void handleInput() {
        if(Gdx.input.justTouched()){
            if(Gdx.input.isTouched() && Gdx.input.getX()>=350 && Gdx.input.getX()<=400 && Gdx.input.getY()>=500 && Gdx.input.getY()<=560){
                gg.setScreen(new Tank1(this.gg, 1, 1, 1));
            }
            if(Gdx.input.isTouched() && Gdx.input.getX()>=440 && Gdx.input.getX()<=620 && Gdx.input.getY()>=500 && Gdx.input.getY()<=560){
                gg.setScreen(new PlayScreen(this.gg, 1, 1));
            }
            if(Gdx.input.isTouched() && Gdx.input.getX()>=56 && Gdx.input.getX()<=220 && Gdx.input.getY()>=150 && Gdx.input.getY()<=200){
                gg.setScreen(new ChooseMode(this.gg));
            }
        }

    }

    public void update(float dt) {
        handleInput();
        //System.out.println(Gdx.input.getX()+" "+ Gdx.input.getY());

    }

    @Override
    public void render(float delta) {
        update(delta);
        gg.batch.begin();
        gg.batch.draw(backs,0,-3, 1200,600);
        gg.batch.draw(select,420,30,210,70);
        gg.batch.draw(right,360+310,30,70,70);
        gg.batch.draw(left,360-20,27,80,80);
        gg.batch.draw(tank3,(1200/2)-(tank1.getWidth()/2)-20,(600/2)-(tank1.getHeight()/2),220,157);
        gg.batch.draw(tank1,850,240,200,100);
        gg.batch.draw(back,35,380,200,89);

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
        back.dispose();
    }
}
