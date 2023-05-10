import dk.sdu.mmmi.cbse.playersystem.PlayerControlSystem;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

module Player {
    requires Common;
    requires Bullet;
    provides IEntityProcessingService with PlayerControlSystem;
    exports dk.sdu.mmmi.cbse.playersystem;
}