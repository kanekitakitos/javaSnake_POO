package Geometrics;

import java.util.Arrays;

import static java.lang.Math.round;

/**
 * Class that defines rectangle.
 *  @author Jose Ferras, Miguel Silva, Brandon Mejia
 * @version v0.0    11/03/2024
 * @inv each line segment formed by the array of points has to have one other line segment to which it is parallel with
 */
public class Retangulo extends Poligono{

    /**
     * Constructor
     * @param p array of points
     */
    public Retangulo(Ponto[] p) {
        super(check(p));
    }

    /**
     * Constructor that is called with a string with the points
     * @param pontos string with the points that will define the rectangle
     */
    public Retangulo(String pontos){
        this(stringToPontos(pontos));
    }

    /**
     * Constructor that creates a rectangle with the centroid x and y and the width and height
     * @param x centroid x
     * @param y centroid y
     * @param width width
     * @param height height
     */
    public Retangulo(double x, double y, double width, double height){ //x y é o centroide
        super(new Ponto[]{new Ponto(x - width/2, y - height/2), new Ponto(x + width/2, y - height/2), new Ponto(x + width/2, y + height/2), new Ponto(x - width/2, y + height/2)});
    }

    /**
     * Tests if the points are elegible to form a rectangle
     * @param p array of points
     * @return array of points to call the super constructor with
     */
    private static Ponto[] check(Ponto[] p){

        if(p.length != 4){
            System.out.println("Geometrics.Retangulo:vi");
            System.exit(0);
        }
        else if(!checkParallelism(p)){
            System.out.println("Geometrics.Retangulo:vi");
            System.exit(0);
        }
        return p;
    }

    /**
     * Tests if for each line segment of the four there is one of the others that is parallel with the other
     * @param p array of points
     * @return true if they are parallel false if not
     */
    private static boolean checkParallelism(Ponto p[]){
        Reta ab = new Reta(p[0], p[1]);
        Reta bc = new Reta(p[1], p[2]);
        Reta cd = new Reta(p[2], p[3]);
        Reta da = new Reta(p[3], p[0]);
        return ab.isParallel(cd) && bc.isParallel(da);
    }

    /**
     * Rotates the rectangle "angle" degrees clockwise about the point c
     * @return rotated Geometrics.Retangulo
     */
    public Retangulo rotate(double angle, Ponto c){
        return new Retangulo(this.rotatePoints(angle, c));
    }

    /**
     * Rotates the rectangle "angle" degrees clockwise about the centroid
     * @return rotated Geometrics.Retangulo
     */
    public Retangulo rotate(double angle){
        return rotate(angle, this.findCentroid());
    }

    /**
     * Translates the rectangle x and y coordinates
     * @return translated rectangle
     */
    public Retangulo translate(double x, double y){
        return new Retangulo(translatePoints(x,y));
    }

    /**
     * Translates the rectangle making it's new centroid point c
     * @param c new centroid
     * @return translated rectangle
     */
    public Retangulo translate(Ponto c){
        double[] coords = findCentroidDifference(c);
        return this.translate(coords[0], coords[1]);
    }

    /**
     * Returns the envolving rectangle of that Poligono
     * @param that Poligono
     * @return envolving rectangle
     */
    public static Retangulo envolvingRectangle(Poligono that){
        double minX = Double.MAX_VALUE;
        double maxX = -1;
        double minY = Double.MAX_VALUE;
        double maxY = -1;
        for(Ponto p : that.pontos()){
            if(p.x() < minX){
                minX = p.x();
            }
            if(p.x() > maxX){
                maxX = p.x();
            }
            if(p.y() < minY){
                minY = p.y();
            }
            if(p.y() > maxY){
                maxY = p.y();
            }
        }
        Ponto p0 = new Ponto(minX, minY);
        Ponto p1 = new Ponto(maxX, minY);
        Ponto p2 = new Ponto(maxX, maxY);
        Ponto p3 = new Ponto(minX, maxY);
        return new Retangulo(new Ponto[]{p0,p1,p2,p3});
    }

    /**
     * Metodo toString
     * @return  transforma o Geometrics.Retangulo num formato de string
     */
    public String toString(){
        return "Geometrics.Retangulo: " + Arrays.asList(this.pontos());
    }
}
