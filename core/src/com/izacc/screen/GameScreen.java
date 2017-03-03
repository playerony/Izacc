package com.izacc.screen;

import com.izacc.character.Character;
import com.izacc.equipment.Equipment;
import com.izacc.game.Izacc;

/**
 * Created by pawel_000 on 2017-02-25.
 */
public class GameScreen extends AbstractScreen {
    private Character character;
    private Equipment equipment;

    public GameScreen(Izacc izacc) {
        super(izacc);
    }

    @Override
    protected void init() {
        character = new Character(Character.Type.MAGE);
        equipment = new Equipment();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update();

        character.render(delta);
    }

    private void update(){
        character.update();
    }
}
