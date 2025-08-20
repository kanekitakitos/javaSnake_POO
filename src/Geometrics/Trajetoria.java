package Geometrics;

import java.util.ArrayList;

/**
 * Class that defines a trajectory with an array of points.
 * @author Jose Ferras, Miguel Silva, Brandon Mejia
 * @version v0.0    20/02/2024
 * @inv number of points has to be >= 2
 * @inv can't have 2 point with the exact same coordinates
 */
public class Trajetoria {
    private Ponto[] pontos;

    /**
     * Constructor
     * @param p array of points that define the trajectory
     */
    public Trajetoria(Ponto[] p){
        check(p);
        this.pontos = new Ponto[p.length];
        for(int i = 0; i < pontos.length; i++){
            this.pontos[i] = new Ponto(p[i].x(), p[i].y());
        }
    }

    private static void check(Ponto[] p){
        if(p.length < 2){
            System.out.println("Geometrics.Trajetoria:vi");
            System.exit(0);
        }
    }

    /**
     * Creates SegementoReta array with all the SegmentosReta that form the Geometrics.Trajetoria
     * @return the Geometrics.SegmentoReta array
     */
    public SegmentoReta[] createSegmentosReta(){
        int n = this.pontos.length;
        SegmentoReta ab;
        SegmentoReta[] segmentosReta = new SegmentoReta[n-1];
        for(int i = 0; i < segmentosReta.length; i++){
            ab = new SegmentoReta(this.pontos[i], this.pontos[i+1]);
            segmentosReta[i] = ab;
        }
        return segmentosReta;
    }

    /**
     * Verifies if Geometrics.Trajetoria collides with any of the obstacles represented by Poligonos
     * @param poligonos array of Poligonos that define the obstacles
     * @return true if it collides with any Polygon, false if not
     */
    public boolean collidesWithObstacles(Poligono[] poligonos){
        SegmentoReta[] segmentosTrajetoria = this.createSegmentosReta();
        ArrayList<SegmentoReta> segmentosPoligonos = Poligono.createSegmentosReta(poligonos);
        for(SegmentoReta sr0 : segmentosTrajetoria){
            for(SegmentoReta sr1 : segmentosPoligonos){
                if(sr0.intersects(sr1)){
                    return true;
                }
            }
        }
        return false;
    }
}