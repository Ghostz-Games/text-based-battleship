package BattleshipGame;

import java.util.Arrays;
import java.util.Random;

public class Game {

    public Placables[][] board = new Placables[14][14];
    public Placables[][] CPUboard = new Placables[14][14];

    public Placables[][] gameBoard = new Placables[14][14];
    public TextUI UI = new TextUI();
    public boolean didPlace1 = false;
    public boolean didPlace2 = false;
    public boolean didPlace3 = false;
    public int prevHitx = -1;
    public int prevHitY = -1;

    public int playerHP = 9;
    public int enemyHP = 9;

    public enum TURN{
        PLAYER,
        CPU
    }

    public TURN currentTurn = TURN.PLAYER;

    public Random random = new Random();
    public void start(){

        setup();
        displayBoard(1);
        UI.message("This is your current board.");

        while(!didPlace1){
            placeBoatSequence(1);
        }
        while(!didPlace2){
            placeBoatSequence(2);
        }
        while(!didPlace3){
            placeBoatSequence(3);
        }

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
        displayBoard(2);

        turnChecker(currentTurn);


    }

    private void turnChecker(TURN currentTurn) {
        if(playerHP > 0 && enemyHP > 0) {
            switch (currentTurn) {
                case PLAYER:
                    displayBoard(3);
                    UI.message("--------------------------------------------------------------------------------------------------");
                    UI.message("PLAYER TURN!");
                    int atkX = Integer.parseInt(UI.userInput("Enter the X coordinate for the shot"));
                    int atkY = Integer.parseInt(UI.userInput("Enter the Y coordinate for the shot"));
                    playerAttack(atkX, atkY);
                    break;
                case CPU:
                    UI.message("ENEMY TURN");
                    if (prevHitx < 0 && prevHitY < 0) {
                        int cpuX = random.nextInt(0, 14);
                        int cpuY = random.nextInt(0, 14);
                        cpuAttack(cpuX, cpuY);
                    } else {
                        int cpuX = prevHitx + random.nextInt(-2, 2);
                        int cpuY = prevHitY + random.nextInt(-2, 2);
                        cpuAttack(cpuX, cpuY);
                    }
                    break;
            }
        }else if((playerHP == 0) || (enemyHP == 0)){
            if(playerHP > enemyHP){
                UI.message("GAME ENDED! PLAYER WIN!");
            }else if(enemyHP > playerHP){
                UI.message("GAME ENDED! CPU WINS!");
            }
        }
    }

    private void cpuAttack(int cpuX, int cpuY) {

        if(board[cpuX][cpuY] instanceof Boat){
            prevHitx = cpuX;
            prevHitY = cpuY;
            UI.message("The CPU hit one of your boats!");
            playerHP -= 1;
            board[cpuX][cpuY] = new DestroyedSpot();
            displayBoard(1);
            UI.message("--------------------------------------------------------------------------------------------------");
            UI.message("The cpu gets another turn!");
            turnChecker(TURN.CPU);
        }else if(board[cpuX][cpuY] instanceof MissedSpot){
            turnChecker(TURN.CPU);
        }else {
            UI.message("The cpu missed!");
            prevHitx = -1;
            prevHitY = -1;
            board[cpuX][cpuY] = new MissedSpot();
            displayBoard(1);
            UI.message("--------------------------------------------------------------------------------------------------");
            turnChecker(TURN.PLAYER);
        }
    }

    private void playerAttack(int atkX, int atkY) {
        if(CPUboard[atkX][atkY] instanceof CPUBoat){
            UI.message("You hit a boat!");
            enemyHP -= 1;
            gameBoard[atkX][atkY] = new DestroyedSpot();
            displayBoard(3);
            UI.message("--------------------------------------------------------------------------------------------------");
            UI.message("You get a second shot!");
            turnChecker(TURN.PLAYER);

        }else {
            UI.message("You missed!");
            gameBoard[atkX][atkY] = new MissedSpot();
            displayBoard(3);
            UI.message("--------------------------------------------------------------------------------------------------");
            turnChecker(TURN.CPU);
        }
    }

    public void setup(){

        UI.message("Running setup . . .");
        for (Placables[] placables : board) {
            Arrays.fill(placables, new EmptySpots());
        }
        for (Placables[] placables : CPUboard) {
            Arrays.fill(placables, new EmptySpots());
        }
        for (Placables[] placables : gameBoard) {
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
        }else if(k == 3){
            for(int i = 0; i < gameBoard.length; i++) {
                for (int j = 0; j < gameBoard[i].length; j++) {
                    System.out.print(gameBoard[j][i]);
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
                    didPlace1 = true;
                } else if(!vert && board[x + 1][y] instanceof EmptySpots) {
                    board[x][y] = new Boat();
                    board[x + 1][y] = new Boat();
                    didPlace1 = true;
                }
            } else {
                System.out.println("There is already a boat on this spot!");
                didPlace1 = false;
            }
        }else {
            UI.message("Boat could not be placed there, please try again!");
            didPlace1 = false;
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
                    didPlace2 = true;
                } else if(!vert && board[x + 1][y] instanceof EmptySpots && board[x + 2][y] instanceof EmptySpots){
                    board[x][y] = new Boat();
                    board[x + 1][y] = new Boat();
                    board[x + 2][y] = new Boat();
                    didPlace2 = true;
                }
            } else {
                System.out.println("There is already a boat on this spot!");
                didPlace2 = false;
            }
        }else {
            UI.message("Boat could not be placed there, please try again!");
            didPlace2 = false;
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
                    didPlace3 = true;
                } else if(!vert && board[x + 1][y] instanceof EmptySpots && board[x + 2][y] instanceof EmptySpots && board[x + 3][y] instanceof EmptySpots) {
                    board[x][y] = new Boat();
                    board[x + 1][y] = new Boat();
                    board[x + 2][y] = new Boat();
                    board[x + 3][y] = new Boat();
                    didPlace3 = true;
                }
            } else {
                System.out.println("There is already a boat on this spot!");
                didPlace3 = false;
            }
        }else {
            UI.message("Boat could not be placed there! Please try again!");
            didPlace3 = false;
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


        public void placeBoatSequence(int seq){

            switch(seq){
                case 1:
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
                    break;
                case 2:
                    userX = Integer.parseInt(UI.userInput("Please place your medium boat now: First the x coordinate: "));
                    userY = Integer.parseInt(UI.userInput("Now the Y coordinate :"));
                    vertInput = UI.userInput("Shall the boat be vertical? Y/N");
                    userVert = false;
                    if(vertInput.equalsIgnoreCase("Y")){
                        userVert = true;
                    }else {
                        userVert = false;
                    }
                    UI.message("Placing the medium boat at "+ userX +", "+ userY+ ", and the boat is vertical? "+ userVert);
                    placeRegularBoat(userX, userY, userVert);
                    displayBoard(1);
                    break;

                case 3:
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
                    break;
            }
        }


}
