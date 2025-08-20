package OOPS;


import Geometrics.*;
import Geometrics.Quadrado;
import OOPS.SnakeBodyPart.Orientation;
import Graphics.*;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Class that defines a snake body part with a square and it's orientation.
 * @author Jose Diogo Ferras, Miguel Silva, Brandon Correia
 * @version v0.0    13/04/2024
 */
public class SnakeBodyPart {
    private Quadrado q;
    private Orientation o;
    private int cellLength;

    /**
     * Constructor
     *
     * @param q Geometrics.Quadrado
     *          Starts with default orientation (right)
     */
    public SnakeBodyPart(Quadrado q) {
        this.q = new Quadrado(q.pontos());
        this.o = new Orientation();
        this.cellLength = 0;
    }

    /**
     * Constructor
     *
     * @param q Geometrics.Quadrado
     * @param o Orientation
     */
    public SnakeBodyPart(Quadrado q, Orientation o) {
        this.q = new Quadrado(q.pontos());
        this.o = new Orientation(o.i());
        this.cellLength = 0;
    }

    /**
     * Getter of q
     *
     * @return copy of this.q
     */
    public Quadrado q() {
        return new Quadrado(this.q.pontos());
    }

    /**
     * Getter of o
     *
     * @return copy of this.o
     */
    public Orientation o() {
        return new Orientation(this.o.i());
    }

    /**
     * Getter of cellLength
     * Computes the cellLength if it hasnt already been calculated, if it has, it just returns it
     *
     * @return this.cellLength
     */
    public int cellLength() {
        if (this.cellLength == 0) {
            this.cellLength = (int) this.q.pontos()[0].dist(this.q.pontos()[1]);
        }
        return this.cellLength;
    }

    /**
     * Update method
     */
    public void update() {
        //right
        if (this.o.i() == 0) {
            this.o.angle = 0;
            this.q = this.q.translate(this.cellLength(), 0);
        }
        //down
        else if (this.o.i() == 1) {
            this.o.angle = 270;
            this.q = this.q.translate(0, -this.cellLength());
        }
        //left
        else if (this.o.i() == 2) {
            this.o.angle = 180;
            this.q = this.q.translate(-this.cellLength(), 0);
        }
        //up
        else if (this.o.i() == 3) {
            this.o.angle = 90;
            this.q = this.q.translate(0, this.cellLength());
        }
    }

    /**
     * Change the direction of the snakeBodyPart
     * if d == "LEFT" calls turnLeft method
     * if d == "RIGHT" calls turnRight method
     * else nothing happens
     *
     * @param d String
     */
    public void changeDirection(String d)
    {
        if (d.equals("LEFT")) {
            this.o.turnLeft();
            this.q.rotate(-90);
            this.o.angle -=90;
        } else if (d.equals("RIGHT")) {
            this.o.turnRight();
            this.q.rotate(90);
            this.o.angle+=90;
        }
    }

    /**
     * Makes this sbp follow that sbp (orientation wise)
     *
     * @param that SnakeBodyPart
     */
    public void follow(SnakeBodyPart that) {
        this.o.follow(that.o);
    }

    /**
     * Verifies if the SnakeBodyPart is contained in that Poligono
     *
     * @param that Poligono
     * @return true if it is, false if not
     */
    public boolean isContainedIn(Poligono that) {
        return this.q.isContainedIn(that);
    }

    /**
     * Verifies if the SnakeBodyPart intersects any of the obstacles in the array
     *
     * @param o obstacles
     * @return true if it does, false if not
     */
    public boolean intersects(Obstacle[] o)
    {
        for (Obstacle o0 : o)
        {
            if (this.q.intersects(o0.p())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Draw method to help represent a snake body part in the GUI
     * @param g Graphics2D
     * @param maxY need this value because Y are the inverse of what they will be represented as
     */
    public void draw(Graphics2D g, int maxY)
    {
        this.q.draw(g, maxY);
    }

    /**
     * Draw method to help represent a snake body part in the GUI
     * @param g Graphics2D
     * @param img BufferedImage
     * @param maxY need this value because Y are the inverse of what they will be represented as
     */
    public void draw(Graphics2D g, BufferedImage img, int maxY, double angle)
    {
        this.q.draw(g,img ,maxY, angle);
    }

    /**
     * Class that defines a orientation of a game object.
     *
     * @author Jose Diogo Ferras, Miguel Silva, Brandon Correia
     * @version v0.0    13/04/2024
     */
    public static class Orientation {
        //0 - right | 1 - down | 2 - left | 3 - up
        private final int[] o = {0, 1, 2, 3};
        private int i;
        private double angle;


        /**
         * Constructor
         * Default orientation is right (0)
         */
        public Orientation() {
            this.angle = 0;
            this.i = 0;
        }

        /**
         * Constructor
         *
         * @param i orientation
         *          Sets the received orientation
         */
        public Orientation(int i)
        {
            if(i == 0)
                this.angle = 0;
            else if(i == 1)
                this.angle = 90;
            else if(i == 2)
                this.angle = 180;
            else if(i == 3)
                this.angle = 270;

            this.i = i;
        }

        /**
         * Getter of i
         *
         * @return this.i
         */
        public int i() {
            return this.i;
        }

        /**
         * Turns the orientation to the left (counter clock wise)
         */
        public void turnLeft()
        {
            this.angle = 180;
            this.i = (this.i + 3) % 4;
        }

        /**
         * Turns the orientation to the right (clock wise)
         */
        public void turnRight() {
            this.angle = 0;
            this.angle = (this.angle - 90) % 360;
            this.i = (this.i + 1) % 4;
        }

        /**
         * Inverts the orientation
         */
        public void invert()
        {
            this.angle = this.angle*-1;
            this.angle = (this.angle + 180) % 360;
            this.i = (this.i + 2) % 4;
        }

        /**
         * This method makes the current Orientation follow that Orientation
         *
         * @param that Orientation
         */
        public void follow(Orientation that) {
            this.angle = that.angle;
            this.i = that.i;
        }

        /**
         * Equals method
         *
         * @param that Orientation
         * @return true if they are equally oriented, false if not
         */
        public boolean equals(Orientation that) {
            return this.i == that.i;
        }

        /**
         * Getter of the top right point of the square
         * @return top right point
         */
        public double angle()
        {
            return angle;
        }

    }
}