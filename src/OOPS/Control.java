package OOPS;

/**
 * Interface that defines either an automated control of the game or a Manual control of the game.
 * @author Jose Ferras, Miguel Silva, Brandon Mejia
 * @version v0.0    16/04/2024
 */
public interface Control {
    /**
     * this method will be used to interpret the input of the player (when its manualPlayer) or
     * to compute the next step of the player (when its autoPlayer)
     * the Game will later use the changeDirection() method depending of what is returned by this function
     * @return 0 - nothing happens, 1 - left, 2 - right
     */
    public int handle(); //0 - nothing happens, 1 - left, 2 - right
}
