package OOPS;

import Geometrics.Ponto;
import Geometrics.Quadrado;
import OOPS.SnakeBodyPart.Orientation;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Class that defines the snake.
 * @author Jose Diogo Ferras, Miguel Silva, Brandon Correia
 * @version v0.0    13/04/2024
 * @pre cellLength >= 1
 */
public class Snake
{
    private ArrayList<SnakeBodyPart> body;
    private int cellLength;

    /**
     * Constructor
     * Snake always starts with one cell only
     * @param cellLength length of each cell (snakeBodyPart)
     * @param spawn spawn point of the snake (this will be the centroid of the first snakeBodyPart)
     * @param o initial orientation of the snake (this will be the initial orientation of the first snakeBodyPart)
     */
    public Snake(int cellLength, Ponto spawn, SnakeBodyPart.Orientation o){
        this.cellLength = cellLength;
        this.body = new ArrayList<>();
        Ponto p0 = new Ponto(spawn.x(), spawn.y()); //bottom left
        Ponto p1 = new Ponto(spawn.x() + cellLength, spawn.y()); //bottom right
        Ponto p2 = new Ponto(spawn.x() + cellLength, spawn.y() + cellLength); //top right
        Ponto p3 = new Ponto(spawn.x(), spawn.y() + cellLength); //top left
        Quadrado q = new Quadrado(new Ponto[]{p0, p1, p2, p3});
        this.body.add(new SnakeBodyPart(q, o));
    }

    /**
     * Getter of head
     * (first position of arrayList)
     * @return head of the snake
     */
    public SnakeBodyPart head(){
        return this.body.get(0);
    }

    /**
     * Getter of body
     * @return copy of the body
     */
    public ArrayList<SnakeBodyPart> body()
    {
        ArrayList<SnakeBodyPart> current = new ArrayList<>();
        for (int i = 0; i <this.body.size() ; i++)
            current.add(new SnakeBodyPart(this.body.get(i).q(), this.body.get(i).o()));

        return current;
    }

    /**
     * Grows the snake 1 cell
     */
    public void grow()
    {
        SnakeBodyPart sbp = this.body.get(this.body.size()-1);
        this.body.add(new SnakeBodyPart(new Quadrado(sbp.q().pontos()), new Orientation(sbp.o().i())));
    }

    /**
     * Updates the snake
     */
    public void update(){
        int n = this.body.size();
        //verify if we need to change the last Position or not (case where the snake has grown)
        if(n > 1 && this.body.get(n-1).q().equals(this.body.get(n-2).q())){
            n--;
        }
        SnakeBodyPart current = this.body.get(n-1);
        SnakeBodyPart next;
        for(int i = n-1; i >= 1 ; i--){
            next = this.body.get(i-1);  //next position is always i-1, because i will follow i-1
            current.update();   //updates the current position
            if(!current.o().equals(next.o())){   //if the current orientation is not the same as the next one then we change it
                current.follow(next);   //current follow next orientation
            }
            current = next;
        }
        this.body.get(0).update();  //updates the head
    }

    /**
     * Change the direction of the snake head
     * if d == "LEFT" calls turnLeft method
     * if d == "RIGHT" calls turnRight method
     * else nothing happens
     * @param d String
     */
    public void changeDirection(String d){
        //changing the direction of the snake is essentially changing the direction of the head of the snake
        this.body.get(0).changeDirection(d);
    }

    /**
     * Verifies if the snake (head) intersects any of the obstacles in the array
     * @param o obstacles
     * @return true if it does, false if not
     */
    public boolean intersects(Obstacle[] o){
        SnakeBodyPart head = this.head();
        if(head.intersects(o)){
            return true;
        }
        return false;
    }
    /**
     * Verifies if the snake intersects itself with it's head
     * @return true if it intersects itself, false if not
     */
    public boolean intersectsItself(){
        int n = this.body.size();
        if(n > 2){
            SnakeBodyPart head = this.head();
            for(int i = 1; i < n; i++){
                if(head.q().equals(this.body.get(i).q())){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Draw method to help represent a snake in the GUI
     * @param g Graphics2D
     * @param maxY need this value because Y are the inverse of what they will be represented as
     */
    public void draw(Graphics2D g, int maxY)
    {
        for (int i = 0; i < this.body.size(); i++)
        {
            if (i == 0)
                this.body.get(i).draw(g, maxY);
            else
                this.body.get(i).draw(g, maxY);
        }
    }

    /**
     * Draw method to help represent a snake in the GUI
     * @param g Graphics2D
     * @param head image of the head
     * @param body image of the body
     * @param tail image of the tail
     * @param maxY need this value because Y are the inverse of what they will be represented as
     */
    public void draw(Graphics2D g,BufferedImage head ,BufferedImage body,BufferedImage tail, int maxY)
    {
        for (int i = 0; i < this.body.size(); i++)
        {
            if(i == 0)
                this.body.get(i).draw(g, head, maxY,this.body.get(i).o().angle());
             else if(i == this.body.size()-1 && i != 0 )
                this.body.get(i).draw(g, tail, maxY,this.body.get(i).o().angle());
            else if( i != 0 && i != this.body.size()-1 )
                this.body.get(i).draw(g, body, maxY,this.body.get(i).o().angle());


        }
    }

}
