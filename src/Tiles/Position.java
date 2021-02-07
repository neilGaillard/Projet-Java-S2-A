package Tiles;

/**
 * Class of two integers representing the position of a tile in the wall, position located on the left bottom case
 */
public class Position {
    private int x, y;

    public Position(){
        this(0,0);
    }

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * x getter
     * @return the x position
     */
    public int getX() {
        return this.x;
    }

    /**
     * y getter
     * @return the y position
     */
    public int getY() {
        return this.y;
    }

    /**
     * x setter
     * @param x the value of x
     */
    public void setX(int x) {
        assert (x >= 0);
        this.x = x;
    }

    /**
     * y setter
     * @param y the value of y
     */
    public void setY(int y) {
        assert (y >= 0);
        this.y = y;
    }
}
