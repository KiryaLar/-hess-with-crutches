public class Queen extends ChessPiece{
    private final String SYMBOL = "Q";


    public Queen(String color) {
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

        if (!checkQueenMove(line, column, toLine, toColumn)) {
            return false;
        }

        return checkPath(chessBoard, line, column, toLine, toColumn);
    }

    private boolean checkQueenMove(int line, int column, int toLine, int toColumn) {
        if (line == toLine && column == toColumn) {
            return false;
        }
        boolean rookMove;
        boolean bishopMove;

        if (line == toLine) {
            rookMove = Math.abs(toColumn - column) > 0;
        } else if (column == toColumn) {
            rookMove = Math.abs(toLine - line) > 0;
        } else rookMove =  false;

        bishopMove = Math.abs(toLine - line) == Math.abs(toColumn - column);

        return rookMove || bishopMove;
    }

    public boolean checkPath(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        int lineStep = (toLine - line) > 0 ? 1 : -1;
        int columnStep = (toColumn - column) > 0 ? 1 : -1;
        int currentLine = line + lineStep;
        int currentColumn = column + columnStep;

        while (currentLine != toLine && currentColumn != toColumn) {
            if (chessBoard.board[currentLine][currentColumn] != null) {
                return false;
            }
            currentLine++;
            currentColumn++;
        }

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
