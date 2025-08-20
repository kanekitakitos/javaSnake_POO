package OOPS;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoreTests {

    //OOPS_0
    @Test
    void username() {
        String username = "username";
        int score = 0;
        Score s = new Score(username, score);
        assertEquals(username, s.username());
    }

    //OOPS_0
    @Test
    void score() {
        String username = "username";
        int score = 12345;
        Score s = new Score(username, score);
        assertEquals(score, s.score());
    }

    //OOPS_0
    @Test
    void testToString(){
        String username = "username";
        int score = 12345;
        Score s = new Score(username, score);
        assertEquals(s.toString(), "username\t12345");
    }

    //OOPS_0
    @Test
    void testCompareTo(){
        String username0 = "username0";
        int score0 = 10;
        Score s0 = new Score(username0, score0);
        String username1 = "username1";
        int score1 = 15;
        Score s1 = new Score(username1, score1);
        String username2 = "username2";
        int score2 = 10;
        Score s2 = new Score(username2, score2);
        assertEquals(s0.compareTo(s1), 1);
        assertEquals(s0.compareTo(s2),0);
        assertEquals(s1.compareTo(s0), -1);
    }


    //OOPS_0
    @Test
    void testCurrentScore()
    {

    }

}