package com.zombiedefense.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.zombiedefense.game.ZombieDefense;

public class MainMenuScreen implements Screen {

    BitmapFont font;
    final ZombieDefense game;

    OrthographicCamera cam;

    public MainMenuScreen(ZombieDefense zd) {
        game = zd;

        cam = new OrthographicCamera();
        cam.setToOrtho(false, 800, 400);

        font = new BitmapFont();
        font.setColor(Color.WHITE);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        cam.update();
        game.batch.setProjectionMatrix(cam.combined);

        game.batch.begin();
        font.draw(game.batch, "Zombie Defense", 100, 500);
        game.batch.end();

        if(Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)) {
            game.setScreen(new GameScreen(game));
        }

        if(Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(game));
        }
    }

    @Override
    public void resize(int width, int height) {

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
