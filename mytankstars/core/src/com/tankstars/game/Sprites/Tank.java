package com.tankstars.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.tankstars.game.tankstars;

public class Tank extends Sprite {
    private String name;
    private Integer tankId;
    private Integer playerId;
    private Integer health;
    public World world;
    public Body b2body;
    private Texture tank;
    private Projectile projectile;
//    private

    public Tank(World world, Integer tankId, Integer playerId){
        this.tankId = tankId;
        this.world = world;
        this.playerId = playerId;
        if (tankId == 1){
            this.health = 100;
        } else if (tankId == 2) {
            this.health = 200;
        }else {
            this.health = 300;
        }

        defineTank();
        projectile = new Projectile(world, playerId, this);
    }

    public void makeProjectile(){
        projectile = new Projectile(world, playerId, this);
    }

    public void update(float dt){
        setPosition(b2body.getPosition().x - getWidth()/2, b2body.getPosition().y - getHeight()/2);

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && b2body.getLinearVelocity().x <= 2){
            b2body.applyLinearImpulse(new Vector2(15f, 0), b2body.getWorldCenter(), true);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && b2body.getLinearVelocity().x >= -2){
            b2body.applyLinearImpulse(new Vector2(-15f, 0), b2body.getWorldCenter(), true);
        }
    }

    public void defineTank(){
        BodyDef bdef = new BodyDef();
        if (playerId == 1){
            bdef.position.set(10, 100);
        }else {
            bdef.position.set(300, 100);
        }
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);
        System.out.println("dadfa");
        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5);

        fdef.shape = shape;
        b2body.createFixture(fdef);
        if (tankId == 1) {
            tank = new Texture("Tank2.png");
            setBounds(10, 10, 16, 16);

        } else if (tankId == 3) {
            tank = new Texture("Tank3.png");
        }else {
            tank = new Texture("Tank1.png");
            setBounds(100, 100, 16, 16);

            //System.out.println("ta");
        }

        setBounds(10, 10, 16, 16);
        setRegion(tank);

        setPosition(b2body.getPosition().x - getWidth()/2, b2body.getPosition().y - getHeight()/2);
    }

    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }

    public Projectile getProjectile() {
        return projectile;
    }
}
