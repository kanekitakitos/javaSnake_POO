package OOPS;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
/**
 * Class that defines the manual control of the game.
 * @author Jose Diogo Ferras, Miguel Silva, Brandon Correia
 * @version v0.0    13/04/2024
 */
public class ManualControl implements Control {

    static int lastPressed = 0;
    static final int RIGHT = 0;
    static final int DOWN = 1;
    static final int LEFT = 2;
    static final int UP = 3;
    Game g;

    /**
     * Constructor of the manual control
     * @param g Game
     */
    public ManualControl(Game g) {
        //new KeyboardListener();
        this.g = g;
    }

    /**
     * Handles the input of the player
     */
    @Override
    public int handle() { //vamos precisar do game para ver a direçao atual da snake
        //last pressed key is RIGHT
        if(lastPressed == KeyEvent.VK_RIGHT){
            //snake is pointed at left or right
            if(g.s().head().o().i() == RIGHT || g.s().head().o().i() == LEFT){
                return 0;
            }
            //snake is pointing down
            if(g.s().head().o().i() == DOWN){
                return 1;
            }
            if(g.s().head().o().i() == UP){
                return 2;
            }
        }
        //last pressed key is DOWN
        if(lastPressed == KeyEvent.VK_DOWN){
            //snake is pointed up or down
            if(g.s().head().o().i() == DOWN || g.s().head().o().i() == UP){
                return 0;
            }
            //snake is pointing left
            if(g.s().head().o().i() == LEFT){
                return 1;
            }
            //snake is pointing right
            if(g.s().head().o().i() == RIGHT){
                return 2;
            }
        }
        //last pressed key is LEFT
        if(lastPressed == KeyEvent.VK_LEFT){
            //snake is pointed left or right
            if(g.s().head().o().i() == LEFT || g.s().head().o().i() == RIGHT){
                return 0;
            }
            //snake is pointing up
            if(g.s().head().o().i() == UP){
                return 1;
            }
            //snake is pointing down
            if(g.s().head().o().i() == DOWN){
                return 2;
            }
        }
        //last pressed key is UP
        if(lastPressed == KeyEvent.VK_UP){
            //snake is pointed up or down
            if(g.s().head().o().i() == UP || g.s().head().o().i() == DOWN){
                return 0;
            }
            //snake is pointing right
            if(g.s().head().o().i() == RIGHT){
                return 1;
            }
            //snake is pointing LEFT
            if(g.s().head().o().i() == LEFT){
                return 2;
            }
        }
        return 0;
    }

    /**
     * Class to help the ManualControl interpret the Keyboard interactions
     * @see KeyAdapter
     * @see KeyEvent
     * @see ManualControl
     */
    static public class Keyboard extends KeyAdapter
    {
        /**
         * Method that is called when a key is pressed
         * @param e KeyEvent
         */
        public void keyPressed(KeyEvent e)
        {
            if(e.getKeyCode() == KeyEvent.VK_UP)
            {
                if(lastPressed != KeyEvent.VK_DOWN )
                    lastPressed = KeyEvent.VK_UP;

            }
            else if(e.getKeyCode() == KeyEvent.VK_DOWN)
            {
                if(lastPressed != KeyEvent.VK_UP)
                    lastPressed = KeyEvent.VK_DOWN;
            }
            else if(e.getKeyCode() == KeyEvent.VK_LEFT)
            {
                if(lastPressed != KeyEvent.VK_RIGHT )
                    lastPressed = KeyEvent.VK_LEFT;
            }
            else if(e.getKeyCode() == KeyEvent.VK_RIGHT)
            {
                if(lastPressed != KeyEvent.VK_LEFT )
                    lastPressed = KeyEvent.VK_RIGHT;
            }
        }
    }
}
