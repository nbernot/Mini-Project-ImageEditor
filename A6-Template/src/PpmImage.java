import java.awt.*;
import java.util.Scanner;

class PpmImage extends Image {


    public PpmImage(int width, int height) {
        super(width, height);
    }

    public PpmImage(String filename){
        super();
        readImage(filename);
    }


    void readImage(String filename){
        // OPEN the scanner. CATCH THE EXPECTION! DO NOT
        // SIMPLY MARK THE METHOD AS "throws" IOEXception"
        Scanner sc = new Scanner(____________);
        // SKIP the P3 line.
        sc.nextLine();
        int width = sc.nextInt();
        int height = sc.nextInt();
        this.setWidth(width);
        this.setHeight(height);
        this.setColors(new Color[______][______]);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                // Populate the pixels array
            }
        }
    }

    @Override
    public void output(String filename) {

    }




}


