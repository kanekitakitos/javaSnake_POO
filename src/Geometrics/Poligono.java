package Geometrics;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Math.*;

/**
 * Class that defines a polygon with an array of points.
 *  @author Jose Ferras, Miguel Silva, Brandon Mejia
 * @version v0.0    18/02/2024
 * @inv number of points has to be > 2
 * @inv there are no 3 consecutive points that are colinear with each other
 * @inv there aren't 2 edges that intersect one another
 */
public class Poligono implements FiguraGeometrica
{
    private final Ponto[] pontos;

    /**
     * Constructor
     * @param p array of points that will form the polygon
     */
    public Poligono(Ponto[] p){
        if(p.length <= 2){
            System.out.println("Geometrics.Poligono:vi");
            System.exit(0);
        }
        else if(checkCollinearity(p)){
            //System.out.println("colinear");
            System.out.println("Geometrics.Poligono:vi");
            System.exit(0);
        }
        else if(checkIntersections(p)){
            //System.out.println("intersecao");
            System.out.println("Geometrics.Poligono:vi");
            System.exit(0);
        }

        this.pontos = new Ponto[p.length];
        for(int i = 0; i < pontos.length; i++){
            this.pontos[i] = new Ponto(p[i].x(), p[i].y());
        }
    }

    /**
     * Constructor that is called with a string with the points
     * @param pontos string with the points that will define the polygon
     */
    public Poligono(String pontos){
        this(stringToPontos(pontos));
    }

    /**
     * Getter of array of points
     * @return array of points of called object
     */
    public Ponto[] pontos()
    {
        Ponto[] pontos = new Ponto[this.pontos.length];
        int i = 0;
        for(Ponto p : this.pontos){
            pontos[i++] = new Ponto(p.x(), p.y());
        }
        return pontos;
    }

    /**
     * Creates a Geometrics.SegmentoReta array from the given points (that form a Geometrics.Poligono)
     * @param p array of points
     * @return the Geometrics.SegmentoReta array
     */
    public static SegmentoReta[] createSegmentosReta(Ponto[] p)
    {
        SegmentoReta ab;
        SegmentoReta[] segmentosReta = new SegmentoReta[p.length];
        for(int i = 0; i < p.length-1; i++)
        {
            ab = new SegmentoReta(p[i], p[i+1]);
            segmentosReta[i] = ab;
        }
        segmentosReta[p.length-1] = new SegmentoReta(p[p.length-1], p[0]);
        return segmentosReta;
    }

    /**
     * Creates a Geometrics.SegmentoReta arrayList for the given Poligonos
     * @param poligonos array of Poligonos
     * @return the Geometrics.SegmentoReta arrayList
     */
    public static ArrayList<SegmentoReta> createSegmentosReta(Poligono[] poligonos){
        ArrayList<SegmentoReta> segmentosReta = new ArrayList<>();
        for(Poligono p : poligonos){
            Ponto[] pontos = p.pontos();
            SegmentoReta[] tempSR = createSegmentosReta(pontos);
            segmentosReta.addAll(Arrays.asList(tempSR));
        }
        return segmentosReta;
    }

    /**
     * Checks if there are any 3 consecutive points that are colinear
     * @param p array of points
     * @return true if there are any 3 consecutive points that are colinear, false if not
     */
    private static boolean checkCollinearity(Ponto[] p)
    {
        Reta r;
        Ponto c;
        for(int i = 0; i < p.length; i++){
            //normal case
            if(i < p.length-2){
                r = new Reta(p[i], p[i+1]);
                c = p[i+2];
            }
            //case for r formed by the last two points, and c by the first one
            else if(i == p.length-2){
                r = new Reta(p[i], p[i+1]);
                c = p[0];
            }
            //case for r formed by the last and first points, and c by the second one
            else{
                r = new Reta(p[i], p[0]);
                c = p[1];
            }
            if(r.belongs(c)){
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if there is any pair of edges that intersect each other
     * @param p array of points
     * @return true if there is any pair of edges that intersect, false if not
     */
    private static boolean checkIntersections(Ponto p[])
    {
        SegmentoReta[] segmentosReta = createSegmentosReta(p);
        SegmentoReta ab, cd;
        for(int i = 0; i < segmentosReta.length-1; i++)
        {
            ab = segmentosReta[i];
            for(int j = i+1; j < segmentosReta.length; j++)
            {
                cd = segmentosReta[j];
                //if it shares the vertex there is no need to see if it intersects
                if(!ab.sharesVertex(cd)){
                    if(ab.intersects(cd)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Calculates wether two Poligonos intersect or not
     * @param that second Geometrics.Poligono (first one is this)
     * @return true if this Geometrics.Poligono intersects that Geometrics.Poligono, false if they dont
     */
    public boolean intersects(Poligono that){
        SegmentoReta[] SRThis = createSegmentosReta(this.pontos);
        SegmentoReta[] SRThat = createSegmentosReta(that.pontos);
        for(SegmentoReta sr0 : SRThis){
            for(SegmentoReta sr1 : SRThat){
                if(sr0.intersects(sr1)){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Calculates wether a Geometrics.Poligono intersects with a Geometrics.Circulo or not
     * @param that Geometrics.Circulo
     * @return true if this Geometrics.Poligono intersects that Geometrics.Circulo, false if they dont
     */
    @Override
    public boolean intersects(Circulo that) {
        return that.intersects(this);
    }

    /**
     * Calculates the perimeter of the Geometrics.Poligono
     * @return perimeter of the Geometrics.Poligono
     */
    public double perimeter(){
        double perimeter = 0;
        int n = this.pontos.length;
        //normal case
        for(int i = 0; i < n-1; i++){
            perimeter += this.pontos[i].dist(this.pontos[i+1]);
        }
        //last case where we need to measure the distance between the last and the first point
        perimeter += this.pontos[n-1].dist(this.pontos[0]);
        return perimeter;
    }

    /**
     * Finds the centroid of the polygon using it's array of points
     * @return centroid
     * @see "https://stackoverflow.com/questions/2792443/finding-the-centroid-of-a-polygon"
     * @see "https://en.wikipedia.org/wiki/Centroid#Of_a_polygon"
     */
    public Ponto findCentroid()
    {
        double x = 0.0;
        double y = 0.0;
        for(Ponto p : this.pontos){
            x += p.x();
            y += p.y();
        }
        return new Ponto(x/this.pontos.length, y/this.pontos.length);
    }

    /**
     * Rotates the points of the polygon "angle" degrees clockwise about the point c
     * @return rotated points
     * @param angle angle to rotate
     * @param c point to rotate around
     * @see "https://danceswithcode.net/engineeringnotes/rotations_in_2d/rotations_in_2d.html"
     */
    public Ponto[] rotatePoints(double angle, Ponto c)
    {
        double x0, y0, x1, y1;
        angle = toRadians(angle);
        Ponto[] rotatedPoints = new Ponto[this.pontos.length];
        for(int i = 0; i < this.pontos.length; i++){
            x0 = this.pontos[i].x();
            y0 = this.pontos[i].y();
            x1 = (x0 - c.x())*cos(angle) - (y0-c.y())*sin(angle) + c.x();
            y1 = (x0 - c.x())*sin(angle) + (y0-c.y())*cos(angle) + c.y();
            rotatedPoints[i] = new Ponto(round(x1), round(y1));
        }
        return rotatedPoints;
    }

    /**
     * Rotates the polygon "angle" degrees clockwise about the point c
     * @param angle angle to rotate
     * @param c point to rotate about
     * @return rotated Poligono
     * @see "https://danceswithcode.net/engineeringnotes/rotations_in_2d/rotations_in_2d.html"
     */
    public Poligono rotate(double angle, Ponto c){
        return new Poligono(rotatePoints(angle,c));
    }

    /**
     * Rotates the polygon "angle" degrees clockwise about the centroid
     * @param angle angle to rotate
     * @return rotated Geometrics.Poligono
     */
    public Poligono rotate(double angle){
        return rotate(angle, this.findCentroid());
    }

    /**
     * Translates the points of the polygon x and y coordinates
     * @param x horizontal translation value
     * @param y vertical translation value
     * @return array of translated points
     */
    public Ponto[] translatePoints(double x, double y){
        Ponto[] translatedPoints = new Ponto[this.pontos.length];
        for(int i = 0; i < this.pontos.length; i++){
            translatedPoints[i] = new Ponto(round(this.pontos[i].x() + x), round(this.pontos[i].y() + y));
        }
        return translatedPoints;
    }

    /**
     * Translates the polygon x and y coordinates
     * @param x horizontal translation value
     * @param y vertical translation value
     * @return translated polygon
     */
    public Poligono translate(double x, double y){
        return new Poligono(translatePoints(x,y));
    }

    /**
     * Finds the difference in coordinates betwenn the current centroid and the new one
     * @param c new to be centroid
     * @return difference of x and y in array of doubles
     */
    public double[] findCentroidDifference(Ponto c){
        double[] coords = new double[2];
        Ponto currentC = this.findCentroid();
        coords[0] = c.x() - currentC.x();
        coords[1] = c.y() - currentC.y();
        return coords;
    }

    /**
     * Translates the polygon making it's new centroid point c
     * @param c new centroid
     * @return translated polygon
     */
    public Poligono translate(Ponto c){
        double[] coords = findCentroidDifference(c);
        return this.translate(coords[0], coords[1]);
    }

    /**
     * Verifies if this Geometrics.Poligono is a duplicate from that Geometrics.Poligono
     * @param that poligono
     * @return true if they are duplicate, false if not
     */
    public boolean equals(Poligono that){
        //if they have different number of points
        if(this.pontos.length != that.pontos.length){
            return false;
        }
        int n = this.pontos.length;
        int k = -1;
        //find the start of the point sequence in that Geometrics.Poligono
        for(int i = 0; i < n; i++){
            if(that.pontos[i].equals(this.pontos[0])){
                k = i;
                break;
            }
        }
        //if it didnt find the first point in that Geometrics.Poligono
        if(k == -1){
            return false;
        }
        Ponto p0;   //point in this
        Ponto p1;   //point in that
        int j;  //index of point in that
        for(int i = 0; i < n; i++){
            j = i+k;
            if(j >= n){
                j = j % n;
            }
            p0 = this.pontos[i];
            p1 = that.pontos[j];
            if(!p0.equals(p1)){
                return false;
            }
        }
        return true;
    }

    /**
     * Makes a string of points into an array of points
     * @param pontos string of points
     * @return array of points
     */
    public static Ponto[] stringToPontos(String pontos){
        String[] p = pontos.split(" ");
        int x = 0;
        int y;
        int j = 0;
        //if its a polygon with n points
        if(p.length % 2 == 1){
            Ponto[] pontoArray = new Ponto[Integer.parseInt(p[0])];
            for(int i = 1; i < p.length; i++){
                if(i % 2 == 1){
                    x = Integer.parseInt(p[i]);
                }
                else {
                    y = Integer.parseInt(p[i]);
                    pontoArray[j++] = new Ponto(x,y);
                }
            }
            return pontoArray;
        }
        //if its a predefined type of polygon
        else{
            ArrayList<Ponto> pontoList = new ArrayList<>();
            for(int i = 0; i < p.length; i++){
                if(i % 2 == 0){
                    x = Integer.parseInt(p[i]);
                }
                else{
                    y = Integer.parseInt(p[i]);
                    pontoList.add(new Ponto(x,y));
                }
            }
            Ponto[] pontoArray = new Ponto[pontoList.size()];
            for(Ponto ponto : pontoList){
                pontoArray[j++] = ponto;
            }
            return pontoArray;
        }
    }

    /**
     * Verifies if this Poligono is contained in That Poligono
     * @see "https://stackoverflow.com/questions/4833802/check-if-polygon-is-inside-a-polygon"
     * @param that Poligono
     * @return true if this Poligono is contained in that Poligono, false if not
     */
    public boolean isContainedIn(Poligono that){
        if(this.intersects(that)){
            return false;
        }
        SegmentoReta[] thatSR = createSegmentosReta(that.pontos);
        //lets find a point that isnt in one of the edges to use in the method in Ponto
        for(Ponto p0 : this.pontos){
            int i = 0;
            for(SegmentoReta s : thatSR){
                if(s.belongs(p0)){
                    i++;
                    break;
                }
            }
            //if point isn't contained in any of the edges then we use the method in Ponto
            if(i == 0){
                return p0.isContainedIn(that);
            }
        }
        return true;    //if all of the points belong to one of the edges of the polygon then it is contained in
    }

    /**
     * Metodo toString
     * @return  transforma o Geometrics.Poligono num formato de string
     */
    @Override
    public String toString() {
        return "Geometrics.Poligono de " + this.pontos.length + " vertices: " + Arrays.asList(this.pontos);
    }


    /**
     * Auxiliary method for draw()
     * @return X coordinate array and Y coordinate array
     */
    public int[][] separateCoordinates(int maxY)
    {
        int[][] result = new int[2][this.pontos.length];
        for(int i = 0; i < this.pontos.length; i++)
        {
            result[0][i] = (int) this.pontos[i].x();
            result[1][i] = maxY - (int) this.pontos[i].y();
        }
        return result;
    }

    /**
     * Draw method to help represent a polygon in the GUI
     * @param g Graphics2D
     * @param maxY need this value because Y are the inverse of what they will be represented as
     */
    public void draw(Graphics2D g, int maxY)
    {
        int[][] points = this.separateCoordinates(maxY);
        g.fillPolygon(points[0],points[1],this.pontos.length);
    }


}
