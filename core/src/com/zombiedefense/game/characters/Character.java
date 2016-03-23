package com.zombiedefense.game.characters;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

import com.badlogic.gdx.physics.box2d.*;
import com.zombiedefense.game.ZombieDefense;

public class Character {
    public Body body;

    public static final float speed = 15f;
    public static final float jumpHeight = 32f;

    public Character(World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(new Vector2(50, 50));
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        body = world.createBody(bodyDef);

        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(2f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circleShape;
        fixtureDef.density = 1;
        fixtureDef.restitution = 0.4f;
        fixtureDef.friction = 1f;

        body.createFixture(fixtureDef);
        circleShape.dispose();

        body.setLinearDamping(1f);
    }

    public void render() {

        body.applyLinearImpulse(new Vector2(speed * inputX(), 0f), body.getWorldCenter(), true);

        /*if(Gdx.input.getY() < (float) Gdx.graphics.getHeight() / 2 || Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            body.applyLinearImpulse(new Vector2(0f, jumpHeight), body.getWorldCenter(), true);
        }*/

    }

    int inputX() {
        if(ZombieDefense.isMobile()) {
            if(Gdx.input.isTouched()){
                if(Gdx.input.getX() >= Gdx.graphics.getWidth() / 2) return 1;
                if(Gdx.input.getX() < Gdx.graphics.getWidth() / 2) return -1;
            }else return 0;
        }else{
            int output = 0;

            if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
                output += 1;
            }
            if(Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
                output -= 1;
            }

            return output;
        }
        return 0;
    }
}
