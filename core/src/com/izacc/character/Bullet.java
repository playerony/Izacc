/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.izacc.character;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.izacc.utility.Entity;

/**
 *
 * @author pawel_000
 */
public class Bullet extends Entity{
    private float attackSpeed = 5.2f;
    
    public enum Direction { LEFT, RIGHT, UP, DOWN };
    private Direction direction;
    
    protected boolean actived = false;

    public Bullet(int direction, float x, float y) {
        super(x, y);
        
        this.direction = Direction.values()[direction];
        init();
    }
    
    private void init() {
        initSpeed();
    }
    
    private void initSpeed() {
        switch(direction){
            case LEFT:
                xVel = -attackSpeed;
                yVel = 0;
                break;
                
            case RIGHT:
                xVel = attackSpeed;
                yVel = 0;
                break;
                
            case UP:
                yVel = attackSpeed;
                xVel = 0;
                break;
                
            default:
                yVel = -attackSpeed;
                xVel = 0;
                break;
        }
    }
    
    public void update() {
        x+=xVel;
        y+=yVel;
    }

    public void render(float delta) {
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.circle(x, y, 10);
        shapeRenderer.end();
    }
    
    public boolean isActived() {
        return actived;
    }

    public void setActived(boolean actived) {
        this.actived = actived;
    }
}
