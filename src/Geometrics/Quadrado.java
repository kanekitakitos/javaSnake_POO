package Geometrics;

import Graphics.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import OOPS.Fruit;

/**
 * Class that defines a Square.
 *  @author Jose Ferras, Miguel Silva, Brandon Mejia
 * @version v0.0    11/03/2024
 * @inv all the sides of the square have to be equal
 */
public class Quadrado extends Retangulo implements Fruit {

    private double angle = 0;

    /**
     * Constructor
     * @param p array of points
     */
    public Quadrado(Ponto[] p) {
        super(check(p));
    }

    /**
     * Constructor that is called with a string with the points
     * @param pontos string with the points that will define the square
     */
    public Quadrado(String pontos){
        this(stringToPontos(pontos));
    }

    /**
     * Constructor that creates a square with the centroid x and y and the cellLength
     * @param x centroid x
     * @param y centroid y
     * @param cellLength length of each cell
     */
    public Quadrado(double x, double y, int cellLength) { //xy is the centroid
        //se x e y é para o centroide, o numero deve ser multiplicado e dividido por 2 para obter o pont
        super(new Ponto[]{new Ponto(x - cellLength/2, y - cellLength/2), new Ponto(x + cellLength/2, y - cellLength/2),  new Ponto(x + cellLength/2, y + cellLength/2),new Ponto(x - cellLength/2, y + cellLength/2)});
    }

    /**
     * Tests if the points are elegible to form a square
     * @param p array of points
     * @return array of points to call the super constructor with
     */
    private static Ponto[] check(Ponto[] p){
        if(!haveSameDistance(p)){
            System.out.println("Geometrics.Quadrado:vi");
            System.exit(0);
        }
        return p;
    }

    /**
     * Verifies if the array of points all have the same difference from each other (in sequence)
     * @param p array of points
     * @return true if they all have the same distance, false if not
     */
    private static boolean haveSameDistance(Ponto p[]){
        double ab = p[0].dist(p[1]);
        double bc = p[1].dist(p[2]);
        double cd = p[2].dist(p[3]);
        double da = p[3].dist(p[0]);
        return (ab == bc && ab == cd && ab == da);
    }

    /**
     * Rotates the square "angle" degrees clockwise about the point c
     * @return rotated Geometrics.Quadrado
     */
    public Quadrado rotate(double angle, Ponto c){
        return new Quadrado(this.rotatePoints(angle, c));
    }

    /**
     * Rotates the square "angle" degrees clockwise about the centroid
     * @return rotated Geometrics.Quadrado
     */
    public Quadrado rotate(double angle){
        return rotate(angle, this.findCentroid());
    }

    /**
     * Translates the square x and y coordinates
     * @return translated square
     */
    public Quadrado translate(double x, double y){
        return new Quadrado(translatePoints(x,y));
    }

    /**
     * Translates the square making it's new centroid point c
     * @return translated square
     */
    public Quadrado translate(Ponto c){
        double[] coords = findCentroidDifference(c);
        return this.translate(coords[0], coords[1]);
    }

    /**
     * Getter of the top left point of the square
     * @return top left point
     */
    public Ponto topLeftPoint() {
        //ver os pontos todos e ver qual é o topLeft
        Ponto[] pontos = this.pontos();
        Ponto topLeft = pontos[0];
        for (Ponto p : pontos) {
            if (p.x() <= topLeft.x() && p.y() >= topLeft.y()) {
                topLeft = p;
            }
        }
        return topLeft;
    }


    /**
     * Metodo toString
     * @return  transforma o Geometrics.Quadrado num formato de string
     */
    public String toString()
    {
        return "Geometrics.Quadrado: " + Arrays.asList(this.pontos());
    }


    @Override
    public boolean isSquare()
    {
        return true;
    }


    /**
     * Metodo que desenha o quadrado
     * @param g Graphics2D
     */
	@Override
	public void draw(Graphics2D g, BufferedImage img,int maxY, double angle)
    {
        Ponto p = findCentroid();
        //g.drawImage(img,(int)p.x()- img.getWidth()/2, maxY - (int)p.y()-img.getHeight()/2,null);
        int x = (int)p.x()- img.getWidth()/2;
        int y = maxY - (int)p.y()-img.getHeight()/2;
        Assets.drawImage(g,img, x , y ,angle);

	}



}
