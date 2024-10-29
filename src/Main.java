import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static ChessBoard buildBoard() {
        ChessBoard board = new ChessBoard("White");

        board.board[0][0] = new Rook("White");
        board.board[0][1] = new Horse("White");
        board.board[0][2] = new Bishop("White");
        board.board[0][3] = new Queen("White");
        board.board[0][4] = new King("White");
        board.board[0][5] = new Bishop("White");
        board.board[0][6] = new Horse("White");
        board.board[0][7] = new Rook("White");
        board.board[1][0] = new Pawn("White");
        board.board[1][1] = new Pawn("White");
        board.board[1][2] = new Pawn("White");
        board.board[1][3] = new Pawn("White");
        board.board[1][4] = new Pawn("White");
        board.board[1][5] = new Pawn("White");
        board.board[1][6] = new Pawn("White");
        board.board[1][7] = new Pawn("White");

        board.board[7][0] = new Rook("Black");
        board.board[7][1] = new Horse("Black");
        board.board[7][2] = new Bishop("Black");
        board.board[7][3] = new Queen("Black");
        board.board[7][4] = new King("Black");
        board.board[7][5] = new Bishop("Black");
        board.board[7][6] = new Horse("Black");
        board.board[7][7] = new Rook("Black");
        board.board[6][0] = new Pawn("Black");
        board.board[6][1] = new Pawn("Black");
        board.board[6][2] = new Pawn("Black");
        board.board[6][3] = new Pawn("Black");
        board.board[6][4] = new Pawn("Black");
        board.board[6][5] = new Pawn("Black");
        board.board[6][6] = new Pawn("Black");
        board.board[6][7] = new Pawn("Black");
        return board;
    }

    public static void main(String[] args) {

        ChessBoard board = buildBoard();
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
               To test the game, you need to enter commands:
               'exit' - to exit
               'replay' - to restart the game
               'castling0' or 'castling7' - for castling along the appropriate line
               'A1 B2'(without "MOVE") - to move a figure from position A1 to B2
               Check whether the pieces can walk through each other, whether they eat each other correctly, is it possible to check and castling?""");
        ArrayList<String> symbols = new ArrayList(List.of("A", "B", "C", "D", "E", "F", "G", "H"));
        System.out.println();
        board.printBoard();
        while (true) {
            String s = scanner.nextLine();
            if (s.equals("exit")) break;
            else if (s.equals("replay")) {
                System.out.println("Again");
                board = buildBoard();
                board.printBoard();
            } else {
                if (s.equals("castling0")) {
                    if (board.castling0()) {
                        System.out.println("The castling was successful");
                        board.printBoard();
                    } else {
                        System.out.println("The castling failed");
                    }
                } else if (s.equals("castling7")) {
                    if (board.castling7()) {
                        System.out.println("The castling was successful");
                        board.printBoard();
                    } else {
                        System.out.println("The castling failed");
                    }
                } else {
                    String[] a = s.split(" ");
                    try {
                        String startCell = a[0];
                        String endCell = a[1];
                        int line = Integer.parseInt(startCell.substring(1)) - 1;
                        int column = symbols.indexOf(startCell.substring(0, 1));
                        int toLine = Integer.parseInt(endCell.substring(1)) - 1;
                        int toColumn = symbols.indexOf(endCell.substring(0, 1));
                        if (board.moveToPosition(line, column, toLine, toColumn)) {
                            System.out.println("The movement was successful");
                            board.printBoard();
                        } else System.out.println("The movement failed");
                    } catch (Exception e) {
                        System.out.println("You entered something wrong, try again");
                    }

                }
            }
        }
    }
}