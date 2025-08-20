package Geometrics;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Interface that defines a geometric figure.
 * @author Jose Ferras, Miguel Silva, Brandon Mejia
 * @version v0.0    16/04/2024
 */
public interface FiguraGeometrica
{
    /**
     * Method that finds the centroid of the figure
     * @return centroid
     */
    public Ponto findCentroid();

    /**
     * Method that finds the perimeter of the figure
     * @return perimeter
     */
    public double perimeter();

    /**
     * Method that checks if the Polygon intersects with another Polygon
     * @return true if they intersect, false if not
     */
    public boolean intersects(Poligono that);

    /**
     * Method that checks if the Polygon intersects with a Circle
     * @return true if they intersect, false if not
     */
    public boolean intersects(Circulo that);

    /**
     * Draw method to help represent a geometric figure in the GUI
     * @param g Graphics2D
     * @param maxY need this value because Y are the inverse of what they will be represented as
     */
    public void draw(Graphics2D g, int maxY);

}