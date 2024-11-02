package github.io.lazyomlette.util.entities.tiles;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import github.io.lazyomlette.entities.components.BreakableComponent;
import github.io.lazyomlette.entities.components.ComponentMappers;
import github.io.lazyomlette.entities.components.PositionComponent;
import github.io.lazyomlette.entities.components.TextureComponent;

public class TileCreationUtil {

    public static void registerTile(Engine engine){
        Entity tile = new Entity();
        tile.add(new BreakableComponent());
        tile.add(new TextureComponent());
        tile.add(new PositionComponent());
        engine.addEntity(tile);
    }
    public static void registerTile(Engine engine, int x, int y){
        Entity tile = new Entity();
        tile.add(new BreakableComponent());
        tile.add(new TextureComponent());
        tile.add(new PositionComponent());
        engine.addEntity(tile);
        ComponentMappers.position.get(tile).x=x;
        ComponentMappers.position.get(tile).y=y;
    }
}
