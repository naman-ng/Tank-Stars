package com.tankstars.game.Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.tankstars.game.tankstars;

public class Hud implements Disposable {
    public Stage stage;
    private Viewport viewport;

    private Integer health1;
    private Integer health2;
    private Integer angel;
    private Integer power;

    Label player1Label;
    Label player2Label;
    Label healthPlayer1;
    Label healthPlayer2;
    Label powerLabel;
    Label angelLabel;


    public Hud(SpriteBatch sb){
        health1 = 100;
        health2 = 200;
        viewport = new FitViewport(tankstars.V_Width, tankstars.V_Height, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Table table= new Table();
        table.top();
        table.setFillParent(true);

        player1Label = new Label("PlayerA", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        player2Label = new Label("PlayerB", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        healthPlayer1 = new Label(String.format("%03d", health1), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        healthPlayer2 = new Label(String.format("%03d", health2), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        powerLabel = new Label("Power-" +String.format("%03d", angel), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        angelLabel = new Label( "Angel-" +String.format("%03d", power), new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(player1Label).expandX().padTop(0);
        table.add(player2Label).expandX().padTop(0);
        table.row();
        table.add(healthPlayer1).expandX();
        table.add(healthPlayer2).expandX();
        table.row();
        table.add(powerLabel).expandX();
        table.add(angelLabel).expandX();

        stage.addActor(table);
    }

//    public void update(float dt){
//        timeCount += dt;
//        if(timeCount >= 1){
//            if (worldTimer > 0) {
//                worldTimer--;
//            } else {
//                timeUp = true;
//            }
//            countdownLabel.setText(String.format("%03d", worldTimer));
//            timeCount = 0;
//        }
//    }
//
//    public static void addScore(int value){
//        score += value;
//        scoreLabel.setText(String.format("%06d", score));
//    }

    @Override
    public void dispose() { stage.dispose(); }
}
