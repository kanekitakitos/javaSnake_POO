package Geometrics;

/**
 *  Class that defines a 2D point, with x and y coordinates.
 *  @author Jose Ferras, Miguel Silva, Brandon Mejia
 *  @version v0.0   18/02/2024
 *  @inv any point created by this class will have to belong to the first quadrant
 */
public class Ponto{
    private double x;
    private double y;

    /**
     * Constructor
     * @param x coordinate
     * @param y coordinate
     */
    public Ponto(double x, double y){
        check(x,y);
        this.x = x;
        this.y = y;
    }

    /**
     * Check
     * @param x coordinate
     * @param y coordinate
     */
    public static void check(double x, double y)
    {
        if(x < 0 || y < 0)
        {
            throw new ArithmeticException("Ponto:iv");
        }
    }

    /**
     * Getter of the x coordinate of a point
     * @return x coordinate of that point
     */
    public double x(){
        return this.x;
    }

    /**
     * Getter of the y coordinate of a point
     * @return y coordinate of that point
     */
    public double y(){
        return this.y;
    }

    /**
     * Calculates the distance between to points
     * @param p second point (first point is the object we call the method with)
     * @return distance between these two points
     */
    public double dist (Ponto p) {
        double dx = x - p.x;
        double dy = y - p.y;
        return Math.sqrt(dx*dx + dy*dy);
    }

    /**
     * Verifies if the points have the exact same coordinates
     * @param that second point (first point is the object we call the method with)
     * @return true if they have the exact same coordinates, false if not
     */
    boolean equals (Ponto that){
        return this.x == that.x && this.y == that.y;
    }

    /**
     * Verifies if this Ponto is Contained it that Poligono
     * @see "https://stackoverflow.com/questions/217578/how-can-i-determine-whether-a-2d-point-is-within-a-polygon?page=1&tab=scoredesc#tab-top"
     * @param that Poligono
     * @return true if the Ponto is contained in the Poligono, false if not
     */
    public boolean isContainedIn(Poligono that){
        //first test if the Ponto is inside the envolvingRectangle
        Retangulo thatER = Retangulo.envolvingRectangle(that);
        double minX = thatER.pontos()[0].x();
        double maxX = thatER.pontos()[1].x();
        double minY = thatER.pontos()[0].y();
        double maxY = thatER.pontos()[2].y();
        if(this.x < minX || this.y < minY || this.x > maxX || this.y > maxY){
            return false;
        }
        SegmentoReta[] thatSR = Poligono.createSegmentosReta(that.pontos());
        //ray casting method
        SegmentoReta ray = new SegmentoReta(this, new Ponto(maxX+1,this.y));
        int i = 0;
        for(SegmentoReta s : thatSR){
            if(ray.intersects(s)){
                i++;
            }
        }
        if(i % 2 == 0){
            return false;
        }
        return true;
    }

    /**
     * toString
     * @return Geometrics.Ponto in string format
     */
    @Override
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }
}
