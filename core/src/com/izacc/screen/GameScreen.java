package com.izacc.screen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.izacc.ability.Ability;
import com.izacc.character.Character;
import com.izacc.character.Spell;
import com.izacc.enemy.Enemy;
import com.izacc.equipment.Equipment;
import com.izacc.equipment.Item;
import com.izacc.equipment.SpellCard;
import com.izacc.equipment.generate.ItemCreator;
import com.izacc.equipment.generate.ItemDropped;
import com.izacc.equipment.ScrollDropped;
import com.izacc.equipment.generate.SpellCreator;
import com.izacc.game.Izacc;
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
    private Random random;
    
    private ArrayList<ItemDropped> itemDropped;
    private ArrayList<ScrollDropped> scrollDropped;
    private ArrayList<Enemy> enemy;
    
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
        random = new Random();
        
        character = new Character(Character.Type.MAGE);
        
        itemDropped = new ArrayList<ItemDropped>();
        scrollDropped = new ArrayList<ScrollDropped>();
        enemy = new ArrayList<Enemy>();
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

    @Override
    public void render(float delta) 
    {
        super.render(delta);
        update();

        character.render(delta);
        equipment.update(character);
        renderEnemies(delta);
        
        renderDroppedItems(delta);
        renderEquipment(delta);
        renderBonuses(delta);
    }
    
    private void renderBonuses(float delta)
    {
        batch.begin();
        
        String str = "Attack damage: " + character.getPlayer().getBaseDamage() + " bonus(" + character.getPlayer().getBonusDamage()+ ")";
        font.setColor(Color.BLACK);
        font.draw(batch, str.toString(), 5, izacc.SCREEN_HEIGHT / 2);
        
        str = "Attack speed: " + character.getPlayer().getBonusAttackSpeed();
        font.setColor(Color.BLACK);
        font.draw(batch, str.toString(), 5, izacc.SCREEN_HEIGHT / 2 - 15);
        
        str = "Movement speed: " + character.getPlayer().getBonusMovemetSpeed();
        font.setColor(Color.BLACK);
        font.draw(batch, str.toString(), 5, izacc.SCREEN_HEIGHT / 2 - 30);
        
        str = "Hajs: " + character.getPlayer().getGold();
        font.setColor(Color.BLACK);
        font.draw(batch, str.toString(), 5, izacc.SCREEN_HEIGHT / 2 - 45);
        
        str = "Hp: " + character.getPlayer().getMaxHealth()+ " bonus(" + character.getPlayer().getBonusHealth()+ ")";
        font.setColor(Color.BLACK);
        font.draw(batch, str.toString(), 5, izacc.SCREEN_HEIGHT / 2 - 60);
        
        batch.end();
    }
    
    private void renderEnemies(float delta)
    {
        for(Enemy e : enemy)
        {
            e.render(delta);
        
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(Color.BLACK);
            shapeRenderer.box(e.getX() - 25, e.getY() + 25, 0.0f, 50.0f, 5.0f, 0.0f);
            shapeRenderer.setColor(Color.RED);
            shapeRenderer.box(e.getX() - 25, e.getY() + 25, 0.0f, (e.getHealth() * 50.0f) / e.getMaxHealth(), 5.0f, 0.0f);
            shapeRenderer.end();
        }
    }
    
    private void renderDroppedItems(float delta)
    {
        batch.begin();
        
        for(ItemDropped item : itemDropped)
        {
            String str = "Efekt: " + item.getItem().effectType.toString();
            
            batch.draw(item.getItem().getIcon(), item.getEntity().getX(), item.getEntity().getY());
            font.setColor(Color.BLACK);
            font.draw(batch, str.toString(), item.getEntity().getX() - 25, item.getEntity().getY() + 50);
        }
        
        for(ScrollDropped scroll : scrollDropped)
        {
            String str = "Spell name: " + scroll.getSpellCard().name;
            
            batch.draw(scroll.getSpellCard().getIcon(), scroll.getEntity().getX(), scroll.getEntity().getY());
            font.setColor(Color.BLACK);
            font.draw(batch, str.toString(), scroll.getEntity().getX() - 50, scroll.getEntity().getY() + 50);
        }
        
        batch.end();
    }
    
    private void renderEquipment(float delta)
    {
        int level = 0;
        equipmentBatch.begin();
        font.setColor(Color.WHITE);
        
        for(Item item : equipment.getBagpack())
        {
            if(item.isPermanent)
                 font.setColor(Color.RED);
            else
                font.setColor(Color.WHITE);
            
            if(item.disable)
            {
                String text = item.name + " " + item.time + " " + item.bonus;
                font.draw(equipmentBatch, text, 5, izacc.SCREEN_HEIGHT - 5 - level * 15);
            }
            else 
            {
                String text = item.name + " " + item.bonus;
                font.draw(equipmentBatch, text, 5, izacc.SCREEN_HEIGHT - 5 - level * 15);
            }
            
            level++;
        }
        
        if(equipment.getSpellCard() != null)
        {
            font.setColor(Color.GREEN);
            
            String text = equipment.getSpellCard().name + " " + equipment.getSpellCard().time;
            font.draw(equipmentBatch, text, 5, izacc.SCREEN_HEIGHT - 5 - level * 15);
        }
        
        equipmentBatch.end();
    }

    private void update()
    {
        character.update();
        enemiesUpdate();
        
        for(Ability en1 : character.getPlayer().getAbilities())
        {
            boolean removed = false;
            
            for(Enemy en2 : enemy)
            {
                if(en1.isCircleCollision(en2) && !en1.isShield())
                {
                    if(character.getPlayer().getSpell() == Spell.SPELL_0)
                        en2.lostHealth(character.getPlayer().getBaseDamage() + character.getPlayer().getBonusDamage());
                    else
                        en2.lostHealth(equipment.getSpellCard().damage + character.getPlayer().getBaseDamage() + character.getPlayer().getBonusDamage());
                        
                    character.getPlayer().getAbilities().remove(en1);
                    
                    removed = true;
                }
                else if(en1.isCircleCollision(en2) && en1.isShield())
                {
                    Vector2 U1 = null;
                    Vector2 U2 = null;
                        
                    if(en2.getX() > en1.getX() && en2.getY() > en1.getY())
                    {
                        U1 = new Vector2(en1.getxVel(), en1.getyVel());
                        U2 = new Vector2(en2.getxVel(), en2.getyVel());
                    }
                    else if(en2.getX() > en1.getX() && en2.getY() < en1.getY())
                    {
                        U1 = new Vector2(en1.getxVel(), -en1.getyVel());
                        U2 = new Vector2(en2.getxVel(), -en2.getyVel());
                    }
                    else if(en2.getX() < en1.getX() && en2.getY() > en1.getY())
                    {
                        U1 = new Vector2(-en1.getxVel(), en1.getyVel());
                        U2 = new Vector2(-en2.getxVel(), en2.getyVel());
                    }
                    else
                    {
                        U1 = new Vector2(-en1.getxVel(), -en1.getyVel());
                        U2 = new Vector2(-en2.getxVel(), -en2.getyVel());
                    }
                    
                    Vector2 axis = new Vector2( U2.x - U1.x , U2.y - U1.y );
                    axis.nor();
                    
                    Vector2 U1x = new Vector2(axis.x * dot(axis, U1), axis.y * dot(axis, U1));
                    Vector2 U1y =  new Vector2(U1.x - U1x.x, U1.y - U1x.y);
                    Vector2 U2x = new Vector2(-1 * axis.x * dot(new Vector2(axis.x * -1, axis.y * -1), U2), -1 * axis.y * dot(new Vector2(axis.x * -1, axis.y * -1), U2));
                    Vector2 U2y =  new Vector2(U2.x - U2x.x, U2.y - U2x.y);
                    
                    Vector2 V1x = new Vector2 ( (U1x.x + U2x.x) - (U1x.x - U2x.x), (U1x.y + U2x.y) - (U1x.y - U2x.y) );
                    Vector2 V2x = new Vector2 ( (U1x.x + U2x.x) - (U2x.x - U1x.x), (U1x.y + U2x.y) - (U2x.y - U1x.y) );
                    Vector2 V1y = U1y;
                    Vector2 V2y = U2y;
                    
                    Vector2 speed1 = new Vector2(V1x.x + V1y.x, V1x.y + V1y.y);
                    Vector2 speed2 = new Vector2(V2x.x + V2y.x, V2x.y + V2y.y);
                            
                    en2.setxVel(speed2.x / 70);
                    en2.setyVel(speed2.y / 70);
                }
            }
            
            if(removed){
                break;
            }
        }
        
        for(ItemDropped item : itemDropped)
        {
            if(character.getPlayer().isColision(item.getEntity()))
            {   
                equipment.addItem(item.getItem());
                character.improvePlayerStats(item.getItem());
                
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
            
                character.getPlayer().setSpell(Spell.valueOf(res));
                equipment.setSpellCard(scroll.getSpellCard());
                scrollDropped.remove(scroll);
                
                break;
            }
        }
    }
    
    private float dot(Vector2 lv, Vector2 rv)
    {
        return lv.x * rv.x + lv.y * rv.y;
    }

    private void enemiesUpdate()
    {
        for(Enemy e : enemy)
        {
            e.update();
            
            if(e.isDead())
            {
                Item item = null;
                SpellCard spellCard = null;
                    
                if(random.nextInt(10) <= 7)
                    item = itemCreator.createRandomItem(e.getMobRank());
                else
                    spellCard = spellCreator.createSpell(e.getMobRank());
            
                if(item != null)
                {
                    ItemDropped it = new ItemDropped(item, e.getX(), e.getY());
                    itemDropped.add(it);
                }
                    
                if(spellCard != null)
                {
                    ScrollDropped scroll = new ScrollDropped(spellCard, e.getX(), e.getY());
                    scrollDropped.add(scroll);
                }
                    
                enemy.remove(e);
                break;
            }
        }
    }
}
