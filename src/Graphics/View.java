package Graphics;

import java.awt.*;
import javax.swing.*;
import OOPS.*;

/**
 * Class that defines the Graphics.View
 * This class will use the Graphics.GamePanel and Graphics.MenuPanel classes to represent the different stages of the game
 * @version v0.0    19/05/2024
 * @author Jose Diogo Ferras, Miguel Silva, Brandon Mejia
 */
public class View extends JFrame
{
    private GamePanel gamePanel;
    private MenuPanel menuPanel;
    private Assets assets = new Assets();

    private boolean firstGame = true;
    /**
     * Graphics.View constructor
     */
    public View(Game g)
    {
        setTitle("OOPSnake Game");
        setIconImage(Assets.icon.getImage());

        menuPanel = new MenuPanel();
        gamePanel = new GamePanel(g);

        this.setResizable(true);

        //Jframe config
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(menuPanel, BorderLayout.NORTH);
        add(gamePanel, BorderLayout.CENTER);


        menuPanel.addStartButtonListener(e -> {
            if (firstGame) {
                gamePanel.startTimer();
                gamePanel.requestFocus();
                firstGame = false;
            }
            else
            {

                gamePanel.setGame(new Game(g.cellLength(), g.n(), g.m(), g.obstacles(), false));
                gamePanel.repaint();
                gamePanel.startTimer();
                gamePanel.requestFocus();
                gamePanel.setPreferredSize(new Dimension(g.maxX(), g.maxY()));
                pack();
                setLocationRelativeTo(null);
            }
        });

        menuPanel.addOptionsButtonListener(e -> {
            gamePanel.stopTimer();
            configureGame();
            gamePanel.startTimer();
            gamePanel.requestFocus();
        });

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Happens when someone clicks on the Custom Start Button
     */
    private void configureGame() {
        int cellLength = Integer.parseInt(JOptionPane.showInputDialog("Insira o comprimento da parte do corpo da cobra:\nDEFAULT: 50"));
        int h = Integer.parseInt(JOptionPane.showInputDialog("Insira quantos espaços a cobra se moverá horizontalmente:\nDEFAULT: 10"));
        int v = Integer.parseInt(JOptionPane.showInputDialog("Insira quantos espaços a cobra se moverá verticalmente:\nDEFAULT: 10"));
        int map = Integer.parseInt(JOptionPane.showInputDialog("Insira o mapa pré-definido: [0] [1] [2] [3] [4]\nDEFAULT: [4] (Vazio)"));
        int mode = Integer.parseInt(JOptionPane.showInputDialog("Insira o modo de jogo: [1] Manual\n[2] Automático\nDEFAULT: [1]):"));

        if (cellLength < 2 || h < 4 || v < 4 || map < 0 || map > 4 || (mode != 1 && mode != 2)) {
            JOptionPane.showMessageDialog(null, "Por favor, insira valores válidos.");
            return;
        }

        Obstacle[] obstacles = Obstacle.getDefaultSet(map, h, v, cellLength);
        boolean control;
        if(mode == 1){
            control = false;
        }
        else{
            control = true;
        }
        Game newGame = new Game(cellLength, h, v, obstacles, control);
        gamePanel.setGame(newGame);
        Assets.resizeAssets(newGame.cellLength());
        gamePanel.repaint();

        //uppdates the preferredSize of gamePanel and packs the JFrame to adapt the frame size with the new panel
        gamePanel.setPreferredSize(new Dimension(newGame.maxX(), newGame.maxY()));
        pack();
        setLocationRelativeTo(null);
    }

}
