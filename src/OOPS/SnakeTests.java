package OOPS;

import Geometrics.Ponto;
import org.junit.jupiter.api.Test;
import OOPS.SnakeBodyPart.Orientation;

import static org.junit.jupiter.api.Assertions.*;

class SnakeTests {

    //OOPS_0
    @Test
    void testGrow() {
        Snake s0 = new Snake(1, new Ponto(0,0), new Orientation());
        assertEquals(s0.body().size(), 1);
        s0.grow();
        assertEquals(s0.body().size(), 2);
        s0.grow();
        assertEquals(s0.body().size(), 3);
        s0.grow();
        assertEquals(s0.body().size(), 4);
    }

    //OOPS_0
    @Test
    void testChangeDirection() {
        Orientation oRIGHT = new Orientation(0);
        Orientation oDOWN = new Orientation(1);
        Orientation oLEFT = new Orientation(2);
        Orientation oUP = new Orientation(3);
        Ponto p0 = new Ponto(5,5);
        Orientation o0 = new Orientation(2);//left
        Snake s0 = new Snake(1, p0, o0);
        assertEquals(s0.head().o().i(), oLEFT.i());
        s0.changeDirection("RIGHT");
        assertEquals(s0.head().o().i(), oUP.i());
        s0.changeDirection("RIGHT");
        assertEquals(s0.head().o().i(), oRIGHT.i());
        s0.changeDirection("RIGHT");
        assertEquals(s0.head().o().i(), oDOWN.i());
        s0.changeDirection("RIGHT");
        assertEquals(s0.head().o().i(), oLEFT.i());
        s0.changeDirection("LEFT");
        assertEquals(s0.head().o().i(), oDOWN.i());
        s0.changeDirection("LEFT");
        assertEquals(s0.head().o().i(), oRIGHT.i());
        s0.changeDirection("LEFT");
        assertEquals(s0.head().o().i(), oUP.i());
        s0.changeDirection("LEFT");
        assertEquals(s0.head().o().i(), oLEFT.i());
    }
}