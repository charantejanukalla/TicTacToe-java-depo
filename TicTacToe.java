import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();
    public static void main(String[] args) {
        char[][] gameBoard= {{' ','|', ' ','|', ' '},
                             {'-', '+', '-', '+', '-'},
                             {' ','|', ' ','|', ' '},
                             {'-', '+', '-', '+', '-'},
                             {' ','|', ' ','|', ' '}};
        printGameBoard(gameBoard);

        
        while (true) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter a position (1-9): ");
            int playerPos=scan.nextInt();
            while (playerPositions.contains(playerPos) || cpuPositions.contains(playerPos)) {
                System.out.println("Position already taken! Please choose another position : ");
                playerPos = scan.nextInt();
                
            }

            placePiece(gameBoard, playerPos,"Player");

            String result = checkWinner();
            if (result.length()>0){
                System.out.println(result);
                break; 
            }

            Random rand= new Random();
            int cpuPos= rand.nextInt(9) + 1;
            while (playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)) {
                cpuPos = rand.nextInt(9) + 1;
                
            }
            placePiece(gameBoard, cpuPos, "Computer");

            printGameBoard(gameBoard);
            result = checkWinner();
            if (result.length()>0){
                System.out.println(result);
                break; 
            }

        }
        
    }
    public static void printGameBoard(char[][] gameBoard) {
        for(char [] row : gameBoard) {
            for(char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }
    public static void placePiece(char[][] gameBoard,int pos, String user){
        char symbol = ' ';
        if(user.equals("Player")){
            symbol = 'X';
            playerPositions.add(pos);
        } else if(user.equals("Computer")){
            symbol = 'O';
            cpuPositions.add(pos);
        } else {
            System.out.println("Invalid user type! Please enter 'Player' or 'Computer'.");
            return;
        }
        switch (pos){
            case 1:
                gameBoard[0][0]= symbol;
                break;
            case 2:
                gameBoard[0][2]=symbol;
                break;
            case 3:
                gameBoard[0][4]=symbol;
                break;
            case 4:
                gameBoard[2][0]=symbol;
                break;        
            case 5:
                gameBoard[2][2]=symbol;
                break;
            case 6:
                gameBoard[2][4]=symbol;
                break;
            case 7:
                gameBoard[4][0]=symbol;
                break;
            case 8:
                gameBoard[4][2]=symbol;
                break;
            case 9:
                gameBoard[4][4]=symbol;
                break;
            default:
                System.out.println("Invalid position! Please enter a number between 1 and 9.");
                break;
        }
    }
    public static String checkWinner() {
        List topRow = Arrays.asList(1,2,3);
        List midRow = Arrays.asList(4,5,6);
        List botRow = Arrays.asList(7,8,9);
        List leftCol = Arrays.asList(1,4,7);
        List midCol = Arrays.asList(2,5,8);
        List rightCol = Arrays.asList(3,6,9);
        List cross1 = Arrays.asList(1,5,9);
        List cross2 = Arrays.asList(3,5,7);

        List<List> winning = new ArrayList<List>();
        winning.add(topRow);
        winning.add(midRow);
        winning.add(botRow);
        winning.add(leftCol);
        winning.add(midCol);
        winning.add(rightCol);
        winning.add(cross1);
        winning.add(cross2);

        for(List l : winning) {
            if(playerPositions.containsAll(l)) {
                return "Congratulations You Won!";
            } else if(cpuPositions.containsAll(l)) {
                return "Computer wins! Sorry ";
            } else if(playerPositions.size()+ cpuPositions.size() == 9) {
                return "It's a draw!";
            }
        }
        return "";
    }
}