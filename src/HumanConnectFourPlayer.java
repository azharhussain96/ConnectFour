/**
 * HumanConnectFourPlayer
 * Creates a human player for the game connect four
 * @author Azhar Hussain
 * @author Alec Cobban
 * 3/6/16
 */
public class HumanConnectFourPlayer extends Player{

    /**
     * Constructor creates player
     * @param playerName name of the human player
     */
    public HumanConnectFourPlayer(String playerName){

        super(playerName);
    }

    /**
     * gets name of player
     * @return player name
     */
    public String getName(){

        return super.getName();
    }

    /**
     * gets the player move
     * @return int column which move is placed
     */
    public int getMove(Connect4State state, Connect4View view){

        // calls view method (depending on graphical or textual) to determine the players picked move
        return view.getUserMove(state);
    }
}
