package github.io.lazyomlette.entities.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;

public class TextureComponent implements Component {
    public Texture texture = new Texture("textures/backgrounds/tiles/dirt.png");
}
