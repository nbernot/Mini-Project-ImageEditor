import java.awt.Color;

class ImageOperations {

    public static void main(String[] args) {
        // TODO.
    }


    public static Image zeroRed(Image img) {
        Color[][] holder = img.getColors();
        for (int i = 0; i < img.getHeight(); i++) {
            for (int j = 0; j < img.getWidth(); j++) {
                Color original = holder[i][j];
                holder[i][j] = new Color(0, original.getGreen(), original.getBlue());

            }
        }
        return img;
    }


    public static Image grayscale(Image img) {
        Color[][] holder = img.getColors();
        for (int i = 0; i < img.getHeight(); i++) {
            for (int j = 0; j < img.getWidth(); j++) {
                Color originalColor = holder[i][j];
                int grayLevel = (originalColor.getRed() + originalColor.getGreen() + originalColor.getBlue()) / 3;
                holder[i][j] = new Color(grayLevel, grayLevel, grayLevel);

            }
        }
        return img;
    }


    public static Image invert(Image img) {
        Color[][] holder = img.getColors();
        for (int i = 0; i < img.getHeight(); i++) {
            for (int j = 0; j < img.getWidth(); j++) {
                Color originalColor = holder[i][j];
                holder[i][j] = new Color(255 - originalColor.getRed(), 255 - originalColor.getGreen(), 255 - originalColor.getBlue());

            }
        }
        return img;
    }


    public static Image crop(Image img, int x1, int y1, int w, int h) {
        // TODO.
    }


    public static Image mirror(Image img, String mode) {
        // TODO.
    }


    public static Image repeat(Image img, int n, String dir) {
        // TODO.
    }

}


