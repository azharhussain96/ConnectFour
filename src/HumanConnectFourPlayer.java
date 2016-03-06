/**
 * Created by azharhussain on 2/28/16.
 */
public class HumanConnectFourPlayer extends Player{

    public HumanConnectFourPlayer(String playerName){
        super(playerName);
    }

    public String getName(){
        return super.getName();
    }

    public int getMove(Connect4State state, Connect4View view){
        return view.getUserMove(state);
    }
}
