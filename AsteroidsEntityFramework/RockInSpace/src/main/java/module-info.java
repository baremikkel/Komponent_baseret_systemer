import dk.sdu.mikol21.asteroidsystem.AsteroidControlSystem;
import dk.sdu.mikol21.asteroidsystem.AsteroidPlugin;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

module RockInSpace {
    requires Common;
    provides IGamePluginService with AsteroidPlugin;
    provides IEntityProcessingService with AsteroidControlSystem;
    exports dk.sdu.mikol21.asteroidsystem;
}