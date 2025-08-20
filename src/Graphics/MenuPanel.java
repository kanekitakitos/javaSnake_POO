package Graphics;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Class that will define the Menu
 * that will be presented by Graphics.View
 * @version v0.0    19/05/2024
 * @author Jose Diogo Ferras, Miguel Silva, Brandon Mejia
 */
public class MenuPanel extends JPanel
{
    JButton startButton;
    JButton optionsButton;

    public MenuPanel()
    {
        setBackground(Color.white);
        //setFocusable(true);
        startButton = new JButton("START");
        optionsButton = new JButton("CUSTOM START");
        add(startButton);
        add(optionsButton);
    }

    public void addStartButtonListener(ActionListener listener){
        startButton.addActionListener(listener);
    }

    public void addOptionsButtonListener(ActionListener listener){
        optionsButton.addActionListener(listener);
    }

}
