package github.io.lazyomlette.entities.components;

import com.badlogic.ashley.core.ComponentMapper;

public class ComponentMappers {
    public static final ComponentMapper<BreakableComponent> breakable = ComponentMapper.getFor(BreakableComponent.class);
    public static final ComponentMapper<TextureComponent> texture = ComponentMapper.getFor(TextureComponent.class);
    public static final ComponentMapper<PositionComponent> position = ComponentMapper.getFor(PositionComponent.class);

}
