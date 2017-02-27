package com.izacc.character;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.izacc.ability.Ability;
import com.izacc.ability.mage.BreathOfDeath;
import com.izacc.ability.mage.LargeFireBall;
import com.izacc.ability.mage.SmallFireBall;
import com.izacc.utility.Entity;
import java.util.ArrayList;

/**
 * Created by pawel_000 on 2017-02-25.
 */
public abstract class Player extends Entity {
    private final float speed = 5.1f;
    private final float friction = 0.9f;
    
    private static boolean clicked = true;

    protected enum Spell { SPELL_1, SPELL_2, SPELL_3 }; 
    protected enum Direction { LEFT, RIGHT, UP, DOWN, STAY };
    
    private Direction textureDirection;
    private Direction direction;
    private Spell spell;
    
    protected ArrayList<Ability> abilities;

    public Player(float x, float y) {
        super(x, y);

        init();
    }

    public abstract void render(float delta);

    public abstract void attack(Entity entity);
    
    private void init(){
        direction = Direction.STAY;
        textureDirection = Direction.RIGHT;
        spell = Spell.SPELL_1;
        
        abilities = new ArrayList<Ability>();
    }

    public void update(){
        inputHandler();
        move();
        
        for(Ability a : abilities)
            a.update();
    }

    private void inputHandler() {
        abilityInputHandler();
        controlInputHandler();
    }
    
    private void controlInputHandler(){
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
        
        if(direction != Direction.STAY)
            textureDirection = direction;
    }
    
    private void abilityInputHandler(){
        if(Gdx.input.isKeyPressed(Input.Keys.NUM_1))
            spell = Spell.SPELL_1;
        else if(Gdx.input.isKeyPressed(Input.Keys.NUM_2))
            spell = Spell.SPELL_2;
        else if(Gdx.input.isKeyPressed(Input.Keys.NUM_3))
            spell = Spell.SPELL_3;
        
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE) && clicked){
            clicked = false;
            
            switch(spell){
                case SPELL_1:
                    abilities.add(new SmallFireBall(textureDirection.ordinal(), x, y));
                    break;
                    
                case SPELL_2:
                    abilities.add(new LargeFireBall(textureDirection.ordinal(), x, y));
                    break;
                    
                default:
                    abilities.add(new BreathOfDeath(textureDirection.ordinal(), x, y));
                    break;
            }
        }else if(!Gdx.input.isKeyPressed(Input.Keys.SPACE) && !clicked){
            clicked = true;
        }
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

    public ArrayList<Ability> getAbilities() {
        return abilities;
    }
}
