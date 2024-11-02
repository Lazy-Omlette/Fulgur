package github.io.lazyomlette.util.entities.tiles;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.math.Vector2;
import github.io.lazyomlette.entities.components.ComponentMappers;
import github.io.lazyomlette.entities.components.PositionComponent;

public class TilePositionUtil {
    public static int tileSize = 1;

    public static Vector2 getTilePosition(Entity entity) {
        if (ComponentMappers.position.get(entity) != null) {
            return new Vector2(ComponentMappers.position.get(entity).x, ComponentMappers.position.get(entity).y);
        } else
            return new Vector2(0, 0);
    }

    public static Entity getTileFromPosition(Vector2 pos, Engine engine) {
        Family tilesWithPosition = Family.all(PositionComponent.class).get();
        for (int i = 0; i < engine.getEntitiesFor(tilesWithPosition).size(); i++) {
            Entity currentTile = engine.getEntitiesFor(tilesWithPosition).get(i);
            if (ComponentMappers.position.get(currentTile).x < pos.x && ComponentMappers.position.get(currentTile).x+tileSize > pos.x && ComponentMappers.position.get(currentTile).y < pos.y && ComponentMappers.position.get(currentTile).y+tileSize > pos.y) {
                return currentTile;
            }
        }
        return null;
    }
}
