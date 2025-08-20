package Graphics;

import OOPS.*;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Class that defines the Graphics.GamePanel
 * This class will use the Game to represent it as a panel
 * @version v0.0    19/05/2024
 * @author Jose Diogo Ferras, Miguel Silva, Brandon Mejia
 */
public class GamePanel extends JPanel
{
    private Game game;
    private Timer timer;


    private BufferedImage background;
    private String topScores = "";

    /**
     * Constructor
     * @param g Game
     */
    public GamePanel(Game g)
    {
        this.game = g;
        Assets.resizeAssets(g.cellLength());
        Assets.resizeBackground(g.maxX(),g.maxY());

        Random r = new Random();
        background = Assets.background.get(r.nextInt(Assets.background.size())%Assets.background.size());
        setFocusable(true);
        setPreferredSize(new Dimension(this.game.maxX(), this.game.maxY()));
        setBackground(Color.BLACK);

        this.timer = new Timer(350, new Controller(this));
        //this.timer.start();
    }

    /**
     * getter method of game
     * @return this.game
     */
    public Game game()
    {
        return this.game;
    }

    /**
     * paint method for Graphics.GamePanel
     * @param g Graphics
     */
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2D.drawImage(background, 0, 0, this.game.maxX(), this.game.maxY(), null);

        //draw the game
        this.game.drawImage(g2D);

        //draw the score
        if(!this.game.gameIsOver())
            this.drawScore(g2D);

        if (this.game.gameIsOver() && topScores != null)
                this.drawGameIsOver(g2D);
    }

//--------------------------------- METHODS --------------------------------------------------------------------------------------------------------


    /**
     * draw the game over screen
     * @param g Graphics2D
     */
    private void drawGameIsOver(Graphics2D g)
    {
        int size = Math.min(this.game.maxX(), this.game.maxY())/Math.max(this.game.m(), this.game.n());
        String GAMEOVER = "GAME OVER";
        g.setFont(new Font("Retro Gaming", Font.BOLD, (size/2) + size/3));
            int x = this.game.maxX()/2 - g.getFontMetrics().stringWidth(GAMEOVER)/2;
            int y =  this.game.maxY()/2 -this.game.maxY()/4 - g.getFontMetrics().getHeight();

            //draw the shadow
        g.setColor(Color.BLACK);
        g.drawString(GAMEOVER,x+5,y+5);
        // text normal
        g.setColor(Color.YELLOW);
        g.drawString(GAMEOVER,x,y);

            String[] players = topScores.split("\n");
            x += g.getFontMetrics().stringWidth(GAMEOVER)/10;
            for (int i = 0; i < players.length-1; i++)
            {
                y += g.getFontMetrics().getHeight();
                // shadow
                g.setColor(Color.BLACK);
                g.setFont(new Font("Retro Gaming", Font.BOLD, size/2-2));
                g.drawString(players[i], x+5, y+6);
                    //text normal
                    g.setColor(Color.YELLOW);
                    g.setFont(new Font("Retro Gaming", Font.BOLD, size/2-2));
                    g.drawString(players[i], x, y);

            }



    }

    /**
     * draw the score
     * @param g Graphics2D
     */
    private void drawScore(Graphics2D g)
    {
        int size = game.cellLength();
        g.setFont(new Font("Retro Gaming", Font.BOLD, size/2));
            int x =(game().cellLength()/5);
            int y = (game().cellLength()/3);

            //draw the shadow
            g.setColor(Color.BLACK);
            g.drawString("Score: " + this.game.score(), x+8, y+8);

        g.setColor(Color.WHITE);
        g.drawString("Score: " + this.game.score(), x+5, y+5);
    }

    /**
     * start the timer
     */
    public void startTimer()
    {
        Assets.music.loop(Clip.LOOP_CONTINUOUSLY);
        this.timer.start();
    }

    /**
     * stop the timer
     */
    public void stopTimer()
    {
        //Assets.gameOver.loop(Clip.LOOP_CONTINUOUSLY);
        this.timer.stop();
    }


    /**
     * setter of Game
     * @param newGame game that will replace the current game
     */
    public void setGame(Game newGame)
    {
        this.game = newGame;
    }


    /**
     * Loads leaderboard and updates it onto the screen
     * Also saves it to the txt file
     */
    private void loadLeaderboard()
    {
        Leaderboard lb = new Leaderboard();
            String name = JOptionPane.showInputDialog("Insira o seu nome:");
            if (name == null || name.equals(""))
                name = "unnamed";

            lb.setScore(name, game.score());
            lb.write(); // salva os dados

           //Mostrar o leaderboard
            StringBuilder sb = new StringBuilder();
            Score[] scores = lb.getScores();
            int scoreNumber =1;
			for (Score score : scores)
				sb.append(scoreNumber++).append(" ").append(score.username()).append(" ").append(score.score()).append("\n");

            topScores = sb.toString();

    }


//--------------------------------- INNER CLASSES --------------------------------------------------------------------------------------------------------
    /**
     * Controller class
     * This class will be responsible for updating the game
     */
    private class Controller implements ActionListener
    {
        private GamePanel p;

        Controller(GamePanel p){
            this.p = p;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            addKeyListener(new ManualControl.Keyboard());
            p.game.update(); //o panel esta sempre a ser atualizado

            if(game.f().isContainedIn(game.s().head().q()))
            {
                Assets.eat.setFramePosition(0);
                Assets.eat.start();
            }


            if (p.game.gameIsOver())
            {
                loadLeaderboard();
                p.timer.stop();
            }

            p.repaint();
        }
    }
}