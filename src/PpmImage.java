import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Represents an image in the PPM format, extending the abstract Image class
 */
public class PpmImage extends Image {

    /**
     * Constructs a PPM image with the specified width and height
     * @param width The width of the image in pixels
     * @param height The height of the image in pixels
     */
    public PpmImage(int width, int height) {
        super(width, height);
    }

    /**
     * Constructs a PPM image by reading in image data from a file
     * @param filename The name of the file to read the image data from
     */
    public PpmImage(String filename) {
        super();
        readImage(filename);
    }

    /**
     * Reads the image and gets data for processing
     * @param filename file name
     */
    void readImage(String filename) {
        try {
            Scanner sc = new Scanner(new File(filename));
            sc.nextLine();
            int width = sc.nextInt();
            int height = sc.nextInt();
            System.out.println(width);
            System.out.println(height);
            sc.nextLine();
            sc.nextLine();
            this.setWidth(width);
            this.setHeight(height);
            this.setColors(new Color[height][width]);
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    int red = sc.nextInt();
                    int green = sc.nextInt();
                    int blue = sc.nextInt();
                    this.getColors()[i][j] = new Color(red, green, blue);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
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
