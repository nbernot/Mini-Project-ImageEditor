import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

class PpmImage extends Image {


    public PpmImage(int width, int height) {
        super(width, height);
    }

    public PpmImage(String filename) {
        super();
        readImage(filename);
    }


    void readImage(String filename) {
        try {
            // OPEN the scanner. CATCH THE EXPECTION! DO NOT
            // SIMPLY MARK THE METHOD AS "throws" IOEXception"
            Scanner sc = new Scanner(new File(filename));
            // SKIP the P3 line.
            sc.nextLine();
            int width = sc.nextInt();
            int height = sc.nextInt();
            this.setWidth(width);
            this.setHeight(height);
            this.setColors(new Color[width][height]);
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    // Populate the pixels array
                    int red = sc.nextInt();
                    int green = sc.nextInt();
                    int blue = sc.nextInt();
                    this.getColors()[i][j] = new Color(red, green, blue);
                }
            }
            sc.close();
        } catch (IOException e) {
            e.getMessage();
        }
    }

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


