package com.izacc.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.izacc.character.Character;
import com.izacc.character.Player;
import com.izacc.enemy.Enemy;
import com.izacc.equipment.EffectType;
import com.izacc.equipment.Equipment;
import com.izacc.equipment.Item;
import com.izacc.equipment.ItemType;
import com.izacc.equipment.SpellCard;
import com.izacc.equipment.generate.ItemCreator;
import com.izacc.equipment.generate.ItemDropped;
import com.izacc.equipment.generate.ScrollDropped;
import com.izacc.equipment.generate.SpellCreator;
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
    private SpellCreator spellCreator;
    private ArrayList<ItemDropped> itemDropped;
    private ArrayList<ScrollDropped> scrollDropped;
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
        itemCreator = new ItemCreator();
        spellCreator = new SpellCreator();
        equipment = new Equipment();
        
        character = new Character(Character.Type.MAGE);
        
        itemDropped = new ArrayList<ItemDropped>();
        scrollDropped = new ArrayList<ScrollDropped>();
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
        if(timeState>=1.0f)
        {
                for(Item item : equipment.getBagpack())
                    if(item.disable && item.time > 0){
                        item.time--;
                    }else{
                        equipment.getBagpack().remove(item);
                        
                        break;
                    }
                
                if(equipment.getSpellCard() != null){
                    if(equipment.getSpellCard().time <= 0 && equipment.getSpellCard().effectType == EffectType.spell && equipment.getSpellCard().itemType == ItemType.spell){
                        character.getPlayer().setSpell(Player.Spell.SPELL_0);
                        equipment.removeSpell();
                    }else
                        equipment.getSpellCard().time--;
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
            font.draw(batch, str.toString(), item.getEntity().getX() - 25, item.getEntity().getY() + 50);
        }
        
        for(ScrollDropped scroll : scrollDropped){
            String str = "Spell name: " + scroll.getSpellCard().name;
            
            batch.draw(scroll.getSpellCard().getIcon(), scroll.getEntity().getX(), scroll.getEntity().getY());
            font.setColor(Color.BLACK);
            font.draw(batch, str.toString(), scroll.getEntity().getX() - 25, scroll.getEntity().getY() + 50);
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
        
        if(equipment.getSpellCard() != null){
            font.setColor(Color.GREEN);
            
            String text = equipment.getSpellCard().name + " " + equipment.getSpellCard().time + " " + equipment.getSpellCard().bonus;
            font.draw(equipmentBatch, text, 5, izacc.SCREEN_HEIGHT - 5 - level * 15);
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
                if(en1.isColision(en2))
                {
                    Item item = null;
                    SpellCard spellCard = null;
                    
                    if(random.nextInt(10) <= 7)
                        item = itemCreator.createRandomItem(en2.getMobRank());
                    else
                        spellCard = spellCreator.createSpell(en2.getMobRank());
            
                    if(item != null)
                    {
                        ItemDropped it = new ItemDropped(item, en2.getX(), en2.getY());
                        itemDropped.add(it);
                    }
                    
                    if(spellCard != null)
                    {
                        ScrollDropped scroll = new ScrollDropped(spellCard, en2.getX(), en2.getY());
                        scrollDropped.add(scroll);
                    }
                    
                    enemy.remove(en2);
                    break;
                }
            }
        }
        
        for(ItemDropped item : itemDropped)
        {
            if(character.getPlayer().isColision(item.getEntity()))
            {   
                equipment.addItem(item.getItem());
                itemDropped.remove(item);
                
                break;
            }
        }
        
        for(ScrollDropped scroll : scrollDropped)
        {
            if(character.getPlayer().isColision(scroll.getEntity()))
            {   
                String res = "SPELL_" + (scroll.getSpellCard().id);
                System.out.println(res);
            
                character.getPlayer().setSpell(Player.Spell.valueOf(res));
                equipment.setSpellCard(scroll.getSpellCard());
                scrollDropped.remove(scroll);
                
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
    
}
