package dk.sdu.mmmi.cbse.main;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import dk.sdu.mikol21.enemysystem.Enemy;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.mmmi.cbse.common.util.SPILocator;
import dk.sdu.mmmi.cbse.components.IProcessor;
import dk.sdu.mmmi.cbse.components.PluginInjection;
import dk.sdu.mmmi.cbse.managers.GameInputProcessor;
import dk.sdu.mmmi.cbse.playersystem.Player;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component("game")
public class Game implements ApplicationListener {

    private static OrthographicCamera cam;
    private final AnnotationConfigApplicationContext components;
    private ShapeRenderer sr;

    private final GameData gameData = new GameData();
    private final World world = new World();

    public Game() {
        this.components = new AnnotationConfigApplicationContext();
        this.components.scan("dk.sdu.mmmi.cbse.components");
        this.components.refresh();
    }

    @Override
    public void create() {

        sr = new ShapeRenderer();

        if (
                gameData.getDisplayWidth() != Gdx.graphics.getWidth()
                        || gameData.getDisplayHeight() != Gdx.graphics.getHeight()
        ) {
            this.updateCam(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        }

        Gdx.input.setInputProcessor(
                new GameInputProcessor(gameData)
        );

        ((PluginInjection) components.getBean("pluginInjector")).startPlugins(gameData, world);
    }

    @Override
    public void render() {

        // clear screen to black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameData.setDelta(Gdx.graphics.getDeltaTime());
        update();
        draw();

        gameData.getKeys().update();
    }

    private void updateCam(int width, int height) {
        gameData.setDisplayWidth(width);
        gameData.setDisplayHeight(height);

        cam = new OrthographicCamera(gameData.getDisplayWidth(), gameData.getDisplayHeight());
        cam.translate((float) gameData.getDisplayWidth() / 2, (float) gameData.getDisplayHeight() / 2);
        cam.update();
    }

    private void update() {
        ((IProcessor) components.getBean("processorInjector")).process(gameData, world);
        ((IProcessor) components.getBean("postProcessorInjector")).process(gameData, world);
    }

    private void draw() {
        for (Entity entity : world.getEntities()) {

            if (entity.getClass() == Player.class)
                sr.setColor(1, 1, 1, 1);
            else if (entity.getClass() == Enemy.class)
                sr.setColor(1, 0.5f, 1, 1);
            else
                sr.setColor(0.1f, 0.3f, 0.0f, 1);

            sr.begin(ShapeRenderer.ShapeType.Line);

            float[] shapex = entity.getShapeX();
            float[] shapey = entity.getShapeY();

            for (int i = 0, j = shapex.length - 1;
                 i < shapex.length;
                 j = i++) {

                sr.line(shapex[i], shapey[i], shapex[j], shapey[j]);
            }

            sr.end();
        }
    }

    @Override
    public void resize(int width, int height) {
        this.updateCam(width, height);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }

    private Collection<? extends IEntityProcessingService> getEntityProcessingServices() {
        return SPILocator.locateAll(IEntityProcessingService.class);
    }

    private Collection<? extends IPostEntityProcessingService> getPostEntityProcessingServices() {
        return SPILocator.locateAll(IPostEntityProcessingService.class);
    }
}
