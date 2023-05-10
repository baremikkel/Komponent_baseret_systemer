package dk.sdu.mikol21.collionsystem;

import dk.sdu.mikol21.bulletsystem.Bullet;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.mmmi.cbse.playersystem.Player;

public class CollisionDetection implements IPostEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        for (Entity entity : world.getEntities()) {
            for (Entity collisionDetection : world.getEntities()) {
                // get life parts on all entities
                LifePart entityLife = entity.getPart(LifePart.class);

                // if the two entities are identical, skip the iteration
                if (entity.getID().equals(collisionDetection.getID()) ||
                (entity instanceof Bullet && collisionDetection instanceof Player) ||
                (entity instanceof Player && collisionDetection instanceof Bullet) ||
                        (entity instanceof Bullet && collisionDetection instanceof Bullet)){
                    continue;
                }

                // CollisionDetection
                if (this.hasCollided(entity, collisionDetection)) {
                    // if entity has been hit, and should have its life reduced
                    if (entityLife.getLife() > 0) {
                        entityLife.setLife(entityLife.getLife() - 1);
                        entityLife.setIsHit(true);
                        // if entity is out of life - remove
                        if (entityLife.getLife() <= 0) {
                            world.removeEntity(entity);
                        }
                    }
                }
            }
        }
    }

    public boolean hasCollided(Entity entity, Entity entity2){
        PositionPart entityMovement1 = entity.getPart(PositionPart.class);
        PositionPart entityMovement2 = entity2.getPart(PositionPart.class);

        float dx = entityMovement1.getX() - entityMovement2.getX();
        float dy =  entityMovement1.getY() - entityMovement2.getY();

        float distance = (float) Math.sqrt(Math.pow(dx,2)+Math.pow(dy,2));
        if (distance < (entity.getRadius() + entity2.getRadius())) {
            return true;
        }
        return false;
    }
}
