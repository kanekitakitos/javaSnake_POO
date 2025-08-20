package Geometrics;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class PoligonoTests {
    @Test
    public void testIntersects0() {
        Ponto[] pA = new Ponto[4];
        pA[0] = new Ponto(5, 5);
        pA[1] = new Ponto(8, 5);
        pA[2] = new Ponto(8, 7);
        pA[3] = new Ponto(5, 7);
        Poligono A = new Poligono(pA);
        Ponto[] pB = new Ponto[4];
        pB[0] = new Ponto(7, 1);
        pB[1] = new Ponto(9, 1);
        pB[2] = new Ponto(9, 3);
        pB[3] = new Ponto(7, 3);
        Poligono B = new Poligono(pB);
        assertFalse(A.intersects(B));
        pA[0] = new Ponto(5, 5);
        pA[1] = new Ponto(8, 5);
        pA[2] = new Ponto(8, 7);
        pA[3] = new Ponto(5, 7);
        Poligono C = new Poligono(pA);
        pB[0] = new Ponto(7, 4);
        pB[1] = new Ponto(9, 4);
        pB[2] = new Ponto(9, 6);
        pB[3] = new Ponto(7, 6);
        Poligono D = new Poligono(pB);
        assertTrue(C.intersects(D));
    }

    @Test
    public void testIsDuplicate() {
        Ponto[] pA = new Ponto[4];
        pA[0] = new Ponto(5, 5);
        pA[1] = new Ponto(8, 5);
        pA[2] = new Ponto(8, 7);
        pA[3] = new Ponto(5, 7);
        Ponto[] pB = new Ponto[4];
        pB[0] = new Ponto(8, 7);
        pB[1] = new Ponto(5, 7);
        pB[2] = new Ponto(5, 5);
        pB[3] = new Ponto(8, 5);
        Poligono A = new Poligono(pA);
        Poligono B = new Poligono(pB);
        assertTrue(A.equals(B));
        Ponto[] pC = new Ponto[3];
        pC[0] = new Ponto(8, 7);
        pC[1] = new Ponto(5, 7);
        pC[2] = new Ponto(5, 5);
        Poligono C = new Poligono(pC);
        assertFalse(A.equals(C));
        pB[0] = new Ponto(10, 7);
        pB[1] = new Ponto(5, 7);
        pB[2] = new Ponto(5, 5);
        pB[3] = new Ponto(8, 5);
        B = new Poligono(pB);
        assertFalse(A.equals(B));
        pB[0] = new Ponto(8, 7);
        pB[1] = new Ponto(5, 7);
        pB[2] = new Ponto(6, 5);
        pB[3] = new Ponto(8, 5);
        B = new Poligono(pB);
        assertFalse(A.equals(B));
    }

    @Test
    public void testStringToPontos() {
        String s0 = "4 5 5 8 6 8 7 5 7";
        Ponto p00 = new Ponto(5, 5);
        Ponto p01 = new Ponto(8, 6);
        Ponto p02 = new Ponto(8, 7);
        Ponto p03 = new Ponto(5, 7);
        Ponto[] p0s = new Ponto[]{p00, p01, p02, p03};
        Poligono p0 = new Poligono(s0);
        for (int i = 0; i < 4; i++) {
            assertEquals(p0.pontos()[i].x(), p0s[i].x());
            assertEquals(p0.pontos()[i].y(), p0s[i].y());
        }
        String s1 = "7 1 9 1 9 3";
        Ponto p10 = new Ponto(7, 1);
        Ponto p11 = new Ponto(9, 1);
        Ponto p12 = new Ponto(9, 3);
        Ponto[] p1s = new Ponto[]{p10, p11, p12};
        Poligono p1 = new Poligono(s1);
        for (int i = 0; i < 3; i++) {
            assertEquals(p1.pontos()[i].x(), p1s[i].x());
            assertEquals(p1.pontos()[i].y(), p1s[i].y());
        }
    }

    @Test
    public void testFindCentroid() {
        Ponto p00 = new Ponto(1, 1);
        Ponto p01 = new Ponto(3, 1);
        Ponto p02 = new Ponto(3, 3);
        Ponto p03 = new Ponto(1, 3);
        Ponto[] p0s = new Ponto[]{p00, p01, p02, p03};
        Poligono p0 = new Poligono(p0s);
        Ponto c0 = p0.findCentroid();
        assertEquals(c0.x(), 2.0);
        assertEquals(c0.y(), 2.0);
    }

    @Test
    public void testRotate() {
        Ponto p00 = new Ponto(1, 1);
        Ponto p01 = new Ponto(3, 1);
        Ponto p02 = new Ponto(3, 5);
        Ponto p03 = new Ponto(1, 5);
        Ponto[] p0s = new Ponto[]{p00, p01, p02, p03};
        Poligono p0 = new Poligono(p0s);
        Poligono p0r = p0.rotate(90);
        assertEquals(p0r.pontos()[0].x(), 4);
        assertEquals(p0r.pontos()[0].y(), 2);
        assertEquals(p0r.pontos()[1].x(), 4);
        assertEquals(p0r.pontos()[1].y(), 4);
        assertEquals(p0r.pontos()[2].x(), 0);
        assertEquals(p0r.pontos()[2].y(), 4);
        assertEquals(p0r.pontos()[3].x(), 0);
        assertEquals(p0r.pontos()[3].y(), 2);
        Poligono p1 = new Poligono(p0s);
        Poligono p1r = p1.rotate(-90, new Ponto(3, 1));
        assertEquals(p1r.pontos()[0].x(), 3);
        assertEquals(p1r.pontos()[0].y(), 3);
        assertEquals(p1r.pontos()[1].x(), 3);
        assertEquals(p1r.pontos()[1].y(), 1);
        assertEquals(p1r.pontos()[2].x(), 7);
        assertEquals(p1r.pontos()[2].y(), 1);
        assertEquals(p1r.pontos()[3].x(), 7);
        assertEquals(p1r.pontos()[3].y(), 3);
    }

    @Test
    public void testTranslate0() {
        Ponto p00 = new Ponto(1, 2);
        Ponto p01 = new Ponto(5, 6);
        Ponto p02 = new Ponto(8, 7);
        Ponto p03 = new Ponto(12, 14);
        Ponto[] p0s = new Ponto[]{p00, p01, p02, p03};
        Poligono p0 = new Poligono(p0s);
        Poligono p0t = p0.translate(-1, 3);
        assertEquals(p0t.pontos()[0].x(), 0);
        assertEquals(p0t.pontos()[0].y(), 5);
        assertEquals(p0t.pontos()[1].x(), 4);
        assertEquals(p0t.pontos()[1].y(), 9);
        assertEquals(p0t.pontos()[2].x(), 7);
        assertEquals(p0t.pontos()[2].y(), 10);
        assertEquals(p0t.pontos()[3].x(), 11);
        assertEquals(p0t.pontos()[3].y(), 17);
    }

    @Test
    public void testTranslate1() {
        Ponto p00 = new Ponto(1, 1);
        Ponto p01 = new Ponto(5, 1);
        Ponto p02 = new Ponto(5, 3);
        Ponto p03 = new Ponto(1, 3);
        Ponto c0 = new Ponto(3, 3);
        Ponto[] p0s = new Ponto[]{p00, p01, p02, p03};
        Poligono p0 = new Poligono(p0s);
        Poligono p0t = p0.translate(c0);
        assertEquals(p0t.pontos()[0].x(), 1);
        assertEquals(p0t.pontos()[0].y(), 2);
        assertEquals(p0t.pontos()[1].x(), 5);
        assertEquals(p0t.pontos()[1].y(), 2);
        assertEquals(p0t.pontos()[2].x(), 5);
        assertEquals(p0t.pontos()[2].y(), 4);
        assertEquals(p0t.pontos()[3].x(), 1);
        assertEquals(p0t.pontos()[3].y(), 4);
        Ponto p10 = new Ponto(1, 0);
        Ponto p11 = new Ponto(3, 0);
        Ponto p12 = new Ponto(2, 3);
        Ponto c1 = new Ponto(2, 3);
        Ponto[] p1s = new Ponto[]{p10, p11, p12};
        Poligono p1 = new Poligono(p1s);
        Poligono p1t = p1.translate(c1);
        assertEquals(p1t.pontos()[0].x(), 1);
        assertEquals(p1t.pontos()[0].y(), 2);
        assertEquals(p1t.pontos()[1].x(), 3);
        assertEquals(p1t.pontos()[1].y(), 2);
        assertEquals(p1t.pontos()[2].x(), 2);
        assertEquals(p1t.pontos()[2].y(), 5);
    }

    @Test
    void testToString() {
        Ponto p00 = new Ponto(1, 1);
        Ponto p01 = new Ponto(5, 1);
        Ponto p02 = new Ponto(5, 3);
        Ponto p03 = new Ponto(1, 3);
        Ponto[] p0s = new Ponto[]{p00, p01, p02, p03};
        Poligono p0 = new Poligono(p0s);
        String ps = p0.toString();
        assertEquals("Geometrics.Poligono de 4 vertices: [(1.0,1.0), (5.0,1.0), (5.0,3.0), (1.0,3.0)]", ps);
    }

    //OOPS_0
    @Test
    void testPerimeter0() {
        Ponto p00 = new Ponto(1, 1);
        Ponto p01 = new Ponto(5, 1);
        Ponto p02 = new Ponto(5, 3);
        Ponto p03 = new Ponto(1, 3);
        Ponto[] p0s = new Ponto[]{p00, p01, p02, p03};
        Poligono p0 = new Poligono(p0s);
        assertEquals(12.0, p0.perimeter());
    }

    //OOPS_0
    @Test
    void testPerimeter1() {
        Ponto p00 = new Ponto(3, 2);
        Ponto p01 = new Ponto(11, 2);
        Ponto p02 = new Ponto(11, 6);
        Ponto p03 = new Ponto(3, 6);
        Ponto[] p0s = new Ponto[]{p00, p01, p02, p03};
        Poligono p0 = new Poligono(p0s);
        assertEquals(24.0, p0.perimeter());
    }

    //OOPS_0
    @Test
    void testPerimeter2() {
        Ponto p00 = new Ponto(2, 3);
        Ponto p01 = new Ponto(2, 2);
        Ponto p02 = new Ponto(6, 2);
        Ponto p03 = new Ponto(6, 1);
        Ponto p04 = new Ponto(7, 1);
        Ponto p05 = new Ponto(7, 5);
        Ponto p06 = new Ponto(6, 5);
        Ponto p07 = new Ponto(6, 3);
        Ponto p08 = new Ponto(5, 3);
        Ponto p09 = new Ponto(5, 4);
        Ponto p10 = new Ponto(3, 4);
        Ponto p11 = new Ponto(3, 3);
        Ponto p12 = new Ponto(2, 3);
        Ponto[] p0s = new Ponto[]{p00, p01, p02, p03, p04, p05, p06, p07, p08, p09, p10, p11, p12};
        Poligono p0 = new Poligono(p0s);
        assertEquals(20.0, p0.perimeter());
    }

    //OOPS_0
    @Test
    void findCentroidDifference0()
    {
        Ponto p00 = new Ponto(1, 1);
        Ponto p01 = new Ponto(3, 1);
        Ponto p02 = new Ponto(3, 3);
        Ponto p03 = new Ponto(1, 3);
        Ponto[] p0s = new Ponto[]{p00, p01, p02, p03};
        Poligono p0 = new Poligono(p0s);
        Ponto c0 = p0.findCentroid();
        assertEquals(c0.x(), 2.0);
        assertEquals(c0.y(), 2.0);
        Ponto p10 = new Ponto(5, 1);
        Ponto p11 = new Ponto(7, 1);
        Ponto p12 = new Ponto(7, 3);
        Ponto p13 = new Ponto(5, 3);
        Ponto[] p1s = new Ponto[]{p10, p11, p12, p13};
        Poligono p1 = new Poligono(p1s);
        Ponto c1 = p1.findCentroid();
        assertEquals(c1.x(), 6.0);
        assertEquals(c1.y(), 2.0);
        //coordenadas do vetor direçao entre os centroides
        double[] coords = p0.findCentroidDifference(p1.findCentroid());
        assertEquals(coords[0], 4.0);
        assertEquals(coords[1], 0.0);

    }

}