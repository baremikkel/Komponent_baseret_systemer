package dk.sdu.mikol21.collionsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

public class CollisionDetection implements IPostEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        for (Entity entity : world.getEntities()) {
            for (Entity collisionDetection : world.getEntities()) {
                if(entity.getID().equals(collisionDetection.getID())){
                    continue;
                }
                LifePart entityLifePart = entity.getPart(LifePart.class);

                if (entityLifePart.getLife() > 0 && this.hasCollided(entity, collisionDetection)) {
                    entityLifePart.setIsHit(true);
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
