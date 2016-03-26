package com.zombiedefense.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.zombiedefense.game.ZombieDefense;
import com.zombiedefense.game.characters.Player;
import com.zombiedefense.game.input.DesktopInputManager;
import com.zombiedefense.game.input.InputManager;
import com.zombiedefense.game.input.MobileInputManager;


public class GameScreen implements Screen {

    static final float ASPECT_RATIO = (float) Gdx.graphics.getWidth() / (float) Gdx.graphics.getHeight();

    static final int WORLD_WIDTH = 20;
    static final int WORLD_HEIGHT = 20;

    static final float lerp = 4f;

    public InputManager inputManager;

    public final ZombieDefense game;
    public World world;

    private OrthographicCamera cam;

    private Player player;

    private Box2DDebugRenderer debugRenderer;


    public GameScreen(ZombieDefense zd) {
        game = zd;

        cam = new OrthographicCamera();
        cam.setToOrtho(false, WORLD_WIDTH * ASPECT_RATIO, WORLD_HEIGHT);

        debugRenderer = new Box2DDebugRenderer();
        world = new World(new Vector2(0, -30f), true);

        player = new Player(this);

        BodyDef fBodyDef = new BodyDef();
        fBodyDef.type = BodyDef.BodyType.KinematicBody;

        Body fBody = world.createBody(fBodyDef);

        Vector2[] vertices = new Vector2[] {
                new Vector2(0f, 100f),
                new Vector2(0f, 0f),
                new Vector2(10f, 0f),
                new Vector2(20f, -2f),
                new Vector2(30f, -2f),
                new Vector2(40f, 0f),
                new Vector2(55f, 0f),
                new Vector2(70f, 2f),
                new Vector2(90f, 2f),
                new Vector2(100f, 5f),
                new Vector2(200f, 5f),
                new Vector2(200f, 100f)
        };

        ChainShape chainShape = new ChainShape();
        chainShape.createChain(vertices);

        FixtureDef fFixtureDef = new FixtureDef();
        fFixtureDef.shape = chainShape;
        fFixtureDef.density = 1;
        fFixtureDef.friction = 0.5f;

        fBody.createFixture(fFixtureDef);

        chainShape.dispose();


        if(ZombieDefense.isMobile()) {
            inputManager = new MobileInputManager();
        }else{
            inputManager = new DesktopInputManager();
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Vector3 position = cam.position;
        position.x += (player.body.getPosition().x - position.x) * (lerp * delta);
        position.y += (player.body.getPosition().y - position.y) * (lerp * delta);

        cam.update();

        debugRenderer.render(world, cam.combined);

        game.batch.setProjectionMatrix(cam.combined);
        game.batch.begin();

        player.render();

        game.batch.end();

        player.update(delta);
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
