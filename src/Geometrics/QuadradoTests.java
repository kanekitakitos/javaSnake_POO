package Geometrics;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuadradoTests {

    //OOPS_0
    @Test
    void isContainedIn0(){
        Ponto p00 = new Ponto(4,2);
        Ponto p01 = new Ponto(6,2);
        Ponto p02 = new Ponto(6,4);
        Ponto p03 = new Ponto(4,4);
        Ponto[] p0s = new Ponto[]{p00,p01,p02,p03};
        Quadrado q0 = new Quadrado(p0s);
        Ponto p10 = new Ponto(0,0);
        Ponto p11 = new Ponto(10,0);
        Ponto p12 = new Ponto(5,10);
        Ponto[] p1s = new Ponto[]{p10,p11,p12};
        Triangulo t0 = new Triangulo(p1s);
        assertTrue(q0.isContainedIn(t0));
    }

    //OOPS_0
    @Test
    void isContainedIn1(){
        Ponto p00 = new Ponto(4,2);
        Ponto p01 = new Ponto(6,2);
        Ponto p02 = new Ponto(6,4);
        Ponto p03 = new Ponto(4,4);
        Ponto[] p0s = new Ponto[]{p00,p01,p02,p03};
        Quadrado q0 = new Quadrado(p0s);
        Ponto p10 = new Ponto(0,0);
        Ponto p11 = new Ponto(12,10);
        Ponto p12 = new Ponto(0,10);
        Ponto[] p1s = new Ponto[]{p10,p11,p12};
        Triangulo t0 = new Triangulo(p1s);
        assertFalse(q0.isContainedIn(t0));
    }

    //OOPS_0
    @Test
    void isContainedIn2(){
        Ponto p00 = new Ponto(4,2);
        Ponto p01 = new Ponto(6,2);
        Ponto p02 = new Ponto(6,4);
        Ponto p03 = new Ponto(4,4);
        Ponto[] p0s = new Ponto[]{p00,p01,p02,p03};
        Quadrado q0 = new Quadrado(p0s);
        Ponto p10 = new Ponto(0,0);
        Ponto p11 = new Ponto(10,10);
        Ponto p12 = new Ponto(0,10);
        Ponto[] p1s = new Ponto[]{p10,p11,p12};
        Triangulo t0 = new Triangulo(p1s);
        assertFalse(q0.isContainedIn(t0));
    }

    //OOPS_0
    @Test
    void isContainedIn3(){
        Ponto p00 = new Ponto(4,2);
        Ponto p01 = new Ponto(6,2);
        Ponto p02 = new Ponto(6,4);
        Ponto p03 = new Ponto(4,4);
        Ponto[] p0s = new Ponto[]{p00,p01,p02,p03};
        Quadrado q0 = new Quadrado(p0s);
        Ponto p10 = new Ponto(3,1);
        Ponto p11 = new Ponto(5,2);
        Ponto p12 = new Ponto(7,1);
        Ponto p13 = new Ponto(6,3);
        Ponto p14 = new Ponto(7,5);
        Ponto p15 = new Ponto(5,4);
        Ponto p16 = new Ponto(3,5);
        Ponto p17 = new Ponto(4,3);
        Ponto[] p1s = new Ponto[]{p10,p11,p12,p13,p14,p15,p16,p17};
        Poligono p0 = new Poligono(p1s);
        assertTrue(q0.isContainedIn(p0));
    }

    @Test
    void isContainedIn4(){
        Ponto p00 = new Ponto(0,0);
        Ponto p01 = new Ponto(1,0);
        Ponto p02 = new Ponto(1,1);
        Ponto p03 = new Ponto(0,1);
        Ponto[] p0s = new Ponto[]{p00,p01,p02,p03};
        Quadrado q0 = new Quadrado(p0s);
        Ponto p10 = new Ponto(0,0);
        Ponto p11 = new Ponto(2,0);
        Ponto p12 = new Ponto(2,2);
        Ponto p13 = new Ponto(0,2);
        Ponto[] p1s = new Ponto[]{p10,p11,p12,p13};
        Quadrado q1 = new Quadrado(p1s);
        assertTrue(q0.isContainedIn(q1));
    }

    @Test
    void isContainedIn5(){
        Ponto p00 = new Ponto(13,8);
        Ponto p01 = new Ponto(14,8);
        Ponto p02 = new Ponto(14,9);
        Ponto p03 = new Ponto(13,9);
        Ponto[] p0s = new Ponto[]{p00,p01,p02,p03};
        Quadrado q0 = new Quadrado(p0s);
        Ponto p10 = new Ponto(12,8);
        Ponto p11 = new Ponto(14,8);
        Ponto p12 = new Ponto(14,10);
        Ponto p13 = new Ponto(12,10);
        Ponto[] p1s = new Ponto[]{p10,p11,p12,p13};
        Quadrado q1 = new Quadrado(p1s);
        assertTrue(q0.isContainedIn(q1));
    }



}