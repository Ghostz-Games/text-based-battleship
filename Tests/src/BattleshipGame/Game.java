package BattleshipGame;

import java.util.Arrays;
import java.util.Random;

public class Game {

    public Placables[][] board = new Placables[14][14];
    public Placables[][] CPUboard = new Placables[14][14];
    public TextUI UI = new TextUI();

    public Random random = new Random();
    public void start(){

        setup();
        displayBoard(1);
        UI.message("This is your current board.");
        int userX = Integer.parseInt(UI.userInput("Please place your small boat first: First the x coordinate: "));
        int userY = Integer.parseInt(UI.userInput("Now the Y coordinate :"));
        String vertInput = UI.userInput("Shall the boat be vertical? Y/N");
        boolean userVert = false;

        if(vertInput.equalsIgnoreCase("Y")){
            userVert = true;
        }else {
            userVert = false;
        }
        UI.message("Placing the small boat at "+ userX +", "+ userY+ ", and the boat is vertical? "+ userVert);
        placeSmallBoat(userX, userY, userVert);
        displayBoard(1);
        userX = Integer.parseInt(UI.userInput("Please place your medium boat now: First the x coordinate: "));
        userY = Integer.parseInt(UI.userInput("Now the Y coordinate :"));
        vertInput = UI.userInput("Shall the boat be vertical? Y/N");
        if(vertInput.equalsIgnoreCase("Y")){
            userVert = true;
        }else {
            userVert = false;
        }
        UI.message("Placing the medium boat at "+ userX +", "+ userY+ ", and the boat is vertical? "+ userVert);
        placeRegularBoat(userX, userY, userVert);
        displayBoard(1);
        userX = Integer.parseInt(UI.userInput("Please place your large boat now: First the x coordinate: "));
        userY = Integer.parseInt(UI.userInput("Now the Y coordinate :"));
        vertInput = UI.userInput("Shall the boat be vertical? Y/N");
        if(vertInput.equalsIgnoreCase("Y")){
            userVert = true;
        }else {
            userVert = false;
        }
        UI.message("Placing the large boat at "+ userX +", "+ userY+ ", and the boat is vertical? "+ userVert);
        placeBigBoat(userX, userY, userVert);
        displayBoard(1);

        String input = UI.userInput("All boats are now placed! Are you satisfied with the placements? Y/N");
        if(input.equalsIgnoreCase("Y")){
            startGame();
        }else {
            start();
        }
    }

    public void startGame(){
        placeCPU();
        displayBoard(1);
    }

    public void setup(){

        UI.message("Running setup . . .");
        for (Placables[] placables : board) {
            Arrays.fill(placables, new EmptySpots());
        }
        for (Placables[] placables : CPUboard) {
            Arrays.fill(placables, new EmptySpots());
        }

    }

    public void displayBoard(int k){

        if(k == 1) {

            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    System.out.print(board[j][i]);
                }
                System.out.println();
            }
            System.out.println("-----------------------------------------------------------------------");
        }else if(k == 2){

            for (int i = 0; i < CPUboard.length; i++) {
                for (int j = 0; j < CPUboard[i].length; j++) {
                    System.out.print(CPUboard[j][i]);
                }
                System.out.println();
            }
            System.out.println("-----------------------------------------------------------------------");
        }
    }


    public void placeSmallBoat(int x, int y, boolean vert) {

        int x_1 = x + 1;
        int x_2 = x + 2;
        int x_3 = x + 3;
        int y_1 = y - 1;
        int y_2 = y - 2;
        int y_3 = y - 3;

        if (x >= 0 && x <= 13 && x_1 >= 0 && x_1 <= 13 && x_2 >= 0 && x_2 <= 13 && x_3 >= 0 && x_3 <= 13 && y >= 0 && y <= 13 && y_1 >= 0 && y_1 <= 13 && y_2 >= 0 && y_2 <= 13 && y_3 >= 0 && y_3 <= 13) {

            if (board[x][y] instanceof EmptySpots) {
                if (vert && board[x][y - 1] instanceof EmptySpots) {
                    board[x][y] = new Boat();
                    board[x][y - 1] = new Boat();
                } else if(!vert && board[x + 1][y] instanceof EmptySpots) {
                    board[x][y] = new Boat();
                    board[x + 1][y] = new Boat();
                }
            } else {
                System.out.println("There is already a boat on this spot!");
            }
        }else {
            UI.message("Boat could not be placed there, please try again!");
        }
    }

    public void placeRegularBoat(int x, int y, boolean vert) {

        int x_1 = x + 1;
        int x_2 = x + 2;
        int x_3 = x + 3;
        int y_1 = y - 1;
        int y_2 = y - 2;
        int y_3 = y - 3;

        if (x >= 0 && x <= 13 && x_1 >= 0 && x_1 <= 13 && x_2 >= 0 && x_2 <= 13 && x_3 >= 0 && x_3 <= 13 && y >= 0 && y <= 13 && y_1 >= 0 && y_1 <= 13 && y_2 >= 0 && y_2 <= 13 && y_3 >= 0 && y_3 <= 13) {


            if (board[x][y] instanceof EmptySpots) {
                if (vert && board[x][y- 1] instanceof EmptySpots && board[x][y - 2] instanceof EmptySpots) {
                    board[x][y] = new Boat();
                    board[x][y - 1] = new Boat();
                    board[x][y - 2] = new Boat();
                } else if(!vert && board[x + 1][y] instanceof EmptySpots && board[x + 2][y] instanceof EmptySpots){
                    board[x][y] = new Boat();
                    board[x + 1][y] = new Boat();
                    board[x + 2][y] = new Boat();
                }
            } else {
                System.out.println("There is already a boat on this spot!");
            }
        }else {
            UI.message("Boat could not be placed there, please try again!");
        }
    }

    public void placeBigBoat(int x, int y, boolean vert) {

        int x_1 = x + 1;
        int x_2 = x + 2;
        int x_3 = x + 3;
        int y_1 = y - 1;
        int y_2 = y - 2;
        int y_3 = y - 3;

        if (x >= 0 && x <= 13 && x_1 >= 0 && x_1 <= 13 && x_2 >= 0 && x_2 <= 13 && x_3 >= 0 && x_3 <= 13 && y >= 0 && y <= 13 && y_1 >= 0 && y_1 <= 13 && y_2 >= 0 && y_2 <= 13 && y_3 >= 0 && y_3 <= 13) {


            if (board[x][y] instanceof EmptySpots) {
                if (vert && board[x][y - 1] instanceof EmptySpots && board[x][y - 2] instanceof EmptySpots && board[x][y - 3] instanceof EmptySpots) {
                    board[x][y] = new Boat();
                    board[x][y - 1] = new Boat();
                    board[x][y - 2] = new Boat();
                    board[x][y - 3] = new Boat();
                } else if(!vert && board[x + 1][y] instanceof EmptySpots && board[x + 2][y] instanceof EmptySpots && board[x + 3][y] instanceof EmptySpots) {
                    board[x][y] = new Boat();
                    board[x + 1][y] = new Boat();
                    board[x + 2][y] = new Boat();
                    board[x + 3][y] = new Boat();
                }
            } else {
                System.out.println("There is already a boat on this spot!");
            }
        }else {
            UI.message("Boat could not be placed there! Please try again!");
        }
    }


    public void  placeCPU(){
        int count = 0;
        while (count < 3){


                int x = random.nextInt(0, 13);
                int y = random.nextInt(0, 13);
                boolean vert = random.nextBoolean();
                int x_1 = x + 1;
                int x_2 = x + 2;
                int x_3 = x + 3;
                int y_1 = y - 1;
                int y_2 = y - 2;
                int y_3 = y - 3;

                if (x >= 0 && x <= 13 && x_1 >= 0 && x_1 <= 13 && x_2 >= 0 && x_2 <= 13 && x_3 >= 0 && x_3 <= 13 && y >= 0 && y <= 13 && y_1 >= 0 && y_1 <= 13 && y_2 >= 0 && y_2 <= 13 && y_3 >= 0 && y_3 <= 13) {

                    switch (count) {
                        case 0 -> {
                            if (board[x][y] instanceof EmptySpots) {
                                if (!vert && CPUboard[x + 1][y] instanceof EmptySpots) {
                                    CPUboard[x][y] = new CPUBoat();
                                    CPUboard[x + 1][y] = new CPUBoat();
                                    count += 1;
                                } else if (vert && CPUboard[x][y - 1] instanceof EmptySpots) {
                                    CPUboard[x][y] = new CPUBoat();
                                    CPUboard[x][y - 1] = new CPUBoat();
                                    count += 1;
                                }
                            }
                        }
                        case 1 -> {
                            if (board[x][y] instanceof EmptySpots) {
                                if (!vert && CPUboard[x + 1][y] instanceof EmptySpots && CPUboard[x + 2][y] instanceof EmptySpots) {
                                    CPUboard[x][y] = new CPUBoat();
                                    CPUboard[x + 1][y] = new CPUBoat();
                                    CPUboard[x + 2][y] = new CPUBoat();
                                    count += 1;
                                } else if (vert && CPUboard[x][y - 1] instanceof EmptySpots && CPUboard[x][y - 2] instanceof EmptySpots) {
                                    CPUboard[x][y] = new CPUBoat();
                                    CPUboard[x][y - 1] = new CPUBoat();
                                    CPUboard[x][y - 2] = new CPUBoat();
                                    count += 1;
                                }
                            }
                        }
                        case 2 -> {
                            if (board[x][y] instanceof EmptySpots) {
                                if (!vert && CPUboard[x + 1][y] instanceof EmptySpots && CPUboard[x + 2][y] instanceof EmptySpots && CPUboard[x + 3][y] instanceof EmptySpots) {
                                    CPUboard[x][y] = new CPUBoat();
                                    CPUboard[x + 1][y] = new CPUBoat();
                                    CPUboard[x + 2][y] = new CPUBoat();
                                    CPUboard[x + 3][y] = new CPUBoat();
                                    count += 1;
                                } else if (vert && CPUboard[x][y - 1] instanceof EmptySpots && CPUboard[x][y - 2] instanceof EmptySpots && CPUboard[x][y - 3] instanceof EmptySpots) {
                                    CPUboard[x][y] = new CPUBoat();
                                    CPUboard[x][y - 1] = new CPUBoat();
                                    CPUboard[x][y - 2] = new CPUBoat();
                                    CPUboard[x][y - 3] = new CPUBoat();
                                    count += 1;
                                }
                            }
                        }
                    }
                } else {
                    System.out.println("Placing enemy boats ...");
                }
            }
        }



}
