package BattleshipGame;

public class DestroyedSpot extends Placables{

    public static final String ANSI_RED = " \u001b[31m";

    public static final String ANSI_RESET = " \u001b[0m";

    @Override
    public String toString(){
        return ANSI_RED+"[X]"+ANSI_RESET;
    }

}
