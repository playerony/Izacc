/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.izacc.equipment.generate;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.izacc.utility.Path;

/**
 *
 * @author pawel_000
 */
public class ItemLoader 
{
    private Json json;
    private JsonReader jsonReader;
    private JsonValue jsonValue;
    
    public ItemLoader()
    {
        json = new Json();
        jsonReader = new JsonReader();
    }
    
    public JsonValue getHealPotions()
    {
        return jsonReader.parse(Gdx.files.internal(Path.SCRIPT_POTION_PATH + "healing.json"));
    }
    
    public JsonValue getMoveSpeedPotions()
    {
        return jsonReader.parse(Gdx.files.internal(Path.SCRIPT_POTION_PATH + "move_speed.json"));
    }
    
    public JsonValue getAttackSpeedPotions()
    {
        return jsonReader.parse(Gdx.files.internal(Path.SCRIPT_POTION_PATH + "attack_speed.json"));
    }
    
    public JsonValue getDamagePotions()
    {
        return jsonReader.parse(Gdx.files.internal(Path.SCRIPT_POTION_PATH + "damage.json"));
    }
    
    public JsonValue getShields()
    {
        return jsonReader.parse(Gdx.files.internal(Path.SCRIPT_STONE_PATH + "shield.json"));
    }
    
    public JsonValue getBags()
    {
        return jsonReader.parse(Gdx.files.internal(Path.SCRIPT_BAG_PATH + "gold.json"));
    }
    
    public JsonValue getSpells()
    {
        return jsonReader.parse(Gdx.files.internal(Path.SCRIPT_SPELL_PATH + "spell.json"));
    }
}
