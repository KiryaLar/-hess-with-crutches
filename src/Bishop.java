public class Bishop extends ChessPiece{

    private final String SYMBOL = "B";


    public Bishop(String color) {
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

        if (!checkBishopMove(line, column, toLine, toColumn)) {
            return false;
        }

        return checkPath(chessBoard, line, column, toLine, toColumn);
    }

    private boolean checkBishopMove(int line, int column, int toLine, int toColumn) {

        if (line == toLine || column == toColumn) {
            return false;
        }

        return Math.abs(toLine - line) == Math.abs(toColumn - column);
    }

    private boolean checkPath(ChessBoard chessBoard,int line, int column, int toLine, int toColumn) {

        int lineStep = (toLine - line) > 0 ? 1 : -1;
        int columnStep = (toColumn - column) > 0 ? 1 : -1;
        int currentLine = line + lineStep;
        int currentColumn = column + columnStep;

        while (currentLine != toLine && currentColumn != toColumn) {
            if (chessBoard.board[currentLine][currentColumn] != null) {
                return false;
            }
            currentLine += lineStep;
            currentColumn += columnStep;
        }

        return true;
    }
    }

