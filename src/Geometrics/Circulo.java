package Geometrics;

import OOPS.Fruit;

import java.awt.*;
import java.awt.image.BufferedImage;
import Graphics.*;
/**
 * Class that defines a circle with a point and it's radius.
 *  @author Jose Ferras, Miguel Silva, Brandon Mejia
 * @version v0.0    16/04/2024
 * @inv radius > 0
 * @inv x-radius > 0 && y-radius > 0
 */
public class Circulo implements FiguraGeometrica, Fruit {
    private Ponto c;
    private double radius;
    private double angle = 0;

    /**
     * Constructor
     */
    public Circulo(Ponto c, double r){
        if(!check(c,r)){
            System.out.println("Circulo:iv");
            System.exit(0);
        }
        this.c = new Ponto(c.x(), c.y());
        this.radius = r;
    }

    /**
     * Check method
     */
    private static boolean check(Ponto c, double r){
        if(r <= 0 || (c.x()-r) < 0 || (c.y()-r) < 0){
            return false;
        }
        return true;
    }


    /**
     * Getter of c
     * @return c
     */
    public double angle()
    {
        return angle;
    }

    /**
     * Setter of c
     * @param angle
     */
    public void setAngle(double angle)
    {
        this.angle = angle;
    }

    /**
     * Basically a getter of c
     * @return c
     */
    @Override
    public Ponto findCentroid() {
        return new Ponto(this.c.x(), this.c.y());
    }

    /**
     * Getter of radius
     * @return radius
     */
    public double radius(){
        return this.radius;
    }

    /**
     * Finds Dmin and Dmax from the center of the circle to all the points in that poligono
     * AUXILIARY FUNCTION FOR isContainedIn AND intersects methods
     */
    private Double[] findDminAndDmax(Poligono that){
        double Dmin = Double.MAX_VALUE;
        double Dmax = -1;
        double cDist;
        for(Ponto p : that.pontos()){
            cDist = this.c.dist(p);
            if(cDist < Dmin){
                Dmin = cDist;
            }
            if(cDist > Dmax){
                Dmax = cDist;
            }
        }
        return new Double[]{Dmin, Dmax};
    }

    /**
     * Verifies if the Geometrics.Circulo is contained in a Geometrics.Poligono
     * @param that Geometrics.Poligono to verify if it is contained
     * @return true if the Geometrics.Circulo is contained in the Geometrics.Poligono, false otherwise
     * @see "https://stackoverflow.com/questions/25701346/how-to-check-if-a-circle-lies-inside-of-convex-polygon"
     */
    @Override
    public boolean isContainedIn(Poligono that) {
        //if the center of the Circle isn't contained in the Poligono then the circle isn't either
        if(!this.c.isContainedIn(that)){
            return false;
        }
        Double[] dminmax = this.findDminAndDmax(that);
        //if radius < dmin then it is contained in the Poligono
        return this.radius < dminmax[0];
    }

    /**
     * Computes the perimeter of the circle
     * @return perimeter
     */
    public double perimeter(){
        return 2*Math.PI*this.radius;
    }

    /**
     * Verifies if this Geometrics.Circulo intersects with that Geometrics.Poligono
     * @param that Geometrics.Poligono
     * @return true if they do, false if not
     */
    @Override
    public boolean intersects(Poligono that) {
        Double[] dminmax = this.findDminAndDmax(that);
        return this.radius > dminmax[0] && this.radius < dminmax[1];
    }

    /**
     * Responds negatively to the question if this Geometrics.Circulo is a Geometrics.Quadrado
     * @return
     */
    @Override
    public boolean isSquare() {
        return false;
    }

    /**
     * Verifies if this Geometrics.Circulo intersects with that Geometrics.Circulo
     * THIS ALSO VERIFIES IF THIS CIRCULO IS CONTAINED IN THAT CIRCULO
     * @param that Geometrics.Circulo
     * @return true if they do, false if not
     * @see "https://www.bbc.co.uk/bitesize/guides/z9pssbk/revision/4"
     */
    @Override
    public boolean intersects(Circulo that) {
        double d = this.c.dist(that.c);
        return d < this.radius + that.radius;
    }

    /**
     * Translates the Circulo +x and +y coordinates
     */
    public Circulo translate(double x, double y){
        return this.translate(new Ponto(this.c.x() + x, this.c.y() + y));
    }

    /**
     * Translates the Circulo to the new center c
     */
    public Circulo translate(Ponto c) {
        return new Circulo(new Ponto(c.x(), c.y()), this.radius);
    }

    @Override
    public String toString(){
        return "Circulo: center:" + this.c + "radius: " + this.radius;
    }

    /**
     * Draw method to help represent a fruit in the GUI
     * @param g Graphics2D
     * @param img BufferedImage
     * @param maxY need this value because Y are the inverse of what they will be represented as
     */
    public void draw(Graphics2D g, BufferedImage img,int maxY,double angle)
    {
        //g.drawImage(img,(int)this.c.x()-img.getWidth()/2,maxY - (int)this.c.y()-img.getHeight()/2,null);
        Assets.drawImage(g,img,(int)this.c.x()-img.getWidth()/2,maxY - (int)this.c.y()-img.getHeight()/2,angle);

    }

    /**
     * Draw method to help represent a fruit in the GUI
     * @param g Graphics2D
     * @param maxY need this value because Y are the inverse of what they will be represented as
     */
    @Override
    public void draw(Graphics2D g, int maxY)
    {
        int diameter = (int)(this.radius*2);
        g.fillOval((int) (this.c.x()-this.radius), maxY - (int) (this.c.y()+this.radius), diameter, diameter);
    }
}