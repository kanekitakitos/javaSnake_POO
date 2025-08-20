package Geometrics;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class PontoTests {

    @Test
    void testCheckPonto0() {
        //CRIACAO DE VARIOS PONTOS VALIDOS
        Ponto p = new Ponto(1, 1);
        Ponto p1 = new Ponto(0, 0);
        Ponto p2 = new Ponto(1, 0);
        Ponto p3 = new Ponto(0, 1);
        Ponto p4 = new Ponto(2, 2);
        Ponto p5 = new Ponto(3123, 34444);
    }

    @Test
    void testGetX0() {
        Ponto p = new Ponto(1, 1);
        assertEquals(1, p.x());
    }

    @Test
    void testGetX1() {
        Ponto p = new Ponto(555, 1);
        assertEquals(555, p.x());
    }

    @Test
    void testGetY0() {
        Ponto p = new Ponto(1, 1);
        assertEquals(1, p.y());
    }

    @Test
    void testGetY1() {
        Ponto p = new Ponto(1, 555);
        assertEquals(555, p.y());
    }

    @Test
    void testToString() {
        Ponto p = new Ponto(1, 1);
        assertEquals("(1.0,1.0)", p.toString());
    }

    @Test
    void testIsContainedIn(){
        Ponto c0 = new Ponto(5,5);
        Ponto p00 = new Ponto(2,2);
        Ponto p01 = new Ponto(8,2);
        Ponto p02 = new Ponto(8,8);
        Ponto p03 = new Ponto(2,8);
        Poligono p0 = new Poligono(new Ponto[]{p00,p01,p02,p03});
        assertTrue(c0.isContainedIn(p0));
        Ponto c1 = new Ponto(10,5);
        assertFalse(c1.isContainedIn(p0));
        Ponto c2 = new Ponto(8,5);
        assertFalse(c2.isContainedIn(p0));
        Ponto c3 = new Ponto(7,5);
        assertTrue(c3.isContainedIn(p0));
    }
}