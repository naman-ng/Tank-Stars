package com.tankstars.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;

public class Airdrop extends Sprite {
    public World world;
    public Body b2body;
    private Texture airDrop;
    private Tank tank1;
    private Tank tank2;

    public Airdrop(World world, Tank tank1, Tank tank2){
        this.world = world;
        this.tank1 = tank1;
        this.tank2 = tank2;
    }
    public void update(){
        setPosition(b2body.getPosition().x - getWidth()/2, b2body.getPosition().y - getHeight()/2);
    }

    public void defineAirdrop(){
        BodyDef bdef = new BodyDef();
        bdef.position.set((tank1.b2body.getPosition().x+tank2.b2body.getPosition().x)/2, 240);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(2);

        fdef.shape = shape;
        b2body.createFixture(fdef);

        this.airDrop = new Texture(Gdx.files.internal("airdrop.png"));
        setBounds(10, 10, 6, 6);
        setRegion(airDrop);
    }
}
