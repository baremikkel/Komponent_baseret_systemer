package dk.sdu.mmmi.cbse.asteroidsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

public class AsteroidPlugin implements IGamePluginService {

    private Entity asteroid;
    public AsteroidPlugin(){}

    @Override
    public void start(GameData gameData, World world) {
        asteroid = createAsteroid(gameData);
        world.addEntity(asteroid);
    }

    private Entity createAsteroid(GameData gameData) {
        float deacceleration = 1;
        float acceleration = 100;
        float maxSpeed = 100;
        float rotationSpeed = 0;
        float x = (float) (Math.random() * gameData.getDisplayWidth());
        float y = (float) (Math.random() * gameData.getDisplayHeight());
        float radians = (float) (3.1415f / Math.random()*2);
        Entity asteroid_ = new Asteroid();
        asteroid_.add(new MovingPart(deacceleration, acceleration, maxSpeed, rotationSpeed));
        asteroid_.add(new PositionPart(x,y,radians));
        asteroid_.setShapeX(new float[6]);
        asteroid_.setShapeY(new float[6]);
        asteroid_.setRadius(20);
        asteroid_.setName("Asteroid");

        return asteroid_;
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(asteroid);
    }
}
