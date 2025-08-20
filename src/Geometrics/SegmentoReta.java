package Geometrics;

import static java.lang.Double.POSITIVE_INFINITY;

/** CLASSE SEGMENTO DE RETA
 *  representa um segmento de reta (cumpre as regras de um Segmento de Geometrics.Geometrics.Reta)
 *  e verifica se dois segmentos de reta se intersectam.
 * @author Jose Ferras, Miguel Silva, Brandon Mejia
 *  @version 1.0 22/02/2024
 *  @Inv
 * Classe Geometrics.Geometrics.Ponto --> representa um ponto no plano cartesiano X e Y
 *  check() --> verifica se dois pontos sao iguais
 *  intersect() --> verifica se dois segmentos de reta se intersectam
 *  directionOfPoints() --> calcula a orientacao de tres pontos
 *  Geometrics.Geometrics.SegmentoReta() --> construtor
 *
 */
public class SegmentoReta
{
    //segmento de reta é dado por 2 pts. (e cada ponto tem 2 coords)
    private Ponto a;
    private Ponto b;


    /**
     * Verifica se dois pontos sao iguais
     * @param a Ponto a
     * @param b Ponto b
     */
    public static void check(Ponto a, Ponto b)
    {
        if(a.equals(b)) {
            System.out.println("Reta:vi");
            System.exit(0);
        };
    }

    /**
     * Construtor para criar um segmentoReta
     * @param p1 ponto 1
     * @param p2 ponto 2
     */
    public SegmentoReta(Ponto p1, Ponto p2 )
    {
        check(p1, p2);
        a = new Ponto(p1.x(),p1.y());
        b = new Ponto(p2.x(),p2.y());
    }

//-------------------- METODOS PRINCIPAIS DO SEGMENTORETA ----------------------------------------------------------------------------------------

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
     * Verifies if these two line segments intersect with each other
     * @param that second line segment (first line segment is the one the method is called with)
     * @return true if two line segments intersect, false if they don't
     * @see "https://www.youtube.com/watch?v=bvlIYX9cgls"
     */
    public boolean intersects(SegmentoReta that)
    {
        Ponto A = this.a();
        Ponto B = this.b();
        Ponto C = that.a();
        Ponto D = that.b();
        double a = ((D.x() - C.x())*(C.y() - A.y())) - ((D.y() - C.y())*(C.x() - A.x()));
        double b = ((D.x() - C.x())*(B.y() - A.y())) - ((D.y() - C.y())*(B.x() - A.x()));
        double c = ((B.x() - A.x())*(C.y() - A.y())) - ((B.y() - A.y())*(C.x() - A.x()));
        //are collinear
        if(b == 0.0 && a == 0.0){
            return false;
        }
        //are parallel
        else if(b == 0.0){
            return false;
        }

        double alpha = a/b;
        double beta = c/b;

        if(alpha > 0 && alpha < 1 && beta > 0 && beta < 1){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Computes the legth of the Geometrics.SegmentoReta
     * @return length of Geometrics.SegmentoReta
     */
    public double lengthOfSegment()
    {
        return a.dist(b);
    }

    /**
     * Verifies if two line segments share one of the vertices
     * @param that second line segment (first line segment is the one the method is called with)
     * @return true if they share a vertex, false if not
     */
    public boolean sharesVertex(SegmentoReta that){
        if(this.a().equals(that.a()) || this.a().equals(that.b()) || this.b().equals(that.a()) || this.b().equals(that.b())){
            return true;
        }
        return false;
    }

    /**
     * Verifies if a Ponto c belongs to the SegmentoReta
     * @param p Ponto
     * @return true if it belongs, false if not
     */
    public boolean belongs(Ponto p)
    {
        if(this.a.equals(p) || this.b.equals(p))
        {
            return true;
        }
        Reta ab = new Reta(this.a, this.b);
        if(ab.belongs(p)){
            //if its not vertical
            if(ab.slope() != POSITIVE_INFINITY){
                if((p.x() > this.a.x() && p.x() < this.b.x()) || (p.x() > this.b.x() && p.x() < this.a.x())){
                    return true;
                }
            }
            else{
                if((p.y() > this.a.y() && p.y() < this.b.y()) || (p.y() > this.b.y() && p.y() < this.a.y())){
                    return true;
                }
            }
        }
        return false;
    }


//------------------------------------------------------------------------------------------------------------------------------------------------------------
}