package com.tankstars.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.tankstars.game.tankstars;

public class Projectile extends Sprite {
    public World world;
    public Body b2body;
    private Texture projectile;
    private Integer playerId;
    private Tank tank;
    private Integer power;
    private double angel;

    public Projectile(World world, Integer playerId, Tank tank){
        this.world = world;
        this.playerId = playerId;
        this.tank = tank;
        defineProjectile();
        power = 0;
        angel = 0.0;
    }

    public void update(float dt){
        setPosition(b2body.getPosition().x - getWidth()/2,
                b2body.getPosition().y - getHeight()/2);

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && b2body.getLinearVelocity().x <= 2){
            b2body.applyLinearImpulse(new Vector2(15f, 0), b2body.getWorldCenter(), true);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && b2body.getLinearVelocity().x >= -2){
            b2body.applyLinearImpulse(new Vector2(-15f, 0), b2body.getWorldCenter(), true);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            angel += 0.4;
            b2body.setTransform(b2body.getPosition(), (float) (b2body.getAngle() + angel));
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            b2body.setTransform(b2body.getPosition(), (float) (b2body.getAngle() + 0.4));
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            Vector2 impulse = new Vector2((float) (Math.cos(b2body.getAngle()) * power),
                    (float) (Math.sin(b2body.getAngle()) * power));
            b2body.applyLinearImpulse(impulse, tank.b2body.getWorldCenter(), true);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.S)) {
            power +=1;
        }
    }

    public void defineProjectile(){
        BodyDef bdef = new BodyDef();
        if (playerId == 1){
            bdef.position.set(10/ 1, 100/ 1);
        }else {
            bdef.position.set(300/ 1, 100/ 1);
        }bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(2/ 1);

        fdef.shape = shape;
        b2body.createFixture(fdef);

        this.projectile = new Texture(Gdx.files.internal("projectile.png"));
        setBounds(10, 10, 6/ 1, 6/1);
        setRegion(projectile);
    }

    public Integer getPower() {
        return power;
    }

    public double getAngel() {
        return angel;
    }
}
