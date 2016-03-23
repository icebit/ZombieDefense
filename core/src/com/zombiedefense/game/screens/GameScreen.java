package com.zombiedefense.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.sun.net.httpserver.*;
import com.sun.net.httpserver.Filter;
import com.zombiedefense.game.ZombieDefense;


public class GameScreen implements Screen {

    static final float ASPECT_RATIO = (float) Gdx.graphics.getWidth() / (float) Gdx.graphics.getHeight();

    static final int WORLD_WIDTH = 100;
    static final int WORLD_HEIGHT = 100;

    final ZombieDefense game;

    OrthographicCamera cam;

    World world;
    Body body;
    Box2DDebugRenderer debugRenderer;

    public GameScreen(ZombieDefense zd) {
        game = zd;

        cam = new OrthographicCamera();
        cam.setToOrtho(false, WORLD_WIDTH * ASPECT_RATIO, WORLD_HEIGHT);

        debugRenderer = new Box2DDebugRenderer();
        world = new World(new Vector2(0, -80f), true);

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(new Vector2(50 * ASPECT_RATIO, 50));
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        body = world.createBody(bodyDef);

        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(2f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circleShape;
        fixtureDef.density = 1;
        fixtureDef.restitution = 0.4f;
        fixtureDef.friction = 1f;
        body.setLinearDamping(1f);

        body.createFixture(fixtureDef);
        circleShape.dispose();

        BodyDef fBodyDef = new BodyDef();
        fBodyDef.position.set(new Vector2(50 * ASPECT_RATIO, 10));
        fBodyDef.type = BodyDef.BodyType.KinematicBody;

        Body fBody = world.createBody(fBodyDef);

        FixtureDef fFixtureDef = new FixtureDef();
        fFixtureDef.density = 1;
        fFixtureDef.friction = 1f;

        PolygonShape polygonShape = new PolygonShape();

        polygonShape.setAsBox(100 * ASPECT_RATIO, 5);
        fFixtureDef.shape = polygonShape;
        fBody.createFixture(fFixtureDef);

        polygonShape.setAsBox(1 * ASPECT_RATIO, 8000, new Vector2(-50f * ASPECT_RATIO, 0f), 0f);
        fFixtureDef.shape = polygonShape;
        fBody.createFixture(fFixtureDef);

        polygonShape.setAsBox(1 * ASPECT_RATIO, 8000, new Vector2(200f * ASPECT_RATIO, 0f), 0f);
        fFixtureDef.shape = polygonShape;
        fBody.createFixture(fFixtureDef);

        polygonShape.setAsBox(1 * ASPECT_RATIO, 8000, new Vector2(80f * ASPECT_RATIO, 0f), -45f);
        fFixtureDef.shape = polygonShape;
        fBody.createFixture(fFixtureDef);

        polygonShape.dispose();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float lerp = 0.99f;
        Vector3 position = cam.position;
        position.x += (body.getPosition().x - position.x) * lerp * delta;
        position.y += (body.getPosition().y - position.y) * lerp * delta;

        cam.update();

        game.batch.setProjectionMatrix(cam.combined);
        game.batch.begin();

        debugRenderer.render(world, cam.combined);

        game.batch.end();

        world.step(1/60f, 6, 2);

        if(Gdx.input.isTouched()) {
            if(Gdx.input.getX() >= Gdx.graphics.getWidth() / 2) {
                body.applyLinearImpulse(new Vector2(15f, 0f), body.getWorldCenter(), true);
            }
            if(Gdx.input.getX() < Gdx.graphics.getWidth() / 2) {
                body.applyLinearImpulse(new Vector2(-15f, 0f), body.getWorldCenter(), true);
            }
            if(Gdx.input.getY() < (float) Gdx.graphics.getHeight() / 2) {
                body.applyLinearImpulse(new Vector2(0f, 32f), body.getWorldCenter(), true);
            }
        }

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            body.applyLinearImpulse(new Vector2(15f, 0f), body.getWorldCenter(), true);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            body.applyLinearImpulse(new Vector2(-15f, 0f), body.getWorldCenter(), true);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            body.applyLinearImpulse(new Vector2(0f, 32f), body.getWorldCenter(), true);
        }
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
