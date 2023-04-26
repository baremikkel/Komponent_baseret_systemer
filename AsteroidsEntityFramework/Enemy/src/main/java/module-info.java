import dk.sdu.mikol21.enemysystem.EnemyControlSystem;
import dk.sdu.mikol21.enemysystem.EnemyPlugin;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

module Enemy {
    requires Common;
    provides IGamePluginService with EnemyPlugin;
    provides IEntityProcessingService with EnemyControlSystem;
    exports dk.sdu.mikol21.enemysystem;
}

