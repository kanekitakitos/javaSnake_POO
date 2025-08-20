package OOPS;

import org.junit.jupiter.api.Test;
import OOPS.SnakeBodyPart.Orientation;

import static org.junit.jupiter.api.Assertions.*;

class OrientationTests {

    //OOPS_0
    @Test
    void testI() {
        Orientation o0 = new Orientation();
        assertEquals(o0.i(), 0);
        Orientation o1 = new Orientation(2);
        assertEquals(o1.i(), 2);
    }

    //OOPS_0
    @Test
    void testTurnLeft() {
        Orientation o0 = new Orientation();
        o0.turnLeft();
        assertEquals(o0.i(), 3);
        o0.turnLeft();
        assertEquals(o0.i(), 2);
        o0.turnLeft();
        assertEquals(o0.i(), 1);
        o0.turnLeft();
        assertEquals(o0.i(), 0);
    }

    //OOPS_0
    @Test
    void testTurnRight() {
        Orientation o0 = new Orientation();
        o0.turnRight();
        assertEquals(o0.i(), 1);
        o0.turnRight();
        assertEquals(o0.i(), 2);
        o0.turnRight();
        assertEquals(o0.i(), 3);
        o0.turnRight();
        assertEquals(o0.i(), 0);
    }

    //OOPS_0
    @Test
    void testInvert() {
        Orientation o0 = new Orientation();
        o0.invert();
        assertEquals(o0.i(), 2);
        o0.invert();
        assertEquals(o0.i(), 0);
        Orientation o1 = new Orientation(1);
        o1.invert();
        assertEquals(o1.i(), 3);
        o1.invert();
        assertEquals(o1.i(), 1);
    }
}