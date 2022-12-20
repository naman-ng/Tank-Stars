package com.tankstars.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;
import com.tankstars.game.tankstars;

public class Tank extends Sprite {
    public World world;
    public Body b2body;
    private Texture tank;

    public Tank(World world, int posx, int posy){
        this.world = world;
        defineTank(posx, posy);
        tank = new Texture(Gdx.files.internal("Tank1.png"));
//        Sprite sprite = new Sprite(tank);
        setBounds(posx, posy, 16/tankstars.PPM, 16/tankstars.PPM);
        setRegion(tank);
    }

    public void update(float dt){
        setPosition(b2body.getPosition().x - getWidth()/2,
                b2body.getPosition().y - getHeight()/2);
    }

    public void defineTank(float x, float y){
        BodyDef bdef = new BodyDef();
        bdef.position.set(10/ tankstars.PPM, 100/ tankstars.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape  shape = new CircleShape();
        shape.setRadius(5/ tankstars.PPM);

        fdef.shape = shape;
        b2body.createFixture(fdef);
    }
}
