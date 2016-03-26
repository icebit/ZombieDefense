package com.zombiedefense.game.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.zombiedefense.game.screens.GameScreen;

public class Player extends Character {

    private static final float speed = 6f;
    private static final float jumpHeight = 9f;

    private float runMultiplier = 1;
    private float runTimer = 0;

    private Sprite sprite;

    public Player(GameScreen gameScreen) {
        super(gameScreen);

        Texture texture = new Texture(Gdx.files.internal("grass.png"));
        sprite = new Sprite(texture);
        sprite.setSize(3, 3);
    }

    @Override
    public void update(float delta) {
        float inputX = inputX();

        if(inputX != 0) {
            runTimer += 1;
        }else{
            runTimer = 0;
            runMultiplier = 1;
        }

        float runMin = 750;
        float runMax = 3500;

        float runMinDelta = runMin * delta;
        float runMaxDelta = runMax * delta;

        float runMultiplierAmount = 0.13f;

        if(runTimer >= runMinDelta && runTimer <= runMaxDelta) {
            runMultiplier = 1 + (runTimer - (runMinDelta)) * (runMinDelta / runMaxDelta) * runMultiplierAmount;
        }

        float desiredVel = speed * inputX() * runMultiplier;
        float impulse = body.getMass() * (desiredVel - body.getLinearVelocity().x);
        body.applyLinearImpulse(new Vector2(impulse, 0), body.getWorldCenter(), true);

        body.applyLinearImpulse(new Vector2(0, jumpHeight * inputY()), body.getWorldCenter(), true);
    }

    public void render() {
        //Render player
        Vector2 pos = body.getPosition();
        sprite.setPosition(pos.x, pos.y);
        sprite.draw(game.game.batch);
    }

    @Override
    int inputX() {
        return super.game.inputManager.inputX();
    }

    @Override
    int inputY() {
        return super.game.inputManager.inputY();
    }
}
