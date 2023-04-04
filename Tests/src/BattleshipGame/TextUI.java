package BattleshipGame;

import java.util.Scanner;

public class TextUI {


public void message(String msg){
    System.out.println(msg);
}

public String userInput(String msg){
    System.out.println(msg);
    Scanner scan = new Scanner(System.in);
    String output = scan.nextLine();
    return output;
}





}


