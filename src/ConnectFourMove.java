/**
 * Created by azharhussain on 2/28/16.
 */
public class ConnectFourMove {
    private int value;
    private int move;

    public ConnectFourMove(int val, int mov){
        value = val;
        move = mov;
    }

    public int getValue() {
        return value;
    }

    public int getMove() {
        return move;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setMove(int move) {
        this.move = move;
    }
}
