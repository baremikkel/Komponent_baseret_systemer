import dk.sdu.mikol21.bulletsystem.BulletControlSystem;
import dk.sdu.mikol21.bulletsystem.BulletPlugin;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

module Bullet {
    requires Common;
    provides IGamePluginService with BulletPlugin;
    provides IEntityProcessingService with BulletControlSystem;
    exports dk.sdu.mikol21.bulletsystem;
}