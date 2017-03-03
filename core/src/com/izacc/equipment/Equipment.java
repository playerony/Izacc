/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.izacc.equipment;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.izacc.utility.Path;
import java.util.ArrayList;

/**
 *
 * @author pawel_000
 */
public class Equipment {
    public Equipment(){
        init();
    }
    
    public void init()
    {
        Json json = new Json();
                
        JsonReader jsonReader = new JsonReader();
        ArrayList<Item> item = new ArrayList<Item>();
        JsonValue jsonValue = jsonReader.parse(Gdx.files.internal(Path.SCRIPT_POTION_PATH + "healing.json"));
        
        JsonValue potionsJson = jsonValue.get("healing");
        
        for (JsonValue potionJson : potionsJson.iterator()) // iterator() returns a list of children
        {
            System.out.println(potionJson.getString("name") + " " + potionJson.getString("bonus"));
        }
    }

}
