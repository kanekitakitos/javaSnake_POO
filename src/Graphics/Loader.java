package Graphics;
import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Class used to help load images and audio
 * @version v0.0    19/05/2024
 * @author Jose Diogo Ferras, Miguel Silva, Brandon Mejia
 */
public class Loader
{
	/**
	 * Load an image from a file
	 * @param path the path to the image file
	 * @return the image loaded
	 */
	public static BufferedImage ImageLoader(String path)
	{
		try
		{
			return ImageIO.read(new File(path));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		return null;
	}


	/**
	 * Load an audio file
	 * @param path the path to the audio file
	 * @return the audio file loaded
	 */
	public static Clip audioLoader(String path)
	{
		try
		{
			AudioInputStream audio = AudioSystem.getAudioInputStream(new File(path));
			Clip clip = AudioSystem.getClip();
			clip.open(audio);
			return clip;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
			return null;
	}

}
