package com.zombiedefense.game.input;

import com.badlogic.gdx.Gdx;

public class MobileInputManager extends InputManager {

    public int inputX() {

        if(Gdx.input.isTouched()){
            if(Gdx.input.getX() >= Gdx.graphics.getWidth() / 2) return 1;
            if(Gdx.input.getX() < Gdx.graphics.getWidth() / 2) return -1;
        }

        return 0;

    }

    public int inputY() {

        if(Gdx.input.justTouched() && Gdx.input.getY() < Gdx.graphics.getHeight() * (1f / 3f)){
            return 1;
        }

        return 0;

    }
}

