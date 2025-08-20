package Geometrics;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CirculoTests {
    //OOPS_0
    @Test
    public void testFindCentroid0(){
        Ponto p0 = new Ponto(5,5);
        Circulo c0 = new Circulo(p0, 2);
        assertEquals(c0.findCentroid().x(), p0.x());
        assertEquals(c0.findCentroid().y(), p0.y());
    }

    //OOPS_0
    @Test
    public void testFindCentroid1(){
        Ponto p0 = new Ponto(15.5,10.25);
        Circulo c0 = new Circulo(p0, 2.346);
        assertEquals(c0.findCentroid().x(), p0.x());
        assertEquals(c0.findCentroid().y(), p0.y());
    }

    //OOPS_0
    @Test
    public void testPerimetro0(){
        Ponto p0 = new Ponto(5,5);
        Circulo c0 = new Circulo(p0, 2);
        assertEquals(c0.perimeter(), 12.566370614359172);
    }

    //OOPS_0
    @Test
    public void testPerimetro1(){
        Ponto p0 = new Ponto(15.5,10.25);
        Circulo c0 = new Circulo(p0, 2.346);
        assertEquals(c0.perimeter(), 14.740352730643309);
    }

    //OOPS_0
    @Test
    void isContainedIn0(){
        Ponto p00 = new Ponto(4,2);
        Circulo c0 = new Circulo(p00, 1);
        Ponto p10 = new Ponto(0,0);
        Ponto p11 = new Ponto(10,0);
        Ponto p12 = new Ponto(5,10);
        Ponto[] p1s = new Ponto[]{p10,p11,p12};
        Triangulo t0 = new Triangulo(p1s);
        assertTrue(c0.isContainedIn(t0));
    }

    //OOPS_0
    @Test
    void isContainedIn1(){
        Ponto p00 = new Ponto(4,2);
        Circulo c0 = new Circulo(p00, 1);
        Ponto p10 = new Ponto(0,0);
        Ponto p11 = new Ponto(10,10);
        Ponto p12 = new Ponto(0,10);
        Ponto[] p1s = new Ponto[]{p10,p11,p12};
        Triangulo t0 = new Triangulo(p1s);
        assertFalse(c0.isContainedIn(t0));
    }

    //OOPS_0
    @Test
    void isContainedIn2(){
        Ponto p00 = new Ponto(5,3);
        Circulo c0 = new Circulo(p00, 0.5);
        Ponto p10 = new Ponto(3,1);
        Ponto p11 = new Ponto(5,2);
        Ponto p12 = new Ponto(7,1);
        Ponto p13 = new Ponto(6,4);
        Ponto p14 = new Ponto(7,5);
        Ponto p15 = new Ponto(5,4);
        Ponto p16 = new Ponto(3,5);
        Ponto p17 = new Ponto(4,4);
        Ponto[] p1s = new Ponto[]{p10,p11,p12,p13,p14,p15,p16,p17};
        Poligono p0 = new Poligono(p1s);
        assertTrue(c0.isContainedIn(p0));
    }

    //OOPS_0
    @Test
    void testIntersects0(){
        Ponto p0 = new Ponto(5,3);
        Circulo c0 = new Circulo(p0, 1);
        Ponto p1 = new Ponto(6,3);
        Circulo c1 = new Circulo(p1, 1);
        assertTrue(c0.intersects(c1));
    }

    //OOPS_0
    @Test
    void testIntersects1(){
        Ponto p0 = new Ponto(10,5);
        Circulo c0 = new Circulo(p0, 1);
        Ponto p1 = new Ponto(14,5);
        Circulo c1 = new Circulo(p1, 3);
        assertFalse(c0.intersects(c1));
    }

    //OOPS_0
    @Test
    void testIntersects2(){
        Ponto p0 = new Ponto(10,5);
        Circulo c0 = new Circulo(p0, 1);
        Ponto p1 = new Ponto(10,5);
        Circulo c1 = new Circulo(p1, 3);
        assertTrue(c0.intersects(c1));
    }

    //OOPS_0
    @Test
    void testIntersects3(){
        Ponto p00 = new Ponto(8,6);
        Circulo c0 = new Circulo(p00, 2);
        Ponto p10 = new Ponto(3,1);
        Ponto p11 = new Ponto(5,2);
        Ponto p12 = new Ponto(7,1);
        Ponto p13 = new Ponto(6,4);
        Ponto p14 = new Ponto(7,5);
        Ponto p15 = new Ponto(5,4);
        Ponto p16 = new Ponto(3,5);
        Ponto p17 = new Ponto(4,4);
        Ponto[] p1s = new Ponto[]{p10,p11,p12,p13,p14,p15,p16,p17};
        Poligono p0 = new Poligono(p1s);
        assertTrue(c0.intersects(p0));
    }

    //OOPS_0
    @Test
    void testIntersects4(){
        Ponto p00 = new Ponto(8,6);
        Circulo c0 = new Circulo(p00, 1);
        Ponto p10 = new Ponto(3,1);
        Ponto p11 = new Ponto(5,2);
        Ponto p12 = new Ponto(7,1);
        Ponto p13 = new Ponto(6,4);
        Ponto p14 = new Ponto(7,5);
        Ponto p15 = new Ponto(5,4);
        Ponto p16 = new Ponto(3,5);
        Ponto p17 = new Ponto(4,4);
        Ponto[] p1s = new Ponto[]{p10,p11,p12,p13,p14,p15,p16,p17};
        Poligono p0 = new Poligono(p1s);
        assertFalse(c0.intersects(p0));
    }

    //OOPS_0
    @Test
    void testIntersects5(){
        Ponto p00 = new Ponto(5,3);
        Circulo c0 = new Circulo(p00, 1);
        Ponto p10 = new Ponto(3,1);
        Ponto p11 = new Ponto(5,2);
        Ponto p12 = new Ponto(7,1);
        Ponto p13 = new Ponto(6,4);
        Ponto p14 = new Ponto(7,5);
        Ponto p15 = new Ponto(5,4);
        Ponto p16 = new Ponto(3,5);
        Ponto p17 = new Ponto(4,4);
        Ponto[] p1s = new Ponto[]{p10,p11,p12,p13,p14,p15,p16,p17};
        Poligono p0 = new Poligono(p1s);
        assertFalse(c0.intersects(p0));
    }

    //OOPS_0
    @Test
    void testTranslate0(){
        Ponto p00 = new Ponto(5,3);
        Circulo c0 = new Circulo(p00, 1);
        Circulo c1 = c0.translate(1,1);
        assertEquals(c1.findCentroid().x(), 6);
        assertEquals(c1.findCentroid().y(), 4);
    }

    //OOPS_0
    @Test
    void testTranslate1(){
        Ponto p00 = new Ponto(5,3);
        Circulo c0 = new Circulo(p00, 1);
        Circulo c1 = c0.translate(-2,-1);
        assertEquals(c1.findCentroid().x(), 3);
        assertEquals(c1.findCentroid().y(), 2);
    }

    //OOPS_0
    @Test
    void testTranslate2(){
        Ponto p00 = new Ponto(5,3);
        Circulo c0 = new Circulo(p00, 1);
        Ponto p01 = new Ponto(100,502);
        Circulo c1 = c0.translate(p01);
        assertEquals(c1.findCentroid().x(), 100);
        assertEquals(c1.findCentroid().y(), 502);
    }
}