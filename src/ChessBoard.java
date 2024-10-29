public class ChessBoard {
    public ChessPiece[][] board = new ChessPiece[8][8];
    String nowPlayer;

    public ChessBoard(String nowPlayer) {
        this.nowPlayer = nowPlayer;
    }

    public String nowPlayerColor() {
        return this.nowPlayer;
    }

    public boolean moveToPosition(int startLine, int startColumn, int endLine, int endColumn) {
        if (checkPos(startLine) && checkPos(startColumn)) {

            if (!nowPlayer.equals(board[startLine][startColumn].getColor())) {
                System.out.println("It's not your turn now");
                return false;
            }

            if (isKingUnderAttack() && !(board[startLine][startColumn] instanceof King)) {
                if (!isPieceDefendKing(startLine, startColumn, endLine, endColumn)) {
                    System.out.println("You must protect the King!");
                    return false;
                }
            }

            if (board[startLine][startColumn].canMoveToPosition(this, startLine, startColumn, endLine, endColumn)) {
                if (board[startLine][startColumn] instanceof King) {
                    ChessPiece copyEnd = board[endLine][endColumn];
                    board[endLine][endColumn] = board[startLine][startColumn];
                    board[startLine][startColumn] = null;
                    if (isKingUnderAttack()) {
                        board[startLine][startColumn] = board[endLine][endColumn];
                        board[endLine][endColumn] = copyEnd;
                        return false;
                    }
                }
                this.nowPlayer = this.nowPlayerColor().equals("White") ? "Black" : "White";
                return true;
            } else return false;

        } else {
            System.out.println("Invalid position");
            return false;
        }

    }

    public void printBoard() {  //print board in console
        System.out.println();
        System.out.println("Player 2(Black)");
        System.out.println();
//        System.out.println("\t0\t1\t2\t3\t4\t5\t6\t7");
        System.out.println("\tA\tB\tC\tD\tE\tF\tG\tH");

//        for (int i = 7; i > -1; i--) {
        for (int i = 8; i > 0; i--) {
            System.out.print(i + "\t");
            for (int j = 0; j < 8; j++) {
                if (board[i - 1][j] == null) {
                    System.out.print(".." + "\t");
                } else {
                    System.out.print(board[i - 1][j].getSymbol() + board[i - 1][j].getColor().substring(0, 1).toLowerCase() + "\t");
                }
            }
            System.out.println();
            System.out.println();
        }
        System.out.println("\tA\tB\tC\tD\tE\tF\tG\tH\n");

        System.out.println("Player 1(White)\n");
        System.out.println("Turn " + nowPlayer);
    }

    public boolean checkPos(int pos) {
        return pos >= 0 && pos <= 7;
    }

    public boolean castling0() {
        if (nowPlayer.equals("Black")) {
            if (board[7][0] == null || board[7][4] == null) return false;
            if (board[7][0].getSymbol().equals("R") && board[7][4].getSymbol().equals("K")
                    && board[7][1] == null && board[7][2] == null && board[7][3] == null) {
                if (board[7][0].getColor().equals("Black") && board[7][4].getColor().equals("Black")
                        && board[7][0].check && board[7][4].check) {
                    board[7][4] = null;
                    board[7][2] = new King("Black");
                    if (isKingUnderAttack()) {
                        board[7][4] = new King("Black");
                        board[7][2] = null;
                        return false;
                    }
                    board[7][0] = null;
                    board[7][3] = new Rook("Black");
                    nowPlayer = "White";
                    return true;

                } else return false;
            } else return false;
        } else {
            if (board[0][0] == null || board[0][4] == null) return false;
            if (board[0][0].getSymbol().equals("R") && board[0][4].getSymbol().equals("K")
                    && board[0][1] == null && board[0][2] == null && board[0][3] == null) {
                if (board[0][0].getColor().equals("White") && board[0][4].getColor().equals("White")
                        && board[0][0].check && board[0][4].check) {
                    board[0][4] = null;
                    board[0][2] = new King("White");
                    if (isKingUnderAttack()) {
                        board[0][4] = new King("White");
                        board[0][2] = null;
                        return false;
                    }
                    board[0][0] = null;
                    board[0][3] = new Rook("White");
                    nowPlayer = "Black";
                    return true;
                } else return false;
            } else return false;
        }
    }

    public boolean castling7() {
        if (nowPlayer.equals("Black")) {
            if (board[7][7] == null || board[7][4] == null) return false;

            if (board[7][7].getSymbol().equals("R") && board[7][4].getSymbol().equals("K")
                    && board[7][5] == null && board[7][6] == null) {
                if (board[7][7].getColor().equals("Black") && board[7][4].getColor().equals("Black")
                        && board[7][7].check && board[7][4].check) {
                    board[7][4] = null;
                    board[7][6] = new King("Black");
                    if (isKingUnderAttack()) {
                        board[7][4] = new King("Black");
                        board[7][6] = null;
                        return false;
                    }
                    board[7][7] = null;
                    board[7][5] = new Rook("Black");
                    nowPlayer = "White";
                    return true;
                } else return false;
            } else return false;
        } else {
            if (board[0][7] == null || board[0][4] == null) return false;

            if (board[0][7].getSymbol().equals("R") && board[0][4].getSymbol().equals("K")
                    && board[0][5] == null && board[0][6] == null) {
                if (board[0][7].getColor().equals("White") && board[0][4].getColor().equals("White")
                        && board[0][7].check && board[0][4].check) {
                    board[0][4] = null;
                    board[0][6] = new King("White");
                    if (isKingUnderAttack()) {
                        board[0][4] = new King("White");
                        board[0][6] = null;
                        return false;
                    }
                    board[0][7] = null;
                    board[0][5] = new Rook("White");
                    nowPlayer = "Black";
                    return true;
                } else return false;
            } else return false;
        }
    }

    public boolean isKingUnderAttack() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] instanceof King) {
                    if (board[i][j].getColor().equals(nowPlayer)) {
                        return isAnybodyBitKing(i, j);
                    }
                }
            }
        }

        return false;
    }

    public boolean isAnybodyBitKing(int toLine, int toColumn) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != null){
                    if (!(board[i][j].getColor().equals(nowPlayer))
                            && board[i][j].canMoveToPosition(this, i, j, toLine, toColumn)) {
                        System.out.println("King is under attack");
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public boolean isPieceDefendKing(int fromLine, int fromColumn, int toLine, int toColumn) {

        ChessPiece originalPiece = board[toLine][toColumn];
        ChessPiece movingPiece = board[fromLine][fromColumn];

        board[toLine][toColumn] = movingPiece;
        board[fromLine][fromColumn] = null;

        boolean isKingStillUnderAttack = isKingUnderAttack();

        board[fromLine][fromColumn] = movingPiece;
        board[toLine][toColumn] = originalPiece;

        return !isKingStillUnderAttack;
    }

}
