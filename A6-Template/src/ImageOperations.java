import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class ImageOperations extends PpmImage {

    public ImageOperations(int width, int height) {
        super(width, height);
    }

    public ImageOperations(String filename) {
        super(filename);
    }

    public static void main(String[] args) {

    }


    public static Image zeroRed(Image img){
        Color[][] holder = img.getColors();
        for (int i = 0; i <img.getHeight(); i++){
            for (int j = 0; j<img.getWidth(); j++){
                Color original = holder[i][j];
                holder[i][j] = new Color(0,original.getGreen(),original.getBlue());
            }
        }
        return img;
    }


    public static Image grayscale(Image img){
        // TODO.
        Color[][] holder = img.getColors();
        for (int i = 0; i <img.getHeight(); i++){
            for (int j = 0; j<img.getWidth(); j++){
                Color original = holder[i][j];
                int grayscale = ((original.getRed() + original.getGreen() + original.getBlue())/3);
                holder[i][j] = new Color(grayscale,grayscale,grayscale);
            }
        }
        return img;
    }


    public static Image invert(Image img){
        // TODO.
        Color[][] holder = img.getColors();
        for (int i = 0; i <img.getHeight(); i++){
            for (int j = 0; j<img.getWidth(); j++){
                Color original = holder[i][j];
                holder[i][j] = new Color(255-original.getRed(),255 -original.getGreen(),255-original.getBlue());
            }
        }
        return img;
    }


    public static Image crop(Image img, int x1, int y1, int w, int h){
        Image croppedImage = new PpmImage(w, h);
        Color[][] sourceColors = img.getColors();
        Color[][] croppedColors = croppedImage.getColors();
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (i + y1 < img.getHeight() && j + x1 < img.getWidth()) {
                    croppedColors[i][j] = sourceColors[i + y1][j + x1];
                } else {
                    croppedColors[i][j] = new Color(0, 0, 0);
                }
            }
        }
        return croppedImage;

    }


    public static Image mirror(Image img, String mode){
        Color[][] colors = img.getColors();
        int width = img.getWidth();
        int height = img.getHeight();
        Image mirroredImage = new PpmImage(width, height);
        Color[][] mirroredColors = mirroredImage.getColors();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if ("H".equals(mode)) {
                    mirroredColors[i][j] = colors[i][width - 1 - j];
                } else if ("V".equals(mode)) {
                    mirroredColors[i][j] = colors[height - 1 - i][j];
                }
            }
        }
        return mirroredImage;
    }


    public static Image repeat(Image img, int n, String dir){
        int width = img.getWidth();
        int height = img.getHeight();
        if ("H".equals(dir)) {
            width *= n;
        } else if ("V".equals(dir)) {
            height *= n;
        }
        Image repeatImage = new PpmImage(width, height);
        Color[][] sourceColors = img.getColors();
        Color[][] repeatedColors = repeatImage.getColors();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int sourceJ = j % img.getWidth();
                int sourceI = i % img.getHeight();
                repeatedColors[i][j] = sourceColors[sourceI][sourceJ];
            }
        }
        return repeatImage;
    }

}


