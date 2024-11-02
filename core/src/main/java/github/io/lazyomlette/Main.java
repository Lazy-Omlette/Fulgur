package github.io.lazyomlette;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import github.io.lazyomlette.entities.components.BreakableComponent;
import github.io.lazyomlette.entities.components.TextureComponent;
import github.io.lazyomlette.physics.PlayerPhysics;
import github.io.lazyomlette.physics.WorldPhysics;
import github.io.lazyomlette.util.entities.tiles.TileCreationUtil;
import github.io.lazyomlette.util.entities.tiles.TilePositionUtil;
import github.io.lazyomlette.util.entities.tiles.breakable.BreakableTileUtil;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture background;
    private Texture playerTexture;
    public static Viewport viewport;
    Sprite playerSprite;
    float speed = 2;
    Vector2 touchPosition;
    Engine engine;
    public static Preferences gameData;
    Family tiles = Family.all(BreakableComponent.class, TextureComponent.class).get();
    private WorldPhysics worldPhysics;
    private PlayerPhysics playerPhysics;





    @Override
    public void create() {
        gameData = Gdx.app.getPreferences("gameData");
        gameData.putInteger("toolStrength", 0);
        viewport = new FitViewport(8, 5);
        batch = new SpriteBatch();
        background = new Texture("textures/backgrounds/libgdx.png");
        playerTexture = new Texture("textures/player/idle/01.png");
        playerSprite = new Sprite(playerTexture);
        playerSprite.setSize(1, 2);
        touchPosition =  new Vector2();
        worldPhysics = new WorldPhysics();
        playerPhysics = new PlayerPhysics(WorldPhysics.getWorld());


        engine = new Engine();
        TileCreationUtil.registerTile(engine, 1, 0);
        TileCreationUtil.registerTile(engine, 0, 2);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        super.resize(width, height);
    }

    public void drawTiles(SpriteBatch batch){
        for (int i = 0; i < engine.getEntitiesFor(tiles).size(); i++) {
            Entity currentTile = engine.getEntitiesFor(tiles).get(i);
            Vector2 drawPosition = TilePositionUtil.getTilePosition(currentTile);
            Texture drawTexture = BreakableTileUtil.getTileTexture(currentTile);
            batch.draw(drawTexture, drawPosition.x, drawPosition.y, 1, 1);

        }
    }

    public void draw(){
        ScreenUtils.clear(Color.valueOf("ffe5bf"));
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        drawBackground(batch);
        drawTiles(batch);
        drawMidGround(batch);
        drawForeground(batch);


        batch.end();
    }

    @Override
    public void render() {

        worldPhysics.update(Gdx.graphics.getDeltaTime());
        float deltaTime = Gdx.graphics.getDeltaTime();
        draw();
        input(deltaTime);
        logic();
        engine.update(deltaTime);
        gameData.flush();
    }


    public void drawMidGround(SpriteBatch batch){
        batch.draw(playerTexture, 0,30);
        playerSprite.draw(batch);
    }

    public void drawBackground(SpriteBatch batch){
        float worldWidth = viewport.getWorldWidth();
        float worldHeight = viewport.getWorldHeight();
    }

    public void drawForeground(SpriteBatch batch){
    }

    public void input(float deltaTime){

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            playerSprite.translateX(speed*deltaTime);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            playerSprite.translateX(-speed*deltaTime);
        }
        if (Gdx.input.justTouched()) {
            touchPosition.set(Gdx.input.getX(), Gdx.input.getY());
            viewport.unproject(touchPosition);

            if(TilePositionUtil.getTileFromPosition(touchPosition, engine) != null) {
                BreakableTileUtil.addTileBreakStage(TilePositionUtil.getTileFromPosition(touchPosition, engine), engine, (byte) 1);
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            playerPhysics.jump();
        }
    }

    private void logic(){

        float worldWidth = viewport.getWorldWidth();
        float worldHeight = viewport.getWorldHeight();

        float playerWidth = playerSprite.getWidth();
        float playerHeight = playerSprite.getHeight();

        playerSprite.setX(MathUtils.clamp(playerSprite.getX(), 0, worldWidth- playerWidth));
    }

    @Override
    public void dispose() {
        batch.dispose();
        background.dispose();
        playerTexture.dispose();
        worldPhysics.dispose();
    }

}



