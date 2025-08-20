package Graphics;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Class used to define and manage the assets
 * @version v0.0    19/05/2024
 * @author Jose Diogo Ferras, Miguel Silva, Brandon Mejia
 */
public class Assets
{

	public static ImageIcon icon = new ImageIcon(Objects.requireNonNull(Assets.class.getClassLoader().getResource("Graphics" + File.separator + "Image" + File.separator + "icon.png")));;
	public static BufferedImage snakeHead, snakeBody,snakeTail;
	public static ArrayList<BufferedImage> fruit = new ArrayList<>(), background = new ArrayList<>();
	public static Clip music, eat, gameOver, win;


	/**
	 * Constructor
	 */
	public Assets()
	{
		init();
	}

	/**
	 * Load all the assets
	 */
	public static void init()
	{
		int backgroundPhotos = 2 ;
		int fruitPhotos = 6;
		for (int j = 0; j < fruitPhotos ; j++)
		{
			 fruit.add(Loader.ImageLoader("src"+ File.separator+"Graphics"+ File.separator+"Image"+ File.separator
					+ File.separator + "food"+j+".png"));

			 if(j < backgroundPhotos)
			background.add(Loader.ImageLoader("src"+ File.separator+"Graphics"+ File.separator+"Image"+ File.separator+"background"+j+".jpg"));
		}

			// Load images
			snakeHead = Loader.ImageLoader("src"+ File.separator+"Graphics"+ File.separator+"Image"+ File.separator+"HEAD.png");
			snakeTail = Loader.ImageLoader("src"+ File.separator+"Graphics"+ File.separator+"Image"+ File.separator+"Tail.png");
			snakeBody = Loader.ImageLoader("src"+ File.separator+"Graphics"+ File.separator+"Image"+ File.separator+"BODY.png");


			// Load audio
			music = Loader.audioLoader("src"+ File.separator+"Graphics"+ File.separator+"rec"+ File.separator+"music.wav");
			eat = Loader.audioLoader("src"+ File.separator+"Graphics"+ File.separator+"rec"+ File.separator+"eat.wav");
	}

	/**
	 * Resize an image
	 * @param img the image to resize
	 * @param newWidth the new width of the image (number of pixels)
	 * @param newHeight the new height of the image (number of pixels)
	 * @return the resized image
	 */
	public static BufferedImage resizeImage(BufferedImage img,int newWidth, int newHeight)
	{
		BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = resizedImage.createGraphics();
			g.drawImage(img, 0, 0, newWidth, newHeight, null);
			g.dispose();
		return resizedImage;
	}

	/**
	 * Resize all the assets
	 * @param size the new size of the assets (number of pixels)
	 */
	public static void resizeAssets(int size)
	{
		snakeHead = resizeImage(snakeHead, size,size);
		snakeBody = resizeImage(snakeBody, size,(int)(size*0.9));
		snakeTail = resizeImage(snakeTail, size,size);
		fruit.replaceAll(img -> resizeImage(img, size / 2, size / 2));
	}

	/**
	 * Resizes the background
	 * @param width width
	 * @param height height
	 */
	public static void resizeBackground(int width, int height)
	{
		background.replaceAll(img -> resizeImage(img, width, height));
	}

	/**
	 * Draws the image
	 * @param g graphics2D
	 * @param img BufferedImage
	 * @param x coordinate
	 * @param y coordinate
	 * @param angle angle
	 */
	public static void drawImage(Graphics2D g, BufferedImage img, int x, int y,double angle)
	{
		AffineTransform at = AffineTransform.getTranslateInstance(x, y);
		at.rotate(Math.toRadians(angle), (double) img.getWidth()/2, (double) img.getHeight()/2);
		g.drawImage(img, at, null);
	}

}
