package OOPS;

/**
 * Class that creates a score given the username and the score.
 * @author Jose Diogo Ferras, Miguel Silva, Brandon Correia
 * @version v0.0    13/04/2024
 */
public class Score implements Comparable<Score>{
    private final String username;
    private final int score;

    /**
     * Constructor of the class
     * @param username username
     * @param score score
     */
    public Score(String username, int score) {

        this.username = username;
        this.score = score;
    }

    /**
     * Getter of the username
     * @return username
     */
    public String username() {
        return username;
    }

    /**
     * Getter of the score
     * @return score
     */
    public int score() {
        return score;
    }

    /**
     * Check if score is >= than 0
     */
    public void checkScore()
    {
        if (score < 0) {
            System.out.println("Score:vi");
            System.exit(0);
        }
        else {
            return;
        }
    }

    /**
     * toString method
     * @return Score in String format
     */
    @Override
    public String toString() {
        return username + "\t" + score ;
    }

    /**
     * compareTo method for Scores
     * @param that Score
     * @return minus one if this score is greater than that score, one if this score is smaller than that score, zero if they are equal
     */
    @Override
    public int compareTo(Score that)
    {
        if(this.score > that.score){
            return -1;
        }
        else if(this.score < that.score){
            return 1;
        }
        else{
            return 0;
        }
    }
}
