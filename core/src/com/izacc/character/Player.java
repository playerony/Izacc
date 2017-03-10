package com.izacc.character;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.izacc.ability.Ability;
import com.izacc.ability.Aurelion;
import com.izacc.ability.Bullet;
import com.izacc.ability.District;
import com.izacc.ability.FireBall;
import com.izacc.ability.Garnet;
import com.izacc.ability.SunShot;
import com.izacc.ability.ThrowGarnet;
import com.izacc.game.Izacc;
import com.izacc.utility.Entity;
import java.util.ArrayList;

/**
 * Created by pawel_000 on 2017-02-25.
 */
public abstract class Player extends Entity 
{
    private final float speed = 5.1f;
    private final float friction = 0.65f;
    private final float baseDamage = 7.5f;
    
    private static boolean clicked = true;

    protected enum Direction { LEFT, RIGHT, UP, DOWN, STAY };
    
    private Direction textureDirection;
    private Direction direction;
    private Spell spell;
    
    private ArrayList<Ability> bullets;

    public Player(float x, float y) {
        super(x, y);

        init();
        this.r = 20;
    }

    public abstract void render(float delta);

    public abstract void attack(Entity entity);
    
    private void init(){
        spell = Spell.SPELL_0;
        direction = Direction.STAY;
        textureDirection = Direction.RIGHT;
        
        bullets = new ArrayList<Ability>();
    }

    public void update(){
        inputHandler();
        move();
        
        if(bullets.size() > 0)
            for(Ability a : bullets)
            {
                boolean checked = false;
                if(a.isActived())
                {
                    for(Ability b : a.getBullets()){
                        bullets.add(b);
                        checked = true;
                    }

                    if(checked){
                        a.getBullets().clear();
                        bullets.remove(a);
                        break;
                    }
                }
            }
        
        for(Ability a : bullets){
            a.update();
            
            if(a.getX() > Izacc.SCREEN_WIDTH)
            {
                bullets.remove(a);
                break;
            }
            else if(a.getX() < 0)
            {
                bullets.remove(a);
                break;
            }
            else if(a.getY()> Izacc.SCREEN_HEIGHT)
            {
                bullets.remove(a);
                break;
            }
            else if(a.getY() < 0)
            {
                bullets.remove(a);
                break;
            }
        }
        
         addBulletsToBuffer();
    }

    private void inputHandler() 
    {
        abilityInputHandler();
        controlInputHandler();
    }
    
    private void controlInputHandler()
    {
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
    
    private void abilityInputHandler()
    {
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE) && clicked){
            clicked = false;
            
            switch(spell)
            {
                case SPELL_1:{
                    bullets.add(new FireBall(textureDirection.ordinal(), x, y, 12.5f));
                    }break;
                    
                case SPELL_2:{
                    bullets.add(new FireBall(textureDirection.ordinal(), x, y));
                    }break;
                    
                case SPELL_3:{
                    bullets.add(new SunShot(textureDirection.ordinal(), x, y, 8));
                    }break;
                    
                case SPELL_4:{
                    bullets.add(new Garnet(textureDirection.ordinal(), x, y));
                    }break;
                
                case SPELL_5:{
                    bullets.add(new SunShot(textureDirection.ordinal(), x, y, 16));
                    }break;
                    
                case SPELL_6:{
                    bullets.add(new ThrowGarnet(textureDirection.ordinal(), x, y));
                    }break;
                    
                case SPELL_7:{
                    bullets.add(new District(textureDirection.ordinal(), x, y));
                    spell = Spell.SPELL_0;
                    }break;
                    
                case SPELL_8:{
                    bullets.add(new Aurelion(textureDirection.ordinal(), x, y));
                    }break;
                    
                case SPELL_9:{
                    bullets.add(new SunShot(textureDirection.ordinal(), x, y, 24));
                    }break;
                    
                default:
                    bullets.add(new Bullet(textureDirection.ordinal(), x, y));
                    break;
            }
        }
        else if(!Gdx.input.isKeyPressed(Input.Keys.SPACE) && !clicked)
        {
            clicked = true;
        }
    }
    
    private void addBulletsToBuffer(){
        for(Ability a : bullets)
            if(a.isActived()){
                for(Bullet b : a.getBullets())
                    bullets.add(b);
                
                a.clearBullets();
                bullets.remove(a);
                break;
            }
    }

    private void move()
    {
        switch (direction)
        {
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

    /**
     * Getters and Setters
     */

    public ArrayList<Ability> getAbilities() 
    {
        return bullets;
    }

    public Spell getSpell()
    {
        return spell;
    }

    public void setSpell(Spell spell)
    {
        this.spell = spell;
    }

    public float getBaseDamage()
    {
        return baseDamage;
    }
    
    
}
