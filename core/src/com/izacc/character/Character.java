package com.izacc.character;

import com.izacc.ability.Ability;
import com.izacc.equipment.Item;
import com.izacc.equipment.SpellCard;


/**
 * Created by pawel_000 on 2017-02-25.
 */
public class Character 
{
    private static final int START_X = 200;
    private static final int START_Y = 200;

    private Player player;

    public Character()
    {
        init();
    }

    private void init()
    {
        initPlayer();
    }

    private void initPlayer()
    {
        player = new Player(START_X, START_Y);
    }
    
    public void improvePlayerStats(Item item)
    {
        switch(item.effectType)
        {
            case gold:
                player.addGold(item.bonus);
                break;
                        
            case max_attack_dmg:
                player.improveBonusDamage(item.bonus);
                break;
                        
            case max_attack_speed:
                player.improveBonusAttackSpeed(-item.bonus);
                break;
                        
            case max_speed:
                player.improveBonusMovemetSpeed(item.bonus);
                break;
                        
            case max_hp:
                player.improveMaxHealth(item.bonus);
                break;
                        
            case attack_dmg:
                player.improveBonusDamage(item.bonus);
                break;
                        
            case hp:
                player.heal(item.bonus);
                break;
                        
            case speed:
                player.improveBonusMovemetSpeed(item.bonus);
                break;
                        
            case attack_speed:
                player.improveBonusAttackSpeed(-item.bonus);
                break;
        }
    }
    
    public void decreasePlayerStats(Item item)
    {
        switch(item.effectType)
        {
            case attack_dmg:
                player.improveBonusDamage(item.bonus);
                break;
                        
            case hp:
                player.heal(item.bonus);
                break;
                        
            case speed:
                player.improveBonusMovemetSpeed(-item.bonus);
                break;
                        
            case attack_speed:
                player.improveBonusAttackSpeed(item.bonus);
                break;
        }
    }

    public void update(SpellCard spellCard)
    {
        player.update(spellCard);
    }

    public void render(float delta)
    {
        for(Ability a : player.getAbilities())
        {
            a.render(delta);
        }
        
        player.render(delta);
    }

    /**
     *
     * Getters
     */

    public Player getPlayer() 
    {
        return player;
    }
}
