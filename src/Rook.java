public class Rook extends ChessPiece{
    private final String SYMBOL = "R";


    public Rook(String color) {
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


        if (!checkRookMove(line, column, toLine, toColumn)) {
            return false;
        }

        if (!checkPath(chessBoard, line, column, toLine, toColumn)) {
            return false;
        }

        check = false;
        return true;
    }

    private boolean checkRookMove(int line, int column, int toLine, int toColumn) {
        if (line == toLine && column == toColumn) {
            return false;
        }
        return line == toLine || column == toColumn;
    }

    public boolean checkPath(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (line == toLine) {
            int start = Math.min(column, toColumn) +1;
            int end = Math.max(column, toColumn);

            for (int i = start; i < end; i++) {
                if (chessBoard.board[line][i] != null) {
                    return false;
                }
            }
        } else if (column == toColumn) {
            int start = Math.min(line, toLine) +1;
            int end = Math.max(line, toLine);

            for (int i = start; i < end; i++) {
                if (chessBoard.board[i][column] != null) {
                    return false;
                }
            }
        }
        return true;
    }

}
