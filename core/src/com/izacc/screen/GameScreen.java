package com.izacc.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.izacc.character.Character;
import com.izacc.enemy.Enemy;
import com.izacc.equipment.Equipment;
import com.izacc.equipment.Item;
import com.izacc.equipment.generate.ItemCreator;
import com.izacc.equipment.generate.ItemDropped;
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
    
    private float timeState = 0.0f;
    
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
        
        timeState+=Gdx.graphics.getDeltaTime();
        if(timeState>=1.0f){
                for(Item item : equipment.getBagpack())
                    if(item.disable && item.time > 0){
                        item.time--;
                    }else{
                        equipment.getBagpack().remove(item);
                        break;
                    }
                
                timeState = 0.0f;
        }
        
        renderDroppedItems(delta);
        renderEquipment(delta);
    }
    
    private void renderDroppedItems(float delta)
    {
        batch.begin();
        
        for(ItemDropped item : itemDropped){
            String str = "Efekt: " + item.getItem().effectType.toString();
            
            batch.draw(item.getItem().getIcon(), item.getEntity().getX(), item.getEntity().getY());
            font.setColor(Color.BLACK);
            font.draw(batch, str.toString(), item.getEntity().getX() - 25, item.getEntity().getY() + 40);
        }
        
        batch.end();
    }
    
    private void randomEnemy()
    {
        Random random = new Random();
        
        int x = 0;
        int y = 0;
        int size = 75;
        
        for(int i=0 ; i<50 ; i++)
        {
            Enemy ene = new Enemy(random.nextInt(3) + 1, 100 + x * size, 100 + y * size);
            enemy.add(ene);
            
            x++;
            
            if(x %10==0)
            {
                x = 0;
                y++;
            }
        }
    }
    
    private void renderEquipment(float delta)
    {
        int level = 0;
        equipmentBatch.begin();
        font.setColor(Color.WHITE);
        
        for(Item item : equipment.getBagpack()){
            if(item.isPermanent){
                 font.setColor(Color.RED);
            }else
                font.setColor(Color.WHITE);
            
            if(item.disable){
                String text = item.name + " " + item.time + " " + item.bonus;
                font.draw(equipmentBatch, text, 5, izacc.SCREEN_HEIGHT - 5 - level * 15);
            }else {
                String text = item.name + " " + item.bonus;
                font.draw(equipmentBatch, text, 5, izacc.SCREEN_HEIGHT - 5 - level * 15);
            }
            
            level++;
        }
        
        equipmentBatch.end();
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
                    Item item = itemCreator.createRandomItem(en2.getMobRank());
            
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
