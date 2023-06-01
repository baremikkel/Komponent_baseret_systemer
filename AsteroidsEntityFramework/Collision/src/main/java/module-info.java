import dk.sdu.mikol21.collionsystem.CollisionDetection;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

module Collision {
    requires Common;

    provides IPostEntityProcessingService with CollisionDetection;
    exports dk.sdu.mikol21.collionsystem;
}