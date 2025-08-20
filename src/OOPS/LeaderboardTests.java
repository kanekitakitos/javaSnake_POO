package OOPS;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LeaderboardTests {


    //OOPS_0
    @Test
    void testSetScore()
    {
        Score[] scores = new Score[11];
        for (int i = 0; i < scores.length; i++)
            scores[i] = new Score("username" + i, i+1);

        Leaderboard lb = new Leaderboard(scores);
        lb.setScore("qualquer", 50);
        Score[] example = lb.getScores();

        assertEquals(example[0].score(), 50);

    }

    //OOPS_0
    @Test
    void testSetScore1()
    {

        // tem só um ponto no jogo, fica na ultima posiçao do array
        Score[] scores = new Score[11];
        for (int i = 0; i < scores.length; i++)
            scores[i] = new Score("username" + i, i+2);

        Leaderboard lb = new Leaderboard(scores);
        lb.setScore("qualquer", 1);
        Score[] example = lb.getScores();

        assertEquals(example[10].score(), 1);
    }


}