import java.awt.*;

// trying to push instead
abstract class Image implements Writable {
    private int width;
    private int height;
    private Color[][] colors;

    public Image(int width, int height) {
        this.width = width;
        this.height = height;
        this.colors = new Color[height][width];
    }

    public Image() {
        this(0, 0);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Color[][] getColors() {
        return colors;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setColors(Color[][] pixels) {
        this.colors = pixels;
    }


}



