package com.izacc.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.izacc.character.Character;
import com.izacc.enemy.Enemy;
import com.izacc.equipment.Equipment;
import com.izacc.equipment.Item;
import com.izacc.equipment.ItemCreator;
import com.izacc.equipment.ItemDropped;
import com.izacc.game.Izacc;
import com.izacc.utility.Entity;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by pawel_000 on 2017-02-25.
 */
public class GameScreen extends AbstractScreen 
{
    private Character character;
    private Equipment equipment;
    private ItemCreator itemCreator;
    private ArrayList<ItemDropped> itemDropped;
    private ArrayList<Enemy> enemy;
    
    private boolean showEquipment;
    private boolean clicked;
    
    public GameScreen(Izacc izacc)
    {
        super(izacc);
        
        randomEnemy();
    }

    @Override
    protected void init() 
    {
        character = new Character(Character.Type.MAGE);
        itemCreator = new ItemCreator();
        equipment = new Equipment();
        itemDropped = new ArrayList<ItemDropped>();
        enemy = new ArrayList<Enemy>();
        
        showEquipment = false;
        clicked = false;
    }

    @Override
    public void render(float delta) 
    {
        super.render(delta);
        update();

        character.render(delta);
        
        // render enemies
        for(Enemy e : enemy)
            e.render(delta);
        
        renderDroppedItems(delta);
        renderEquipment(delta);
    }
    
    private void renderDroppedItems(float delta)
    {
        batch.begin();
        for(ItemDropped item : itemDropped)
            batch.draw(item.getItem().getIcon(), item.getEntity().getX(), item.getEntity().getY());
        batch.end();
    }
    
    private void randomEnemy()
    {
        Random random = new Random();
        
        for(int i=0 ; i<50 ; i++)
        {
            Enemy ene = new Enemy(random.nextInt(3) + 1, random.nextInt(Izacc.SCREEN_WIDTH), random.nextInt(izacc.SCREEN_WIDTH));
            enemy.add(ene);
        }
    }
    
    private void renderEquipment(float delta)
    {
        if(showEquipment)
        {
            shapeRenderer.setColor(Color.BROWN);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.rect(100, 100, 400, 300);
            shapeRenderer.end();
           
            int size=50;
            int x = 0, y = 0;
            for(int i=0 ; i<25 ; i++)
            {
                if(equipment.getBagpack().get(i) != null)
                {
                    equipmentBatch.begin();
                    equipmentBatch.draw(equipment.getBagpack().get(i).getIcon(), 110 + (x * size + 10 * x), 110 + (y * size + 10 * y));
                    if(equipment.getBagpack().get(i).count > 1)
                    {
                        font.draw(equipmentBatch, String.valueOf(equipment.getBagpack().get(i).count), 130 + (x * size + 10 * x) + 20, 120 + (y * size + 10 * y));
                    }
                    equipmentBatch.end();
                }
                else
                {
                    shapeRenderer.setColor(Color.WHITE);
                    shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                    shapeRenderer.rect(110 + (x * size + 10 * x), 110 + (y * size + 10 * y), size, size);
                    shapeRenderer.end();
                }
                
                x++;
                if(x%5==0)
                {
                    x = 0;
                    y++;
                }
            }
        }
    }

    private void update(){
        character.update();
        Random random = new Random();
        
        for(Entity en1 : character.getPlayer().getAbilities())
        {
            for(Enemy en2 : enemy)
            {
                if(isColision(en2, en1))
                {
                    Item item = itemCreator.createHealPotion(en2.getMobRank());
            
                    if(item != null)
                    {
                        ItemDropped it = new ItemDropped(item, en2.getX(), en2.getY());
                        itemDropped.add(it);
                    }
                    
                    enemy.remove(en2);
                    break;
                }
            }
        }
        
        for(ItemDropped item : itemDropped)
        {
            if(isColision(item.getEntity(), character.getPlayer()))
            {
                equipment.addItem(item.getItem());
                itemDropped.remove(item);
                
                break;
            }
        }
        
        if(Gdx.input.isKeyPressed(Input.Keys.E) && !clicked)
        {
            showEquipment = !showEquipment;
            clicked = true;
        }
        else if(!Gdx.input.isKeyPressed(Input.Keys.E) && clicked)
            clicked = false;
    }
    
    private boolean isColision(Entity e1, Entity e2)
    {
        float leftA, leftB;
        float rightA, rightB;
        float topA, topB;
        float bottomA, bottomB;

        leftA = e1.getX() - 20;
        rightA = e1.getX() + 40;
        topA =  e1.getY() - 20;
        bottomA =  e1.getY() + 40;

        leftB = e2.getX() - 10;
        rightB = e2.getX() + 20;
        topB = e2.getY() - 10;
        bottomB = e2.getY() + 20;
        
        if( bottomA <= topB )
        {
            return false;
        }

        if( topA >= bottomB )
        {
            return false;
        }

        if( rightA <= leftB )
        {
            return false;
        }

        if( leftA >= rightB )
        {
            return false;
        }

        return true;
    }
    
}
