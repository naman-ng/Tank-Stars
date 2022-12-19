package com.tankstars.game.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;
import com.tankstars.game.tankstars;

public class Tank extends Sprite {
    public World world;
    public Body b2body;

    public Tank(World world){
        this.world = world;
        defineTank();
    }

    public void defineTank(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(64/ tankstars.PPM, 32/ tankstars.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape  shape = new CircleShape();
        shape.setRadius(5/ tankstars.PPM);

        fdef.shape = shape;
        b2body.createFixture(fdef);
    }
}
