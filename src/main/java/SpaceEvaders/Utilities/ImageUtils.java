package SpaceEvaders.Utilities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class ImageUtils {
  
  public static BufferedImage darkenImage(Image image, float darkeningFactor) {
    // Create a BufferedImage with the same dimensions as the original image
    BufferedImage darkenedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);

    // Get the Graphics2D object for the BufferedImage
    Graphics2D g2d = darkenedImage.createGraphics();

    // Draw the original image onto the BufferedImage
    g2d.drawImage(image, 0, 0, null);

    // Apply the darkening effect to the image
    for (int x = 0; x < darkenedImage.getWidth(); x++) {
      for (int y = 0; y < darkenedImage.getHeight(); y++) {
        // Get the color of the pixel at (x, y)
        int rgb = darkenedImage.getRGB(x, y);
        Color color = new Color(rgb, true);

        // Apply the darkening effect by reducing the color components
        int darkenedR = (int) (color.getRed() * darkeningFactor);
        int darkenedG = (int) (color.getGreen() * darkeningFactor);
        int darkenedB = (int) (color.getBlue() * darkeningFactor);

        // Create the darkened color
        Color darkenedColor = new Color(darkenedR, darkenedG, darkenedB, color.getAlpha());

        // Set the darkened color to the pixel at (x, y)
        darkenedImage.setRGB(x, y, darkenedColor.getRGB());
      }
    }

    // Dispose the Graphics2D object
    g2d.dispose();

    // Return the darkened image
    return darkenedImage;
  }

  public static BufferedImage scaleImage(Image image, int width, int height) {
    // Create a BufferedImage with the desired width and height
    BufferedImage scaledImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

    // Get the Graphics2D object for the BufferedImage
    Graphics2D g2d = scaledImage.createGraphics();

    // Draw the original image onto the scaled BufferedImage
    g2d.drawImage(image, 0, 0, width, height, null);

    // Dispose the Graphics2D object
    g2d.dispose();

    // Return the scaled image
    return scaledImage;
  }
}
