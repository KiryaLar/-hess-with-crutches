import java.util.concurrent.BlockingDeque;

public class Pawn extends ChessPiece{
    private final String SYMBOL = "P";


    public Pawn(String color) {
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

        return checkPawnMove(chessBoard, line, column, toLine, toColumn);
    }

    public boolean checkPawnMove(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (line == toLine && column == toColumn) {
            return false;
        }

        int direction = this.getColor().equals("White") ? 1 : -1;

        if (column == toColumn) {
            if (toLine - line == direction) {
                return chessBoard.board[toLine][toColumn] == null;
            }
            if (((line == 1 && this.getColor().equals("White"))
                    || (line == 6 && this.getColor().equals("Black")))
                    && toLine - line == direction * 2) {
                    return chessBoard.board[toLine][toColumn] == null && chessBoard.board[toLine - direction][toColumn] == null;
                }

        }

        if (Math.abs(toColumn - column) == 1 && (toLine - line) == direction) {
            return chessBoard.board[toLine][toColumn] != null
                    && !chessBoard.board[toLine][toColumn].getColor().equals(this.getColor());
        }

        return false;
    }

}
