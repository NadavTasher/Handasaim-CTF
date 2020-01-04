import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Random;

public class Encoder {

    public static File ROOT_DIRECTORY = new File(System.getProperty("user.dir"));

    public static void main(String[] args) {
        int size = 16;

        BufferedImage bufferedImage = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);

        Random random = new Random();

        byte[] bytes = readData();

        for (int i = 0; i < bytes.length; i++) {
            byte section = bytes[i];
            for (int bit = 0; bit < 8; bit++) {
                int pixel = (i * 8) + bit;
                int pixelX = pixel % size;
                int pixelY = (pixel - pixelX) / size;
                if (pixelY < size) {
                    bufferedImage.setRGB(pixelX, pixelY, ((section >> (7 - bit) & 1) == 1 ? Color.white : Color.black).getRGB());
                }
            }
        }

        for (int bits = bytes.length * 8; bits < size * size; bits++) {
            int pixelX = bits % size;
            int pixelY = (bits - pixelX) / size;
            bufferedImage.setRGB(pixelX, pixelY, (random.nextBoolean() ? Color.white : Color.black).getRGB());
        }


        File file = new File(new File(ROOT_DIRECTORY, "lostpen"), "lostpen.png");
        try {
            System.out.print("Writing: ");
            ImageIO.write(bufferedImage, "png", file);
            System.out.println("Done");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static byte[] readData() {
        try {
            File bin = new File(new File(ROOT_DIRECTORY, "binary"), "binary");
            String string = new String(Files.readAllBytes(bin.toPath()));
            return string.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }
}
