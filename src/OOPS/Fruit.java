package OOPS;

import Geometrics.Poligono;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Interface that defines a fruit.
 * @author Jose Diogo Ferras, Miguel Silva, Brandon Correia
 * @version v0.0    13/04/2024
 */
public interface Fruit { //quadrado ou circulo.

    /**
     * Verifies if the fruit is contained in a Geometrics.Poligono
     * @param that Geometrics.Poligono to verify if it is contained
     * @return true if the fruit is contained in the Geometrics.Poligono, false otherwise
     */
    public boolean isContainedIn(Poligono that);

    /**
     * Verifies if the fruit intersects with a Geometrics.Poligono
     * @param that Geometrics.Poligono
     * @return true if the fruit intersects with the Geometrics.Poligono, false if not
     */
    public boolean intersects(Poligono that);

    /**
     * Verifies if the fruit is squared
     * @return true if the fruit is squared, false if not
     */
    boolean isSquare();

    /**
     * Draw method to help represent a fruit in the GUI
     * @param g Graphics2D
     * @param img BufferedImage
     */
    public void draw(Graphics2D g, BufferedImage img, int maxY, double angle);

    public void draw(Graphics2D g, int maxY);
}
