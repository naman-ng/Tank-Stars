package com.tankstars.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.tankstars.game.tankstars;

import java.io.Serializable;

public class ChooseMode implements Screen, Serializable {
    private Texture chooseScreen;
    private Texture chooseMode;
    private Texture one;
    private Texture comp;
    private tankstars gg;

    protected ChooseMode(tankstars gg) {
        this.gg = gg;
        chooseScreen = new Texture("ExitScreen.png");
        chooseMode = new Texture("choosemode.png");
        one = new Texture("one.png");
        comp = new Texture("comp.png");

    }

    protected void handleInput() {
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            gg.setScreen(new MenuScreen(this.gg));
        }
        if(Gdx.input.isTouched() && Gdx.input.getX()>=(1200/2)-(chooseMode.getWidth()/2)+40
                && Gdx.input.getX()<=(1200/2)-(chooseMode.getWidth()/2)+40+280 &&
                Gdx.input.getY()>=500-(600/2)+(chooseMode.getHeight()/2)-22-333
                && Gdx.input.getY()<=500-(600/2)+(chooseMode.getHeight()/2)-22){
            gg.setScreen(new Tank1(this.gg, 1, 1, 1));
        }
    }


    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(float delta) {
        update(delta);
        gg.batch.begin();
        gg.batch.draw(chooseScreen,0,0, 1200,600);
        gg.batch.draw(chooseMode,(1200/2)-(chooseMode.getWidth()/2),(600/2)-(chooseMode.getHeight()/2),615,450);
        gg.batch.draw(one,(1200/2)-(chooseMode.getWidth()/2)+40,(600/2)-(chooseMode.getHeight()/2)+22,280,333);
        gg.batch.draw(comp,(1200/2)-(chooseMode.getWidth()/2)+40+260,(600/2)-(chooseMode.getHeight()/2)+10,275,343);
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
        chooseScreen.dispose();
        comp.dispose();
        one.dispose();
    }
}
