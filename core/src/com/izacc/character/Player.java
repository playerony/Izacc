package com.izacc.character;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.izacc.utility.Entity;

/**
 * Created by pawel_000 on 2017-02-25.
 */
public abstract class Player extends Entity {
    private float speed = 5.0f;
    private float friction = 0.9f;

    private enum Direction { LEFT, RIGHT, UP, DOWN, STAY };

    private Direction direction;

    public Player(float x, float y) {
        super(x, y);

        direction = Direction.STAY;
    }

    public abstract void render(float delta);

    public abstract void attack();

    public void update(){
        inputHandler();
        move();
    }

    private void inputHandler() {
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
            direction = Direction.LEFT;
        else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            direction = Direction.RIGHT;
        else if(Gdx.input.isKeyPressed(Input.Keys.UP))
            direction = Direction.UP;
        else if(Gdx.input.isKeyPressed(Input.Keys.DOWN))
            direction = Direction.DOWN;
        else
            direction = Direction.STAY;
    }

    private void move(){
        switch (direction){
            case LEFT:
                xVel = -Math.abs(speed);
                break;

            case RIGHT:
                xVel = Math.abs(speed);
                break;

            case UP:
                yVel = Math.abs(speed);
                break;

            case DOWN:
                yVel = -Math.abs(speed);
                break;

            default:
                break;
        }

        x += xVel;
        y += yVel;

        xVel*=friction;
        yVel*=friction;
    }

    public void addGold(float gold){
        this.gold += gold;
    }

    /**
     * Getters and Setters
     */


}
