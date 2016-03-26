package com.zombiedefense.game.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class DesktopInputManager extends InputManager {

    public int inputX() {

        int output = 0;

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            output += 1;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            output -= 1;
        }

        return output;

    }

    public int inputY() {

        if(Gdx.input.isKeyJustPressed (Input.Keys.SPACE)){
                return 1;
        }
        return 0;

    }

}