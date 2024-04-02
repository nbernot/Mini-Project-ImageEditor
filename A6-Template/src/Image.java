import java.awt.*;
// trying to push instead
abstract class Image {
    private int width;
    private int height;
    private Color[][] pixels;

    public Image(int width, int height) {
       this.width = width;
       this.height = height;
       this.pixels = new Color[width][height];
    }
    public Image(){
        this(0,0);
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
    public Color[][] getPixels(){
        return pixels;
    }
    public void setWidth(int width){
        this.width = width;
    }
    public void setHeight(int height){
        this.height = height;
    }
    public void setPixels(Color[][] pixels){
        this.pixels = pixels;
    }





}
