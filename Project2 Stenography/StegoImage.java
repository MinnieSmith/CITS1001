/**
 * Use to manipulate images for stenography
 *
 * @author (Minh Trang Smith)
 * @version (October 2019)
 */
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Graphics2D;

public class StegoImage
{
    private BufferedImage image;
    private int width;
    private int height;
    private File file;

    /**
     * Constructor for objects of class StegoImage
     */
    public StegoImage(String filename)
    {
        try{
            file = new File(filename);
            image = ImageIO.read(file);
        } catch(IOException e) {
            System.out.println("Error: "+e);
        }

        width = getWidth(image);
        height = getHeight(image);

    }

    /**
     * Returns the image field value
     */
    public BufferedImage getImage()
    {
        return image;
    }

    /**
     * Returns the width of the image
     */
    public int getWidth(BufferedImage image)
    {
        return image.getWidth();
    }

    /**
     * Returns the height of the image
     */
    public int getHeight(BufferedImage image)
    {
        return image.getHeight();
    }

    /**
     * Extension: resize image
     */
    public void resizeImage(int newWidth, int newHeight)
    {
        try {
            BufferedImage outImage = new BufferedImage(newWidth, newHeight, image.getType());
            Graphics2D graphics = outImage.createGraphics();
            graphics.drawImage(image, 0, 0, newWidth, newHeight, null);
            graphics.dispose();
            image = outImage;
            width = newWidth;
            height = newHeight;
        }
        catch (IllegalArgumentException e) {
            System.out.println("IllegalArgumentException");
        }
    }
    
    /**
     * Modify image by changing every pixel by scaling RGB components 
     */
    public void scaleImage(int div, int mult)
    {
        try {
            for(int x=0; x<width; ++x)
            {
                for(int y =0; y<height; ++y)
                {
                    Color c = new Color(image.getRGB(x,y));
                    int r = c.getRed();
                    int g = c.getGreen();
                    int b = c.getBlue();
    
                    int scaled_r = (r/div) * mult;
                    int scaled_g = (g/div) * mult;
                    int scaled_b = (b/div) * mult;
    
                    Color c_scaled = new Color(scaled_r, scaled_g, scaled_b);
                    int p = c_scaled.getRGB();
    
                    image.setRGB(x, y, p);
                }
            }
        }
        catch (IllegalArgumentException e) {
            System.out.println("IllegalArgumentException");
        }
    }

    /**
     * Modify image by setting the lowest bit of each pixel colour to 0
     * quantizing coverQ
     */
    public void clearLowBit()
    {
        for(int x=0; x<width; ++x)
        {
            for(int y =0; y<height; ++y)
            {
                int p = image.getRGB(x,y);
                Color c = new Color(image.getRGB(x,y));
                int r = c.getRed();
                int g = c.getGreen();
                int b = c.getBlue();

                if (r % 2 == 1)
                {
                    r = r - 1;
                }
                if (g % 2 == 1)
                {
                    g = g - 1;
                }

                if (b % 2 == 1)
                {
                    b = b - 1;
                }

                Color c_lower = new Color(r, g, b);

                p = c_lower.getRGB();

                image.setRGB(x, y, p);

            }
        }

    }

    /**
     * Modify image by thresholding its pixels to 0 or 1 in each colour pixel
     * reducing image SecretZ
     */
    public void setZeroOne()
    {
        scaleImage(128, 1);
    }

    /**
     * Modify image by upscalling each 0,1 pixel to a greyscale for viewing
     */
    public void setBlackWhite()
    {
        scaleImage(1,128);       
    }

    /**
     * Modify image by setting each pixel to just its low bit value (0 or 1)
     */
    public void setLowBit()
    {
        for(int x=0; x<width; ++x)
        {
            for(int y =0; y<height; ++y)
            {
                Color c = new Color(image.getRGB(x,y));
                int r = c.getRed();
                int g = c.getGreen();
                int b = c.getBlue();

                r = r % 2;
                g = g % 2;
                b = b % 2;

                Color c_lowbit = new Color(r, g, b);

                int p = c_lowbit.getRGB();

                image.setRGB(x, y, p);

            }
        }
    }

    /**
     * Modify this image to become this + newimage by adding each RGB pixels
     */
    public void mergeImage(StegoImage newimage)
    {
        if(newimage.getImage().getWidth() != width || newimage.getImage().getHeight() != height)
        {
            System.out.println("Incompatible image sizes, please resize images");
        }
        
        try {
            for(int x=0; x<width; ++x)
            {
                for(int y =0; y<height; ++y)
                {
                    Color c = new Color(image.getRGB(x,y));
                    int r = c.getRed();
                    int g = c.getGreen();
                    int b = c.getBlue();
    
                    Color nc = new Color(newimage.getImage().getRGB(x,y));
                    int nr = nc.getRed();
                    int ng = nc.getGreen();
                    int nb = nc.getBlue();
    
                    r = r + nr;
                    g = g + ng;
                    b = b + nb;
    
                    Color c_merged = new Color(r, g, b);
    
                    int p = c_merged.getRGB();
    
                    image.setRGB(x, y, p);
                }
            }
        }
        catch (IllegalArgumentException e) {
            System.out.println("IllegalArgumentException");
        }
    }

    /**
     * Extension: save StegoImage to an image file
     */
    public void saveImage(String filename, String filetype)
    {
        try
        {
            File outfile = new File(filename);
            ImageIO.write(image, filetype, outfile);
            System.out.println("File writing complete");
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
    }
}
