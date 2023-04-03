package BattleshipGame;

public class Boat extends Placables{

    public static final String ANSI_RED = " \u001b[31m";
    public static final String ANSI_BLUE = " \u001b[34m";
    public static final String ANSI_RESET = " \u001b[0m";

    public Boat(){
        super();


    }


    @Override
    public String toString(){
        return ANSI_BLUE+"[X]"+ANSI_RESET;
    }

}
