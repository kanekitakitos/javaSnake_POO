package OOPS;

import java.io.*; //Scanner, File, FileNotFoundException, PrintWriter
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Class that defines the Leaderboard of the game.
 * @author Jose Diogo Ferras, Miguel Silva, Brandon Correia
 * @version v0.1    3/05/2024
 */
public class Leaderboard
{
    private Score[] scores = new Score[11];

    /**
     * Constructor of the Leaderboard
     */
    public Leaderboard()
    {
       read();
    }


    /**
     * Constructor of the Leaderboard
     * @param scores array of scores
     */
    public Leaderboard(Score[]  scores)
    {
        for (int i = 0; i < 11; i++)
            this.scores[i] = new Score(scores[i].username(), scores[i].score());
    }


    /**
     * Reads the 10 scores from the file
     */
    public void read()
    {
        try
        {
            String path = "src"+File.separator+"OOPS"+File.separator+"leaderboard.tsv";

            File file = new File(path);
            FileReader fr = new FileReader(file); // abre o file
            BufferedReader br = new BufferedReader(fr); // ler o file por linhas

            String buffer = br.readLine(); // ler a primeira linha que nao importa
            int i = 0;
            while ((buffer = br.readLine()) != null)
            {
                this.scores[i++] = parseScore(   buffer  );
            }
            br.close();
            fr.close();
        }
        catch (IOException e ) // algum problema em executar?, diz-me que aconteceu
        {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Parses a score from a string
     * @param buffer String
     * @return score in Score format
     */
    private Score parseScore(String buffer)
    {
        String[] tokens = buffer.split("\t");
        if(tokens.length == 3){ //when there is a score
            return new Score(tokens[1], Integer.parseInt(tokens[2]));
        }   //when there isnt
        else{
            return null;
        }
    }

    /**
     * Writes the first 10 scores to the file
     */
    public void write()
    {
        try
        {
            String path = "src"+File.separator+"OOPS"+File.separator+"leaderboard.tsv";

            FileWriter fw = new FileWriter(path);// abre o arquivo
            BufferedWriter bw = new BufferedWriter(fw); // escreve no arquivo

                int rank = 1;
                bw.write("#\tPlayer\tScore\n");
                for (int i = 0; i < 10; i++)
                {
                    if(this.scores[i] == null){
                        bw.write(rank+"\t----------------------\n");
                    }
                    else{
                        bw.write(rank+"\t"+this.scores[i].toString() + "\n");
                    }
                    rank++;
                }


            bw.flush();
            bw.close();
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    /**
     * getter of scores
     * @return this.scores
     */
    public Score[] getScores()
    {
        Score[] that = new Score[11];
        for (int i = 0; i < this.scores.length; i++)
            that[i] = new Score(this.scores[i].username(), this.scores[i].score());

        return that;
    }

    /**
     * Sets the current score on the leaderboard after asking the user for the name
     * @param name name
     * @param score score
     */
    public void setScore(String name, int score)
    {
        Score newScore = new Score(name, score);
        this.scores[10] = newScore;

        this.sort();
    }


    /**
     * sort method for leaderboard
     */
    private void sort(){
        ArrayList<Score> scoresList = new ArrayList();
        for(int i = 0; i < this.scores.length; i++){
            if(this.scores[i] != null){
                scoresList.add(this.scores[i]);
            }
        }
        Collections.sort(scoresList);
        int n = scoresList.size();
        for(int i = 0; i < this.scores.length; i++){
            if(i < n){
                this.scores[i] = scoresList.get(i);
            }
            else{
                this.scores[i] = null;
            }
        }
    }


    /**
     * Transforms the array of scores into a string
     */
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("#\tUsername\tScore\n");
        int rank = 1;

            for (int i = 0; i < 10; i++)
            {
                if(this.scores[i] == null){
                    sb.append(rank+"\t----------------------\n");
                }
                else{
                    sb.append(rank+"\t"+this.scores[i].toString() + "\n");
                }
                rank++;
            }

        return sb.toString();
    }

    /**
     * Prints the leaderboard using toString
     */
    public void print(){
        System.out.println(this);
    }

    public boolean isHighScore(int score) {
        return score > this.scores[9].score();
    }
}

