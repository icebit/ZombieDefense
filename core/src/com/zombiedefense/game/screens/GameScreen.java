package com.zombiedefense.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.zombiedefense.game.ZombieDefense;

public class GameScreen implements Screen {

    static final float ASPECT_RATIO = (float) Gdx.graphics.getWidth() / (float) Gdx.graphics.getHeight();

    static final int WORLD_WIDTH = 100;
    static final int WORLD_HEIGHT = 100;

    final ZombieDefense game;

    OrthographicCamera cam;

    World world;

    Box2DDebugRenderer debugRenderer;

    public GameScreen(ZombieDefense zd) {
        game = zd;

        cam = new OrthographicCamera();
        cam.setToOrtho(false, WORLD_WIDTH * ASPECT_RATIO, WORLD_HEIGHT);

        debugRenderer = new Box2DDebugRenderer();
        world = new World(new Vector2(0, -10.0f), true);

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(new Vector2(50 * ASPECT_RATIO, 50));
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        Body body = world.createBody(bodyDef);

        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(2f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circleShape;
        fixtureDef.density = 1;
        fixtureDef.friction = 0.1f;

        Fixture fixture = body.createFixture(fixtureDef);
        circleShape.dispose();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        cam.update();

        game.batch.setProjectionMatrix(cam.combined);
        game.batch.begin();

        debugRenderer.render(world, cam.combined);

        game.batch.end();

        world.step(1/60f, 6, 2);
    }

    @Override
    public void resize(int width, int height) {
        cam.setToOrtho(false, WORLD_WIDTH * ASPECT_RATIO, WORLD_HEIGHT);
        cam.update();
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
