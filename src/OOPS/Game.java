package OOPS;
import Geometrics.*;
import OOPS.SnakeBodyPart.Orientation;
import Graphics.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Class that defines the Game
 * @author Jose Ferras, Miguel Silva, Brandon Mejia
 * @pre cellLength >= 2
 * @pre obstacles belong to the game
 * @version v0.0    17/04/2024
 */
public class Game {

    private int n;
    private int m;
    private int cellLength;
    private Obstacle[] obstacles;
    private Snake s;
    private Fruit f;
    private int score;
    private int maxX;
    private int maxY;
    private Retangulo arena;
    private Control control;
    private boolean gameIsOver;

    private BufferedImage food = null;

    /**
     * Constructor
     * @param cellLength length of each cell
     * @param n number of horizontal cells
     * @param m number of vertical cells
     * @param obstacles array of obstacles
     * @param control boolean that defines if the game is controlled by the player or by the computer
     */
    public Game(int cellLength, int n, int m, Obstacle[] obstacles, boolean control) {
        this.n = n;
        this.m = m;
        this.cellLength = cellLength;
        this.obstacles = obstacles;
        this.score = 0;
        this.maxX = n*cellLength;
        this.maxY = m*cellLength;
        this.arena = new Retangulo(new Ponto[]{new Ponto(0,0), new Ponto(this.maxX,0), new Ponto(this.maxX,this.maxY), new Ponto(0,this.maxY)});
        this.s = createSnake(cellLength, this.maxX, this.maxY, obstacles);

        if(control)
            this.control = new AutoControl(this);
        else
            this.control = new ManualControl(this);

        summonFruit();
    }

    /**
     * Will create a snake for the game, with a random position and orientation.
     * Makes sure it isn't created somewhere it shouldn't
     * @param cellLength length of each cell
     * @param maxX max X coordinate possible
     * @param maxY max Y coordinate possible
     * @param obstacles array of obstacles
     * @return Snake
     */
    private static Snake createSnake(int cellLength, int maxX, int maxY, Obstacle[] obstacles){
        Random r = new Random();
        int rX;  //random x
        int rY;  //random y
        Ponto rSpawn;   //random spawn
        SnakeBodyPart.Orientation rO; //random orientations
        Snake s = null;    //snake
        boolean safePostion = false;
        while(!safePostion){
            rX = (int)(cellLength*Math.round((double)r.nextInt(0, maxX-cellLength)/cellLength));    //bottom left
            rY = (int)(cellLength*Math.round((double)r.nextInt(0, maxY-cellLength)/cellLength));    //bottom left
            rSpawn = new Ponto(rX,rY);
            //here the orientation will be decided in a random way but making sure it always points at the furthest of walls
            //(depending if its vertical or horizontal given the random boolean)
            if(r.nextBoolean()){
                //horizontal
                if(rX > maxX/2){
                    //left
                    rO = new Orientation(2);
                }
                else{
                    //right
                    rO = new Orientation(0);
                }
            }
            else{
                //vertical
                if(rY > maxY/2){
                    //down
                    rO = new Orientation(1);
                }
                else{
                    //up
                    rO = new Orientation(3);
                }
            }
            s = new Snake(cellLength, rSpawn, rO);
                if(!s.intersects(obstacles))   //if the created snake doesn't intersect with the obstacles then this snake is good to go
                    safePostion = true;

        }
        return s;
    }

    /**
     * Getter of n
     * @return this.n
     */
    public int n(){
        return this.n;
    }

    /**
     * Getter of control
     * @return this.control
     */
    public Control control(){
        return this.control;
    }

    /**
     * Getter of m
     * @return this.m
     */
    public int m(){
        return this.m;
    }

    /**
     * Getter of cellLength
     * @return this.cellLength
     */
    public int cellLength(){
        return this.cellLength;
    }

    /**
     * Getter of obstacles
     * @return this.obstacles
     */
    public Obstacle[] obstacles()
    {
        return this.obstacles;
    }

    /**
     * Getter of Snake s
     * @return this.s
     */
    public Snake s(){
        return this.s;
    }

    /**
     * Getter of Fruit
     * @return this.f
     */
    public Fruit f(){
        return this.f;
    }

    /**
     * Getter of score
     * @return this.score
     */
    public int score(){
        return this.score;
    }

    /**
     * Getter of maxX
     * @return this.maxX
     */
    public int maxX(){
        return this.maxX;
    }

    /**
     * Getter of maxY
     * @return this.maxY
     */
    public int maxY(){
        return this.maxY;
    }

    /**
     * Getter of gameIsOver
     * @return gameIsOver
     */
    public boolean gameIsOver(){
        return gameIsOver;
    }

    /**
     * Updates the game state.
     */
    public void update()
    {
        this.handleControl();
        this.tryToEat();    //try to eat fruit

        try
        {
            this.s.update();    //update snake
        }
        //if this exception is catched it means that the snaked tried to grow to negative values,
        //which counts as outOfBounds
        catch(ArithmeticException e)
        {
            this.gameOver();
        }
            for(Obstacle o : this.obstacles)  //update each obstacle
                o.update();

        if(this.s.intersects(this.obstacles) || this.snakeOutOfBounds() || this.s.intersectsItself()){
            this.gameOver();
        }
    }

    /**
     * Summon Fruit
     */
    public void summonFruit()
    {
        Random r = new Random();
        int rX;  //random x
        int rY;  //random y
        Ponto rSpawn;   //random spawn
        int fruitLength = cellLength/2;
        int fruitShape = 0;
        boolean safePostion = false;
        while(!safePostion){
            rX = (int) ((fruitLength*(Math.round((double)r.nextInt(0, maxX-fruitLength)/fruitLength))));
            rY = (int) ((fruitLength*(Math.round((double)r.nextInt(0, maxY-fruitLength)/fruitLength))));
            rSpawn = new Ponto(rX,rY);
            //this is so it always spawns in a place where the snake will completely contain the fruit
            int nQuadranteX = rX / cellLength;
            int xQuadrante = nQuadranteX * cellLength;
            int nQuadranteY = rY / cellLength;
            int yQuadrante = nQuadranteY * cellLength;
            if(rX - xQuadrante > (cellLength-fruitLength) || rY - yQuadrante > (cellLength-fruitLength)){
                continue;
            }

            //if its even then a square is created, if its uneven then a circle is created
            if(fruitShape % 2 == 0){
                Ponto p0 = new Ponto(rX,rY); //bottom left
                Ponto p1 = new Ponto(rX + fruitLength,rY); //bottom right
                Ponto p2 = new Ponto(rX + fruitLength,rY + fruitLength); //top right
                Ponto p3 = new Ponto(rX,rY + fruitLength); //top left
                this.f = new Quadrado(new Ponto[]{p0,p1,p2,p3});
            }
            else{
               this.f = new Circulo(new Ponto(rX + ((double) fruitLength /2),rY + ((double) fruitLength /2)), ((double) fruitLength /2));
            }
            //verifies if the created fruit intersects with any obstacle.
            boolean intersects = false;
            for(Obstacle o : obstacles){
                if(fruitIntersectsAnyAngles(o) || this.f.isContainedIn(o.p())){
                    intersects = true;
                    break;
                }
            }
            //if it doesnt intersect with an obstacle verifies if it intersects with the snake itself
            if(!intersects){
                for(SnakeBodyPart sbp : this.s.body()){
                    if(this.f.intersects(sbp.q()) || this.f.isContainedIn(sbp.q())){
                        intersects = true;
                        break;
                    }
                }
            }
            //if it doesnt intersect then the fruit is in a safe position
            if(!intersects){
                safePostion = true;
            }
            fruitShape++;
        }
    }

    /**
     * This method creates, for each obstacle, 20 rectangles that represent the obstacles in the next 20 steps.
     * It then checks if the fruit is contained in any of these rectangles.
     * @param o Obstacle
     * @return true if it intersects, false if not
     */
    private boolean fruitIntersectsAnyAngles(Obstacle o)
    {
        //Eu quero guardar um array de 20 Poligonos, que vao ser retangulos sobrecritos aos pols
        Obstacle temp = new Obstacle(o.p(), o.p().findCentroid(), o.angle());
        Poligono[] forbiddenPositions = new Poligono[20];
        Ponto[] pontos = temp.p().pontos();
        for (int i = 0; i < 20; i++) {
            Retangulo r = Retangulo.envolvingRectangle(temp.p());
            forbiddenPositions[i] = new Poligono(r.pontos());
            r = expandArea(r);
            temp = new Obstacle(temp.p().rotate(temp.angle(), temp.c()), temp.p().findCentroid(), temp.angle());
        }

        for (Poligono p : forbiddenPositions) {
            if (this.f.isContainedIn(p)) {
                return true;
            }
        }
        return false;
    }


    /**
     * Expands the area of the rectangle in order for the fruit to spawn in a safe position
     * @param r Retangulo
     * @return expanded rectangle
     */
    private Retangulo expandArea(Retangulo r)
    {
        Ponto centro = r.findCentroid();
        Ponto[] pontos = r.pontos();
        Ponto[] newPontos = new Ponto[4];

        for (int i = 0; i < 4; i++) {
            Ponto p = pontos[i];
            double x = p.x();
            double y = p.y();
            if (x < centro.x()) {
                x -= (double) cellLength /2;
            } else {
                x += (double) cellLength /2;
            }
            if (y < centro.y()) {
                y -= (double) cellLength /2;
            } else {
                y += (double) cellLength /2;
            }
            if (x < 0) {
                x = 0;
            }
            if (y < 0) {
                y = 0;
            }
            newPontos[i] = new Ponto(x, y);
        }
        return new Retangulo(newPontos);

    }



    /**
     * Verification if the snake is able to eat the fruit
     */
    public void tryToEat()
    {
        if(this.f.isContainedIn(this.s.head().q()))
        {
            this.s.grow();
            this.score+=50;
            summonFruit();
        }
    }

    /**
     * Verifies if the snake is contained in the game arena
     */
    private boolean snakeOutOfBounds(){
        return !this.s.body().get(0).isContainedIn(this.arena);
    }

    /**
     * Game Over. it will be called when the snake collides with an obstacle.
     */
    private void gameOver()
    {
        this.gameIsOver = true;
        /*
        Leaderboard lb = new Leaderboard();
        System.out.println("ENTER YOUR NAME: ");
        String name = new java.util.Scanner(System.in).nextLine();
        lb.setScore(name, this.score);
        lb.print();
        lb.write();
        //System.exit(0);

         */
    }

    /**
     * Handles the control of the snake
     * This will be called in update() method. It calls c.handle(this) and then changes the direction of the snake accordingly.
     */
    private void handleControl() {
        int c = this.control.handle();
        if(c == 1){
            this.s.changeDirection("LEFT");
        }
        else if(c == 2){
            this.s.changeDirection("RIGHT");
        }
    }

    /**
     * Draw method to help represent the game in the GUI
     * @param g Graphics2D
     */
    public void draw(Graphics2D g)
    {
        
        g.setColor(Color.BLUE);
        for(Obstacle o : this.obstacles){
            o.draw(g, this.maxY);
        }
        g.setColor(Color.RED);
        this.f.draw(g, this.maxY);
        g.setColor(Color.GREEN);
        this.s.draw(g, this.maxY);
    }


    /**
     * Draw method to help represent the game in the GUI
     * @param g Graphics2D
     */
    public void drawImage(Graphics2D g)
    {
        g.setColor(Color.RED);
        for(Obstacle o : this.obstacles)
            o.draw(g, this.maxY);


        if(this.f.isContainedIn(this.s.head().q()) || food == null )
        {
            Random r = new Random();
            food = Assets.fruit.get(r.nextInt(0,999)%Assets.fruit.size());
        }


        this.f.draw(g, food, this.maxY,0);


        this.s.draw(g,Assets.snakeHead,Assets.snakeBody,Assets.snakeTail,this.maxY);
    }






    //-------------------------
    //Setters para a cabeça da snake e para a fruit para uso exclusivo de testes.
    //-------------------------

    /**
     * Setter of the snake head
     * @param newHead new head of the snake
     */
    public void setHead(SnakeBodyPart newHead){
        this.s.body().set(0, newHead);
    }

    /**
     * Setter of the snake body, except the head
     * @param newBody new body of the snake
     */
    public void setBody(SnakeBodyPart[] newBody){
        SnakeBodyPart oldHead = this.s.body().get(0);
        this.s.body().clear();
        this.s.body().add(oldHead);
        for (int i = 0; i < newBody.length; i++){
            this.s.body().add(newBody[i]);
        }

    }

    /**
     * Setter of the fruit
     * @param newFruit new fruit
     */
    public void setFruit(Fruit newFruit){
        this.f = newFruit;
    }

    /**
     * Force grow the snake
     */
    public void forceGrow() {
        this.s.grow();
    }

}