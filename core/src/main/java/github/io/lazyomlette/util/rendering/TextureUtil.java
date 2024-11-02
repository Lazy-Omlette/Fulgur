package github.io.lazyomlette.util.rendering;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

public class TextureUtil {
    public static Texture combineTextures(Texture texture1, Texture texture2) {
        if(texture2 != null && texture1 != null) {
            texture1.getTextureData().prepare();
            Pixmap pixmap1 = texture1.getTextureData().consumePixmap();

            texture2.getTextureData().prepare();
            Pixmap pixmap2 = texture2.getTextureData().consumePixmap();

            pixmap1.drawPixmap(pixmap2, 0, 0);
            Texture textureResult = new Texture(pixmap1);

            pixmap1.dispose();
            pixmap2.dispose();

            return textureResult;
        }
        return texture1;
    }
}
