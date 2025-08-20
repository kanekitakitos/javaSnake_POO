package OOPS;

import Geometrics.*;
import Geometrics.Quadrado;
import org.junit.jupiter.api.Test;
import OOPS.SnakeBodyPart.Orientation;

import static org.junit.jupiter.api.Assertions.*;

class SnakeBodyPartTests {

    //OOPS_0
    @Test
    void testChangeDirection(){
        Ponto p0 = new Ponto(5,5);
        Ponto p1 = new Ponto(6,5);
        Ponto p2 = new Ponto(6,6);
        Ponto p3 = new Ponto(5,6);
        Ponto[] p0s = new Ponto[]{p0,p1,p2,p3};
        Quadrado q0 = new Quadrado(p0s);
        Orientation o0 = new Orientation(); //RIGHT
        SnakeBodyPart sbp0 = new SnakeBodyPart(q0); //starts to the RIGHT (default)
        o0.turnRight();
        sbp0.changeDirection("RIGHT");
        assertEquals(sbp0.o().i(), o0.i());
        o0.turnRight();
        sbp0.changeDirection("RIGHT");
        assertEquals(sbp0.o().i(), o0.i());
        o0.turnLeft();
        sbp0.changeDirection("LEFT");
        assertEquals(sbp0.o().i(), o0.i());
        o0.turnLeft();
        sbp0.changeDirection("LEFT");
        assertEquals(sbp0.o().i(), o0.i());
        o0.turnLeft();
        sbp0.changeDirection("LEFT");
        assertEquals(sbp0.o().i(), o0.i());
        o0.turnLeft();
        sbp0.changeDirection("LEFT");
        assertEquals(sbp0.o().i(), o0.i());
        o0.turnLeft();
        sbp0.changeDirection("LEFT");
        assertEquals(sbp0.o().i(), o0.i());
        o0.turnLeft();
        sbp0.changeDirection("LEFT");
        assertEquals(sbp0.o().i(), o0.i());
    }
}