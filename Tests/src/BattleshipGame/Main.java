package BattleshipGame;

public class Main {


    public static Game game = new Game();
    public static TextUI UI = new TextUI();

    public static void main(String[] args){

        //game.start();
        UI.message("Hello and welcome to the battleship board game the text-based game! (Title in working progress)");
        String input = UI.userInput("To begin the prototype please enter your name!");
        UI.message("Hello "+ input);
        input = UI.userInput("Please press Y to begin, or N to close the program:");
        if(input.equalsIgnoreCase("Y")){
            game.start();
        }else {
            System.exit(0);
        }

    }


}
