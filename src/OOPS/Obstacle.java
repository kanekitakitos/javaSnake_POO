package OOPS;

import Geometrics.*;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Class that defines an obstacle.
 * @author Jose Diogo Ferras, Miguel Silva, Brandon Mejia
 * @version v0.0    13/04/2024
 */
public class Obstacle {

    private Poligono p;
    private Ponto c;
    private double angle;

    /**
     * Constructor of an obstacle that will rotate around Point c
     * @param p Geometrics.Poligono
     * @param c Geometrics.Ponto
     * @param angle angle
     */
    public Obstacle(Poligono p, Ponto c, double angle)
    {
        this.p = new Poligono(p.pontos());
        this.c = new Ponto(c.x(), c.y());
        this.angle = angle;
    }

    /**
     * Constructor of an obstacle that will rotate around it's own centroid
     * @param p Geometrics.Poligono
     * @param angle angle
     */
    public Obstacle(Poligono p, double angle)
    {
        this.p = new Poligono(p.pontos());
        this.c = this.p.findCentroid();
        this.angle = angle;
    }

    /**
     * Constructor of a static object
     * @param p Geometrics.Poligono
     */
    public Obstacle(Poligono p)
    {
        this.p = new Poligono(p.pontos());
        this.c = this.p.findCentroid();
        this.angle = 0;
    }

    /**
     * Creates a set of obstacles that are predefined
     * @param i - index that defines which predefined set to return
     * @param h - horizontal cells
     * @param v - vertical cells
     * @param cellLength - length of each cell
     * @return obstacles for the desired map size
     */
    public static Obstacle[] getDefaultSet(int i, int h, int v, int cellLength) {
        int maxX = h * cellLength;
        int maxY = v * cellLength;

        //Mapa 0
        if(i == 0){
            Triangulo t = new Triangulo(maxX/4, maxY/4, 44);
            Obstacle o = new Obstacle(t,new Ponto(maxX /2, maxY /2), 10);

            Triangulo t1 = new Triangulo(maxX-maxX/4, maxX-maxX/4, 44);
            Obstacle o1 = new Obstacle(t1,new Ponto(maxX /2, maxY /2), 10);
            return new Obstacle[]{o,o1};
        }
        int unit = Math.min(maxX,maxY)/8;
        //Mapa 1
        if (i == 1){
            Poligono p = new Poligono(new Ponto[]{new Ponto((1.5)*unit,2*unit),new Ponto(2*unit,2*unit),new Ponto(2*unit,unit),new Ponto(4*unit,3*unit),new Ponto(3*unit,3*unit),new Ponto(3*unit,(3.5)*unit),new Ponto(2*unit,3*unit)});
            Poligono p2 = p.translate(new Ponto( maxX /3,maxY-4*unit));
            Obstacle o2 = new Obstacle(p2,new Ponto(maxX-2*unit,maxY-2*unit),7);
            return new Obstacle[]{o2};
        }
        unit = Math.min(maxX,maxY)/20;
        if(i == 2){
            Retangulo r = new Retangulo(maxX/4, maxY/2, unit, unit/2);
            Retangulo rr = new Retangulo(maxX/4, maxY/2, unit+unit/3, unit/2);
            Obstacle o4 = new Obstacle(r, 7);
            Obstacle o3 = new Obstacle(rr, -14);

            Retangulo r1 = new Retangulo(3*maxX/4, maxY/2, unit, unit/2);
            Retangulo rr1 = new Retangulo(3*maxX/4, maxY/2, unit+unit/3, unit/2);
            Obstacle o5 = new Obstacle(r1, -7);
            Obstacle o6 = new Obstacle(rr1, 14);
            return new Obstacle[]{o3,o4,o5,o6};
        }
        if(i == 3){
            Triangulo t2 = new Triangulo((maxX/2), (maxY/2), (int) (Math.min(maxX,maxY)/(5)));
            Triangulo t3 = new Triangulo((maxX/2), (maxY/2), (int) (Math.min(maxX,maxY)/(5)));
            Obstacle o7 = new Obstacle(t2,new Ponto(maxX/2,maxY/2),-10); //centro de rotação em (maxX/2,maxY/2)
            Obstacle o8 = new Obstacle(t3,new Ponto(maxX/2,maxY/2),13); //centro de rotação em (maxX/2,maxY/2)
            return new Obstacle[]{o7,o8};
        }
        return new Obstacle[0];
    }

    /**
     * getter of p
     * @return this.p
     */
    public Poligono p()
    {
        return new Poligono(this.p.pontos());
    }

    /**
     * getter of c
     * @return this.c
     */
    public Ponto c()
    {
        return new Ponto(this.c.x(), this.c.y());
    }

    /**
     * getter of angle
     * @return this.angle
     */
    public double angle()
    {
        return this.angle;
    }

    /**
     * Updates the obstacle
     */
    public void update()
    {
        if(this.angle != 0){
            this.p = this.p.rotate(this.angle, this.c);
        }
    }

    /**
     * Draw method to help represent an obstacle in the GUI
     * @param g Graphics2D
     * @param maxY need this value because Y are the inverse of what they will be represented as
     */
    public void draw(Graphics2D g, int maxY){
        this.p.draw(g, maxY);
    }


}
