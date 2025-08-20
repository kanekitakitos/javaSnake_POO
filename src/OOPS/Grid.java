package OOPS;
import Geometrics.*;

import java.text.ParsePosition;


/**
 * Class that defines the grid.
 * @author Jose Diogo Ferras, Miguel Silva, Brandon Correia
 * @version v0.0    13/04/2024
 * @pre rows >= 1 && columns >= 1
 */
public class Grid {

    private int rows;
    private int columns;
    private char[][] grid;
    private int score;
    private int dirHead;

    /**
     * Constructor of Grid
     * @param rows number of rows
     * @param columns number of columns
     */
    public Grid(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.grid = new char[rows][columns];
        this.score = 0;
        reset();
    }

    /**
     * Reset Grid
     * Estrutura:
     * . . . . . . . . . . .
     * . . . . . . . . . . .
     * . . . . . . . . . . .
     * . T T T T T T H H H .
     * . T T T T T T H H H .
     * . T T T T T T H H H .
     * . . . . . . . . . . .
     * Dir H: 0 Pontos: 0
     */

    public void reset() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                grid[i][j] = '.';
            }
        }
    }

    /**
     * Print the entire grid in its current state to the console. Each line of the grid should be printed on a new line.
     */
    public void print() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(getCharAt(i, j) + " ");
            }
            System.out.println();
        }
        printScoreAndDirection();
    }

    /**
     * Print the score and the direction of the head of the snake (Last Line)
     * Exemplo: Dir H: 0               Pontos: 0
     */
    private void printScoreAndDirection() {
        //existem "rows" espaços. "Dir H: XXX" ocupa 7 + num chars do score e "Pontos: XXX" ocupa 8 + num chars do score
        int dirH = 7 + String.valueOf(dirHead).length();
        int pontos = 8 + String.valueOf(score).length();

        //dirH começa no 0 e os pontos é em rows-pontos

        System.out.print("Dir H: " + dirHead);
        for (int i = 0; i < (rows-pontos)*2; i++) {
            System.out.print(" ");
        }
        System.out.println("Pontos: " + score);


    }

    /**
     * Sets char in the desired location
     * @param c char
     * @param row row
     * @param column column
     */
    public void setCharAt(char c, int row, int column) {
        grid[row][column] = c;
    }

    /**
     * Gets char from the desired location
     * @param row row
     * @param column column
     * @return char in [row][column] position
     */
    public char getCharAt(int row, int column) {
        return grid[row][column];
    }

    /**
     * Interprets the game and updates the grid
     * @param game to interpret
     */
    public void interpretGame(Game game) {
        reset();
        updateObstacles(game.obstacles(), game.cellLength());
        updateFruit(game.f(), game.cellLength());
        updateSnake(game.s());
        updateScore(game.score());
        print();
    }

    /**
     * Updates the grid with the snake
     * @param snake snake to Update
     */
    public void updateSnake(Snake snake) {
        for (int i = snake.body().size()-1; i >= 0; i--) {
            SnakeBodyPart sbp = snake.body().get(i);
            if (i == 0) {
                dirHead = directionToAngle(sbp.o().i());
                setSimpleSquare(sbp.q(), 'H', sbp.cellLength());
            } else {
                setSimpleSquare(sbp.q(), 'T', sbp.cellLength());
            }
        }
    }

    /**
     * Converts a direction to an angle - Used in updateSnake, to after be used on the printScoreAndDirection method
     * @param i of direction
     * @return angle
     */
    private static int directionToAngle(int i) {
        switch (i) {
            case 0: //esq-dir
                return 0;
            case 1: //cima-baixo
                return 270;
            case 2: //dir-esq
                return 180;
            case 3: //baixo-cima
                return 90;
            default:
                return -1;
        }
    }

    /**
     * Sets a simple square in the grid. By simple it means that the sides are parallel to the x and y axis.
     * Used on the Fruit and SnakeBodyPart interpreting methods
     * @param q - Quadrado
     * @param c - char
     * @param side - side length, int.
     */
    private void setSimpleSquare(Quadrado q, char c, int side) { //side é o numero de indices que um lado do quadrado ocupa
        Ponto topLeft = q.topLeftPoint();
        int squareSide = (int) q.pontos()[0].dist(q.pontos()[1]); //tamanho entre os pontos no quadrado.

        int topleftX = (int) topLeft.x();
        int topleftY = this.rows - (int) (topLeft.y());

        for (int i = 0; i < side; i++) {
            for (int j = 0; j < side; j++) {
                setCharAt(c, topleftY + i, topleftX + j);
            }
        }
    }


    /**
     * Sets a polygon in the grid. The polygon is interpreted correctely independently of the angle of the sides.
     * @param p Poligono
     * @param c char
     */
    protected void setPolygon(Poligono p, char c)
    {
        Ponto[] pontos = p.pontos();
        int minX = (int) pontos[0].x();
        int minY = (int) pontos[0].y();
        int maxX = (int) pontos[0].x();
        int maxY = (int) pontos[0].y();

        for (Ponto ponto : pontos) {
            if (ponto.x() < minX) {
                minX = (int) ponto.x();
            }
            if (ponto.y() < minY) {
                minY = (int) ponto.y();
            }
            if (ponto.x() > maxX) {
                maxX = (int) ponto.x();
            }
            if (ponto.y() > maxY) {
                maxY = (int) ponto.y();
            }
        }

        for (int i = minY; i <= maxY; i++)
        {
            for (int j = minX; j <= maxX+1; j++)
            {
                Ponto p1 = new Ponto(j, i);
                if (p1.isContainedIn(p)) {
                    setCharAt(c, this.rows - 1 - i, j - 1);
                }
            }
        }

    }

    /**
     * Sets a circle in the grid.
     * @param cir Circulo
     * @param c char
     */
    protected void setCircle(Circulo cir, char c) {

        Ponto centro = cir.findCentroid();
        int raio = (int) cir.radius();
        int x = (int) centro.x();
        int y = (int) centro.y();

        if(cir.radius() == 0.5){
            setCharAt(c, this.rows-1-y,x);
            return;
        }
        else if(cir.radius() == 1){
            setSimpleSquare(new Quadrado(centro.x(),centro.y(),2), c, 2);
            return;
        }

        for (int i = y - raio; i <= y + raio; i++) {
            for (int j = x - raio; j <= x + raio; j++) {
                Ponto p = new Ponto(j, i);
                if (p.dist(centro) <= raio) {
                    setCharAt(c, this.rows - 1 - i, j);
                }
            }
        }
    }

    /**
     * Updates the grid with the fruit
     * @param fruit Fruit
     * @param cellLength cell length
     */
    public void updateFruit(Fruit fruit, int cellLength) {

        if (fruit.isSquare())
        {
            setSimpleSquare((Quadrado) fruit, 'F', cellLength/2);
        }
        else
        {
            setCircle((Circulo) fruit, 'F');
        }
    }



    /**
     * Updates the grid with the score (int)
     * @param score score
     */
    public void updateScore(int score) {
        this.score = score;
    }

    /**
     * Updates the grid with the obstacles
     * @param obstacles obstacles
     * @param cellLength cell length
     */
    public void updateObstacles(Obstacle[] obstacles, int cellLength) {
        for (Obstacle o : obstacles) {
            addObstacle(o,cellLength);
        }
    }

    /**
     * Adds an obstacle to the grid
     * @param obstacle obstacle
     * @param cellLength cell length
     */
    public void addObstacle(Obstacle obstacle, int cellLength) {
        Ponto[] pontos = obstacle.p().pontos();
        setPolygon(obstacle.p(), 'O');
    }


}