package SpaceEvaders.Utilities;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class FontManager {

    public static Font getCustomFont(float size) {
    try {
        InputStream inputStream = FontManager.class.getResourceAsStream("/fonts/space_jaeger.otf");
        Font font = Font.createFont(Font.TRUETYPE_FONT, inputStream);
        return font.deriveFont(Font.PLAIN, size);
    } catch (FontFormatException | IOException e) {
        e.printStackTrace();
        return new Font("Arial", Font.PLAIN, (int) size);
    }
}

}
