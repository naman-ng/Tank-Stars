package com.tankstars.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tankstars.game.tankstars;

import java.io.Serializable;

public class ExitScreen implements Screen, Serializable {

    private Texture backScreen;
    private Texture choice;
    private Texture canceled;
    private Texture okay;
    private tankstars gg;

    protected ExitScreen(tankstars gg) {
        this.gg = gg;
        backScreen= new Texture("ExitScreen.png");
        choice = new Texture("confirm.png");
        canceled = new Texture("cancel.png");
        okay = new Texture("okay.png");
    }

    protected void handleInput() {
        if((Gdx.input.isTouched() && Gdx.input.getX()>=370 && Gdx.input.getX()<=510 && Gdx.input.getY()>=360 && Gdx.input.getY()<=420 )){
            gg.setScreen(new MenuScreen(this.gg));
        }
        else if(Gdx.input.isTouched() && Gdx.input.getX()>=670 && Gdx.input.getX()<=820 && Gdx.input.getY()>=360 && Gdx.input.getY()<=420 ){
            Gdx.app.exit();
        }

    }

    public void update(float dt) {
        handleInput();
     //   System.out.println(Gdx.input.getX()+" "+ Gdx.input.getY());
    }

    @Override
    public void render(float delta) {
        update(delta);
        gg.batch.begin();
        gg.batch.draw(backScreen,0,0, 1200,600);
        gg.batch.draw(choice,(1200/2)-(choice.getWidth()/2),(600/2)-(choice.getHeight()/2),550,300);
        gg.batch.draw(canceled,(1200/2)-(choice.getWidth()/2)+50,(600/2)-(choice.getHeight()/2)+40,145,55);
        gg.batch.draw(okay,(1200/2)-(choice.getWidth()/2)+350,(600/2)-(choice.getHeight()/2)+40,145,55);
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
        backScreen.dispose();
        choice.dispose();
        canceled.dispose();
        okay.dispose();

    }
}
