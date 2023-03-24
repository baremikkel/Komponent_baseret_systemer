package dk.sdu.mikol21.collionsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

public class CollisionDetection implements IPostEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        for (Entity entity: world.getEntities()) {
            for (Entity entityN: world.getEntities()) {
                if (entity.getID().equals(entityN.getID())) {
                    continue;
                }
                if (this.hasCollided(entity, entityN)){

                }
            }
        }
    }

    public boolean hasCollided(Entity entity, Entity entity2){
        PositionPart entityMovement1 = entity.getPart(PositionPart.class);
        PositionPart entityMovement2 = entity2.getPart(PositionPart.class);

        float dx = entityMovement1.getX() - entityMovement2.getX();
        float dy =  entityMovement1.getY() - entityMovement2.getY();

        float distance = (float) Math.sqrt(dx*dx+dy*dy);
        if (distance < (entity.getRadius() + entity2.getRadius())) {
            System.out.println("Collision between: "+entity.getName() +" & "+ entity2.getName()+ " Distance: "+distance);
            return true;
        }
        return false;
    }
}
