import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class PpmImage extends Image {
    private int width;
    private int height;
    private Color[][] color;


    public PpmImage(int width, int height) {
        super(width, height);
    }

    public PpmImage(String filename){
        super();
        readImage(filename);
    }

    /**
     * Reads the image and gets data for processing
     * @param filename file name
     */
    void readImage(String filename){
        try {
            // OPEN the scanner. CATCH THE EXPECTION! DO NOT
            // SIMPLY MARK THE METHOD AS "throws" IOEXception"
            Scanner sc = new Scanner(new File(filename));
            // SKIP the P3 line.
            sc.nextLine();
            int width = sc.nextInt();
            int height = sc.nextInt();
            System.out.println("Read image dimensions: " + width + " x " + height);
            this.setWidth(width);
            this.setHeight(height);
            this.setColors(new Color[width][height]);
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    // Populate the pixels array
                    int red = sc.nextInt();
                    int green = sc.nextInt();
                    int blue = sc.nextInt();
                    this.getColors()[i][j] = new Color(red,green,blue);
                }
            }
            sc.close();
        }
        catch(IOException e){
            e.getMessage();
        }
    }

    /**
     * Designates the output for the new updated image
     * @param filename filename
     */
    @Override
    public void output(String filename) {
        try (PrintWriter write = new PrintWriter(filename)) {
            write.println("P3");
            System.out.println("Writing image dimensions: " + getWidth() + " x " + getHeight());
            write.println(getWidth() + " " + getHeight());
            write.println(255);
            for (Color[] row : getColors()) {
                for (Color pixel : row) {
                    write.println(pixel.getRed() + " " + pixel.getGreen() + " " + pixel.getBlue());
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error writing to file: " + filename);
        }
    }

}

