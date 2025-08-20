package OOPS;

import javax.swing.*;

/**
 * Class that makes the program able to listen to keyboard presses.
 * @author Jose Ferras, Miguel Silva, Brandon Mejia
 * @version v0.0    08/05/2024
 */
public class KeyboardListener {
    /**
     * Constructor
     * Creates a JFrame that listens to keyboard presses
     */
    public KeyboardListener(){
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setFocusable(true);
        frame.setSize(1,1);
        frame.addKeyListener(new ManualControl.Keyboard());
    }
}
