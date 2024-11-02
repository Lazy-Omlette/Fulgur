package github.io.lazyomlette.util.entities.tiles.breakable;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import github.io.lazyomlette.entities.components.BreakableComponent;
import github.io.lazyomlette.entities.components.ComponentMappers;
import github.io.lazyomlette.entities.components.TextureComponent;
import github.io.lazyomlette.util.rendering.TextureUtil;

import static github.io.lazyomlette.Main.gameData;

public class BreakableTileUtil {
    public static byte maxTileBreakProgress = 5;

    private static final ComponentMapper<BreakableComponent> breakableMapper = ComponentMappers.breakable;
    private static final ComponentMapper<TextureComponent> textureMapper = ComponentMappers.texture;

    public static Texture getTileTexture(Entity entity){
        Texture texture = textureMapper.get(entity).texture;
        Texture breakingTexture = getBreakTexture(breakableMapper.get(entity).brokenStage);
        return TextureUtil.combineTextures(texture, breakingTexture);
    }

    public static Texture getBreakTexture(byte breakProgress){
        if(breakProgress <= 1 || breakProgress >= maxTileBreakProgress){
            return null;
        } else {
            return new Texture("textures/backgrounds/tiles/breaking/" + breakProgress + ".png");
        }
    }

    public static void addTileBreakStage(Entity entity, Engine engine, byte amount){
        if(gameData.getInteger("toolStrength") >= ComponentMappers.breakable.get(entity).toolStrength) {
            if (breakableMapper.get(entity).brokenStage + amount >= maxTileBreakProgress) {
                engine.removeEntity(entity);
                gameData.putInteger("cash", gameData.getInteger("cash") + 1);
            } else {
                breakableMapper.get(entity).brokenStage += amount;
            }
        }
    }
}
