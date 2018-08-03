package script;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utils.Driver;
import utils.ImageUtils;

public class Main extends Driver {
  public static String imgArray[][];
  public static Integer startDrawingPostionX = 185;
  public static Integer startDrawingPostionY = 200;
  public static Integer lastDrawingPositionX = 0;
  public static Integer lastDrawingPositionY = 0;
  public static Boolean isStartedDrawing = false;
  
  @Before
  public void beforeTest() throws Exception {
    imgArray = ImageUtils.getImageArray();
    startDriver();
  }
  
  @Test
  public void script() throws Exception {
    acessar(
        "http://htmlpreview.github.io/?https://raw.githubusercontent.com/krisrak/html5-canvas-drawing-app/master/index_javascript.html");
    Thread.sleep(5000);
    esperarPagina();
    for (int cntImgPosX = 0; cntImgPosX < ImageUtils.image.getHeight(); cntImgPosX++) {
      for (int cntImgPosY = 0; cntImgPosY < ImageUtils.image.getWidth(); cntImgPosY++) {
        if (!imgArray[cntImgPosX][cntImgPosY].equals("branco")) {
          Integer imgX = cntImgPosX + startDrawingPostionX;
          Integer imgY = cntImgPosY + startDrawingPostionY;
//          if (!isStartedDrawing) {
//            lastDrawingPositionX = imgX;
//            lastDrawingPositionY = imgY;
//          }
//          if (isNeighbor(imgX, imgY, lastDrawingPositionX, lastDrawingPositionY)) {
          clicaRobot(imgX, imgY);
//            lastDrawingPositionX = imgX;
//            lastDrawingPositionY = imgY;
//            if (!isStartedDrawing) {
//              mousePressRobot();
//              isStartedDrawing = true;
//            }
//          }
        }
      }
    }
    mouseReleaseRobot();
    Thread.sleep(10000);
  }
  
  public Boolean isNeighbor(Integer initialX, Integer initialY, Integer finalX, Integer finalY) {
    if (comparePixels(initialX, finalX) && comparePixels(initialY, finalY)) {
      return true;
    }
    return false;
  }
  
  private Boolean comparePixels(Integer firstPixel, Integer secondPixel) {
    if (firstPixel == secondPixel || firstPixel + 1 == secondPixel || firstPixel - 1 == secondPixel) {
      return true;
    }
    return false;
  }
  
  @After
  public void afterTest() {
    driver.close();
  }
}
