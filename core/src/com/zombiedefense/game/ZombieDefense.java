package com.zombiedefense.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.zombiedefense.game.screens.GameScreen;
import com.zombiedefense.game.screens.MainMenuScreen;

public class ZombieDefense extends Game {
	public SpriteBatch batch;

	@Override
	public void create () {
        batch = new SpriteBatch();
		this.setScreen(new GameScreen(this));
	}

	@Override
	public void render () {
		super.render();

	}

	public static boolean isMobile() {
        return (Gdx.app.getType() == Application.ApplicationType.Android ||
                Gdx.app.getType() == Application.ApplicationType.iOS);
	}

	public void dispose() {
		batch.dispose();
	}
}