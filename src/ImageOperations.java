import java.awt.*;
import java.util.Objects;

/**
 * Provides static methods for various image operations such as color inversion, grayscaling, cropping, etc
 */
public class ImageOperations {

    /**
     * Terminal command inputs within class main function
     * @param args input commands
     */
    public static void main(String[] args) {
        switch (args[0]) {
            case "--zerored":
                zerored(new PpmImage(args[1])).output(args[2]);
                break;
            case "--grayscale":
                grayscale(new PpmImage(args[1])).output(args[2]);
                break;
            case "--invert":
                invert(new PpmImage(args[1])).output(args[2]);
                break;
            case "--crop":
                crop(new PpmImage(args[5]), Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4])).output(args[6]);
                break;
            case "--mirror":
                mirror(new PpmImage(args[2]), args[1]).output(args[3]);
                break;
            case "--repeat":
                repeat(new PpmImage(args[3]), Integer.parseInt(args[2]), args[1]).output(args[4]);
                break;
            default:
                System.err.println("Not a Command");
        }
    }

    /**
     * Removes the red channel from the colors of the image
     * @param img Image
     * @return new img with red channel removed
     */
    public static Image zerored(Image img) {
        Color[][] holder = img.getColors();
        for (int i = 0; i < img.getHeight(); i++) {
            for (int j = 0; j < img.getWidth(); j++) {
                Color original = holder[i][j];
                holder[i][j] = new Color(0, original.getGreen(), original.getBlue());
            }
        }
        img.setColors(holder);
        return img;
    }

    /**
     * Converts the image to grayscale
     * @param img Image
     * @return new img in grayscale
     */
    public static Image grayscale(Image img) {
        Color[][] holder = img.getColors();
        for (int i = 0; i < img.getHeight(); i++) {
            for (int j = 0; j < img.getWidth(); j++) {
                Color original = holder[i][j];
                int grayscale = ((original.getRed() + original.getGreen() + original.getBlue()) / 3);
                holder[i][j] = new Color(grayscale, grayscale, grayscale);
            }
        }
        img.setColors(holder);
        return img;
    }

    /**
     * Inverts the pixel data, (inverse colors)
     * @param img Image
     * @return new img with inverted pixels
     */
    public static Image invert(Image img) {
        Color[][] holder = img.getColors();
        for (int i = 0; i < img.getHeight(); i++) {
            for (int j = 0; j < img.getWidth(); j++) {
                Color original = holder[i][j];
                holder[i][j] = new Color(255 - original.getRed(), 255 - original.getGreen(), 255 - original.getBlue());
            }
        }
        img.setColors(holder);
        return img;
    }

    /**
     * Crops the original image to new dimensions
     * @param img original Image
     * @param x1 starting pixel x-coordinate
     * @param y1 starting pixel y-coordinate
     * @param w new cropped width
     * @param h new cropped height
     * @return new cropped img
     */
    public static Image crop(Image img, int x1, int y1, int w, int h) {
        Image croppedImage = new PpmImage(w, h);
        Color[][] sourceColors = img.getColors();
        Color[][] croppedColor = new Color[h][w];
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                if ((y1 + i) < img.getHeight() && (x1 + j) < img.getWidth()) {
                    croppedColor[i][j] = sourceColors[y1 + i][x1 + j];
                } else {
                    croppedColor[i][j] = new Color(0, 0, 0);
                }
            }
        }
        croppedImage.setColors(croppedColor);
        return croppedImage;
    }

    /**
     * Mirrors the image vertically or horizontally
     * @param img Image
     * @param mode "H" or "V"
     * @return new mirrored img
     */
    public static Image mirror(Image img, String mode) {
        Color[][] colors = img.getColors();
        Image mirroredImage = new PpmImage(img.getWidth(), img.getHeight());
        for (int i = 0; i < colors.length; i++) {
            for (int j = 0; j < colors[0].length; j++) {
                Color sun = colors[i][j];
                mirroredImage.getColors()[i][j] = new Color(sun.getRed(), sun.getGreen(), sun.getBlue());
            }
        }
        if (mode.equals("H")) {
            Color[][] result = mirroredImage.getColors();
            for (int i = 0; i < result.length / 2; i++) {
                for (int j = 0; j < result[0].length; j++) {
                    Color pixelColor = result[i][j];
                    mirroredImage.getColors()[img.getWidth() - i - 1][j] = pixelColor;
                }
            }
            return mirroredImage;
        } else if (mode.equals("V")) {
            Color[][] result = mirroredImage.getColors();
            for (int i = 0; i < result.length; i++) {
                for (int j = 0; j < result[0].length / 2; j++) {
                    Color pixelColor = result[i][j];
                    mirroredImage.getColors()[i][img.getHeight() - j - 1] = pixelColor;
                }
            }
            return mirroredImage;
        }
        return mirroredImage;
    }

    /**
     * Repeats an image with the image repeated either side-by-side
     * or top-to-bottom, depending on the given argument, n times
     * @param img original Image
     * @param n repeated number
     * @param dir direction repeated
     * @return new repeated img
     */
    public static Image repeat(Image img, int n, String dir) {
        if (n == 1) {
            return img;
        } else if (Objects.equals(dir, "H")) {
            return horiz(img, n);
        } else {
            return vert(img, n);
        }
    }

    /**
     * Creates a vertically repeated image based on the input image and the number of repetitions
     * @param img The original image to repeat
     * @param n The number of times to repeat the image vertically
     * @return A new Image object that repeats the original image vertically n times
     */
    private static Image vert(Image img, int n) {
        Image repeated = new PpmImage(img.getWidth(), img.getHeight() * n);
        Color[][] imageColor = img.getColors();
        Color[][] repColor = new Color[img.getHeight() * n][img.getWidth()];
        int counter = 0;
        while (counter < repColor.length) {
            for (int i = 0; i < imageColor.length; i++) {
                for (int j = 0; j < imageColor[0].length; j++) {
                    repColor[counter + i][j] = imageColor[i][j];
                }
            }
            counter += imageColor.length;
        }
        repeated.setColors(repColor);
        return repeated;
    }

    /**
     * Creates a horizontally repeated image based on the input image and the number of repetitions
     * @param img The original image to repeat
     * @param n The number of times to repeat the image horizontally
     * @return A new Image object that repeats the original image horizontally n times
     */
    private static Image horiz(Image img, int n) {
        Image repeated = new PpmImage(img.getWidth() * n, img.getHeight());
        Color[][] imageColor = img.getColors();
        Color[][] repColor = new Color[img.getHeight()][img.getWidth() * n];
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < imageColor.length; i++) {
                for (int j = 0; j < imageColor[0].length; j++) {
                    repColor[i][k * img.getHeight() + j] = imageColor[i][j];
                }
            }
        }
        repeated.setColors(repColor);
        return repeated;
    }

}
