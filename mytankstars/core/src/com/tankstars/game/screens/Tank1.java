package com.tankstars.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tankstars.game.tankstars;

import java.io.Serializable;

public class Tank1 implements Screen, Serializable {
    private Texture backs;
    private Texture tank1;
    private Texture tank2;
    private Texture tank3;
    private Texture left;
    private Texture right;
    private Texture select;
    private Texture back;
    private tankstars gg;
    private Integer playerId;
    private Integer tankId1;
    private Integer tankId2;


    protected Tank1(tankstars gg, Integer playerId, Integer tankId1, Integer tankId2) {
        this.gg = gg;
        tank1 = new Texture("Tank1.png");
        tank2 = new Texture("Tank2.png");
        tank3 = new Texture("Tank3.png");
        left = new Texture("left.png");
        right = new Texture("right.png");
        select = new Texture("select.png");
        backs = new Texture("backs.png");
        back = new Texture("Back.png");
        this.playerId = playerId;
        this.tankId1 = tankId1;
        this.tankId2 = tankId2;
    }


    protected void handleInput() {
        if(Gdx.input.justTouched()){
//            if(Gdx.input.isTouched() && Gdx.input.getX()>=650 && Gdx.input.getX()<=710 && Gdx.input.getY()>=500 && Gdx.input.getY()<=560){
//                gg.setScreen( new Tank2(this.gg));
//            }
//            if(Gdx.input.isTouched() && Gdx.input.getX()>=300 && Gdx.input.getX()<=350 && Gdx.input.getY()>=500 && Gdx.input.getY()<=560){
//                gg.setScreen(new Tank3(this.gg));
//            }
            if(Gdx.input.isTouched() && Gdx.input.getX()>=420 && Gdx.input.getX()<=590 && Gdx.input.getY()>=510 && Gdx.input.getY()<=570){
                if (playerId == 1){
                    gg.setScreen(new Tank1(this.gg, 2, tankId1, tankId2));
                }else {
                    gg.setScreen(new PlayScreen(this.gg, tankId1, tankId2));
                }
            }
            if(Gdx.input.isTouched() && Gdx.input.getX()>=60 && Gdx.input.getX()<=235 && Gdx.input.getY()>=100 && Gdx.input.getY()<=160){
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
        gg.batch.draw(select,360+30,30,210,70);
        gg.batch.draw(right,360+210+40+30,30,70,70);
        gg.batch.draw(left,360-40-60+30,27,80,80);
        gg.batch.draw(tank1,(1200/2)-(tank1.getWidth()/2),(600/2)-(tank1.getHeight()/2),220,157);
        gg.batch.draw(tank2,900,210,200,100);
        gg.batch.draw(tank3,100,210,200,100);
        gg.batch.draw(back,35,420,200,89);

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


