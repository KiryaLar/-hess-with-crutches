public abstract class ChessPiece {
    private String color;
    protected boolean check = true;


    protected ChessPiece(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    abstract public String getSymbol();

    public abstract boolean canMoveToPosition(ChessBoard board, int line, int column, int toLine, int toColumn);

    protected boolean checkPos(int pos) {
        return pos >= 0 && pos <= 7;
    }
}
