import java.awt.*;
import java.util.Objects;

public class ImageOperations {

    /**
     * Terminal command inputs within class main function
     * @param args input commands
     */
    public static void main(String[] args) {
        switch(args[0]){
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
                crop(new PpmImage(args[5]),Integer.parseInt(args[1]),Integer.parseInt(args[2]),Integer.parseInt(args[3]),Integer.parseInt(args[4])).output(args[6]);
                break;
            case "--mirror":
                mirror(new PpmImage(args[2]),args[1]).output(args[3]);
                break;
            case "--repeat":
                repeat(new PpmImage(args[3]),Integer.parseInt(args[2]),args[1]).output(args[4]);
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
    public static Image zerored(Image img){
        Color[][] holder = img.getColors();
        for (int i = 0; i < img.getHeight(); i++){
            for (int j = 0; j < img.getWidth(); j++){
                Color original = holder[i][j];
                holder[i][j] = new Color(0,original.getGreen(),original.getBlue());
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
    public static Image grayscale(Image img){
        // TODO.
        Color[][] holder = img.getColors();
        for (int i = 0; i < img.getHeight(); i++){
            for (int j = 0; j < img.getWidth(); j++){
                Color original = holder[i][j];
                int grayscale = ((original.getRed() + original.getGreen() + original.getBlue())/3);
                holder[i][j] = new Color(grayscale,grayscale,grayscale);
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
    public static Image invert(Image img){
        // TODO.
        Color[][] holder = img.getColors();
        for (int i = 0; i < img.getHeight(); i++){
            for (int j = 0; j < img.getWidth(); j++){
                Color original = holder[i][j];
                holder[i][j] = new Color(255-original.getRed(),255 -original.getGreen(),255-original.getBlue());
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
    public static Image crop(Image img, int x1, int y1, int w, int h){
        Image croppedImage = new PpmImage(w, h);
        Color[][] sourceColors = img.getColors();
        Color[][] croppedColor = new Color[h][w];
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                croppedColor[j][i] = sourceColors[x1+j][y1+i];
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

    /**
     * Repeats an image with the image repeated either side-by-side
     * or top-to-bottom, depending on the given argument, 𝑛 times
     * @param img original Image
     * @param n repeated number
     * @param dir direction repeated
     * @return new repeated img
     */
    public static Image repeat(Image img, int n, String dir){
        if(n==1){
            return img;
        } else if (Objects.equals(dir, "H")) {
            return horiz(img,n);
        }
        else{
            return vert(img,n);
        }
    }

    private static Image vert(Image img, int n){
        Image repeated = new PpmImage(img.getWidth(), img.getHeight()*n);

        Color[][] imageColor = img.getColors();
        Color[][] repColor = new Color[img.getHeight()*n][img.getWidth()];

        int counter =0;
        while(counter <repColor.length){
            for(int i = 0;i<imageColor.length;i++){
                for(int j = 0; j<imageColor[0].length;j++){
                    repColor[counter+i][j] = imageColor[i][j];
                }
            }
            counter += imageColor.length;
        }

        repeated.setColors(repColor);
        return repeated;
    }
    private static Image horiz(Image img, int n){
        Image repeated = new PpmImage(img.getWidth()*n, img.getHeight());

        Color[][] imageColor = img.getColors();
        Color[][] repColor = new Color[img.getHeight()][img.getWidth()*n];

        int counter = 0;
        while (counter<repColor.length){
            for(int i = 0; i<imageColor.length;i++){
                for(int j = 0; j<imageColor[0].length;j++){
                    repColor[i][counter+j] = imageColor[i][j];
                }
            }
            counter+= imageColor[0].length;
        }
        repeated.setColors(repColor);
        return repeated;
    }
}


