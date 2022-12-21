package com.tankstars.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;
import com.tankstars.game.tankstars;

public class Projectile extends Sprite {
    public World world;
    public Body b2body;
    private Texture projectile;
    private Integer playerId;

    public Projectile(World world, Integer playerId){
        this.world = world;
        this.playerId = playerId;
        defineProjectile();
    }

    public void update(float dt){
        setPosition(b2body.getPosition().x - getWidth()/2,
                b2body.getPosition().y - getHeight()/2);
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
}
