package OOPS;

import Geometrics.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FruitTest {

    //OOPS_0
    @Test
    void isContainedIn_Sq1() {
        //1 cellLength = 5
        //Crio um Game de 50x50 com 0 obstaculos
        Game g = new Game(5,50,50, new Obstacle[]{}, false);
        //Ao criar o game ele fez uma snake num local aleatorio.
        //Tambem coloca uma fruit num local aleatorio

        //Buscar a cabeça e colocá-la num sitio por exemplo 33x33
        Quadrado newSquare = g.s().head().q();
        Quadrado newSquare2 = newSquare.translate(33,33);
        SnakeBodyPart newHead = new SnakeBodyPart(newSquare2);
        //Vou buscar a fruit e colocá-la num sitio por exemplo 33x33
        Quadrado newSquareF = (Quadrado) g.f();
        Fruit newFruit = newSquareF.translate(33,44);
        assertFalse(newFruit.isContainedIn(newSquare2));
    }


    //OOPS_0
    @Test
    void intersects_Sq() {
        Fruit f = new Quadrado(new Ponto[]{new Ponto(0,0), new Ponto(1,0), new Ponto(1,1), new Ponto(0,1)});
        Quadrado q = new Quadrado(new Ponto[]{new Ponto(5,5), new Ponto(6,5), new Ponto(6,6), new Ponto(5,6)});
        assertFalse(f.intersects(q));
    }

    //OOPS_0
    @Test
    void intersects_Cir1() {
        Fruit f = new Circulo(new Ponto(3, 3), 2);
        Quadrado q = new Quadrado(new Ponto[]{new Ponto(8, 8), new Ponto(9, 8), new Ponto(9, 9), new Ponto(8, 9)});
        assertFalse(f.intersects(q));
    }

    //OOPS_0
    @Test
    void isSquare() {
        Fruit f = new Quadrado(new Ponto[]{new Ponto(0,0), new Ponto(1,0), new Ponto(1,1), new Ponto(0,1)});
        assertTrue(f.isSquare());
    }

    //OOPS_0
    @Test
    void isSquare1() {
        Fruit f = new Circulo(new Ponto(5, 5), 4);
        assertFalse(f.isSquare());
    }

}