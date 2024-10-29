public class Horse extends ChessPiece{

    private final String SYMBOL = "H";


    public Horse(String color) {
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

        return checkHorseMove(line, column, toLine, toColumn);
    }

    public boolean checkHorseMove(int line, int column, int toLine, int toColumn) {
        boolean checkVerticalMove = Math.abs(toLine - line) == 2 && Math.abs(toColumn - column) == 1;
        boolean checkHorizontalMove = Math.abs(toColumn - column) == 2 && Math.abs(toLine - line) == 1;
        return checkVerticalMove || checkHorizontalMove;
    }

}
