package BattleshipGame;

public class MissedSpot extends Placables{


    public static final String ANSI_YELLOW = " \u001b[33m";

    public static final String ANSI_RESET = " \u001b[0m";

    @Override
    public String toString(){
        return ANSI_YELLOW+"[X]"+ANSI_RESET;
    }



}
