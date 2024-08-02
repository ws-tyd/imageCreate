package com.jyyy.picturegeneration.pojo;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class TextImageGenerator {

    public static void main(String[] args) {
        int width = 400;
        int height = 200;

        // Create a BufferedImage object
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // Get Graphics2D object from the BufferedImage
        Graphics2D g2d = image.createGraphics();

        // Set background color
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);

        // Set text
        String text = "Hello, Java!";
        Font font = new Font("Arial",Font.TRUETYPE_FONT, 20);
        g2d.setFont(font);

        // Get text dimensions
        FontMetrics fm = g2d.getFontMetrics();
        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getHeight();

        // Calculate position to center text
        int x = (width - textWidth) / 2;
        int y = (height - textHeight) / 2 + fm.getAscent();

        // Set text color, border color and shadow color
        Color textColor = Color.WHITE;
        Color borderColor = Color.BLUE;
        Color shadowColor = Color.PINK;

        // Draw shadow
        g2d.setColor(shadowColor);
        g2d.drawString(text, x + 5, y + 5);
        // Draw border
        g2d.setColor(borderColor);
        g2d.drawString(text, x - 1, y - 1);
        g2d.drawString(text, x + 1, y + 1);
        g2d.drawString(text, x - 1, y + 1);
        g2d.drawString(text, x + 1, y - 1);

        // Draw text
        g2d.setColor(textColor);
        g2d.drawString(text, x, y);

        // Dispose Graphics2D object
        g2d.dispose();

        // Save as PNG
        File outputFile = new File("text_image.png");
        try {
            ImageIO.write(image, "png", outputFile);
            System.out.println("Image saved: " + outputFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}