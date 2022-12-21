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
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.tankstars.game.Scenes.Hud;
import com.tankstars.game.Sprites.Player;
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

    private Tank tank1;
    private Tank tank2;
    private Projectile projectile1;
    private Projectile projectile2;
    private Player playerA;
    private Player playerB;

    public PlayScreen(tankstars game, Integer tankId1, Integer tankId2){
        this.game = game;
        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(tankstars.V_Width, tankstars.V_Height, gameCam);
        hud = new Hud(game.batch);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("second.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1/1);
        gameCam.position.set(gamePort.getWorldWidth()/2, gamePort.getWorldHeight()/2, 0);

        world = new World(new Vector2(0, -10), true);
        b2dr = new Box2DDebugRenderer();

        BodyDef bdef = new BodyDef();
        Body body;

        playerA = new Player(world, 1, tankId1);
        playerB = new Player(world, 2, 2);

        tank1 = playerA.getTank();
        tank2 = playerB.getTank();
        projectile1 = playerA.getTank().getProjectile();
        projectile2 = playerB.getTank().getProjectile();
        playerA.setTurn(true);
        playerB.setTurn(false);

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
            worldVertices[i] = new Vector2(vertices[i *2] / 1, vertices[i * 2 + 1] / 1);
        }
        ChainShape cs = new ChainShape();
        cs.createChain(worldVertices);
        return cs;
    }

    @Override
    public void show() {

    }

    public void handleInput(float dt){
//        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && tank1.b2body.getLinearVelocity().x <= 2){
//            tank1.b2body.applyLinearImpulse(new Vector2(15f, 0), tank1.b2body.getWorldCenter(), true);
//            projectile1.b2body.applyLinearImpulse(new Vector2(15f, 0), tank1.b2body.getWorldCenter(), true);
//            //projectile1.setPosition(tank1.b2body.getPosition().x, tank1.b2body.getPosition().y);
//
//        }
//        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && tank1.b2body.getLinearVelocity().x >= -2){
//            tank1.b2body.applyLinearImpulse(new Vector2(-15f, 0), tank1.b2body.getWorldCenter(), true);
//            projectile1.b2body.applyLinearImpulse(new Vector2(-15f, 0), tank1.b2body.getWorldCenter(), true);
//            //projectile1.setPosition(tank1.getX(), tank1.getY());
//        }
        if (Gdx.input.isKeyPressed(Input.Keys.V) && tank2.b2body.getLinearVelocity().x >= -2){
            tank2.b2body.applyLinearImpulse(new Vector2(-0.15f, 0), tank2.b2body.getWorldCenter(), true);
            projectile2.b2body.applyLinearImpulse(new Vector2(-0.15f, 0), tank2.b2body.getWorldCenter(), true);
            //projectile1.setPosition(tank1.getX(), tank1.getY());
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
//            Vector2 force = new Vector2((float) (Math.cos(projectile1.b2body.getAngle()) * 2),
//                    (float) (Math.sin(projectile1.b2body.getAngle()) * 20));
//            projectile1.b2body.applyForce(force, projectile1.b2body.getPosition(), true);
            Vector2 impulse = new Vector2((float) (Math.cos(projectile1.b2body.getAngle()) * 0.5),
                    (float) (Math.sin(projectile1.b2body.getAngle()) * 0.5));
//            projectile1.b2body.applyForce(force, projectile1.b2body.getPosition(), true);
            projectile1.b2body.applyLinearImpulse(impulse, tank1.b2body.getWorldCenter(), true);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            projectile1.b2body.setTransform(projectile1.b2body.getPosition(), (float) (projectile1.b2body.getAngle() + 0.4));
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            playerA.setTurn(!playerA.getTurn());
            playerB.setTurn(!playerB.getTurn());
        }

        if (playerA.getTurn()){
            tank1.update(dt);
            projectile1.update(dt);
        } else {
            tank2.update(dt);
            projectile2.update(dt);
        }
    }

    public void update(float dt){
        world.step(1/60f, 6, 2);

//        projectile1.b2body.setTransform(tank1.b2body.getPosition(), projectile1.b2body.getAngle() );
        //System.out.println(projectile1.b2body.getPosition().toString());
        handleInput(dt);

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
        tank1.draw(game.batch);
        projectile1.draw(game.batch);
        tank2.draw(game.batch);
        projectile2.draw(game.batch);
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
