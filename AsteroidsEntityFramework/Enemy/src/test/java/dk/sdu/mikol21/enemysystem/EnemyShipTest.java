package dk.sdu.mikol21.enemysystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

public class EnemyShipTest {
    @Mock
    private GameData gameData;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        when(gameData.getDisplayWidth()).thenReturn((int) 800f);
        when(gameData.getDisplayHeight()).thenReturn((int) 600f);
    }

    @Test
    void testCreateEnemyShip() {
        Entity enemyShip = EnemyPlugin.createEnemyShip(gameData);

        // Verify that the enemy ship has the correct components
        Assertions.assertNotNull(enemyShip.getPart(MovingPart.class));
        Assertions.assertNotNull(enemyShip.getPart(PositionPart.class));
        Assertions.assertNotNull(enemyShip.getPart(LifePart.class));

        // Verify that the components have the correct values
        MovingPart movingPart = enemyShip.getPart(MovingPart.class);
        //Speed
        Assertions.assertEquals(10f, movingPart.getDeceleration());
        Assertions.assertEquals(150f, movingPart.getAcceleration());
        Assertions.assertEquals(150f, movingPart.getMaxSpeed());
        Assertions.assertEquals(5f, movingPart.getRotationSpeed());
        //Position
        PositionPart positionPart = enemyShip.getPart(PositionPart.class);
        Assertions.assertEquals(400f, positionPart.getX());
        Assertions.assertEquals(300f, positionPart.getY());
        Assertions.assertEquals(3.1415f / 2, positionPart.getRadians());

        LifePart lifePart = enemyShip.getPart(LifePart.class);
        Assertions.assertEquals(1, lifePart.getLife());
    }
}
