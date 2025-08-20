package OOPS;

import Geometrics.*;
import jdk.dynalink.StandardNamespace;
import org.junit.jupiter.api.Test;
import OOPS.SnakeBodyPart.Orientation;

import static org.junit.jupiter.api.Assertions.*;

class GridTest {

    @Test
    void reset() {
        //O reset é suposto limpar o grid e e ficar com "x"  . . . . . . . . . . .
        //Por isso posso verificar se os charsAt são todos ". . . . . . . . . . ."

        Grid grid = new Grid(50,50);
        grid.reset();
        //PONTO ESPACO PONTO ESPAÇO
        for (int i = 0; i < 49; i++) {
            for (int j = 0; j < 50; j++) {
                assertEquals(grid.getCharAt(i,j), '.');
            }
        }

        //nao testo a ultima linha porque é a direção da snake e os pontos.

    }

    @Test
    void print() { //printa tudo no seu current state
        //Posso criar uma grid e uma snake. depois fazer updateSnake e depois fazer print

        Grid grid = new Grid(50,50);

        Snake s = new Snake(5,new Ponto(25,25),new Orientation(0));

        grid.updateSnake(s);
    }

    @Test
    void setCharAt() {
        Grid grid = new Grid(50,50);
        grid.setCharAt('H', 25, 25);
        assertEquals(grid.getCharAt(25,25), 'H');
    }

}