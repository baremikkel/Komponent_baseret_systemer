package dk.sdu.mmmi.cbse.components;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

public interface IProcessor {
    /**
     *
     * @param gameData
     * @param world
     *
     * @See Gamedata
     * @See World
     */
    void process(GameData gameData, World world);
}
