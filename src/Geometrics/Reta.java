package Geometrics;

import static java.lang.Double.NEGATIVE_INFINITY;
import static java.lang.Double.POSITIVE_INFINITY;

/**
 *  Class that defines a line, with 2 points.
 *  @author Jose Ferras, Miguel Silva, Brandon Mejia
 *  @version v0.0   18/02/2024
 *  @inv Lines can't be formed with two points that have the same coordinates
 */
public class Reta
{
    private Ponto a;
    private Ponto b;

    /**
     * Constructor
     * @param a first point
     * @param b second point
     */
    public Reta(Ponto a, Ponto b){
        check(a,b);
        this.a = new Ponto(a.x(), a.y());
        this.b = new Ponto(b.x(), b.y());
    }

    /**
     * Verifica se dois pontos sao iguais
     * @param a Ponto a
     * @param b Ponto b
     *
     */
    public static void check(Ponto a, Ponto b)
    {
        if(a.equals(b)) {
            System.out.println("Reta:vi");
            System.exit(0);
        };
    }

    /**
     * Getter of point a
     * @return point a
     */
    public Ponto a(){
        return this.a;
    }

    /**
     * Getter of point b
     * @return point b
     */
    public Ponto b(){
        return this.b;
    }

    /**
     * Computes the slope of the Geometrics.Reta
     * @return slope
     */
    public double slope()
    {
        double m = ((this.b.y() - this.a.y()) /(this.b.x() - this.a.x()));
        if(m == -0)
            m = 0;
        if (m == Float.NEGATIVE_INFINITY)
            m = Float.POSITIVE_INFINITY;
        return m;
    }

    /**
     * Verifies if the third point c is coliear with the line the method is called with
     * @param c third point
     * @return true if point c is colinear with the line, false if not
     */
    public boolean belongs(Ponto c)
    {
        double abm =  this.slope();
        Reta bc = new Reta(this.b(), c);
        double bcm =  bc.slope();
        if(abm == -0.0){
            abm = 0.0;
        }
        else if(abm == NEGATIVE_INFINITY){
            abm = POSITIVE_INFINITY;
        }
        if(bcm == -0.0){
            bcm = 0.0;
        }
        else if(bcm == NEGATIVE_INFINITY){
            bcm = POSITIVE_INFINITY;
        }
        return abm == bcm;
    }

    /**
     * Verifies if this Geometrics.Reta and that Geometrics.Reta are parallel
     * @param that second Geometrics.Reta
     * @return true if Retas are parallel, false if not
     */
    public boolean isParallel(Reta that){
        double abm = this.slope();
        if(abm == -0.0){
            abm = 0.0;
        }
        else if(abm == NEGATIVE_INFINITY){
            abm = POSITIVE_INFINITY;
        }
        double cdm = that.slope();
        if(cdm == -0.0){
            cdm = 0.0;
        }
        else if(cdm == NEGATIVE_INFINITY){
            cdm = POSITIVE_INFINITY;
        }
        return abm == cdm;
    }
}
