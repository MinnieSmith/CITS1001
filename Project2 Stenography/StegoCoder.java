
/**
 * Write a description of class StegoCoder here.
 *
 * @author (Minh Trang Smith)
 * @version (October 2019)
 */
import java.awt.image.BufferedImage;

public final class StegoCoder
{
    /**
     * Constructor for objects of class StegoCoder
     */
    public StegoCoder()
    {
        ;
    }

    /**
     * Return a new message by hiding a secret in the cover image
     */
    public static StegoImage encrypt(StegoImage cover, StegoImage secret)
    {
        cover.clearLowBit();
        secret.setZeroOne();
        cover.mergeImage(secret);
        return cover;
        
    }
    
    /**
     * Return a new message by hiding a secret in the cover image
     */
    public static StegoImage decrypt(StegoImage message)
    {
        message.setLowBit();
        message.setBlackWhite();
        return message;
        
    }
}
