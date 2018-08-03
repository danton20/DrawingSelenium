package utils;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import javax.imageio.ImageIO;


public class ImageUtils {
  private static String imgPath = System.getProperty("user.dir") + "\\img\\circle.png";
  public static BufferedImage image;
  
  private static void readImg() throws Exception {
    image = ImageIO.read(new File(imgPath));
  }
  
  private static String getPixelColor(int clr) {
    int red = (clr & 0x00ff0000) >> 16;
    int green = (clr & 0x0000ff00) >> 8;
    int blue = clr & 0x000000ff;
    double luminance = 0.2126 * red + 0.7152 * green + 0.0722 * blue;
    String hex = "";
    if (luminance > 230) {
      hex = "branco";
    }
    else {
      hex = String.format("%02x%02x%02x", red, green, blue);
    }
    return hex;
  }
  
  public static String[][] getImageArray() throws Exception {
    readImg();
    final byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
    final int width = image.getWidth();
    final int height = image.getHeight();
    final boolean hasAlphaChannel = image.getAlphaRaster() != null;
    String[][] result = new String[height][width];
    if (hasAlphaChannel) {
      final int pixelLength = 4;
      for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
        int argb = 0;
        argb += (((int) pixels[pixel] & 0xff) << 24); // alpha
        argb += ((int) pixels[pixel + 1] & 0xff); // blue
        argb += (((int) pixels[pixel + 2] & 0xff) << 8); // green
        argb += (((int) pixels[pixel + 3] & 0xff) << 16); // red
        result[row][col] = getPixelColor(argb);
        col++;
        if (col == width) {
          col = 0;
          row++;
        }
      }
    }
    else {
      final int pixelLength = 3;
      for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
        int argb = 0;
        argb += -16777216; // 255 alpha
        argb += ((int) pixels[pixel] & 0xff); // blue
        argb += (((int) pixels[pixel + 1] & 0xff) << 8); // green
        argb += (((int) pixels[pixel + 2] & 0xff) << 16); // red
        result[row][col] = getPixelColor(argb);
        col++;
        if (col == width) {
          col = 0;
          row++;
        }
      }
    }
    return result;
  }
}
