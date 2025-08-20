package Geometrics;

import java.util.Arrays;

/**
 * Class that defines a Triangle.
 *  @author Jose Ferras, Miguel Silva, Brandon Mejia
 * @version v0.0    11/03/2024
 * @inv has to be formed by three points
 */
public class Triangulo extends Poligono{

    /**
     * Constructor
     * @param p array of points that will form the triangle
     */
    public Triangulo(Ponto[] p) {
        super(p);
    }

    /**
     * Constructor that is called with a string with the points
     * @param pontos string with the points that will define the triangle
     */
    public Triangulo(String pontos){
        this(stringToPontos(pontos));
    }

    /**
     * Constructor that creates a triangle with the centroid x and y and the cellLength
     * @param centroX centroid x
     * @param centroY centroid y
     * @param cellLength length of each cell
     */
    public Triangulo(int centroX, int centroY, int cellLength){
        this(new Ponto[]{new Ponto(centroX, centroY - cellLength/2), new Ponto(centroX - cellLength/2, centroY + cellLength/2), new Ponto(centroX + cellLength/2, centroY + cellLength/2)});
    }

    /**
     * Tests if the points are elegible to form a triangle
     * @param p array of points
     * @return array of points to call the super constructor with
     */
    private static Ponto[] trianguloTests(Ponto[] p){
        if(p.length != 3){
            System.out.println("Geometrics.Triangulo:vi");
            System.exit(0);
        }
        return p;
    }

    /**
     * Rotates the triangle "angle" degrees clockwise about the point c
     * @return rotated Geometrics.Triangulo
     */
    public Triangulo rotate(double angle, Ponto c){
        return new Triangulo(rotatePoints(angle, c));
    }

    /**
     * Rotates the triangle "angle" degrees clockwise about the centroid
     * @return rotated Geometrics.Triangulo
     */
    public Triangulo rotate(double angle){
        return rotate(angle, this.findCentroid());
    }

    /**
     * Translates the triangle x and y coordinates
     * @return translated triangle
     */
    public Triangulo translate(double x, double y){
        return new Triangulo(translatePoints(x,y));
    }

    /**
     * Translates the triangle making it's new centroid point c
     * @return translated triangle
     */
    public Triangulo translate(Ponto c){
        double[] coords = findCentroidDifference(c);
        return this.translate(coords[0], coords[1]);
    }

    /**
     * Metodo toString
     * @return  transforma o Geometrics.Triangulo num formato de string
     */
    public String toString(){
        return "Geometrics.Triangulo: " + Arrays.asList(this.pontos());
    }
}
