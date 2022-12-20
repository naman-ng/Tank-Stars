package com.tankstars.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.tankstars.game.Scenes.Hud;
import com.tankstars.game.Sprites.Projectile;
import com.tankstars.game.Sprites.Tank;
import com.tankstars.game.tankstars;

public class PlayScreen implements Screen {
    private tankstars game;
    private OrthographicCamera gameCam;
    private Viewport gamePort;
    private Hud hud;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

//    Box 2d
    private World world;
    private Box2DDebugRenderer b2dr;

    private Tank tank;
    private Projectile projectile;

    public PlayScreen(tankstars game){
        this.game = game;
        gameCam = new OrthographicCamera();
        gamePort = new StretchViewport(tankstars.V_Width / tankstars.PPM, tankstars.V_Height/ tankstars.PPM, gameCam);
        hud = new Hud(game.batch);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("second.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1/tankstars.PPM);
        gameCam.position.set(gamePort.getWorldWidth()/2, gamePort.getWorldHeight()/2, 0);

        world = new World(new Vector2(0, -5), true);
        b2dr = new Box2DDebugRenderer();

        BodyDef bdef = new BodyDef();
        Body body;

        tank = new Tank(world, 80, 80);
        projectile = new Projectile(world, 80, 80);

        for(MapObject object: map.getLayers().get("ground").getObjects()){
            Shape shape;
            if (object instanceof PolylineMapObject){
            shape = createPolyline((PolylineMapObject) object);
            } else {
                continue;
            }

            bdef.type = BodyDef.BodyType.StaticBody;

            body = world.createBody(bdef);
            body.createFixture(shape, 1.01f);
        }
    }

    private static ChainShape createPolyline(PolylineMapObject polyline) {
        float[] vertices = polyline.getPolyline().getTransformedVertices();
        Vector2[] worldVertices = new Vector2[vertices.length / 2];

        for (int i = 0; i< worldVertices.length; i++){
            worldVertices[i] = new Vector2(vertices[i *2] / tankstars.PPM, vertices[i * 2 + 1] / tankstars.PPM);
        }
        ChainShape cs = new ChainShape();
        cs.createChain(worldVertices);
        return cs;
    }

    @Override
    public void show() {

    }
    public void handleInput(float dt){
//        player.b2body.getLinearDamping();
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && tank.b2body.getLinearVelocity().x <= 2){
            tank.b2body.applyLinearImpulse(new Vector2(0.1f, 0), tank.b2body.getWorldCenter(), true);
            projectile.b2body.applyLinearImpulse(new Vector2(0.1f, 0), tank.b2body.getWorldCenter(), true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && tank.b2body.getLinearVelocity().x >= -2){
            tank.b2body.applyLinearImpulse(new Vector2(-0.1f, 0), tank.b2body.getWorldCenter(), true);
            projectile.b2body.applyLinearImpulse(new Vector2(-0.1f, 0), tank.b2body.getWorldCenter(), true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
//            Vector2 force = new Vector2((float) (Math.cos(projectile.b2body.getAngle()) * 2),
//                    (float) (Math.sin(projectile.b2body.getAngle()) * 20));
//            projectile.b2body.applyForce(force, projectile.b2body.getPosition(), true);
            Vector2 impulse = new Vector2((float) (Math.cos(projectile.b2body.getAngle()) * 0.2),
                    (float) (Math.sin(projectile.b2body.getAngle()) * 0.2));
//            projectile.b2body.applyForce(force, projectile.b2body.getPosition(), true);
            projectile.b2body.applyLinearImpulse(impulse, tank.b2body.getWorldCenter(), true);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            projectile.b2body.setTransform(projectile.b2body.getPosition(), (float) (projectile.b2body.getAngle() + 0.4));
        }
    }

    public void update(float dt){
        world.step(1/60f, 6, 2);

//        projectile.b2body.setTransform(tank.b2body.getPosition(), tank.b2body.getAngle() );

        handleInput(dt);
        tank.update(dt);
        projectile.update(dt);

        gameCam.update();
        renderer.setView(gameCam);
    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();

        b2dr.render(world, gameCam.combined);

        game.batch.setProjectionMatrix(gameCam.combined);
        game.batch.begin();
        tank.draw(game.batch);
        projectile.draw(game.batch);
        game.batch.end();

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
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

    }
}
