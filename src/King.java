public class King extends ChessPiece{
    private final String SYMBOL = "K";


    public King(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return super.getColor();
    }

    @Override
    public String getSymbol() {
        return SYMBOL;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (!checkPos(toLine) || !checkPos(toColumn)) {
            return false;
        }

        if (chessBoard.board[toLine][toColumn] != null ) {
            if (chessBoard.board[toLine][toColumn].getColor().equals(this.getColor())){
                return false;
            }
        }



        boolean canMove = checkKingMove(line, column, toLine, toColumn);
        if (canMove) {
            check = false;
        }

        return canMove;
    }

    public boolean checkKingMove(int line, int column, int toLine, int toColumn) {
        if (line == toLine && column == toColumn) {
            return false;
        }
        return Math.abs(toLine - line) <= 1 && Math.abs(toColumn - column) <= 1;
    }

}
