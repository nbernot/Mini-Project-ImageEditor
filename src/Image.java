import java.awt.*;

/**
 * Represents an abstract concept of an image with basic properties such as width, height, and color information
 */
abstract class Image implements Writable {
    // instance variables
    private int width;
    private int height;
    private Color[][] colors;

    /**
     * Constructs an Image object with specified width and height
     * @param width the width of the image
     * @param height the height of the image
     */
    public Image(int width, int height) {
        this.width = width;
        this.height = height;
        this.colors = new Color[height][width];
    }

    /**
     * Constructs a default Image object with a width and height of 0
     */
    public Image() {
        this(0, 0);
    }

    /**
     * Retrieves the height of the image
     * @return the height of the image in pixels
     */
    public int getWidth() {
        return width;
    }

    /**
     * Retrieves the height of the image
     * @return the height of the image in pixels
     */
    public int getHeight() {
        return height;
    }

    /**
     * Retrieves the color data of the image
     * @return a 2D array of Color objects representing the color information of each pixel
     */
    public Color[][] getColors() {
        return colors;
    }

    /**
     * Sets the width of the image. This method does not resize the image; it only updates the width attribute
     * @param width the new width to be set for the image in pixels
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Sets the height of the image. This method does not resize the image; it only updates the height attribute
     * @param height the new height to be set for the image in pixels
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Sets the color data of the image. This method expects a 2D array of Color objects corresponding to the new color data
     * @param pixels a 2D array of Color objects representing the new color information of each pixel
     */
    public void setColors(Color[][] pixels) {
        this.colors = pixels;
    }

}
