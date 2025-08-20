package OOPS;

import Geometrics.Circulo;
import Geometrics.Ponto;
import Geometrics.Quadrado;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GameTests {

    //variaveis globais
    //Incremento por comer fruta <- 5

    int eatingScore = 50;

    //OOPS_0
    @Test
    void createSnake0(){
        //1 cellLength = 5
        //Crio um Game de 50x50 com 0 obstaculos
        Game g = new Game(5,50,50, new Obstacle[]{}, false);
        //Ao criar o game ele fez uma snake num local aleatorio.
        //Vou buscar a snake e verificar se a sua cabeça está num local aleatorio
        Snake s = g.s();
        Ponto p = s.head().q().findCentroid();
        assertTrue((p.x() >= 0 && p.x() <= (5*50)) && (p.y() >= 0 && p.y() <= (5*50)));
    }

    //OOPS_0
    @Test
    void summonFruit() {
        //FOR THESE TESTS TO WORK THE SEED OF THE RANDOM NUMBER GENERATOR WILL HAVE TO BE 0
        //THE NUMBER OF THE SEQUENCE ARE NOTED IN THE MAIN FUNCTION
        Quadrado q0 = new Quadrado("115 115 135 115 135 135 115 135");
        Quadrado q1 = new Quadrado("50 50 100 50 100 100 50 100");
        Obstacle o0 = new Obstacle(q0);
        Obstacle o1 = new Obstacle(q1);
        //will create a 250x250 game with 2 obstacles
        Game g = new Game(5,50,50, new Obstacle[]{o0,o1}, false);
        g.summonFruit(); //random generations done by this will be 145 and 95 (95 will pass)
        assertFalse(g.f().intersects(o0.p()));
        g.summonFruit(); //random generations done by this will be 75 and 0 (0 will pass)
        assertFalse(g.f().intersects(o1.p()));
    }

    //OOPS_0
    @Test
    void tryToEat0() { //TESTE EM QUE NÃO DÁ PARA COMER A FRUTA
        //1 cellLength = 5
        //Crio um Game de 50x50 com 0 obstaculos
        Game g = new Game(5,50,50, new Obstacle[]{}, false);
        //Ao criar o game ele fez uma snake num local aleatorio.
        //Tambem coloca uma fruit num local aleatorio
        //Buscar a cabeça e colocá-la num sitio por exemplo 33x33
        Quadrado newSquare = g.s().head().q();
        Quadrado newSquare2 = newSquare.translate(33,33);
        SnakeBodyPart newHead = new SnakeBodyPart(newSquare2);
        g.setHead(newHead);

        if (g.f().isSquare()){
            //é quadrado. por isso coloco o quadrado da fruit num sitio por exemplo 33x33
            Quadrado newSquare3 = (Quadrado) g.f();
            Fruit newFruit = newSquare3.translate(33,35);
            g.setFruit(newFruit);
            int scoreAntes = g.score();
            int scoreDepois;
            g.tryToEat();
            scoreDepois = g.score();
            assertEquals(scoreAntes, scoreDepois);
        }
        else
        {
            //nao é quadrado, pelo que é um circulo.
            Circulo newCircle = (Circulo) g.f();
            Fruit newFruit = newCircle.translate(33,35);
            g.setFruit(newFruit);
            int scoreAntes = g.score();
            int scoreDepois;
            g.tryToEat();
            scoreDepois = g.score();
            assertEquals(scoreAntes, scoreDepois);
        }
    }

    //OOPS_0
    @Test
    void testGameOver0() { // a cobra nao colide com nada ( collisao da parede )
        Game g = new Game(5,100,100, new Obstacle[]{}, false); //criar um game de 100x100
        SnakeBodyPart sbp = g.s().head();
        Ponto centerSnakeHead = sbp.q().findCentroid();
        // a cobra deve cumprir com estas coordenadas para nao passar o limite
        boolean gameOver = centerSnakeHead.x() < g.maxY() && centerSnakeHead.y() < g.maxY()
                            && centerSnakeHead.x() > 0 && centerSnakeHead.y() > 0;
        assertTrue(gameOver);
    }

}