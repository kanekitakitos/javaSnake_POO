package Geometrics;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SegmentoRetaTests {

    @Test
    void belongs0() {
        Ponto a = new Ponto(0,0);
        Ponto b = new Ponto(0,10);
        SegmentoReta ab = new SegmentoReta(a,b);
        Ponto c = new Ponto(0,0);
        assertTrue(ab.belongs(c));
    }

    @Test
    void belongs1() {
        Ponto a = new Ponto(0,0);
        Ponto b = new Ponto(2,0);
        SegmentoReta ab = new SegmentoReta(a,b);
        Ponto c = new Ponto(1,0);
        assertTrue(ab.belongs(c));
    }
}