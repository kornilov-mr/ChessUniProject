package logic;

import java.util.Objects;

public class Move {
    private final int xEnd;
    private final int yEnd;
    private final int xStart;
    private final int yStart;

    public Move(int xStart, int yStart, int xEnd, int yEnd) {
        this.xEnd = xEnd;
        this.yEnd = yEnd;
        this.xStart = xStart;
        this.yStart = yStart;
    }

    public Move getReversedMove(){
        return new Move(xEnd,yEnd,xStart,yStart);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Move move)) return false;
        return xEnd == move.xEnd && yEnd == move.yEnd && xStart == move.xStart && yStart == move.yStart;
    }


    @Override
    public int hashCode() {
        return Objects.hash(xEnd, yEnd, xStart, yStart);
    }

    public int getXEnd() {
        return xEnd;
    }

    public int getYEnd() {
        return yEnd;
    }


    public int getXStart() {
        return xStart;
    }

    public int getYStart() {
        return yStart;
    }


    @Override
    public String toString() {
        return "Move{" +
                "xStart=" + xStart +
                ", yStart=" + yStart +
                ", xEnd=" + xEnd +
                ", yEnd=" + yEnd +
                '}';
    }
}
