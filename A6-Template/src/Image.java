import java.awt.*;

abstract class Image implements Writable{
    private int width;
    private int height;
    private Color[][] colors;

    //Constructor with 2 inputs
    public Image(int width, int height) {
        this.width = width;
        this.height = height;
        this.colors = new Color[height][width];
    }

    // Empty Constructor
    public Image(){
        this(0,0);
    }

    // Gets width of Image in pixels
    public int getWidth(){
        return width;
    }

    // Gets height of Image in pixels
    public int getHeight(){
        return height;
    }

    // Gets colors of Image as Color[][]
    public Color[][] getColors(){
        return colors;
    }

    // Sets widths of Image
    public void setWidth(int width){
        this.width = width;
    }

    // Sets height of Image
    public void setHeight(int height){
        this.height = height;
    }

    // Sets colors of Image with (Color[][] pixels)
    public void setColors(Color[][] pixels){
        this.colors = pixels;
    }



}

