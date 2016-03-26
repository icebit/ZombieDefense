package com.zombiedefense.game.characters;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.zombiedefense.game.screens.GameScreen;

public class Character {

    public Body body;
    public GameScreen game;

    private static final float speed = 6f;
    private static final float jumpHeight = 9f;

    public Character(GameScreen gameScreen) {
        game = gameScreen;

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(new Vector2(10, 10));
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        body = game.world.createBody(bodyDef);

        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(0.5f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circleShape;
        fixtureDef.density = 1;
        fixtureDef.restitution = 0.1f;
        fixtureDef.friction = 0f;

        body.createFixture(fixtureDef);
        circleShape.dispose();

        body.setFixedRotation(true);
    }

    public void update(float delta) {
        float desiredVel = speed * inputX();
        float impulse = body.getMass() * (desiredVel - body.getLinearVelocity().x);
        body.applyLinearImpulse(new Vector2(impulse, 0), body.getWorldCenter(), true);

        body.applyLinearImpulse(new Vector2(0, jumpHeight * inputY()), body.getWorldCenter(), true);
    }

    int inputX() {
        return 0;
    }

    int inputY() {
        return 0;
    }

}
