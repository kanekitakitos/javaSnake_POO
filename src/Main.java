
import Graphics.View;
import OOPS.Game;
import OOPS.Obstacle;

import java.io.IOException;

/**
 * Class that defines the game
 */
public class Main {
    /**
     * Main method
     * @param args arguments
     * @throws IOException exception
     */
    public static void main(String[] args) throws IOException
    {

        int cellLength = 50;
        int h = 10;
        int v = 10;
        int map = 4;

        Obstacle[] obstacles = Obstacle.getDefaultSet(map, h, v, cellLength);
        Game game = new Game(cellLength, h, v, obstacles, false);

        // Cria a visão do jogo
        new View(game);

        /*
        Grid gr = new Grid(cellLength*v,cellLength*h);
        gr.interpretGame(g);


        long now;
        long lastTime = System.currentTimeMillis();
        double dif;


        while(true){
            now = System.currentTimeMillis();
            dif = now - lastTime;

            if((c == 2 && dif >= 100) || (c == 1 && dif >= 750)){
                g.update();
                gr.interpretGame(g);
                lastTime = System.currentTimeMillis();
            }
        }
         */
    }

/*
    private static void isvalidifrotated(Quadrado q1, int angle, Ponto ponto) {
        try {
            while (angle < 360) {
                q1 = q1.rotate(angle, ponto);
                angle += angle;
            }
            System.out.println("O QUADRADO INTRODUZIDO VAI DAR");
        } catch (ArithmeticException e) {
            System.out.println("O QUADRADO INTRODUZIDO NAO VAI DAR");
        }
    }

    private static void isvalidPolygon(Poligono p, int angle, Ponto ponto) {
        try {
            while (angle < 360) {
                p = (Poligono) p.rotate(angle, ponto);
                angle += angle;
            }
            System.out.println("O POLIGONO INTRODUZIDO VAI DAR");
        } catch (ArithmeticException e) {
            System.out.println("O POLIGONO INTRODUZIDO NAO VAI DAR");
        }
    }

 */


}

/*
        Number sequence for seed 0 with origin 0 and bound 245 (used for Game of maxX = 250 and maxY = 250)
145
95
75
0
95
65
50
210
105
45
20
140
100
35
200
85
60
210
110
210
80
180
95
170
140
135
190
150
210
15
35
185
210
40
135
225
10
100
5
85
70
75
115
35
120
35
90
20
130
75
190
60
145
95
135
135
180
170
200
210
75
35
20
225
245
130
5
70
40
75
75
40
200
135
155
20
30
155
210
115
220
85
210
30
5
135
150
30
180
150
145
90
120
45
230
205
165
50
230
15
 */