
/**
 * Write a description of class StegoViewer here.
 *
 * @author (Minh Trang Smith)
 * @version (October 2019)
 */
import java.awt.Color;
import java.awt.image.BufferedImage;

public class StegoViewer
{
    private SimpleCanvas sc;
    public static int IMG_WIDTH = 300;
    public static int IMG_HEIGHT = 300;
    public static int CAPTION_MARGIN = 50;
    public static int NUM_IMAGES = 4;
    private final static Color BG_COLOUR = Color.white;
    private final static Color TEXT_COLOUR = Color.black;

    public StegoViewer()
    {
        sc = new SimpleCanvas("Steganography Viewer", IMG_WIDTH * NUM_IMAGES, IMG_HEIGHT + CAPTION_MARGIN, BG_COLOUR);
       
    }

    public void displayImage(BufferedImage image, int window)
    {
        if(image.getWidth() > IMG_WIDTH || image.getHeight()> IMG_HEIGHT)
        {
            System.out.println("Please resize image");
        }
        
        try {
            sc.drawImage(image, (window)*IMG_WIDTH, 0);
        }
        catch (IllegalArgumentException e) {
            System.out.println("IllegalArgumentException");
        }
        if(window == 0)
        {
            sc.drawString("Cover", 100, 310, TEXT_COLOUR);
        }
        else if(window == 1)
        {
            sc.drawString("Secret", 400, 310, TEXT_COLOUR);
        }
        else if(window == 2)
        {
            sc.drawString("Encrypted Message", 700, 310, TEXT_COLOUR);
        }
        else if (window == 3)
        {
            sc.drawString("Decrypted Secret", 1000, 310, TEXT_COLOUR);
        }
        else
        {
            System.out.println("Invalid window number");
        }

    }

    
}
