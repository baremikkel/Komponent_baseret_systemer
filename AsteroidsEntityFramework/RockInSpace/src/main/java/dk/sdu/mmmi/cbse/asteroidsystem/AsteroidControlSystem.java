package dk.sdu.mmmi.cbse.asteroidsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

public class AsteroidControlSystem implements IEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        for (Entity asteroid : world.getEntities(Asteroid.class)) {
            PositionPart positionPart = asteroid.getPart(PositionPart.class);
            MovingPart movingPart = asteroid.getPart(MovingPart.class);

            movingPart.setUp(true);

            movingPart.process(gameData, asteroid);
            positionPart.process(gameData, asteroid);

            updateShape(asteroid);
        }
    }

    private void updateShape(Entity asteroid) {
        float[] shapex = asteroid.getShapeX();
        float[] shapey = asteroid.getShapeY();
        PositionPart positionPart = asteroid.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = positionPart.getRadians();
        float constant = 20;

        shapex[0] = (float) (x + Math.cos(radians) * constant);
        shapey[0] = (float) (y + Math.sin(radians) * constant);

        shapex[1] = (float) (x + Math.cos(radians - 2 * Math.PI / 6) * constant);
        shapey[1] = (float) (y + Math.sin(radians - 2 * Math.PI / 6) * constant);

        shapex[2] = (float) (x + Math.cos(radians - 4 * Math.PI / 6) * constant);
        shapey[2] = (float) (y + Math.sin(radians - 4 * Math.PI / 6) * constant);

        shapex[3] = (float) (x + Math.cos(radians + Math.PI) * constant);
        shapey[3] = (float) (y + Math.sin(radians + Math.PI) * constant);

        shapex[4] = (float) (x + Math.cos(radians + 4 * Math.PI / 6) * constant);
        shapey[4] = (float) (y + Math.sin(radians + 4 * Math.PI / 6) * constant);

        shapex[5] = (float) (x + Math.cos(radians + 2 * Math.PI / 6) * constant);
        shapey[5] = (float) (y + Math.sin(radians + 2 * Math.PI / 6) * constant);

        asteroid.setShapeX(shapex);
        asteroid.setShapeY(shapey);
    }
}
