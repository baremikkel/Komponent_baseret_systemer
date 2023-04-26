import dk.sdu.mikol21.collionsystem.CollisionDetection;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

module Collision {
    requires Common;
    requires Player;
    requires RockInSpace;
    requires Enemy;
    requires Bullet;
    provides IPostEntityProcessingService with CollisionDetection;
    exports dk.sdu.mikol21.collionsystem;
}