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
import com.tankstars.game.Sprites.Player;
import com.tankstars.game.Sprites.Projectile;
import com.tankstars.game.Sprites.Tank;
import com.tankstars.game.tankstars;

public class Hud implements Disposable {
    public Stage stage;
    private Viewport viewport;

    private Integer health1 = 0;
    private Integer health2 = 0;
    private double angel;
    private Integer power;

    private Player player1;
    private Player player2;
    private Tank tank1;
    private Tank tank2;
    private Projectile projectile1;
    private Projectile projectile2;

    Label player1Label;
    Label player2Label;
    Label healthPlayer1;
    Label healthPlayer2;
    Label powerLabel;
    Label angelLabel;


    public Hud(SpriteBatch sb, Player player1, Player player2, Tank tank1, Tank tank2, Projectile projectile1, Projectile projectile2){
        this.player1 = player1;
        this.player2 = player2;
        this.tank1 = tank1;
        this.tank2 = tank2;
        this.projectile1 = projectile1;
        this.projectile2 = projectile2;


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
        angelLabel = new Label("Power-" +String.format("%03f", angel), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        powerLabel = new Label( "Angel-" +String.format("%03d", power), new Label.LabelStyle(new BitmapFont(), Color.WHITE));

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

    public void update(float dt){
        health1 = tank1.getHealth();
        health2 = tank2.getHealth();
        if (player1.getTurn()){
            angel = projectile1.getAngel();
            power = projectile1.getPower();
        }else{
            angel = projectile2.getAngel();
            power = projectile2.getPower();
        }
        healthPlayer1.setText(String.format("%03d", health1));
        healthPlayer2.setText(String.format("%03d", health2));
        angelLabel.setText("Power-" +String.format("%03f", angel));
        powerLabel.setText("Angel-" +String.format("%03d", power));
    }
//
//    public static void addScore(int value){
//        score += value;
//        scoreLabel.setText(String.format("%06d", score));
//    }

    @Override
    public void dispose() { stage.dispose(); }
}
