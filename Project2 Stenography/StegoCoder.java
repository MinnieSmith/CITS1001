
/**
 * Write a description of class StegoCoder here.
 *
 * @author (Minh Trang Smith)
 * @version (October 2019)
 */

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
        try {
            cover.clearLowBit();
            secret.setZeroOne();
            cover.mergeImage(secret);
        }
        catch (IllegalArgumentException e) {
            System.out.println("IllegalArgumentException");
        }
        return cover;
    }

    /**
     * Return a new message by hiding a secret in the cover image
     */
    public static StegoImage decrypt(StegoImage message)
    {
        try {
            message.setLowBit();
            message.setBlackWhite();
        }
        catch (IllegalArgumentException e) {
            System.out.println("IllegalArgumentException");
        }    
        return message;

    }
}
