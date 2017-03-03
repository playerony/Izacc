/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.izacc.equipment;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.izacc.utility.Path;

/**
 *
 * @author pawel_000
 */
public class ItemLoader {
    private Json json;
    private JsonReader jsonReader;
    private JsonValue jsonValue;
    
    public ItemLoader(){
        json = new Json();
        jsonReader = new JsonReader();
    }
    
    public JsonValue getHealPotions(){
        return jsonReader.parse(Gdx.files.internal(Path.SCRIPT_POTION_PATH + "healing.json"));
    }
    
    public JsonValue getSpeedPotions(){
        return jsonReader.parse(Gdx.files.internal(Path.SCRIPT_POTION_PATH + "speed.json"));
    }
}
