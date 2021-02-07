package Tiles;

import Enumerations.Color;
import Enumerations.Identifier;
import Enumerations.Size;

/**
 * Class representing a single tile element
 * @author Neil GAILLARD, Alexis BANG
 */
public class Tile {
    private Identifier identifier;
    private Size sizeX;
    private Size sizeY;
    private Color color;
    private Position pos;

    public Tile(Size sizeX, Size sizeY, Color color){
        assert (color != Color.NEUTRAL);
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.color = color;
        this.identifier = Identifier.defineState(this.sizeX, this.sizeY, this.color);
        this.pos = new Position();
    }

    public Tile(Size sizeX, Size sizeY, Color color, Identifier x){
        assert (color != Color.NEUTRAL);
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.color = color;
        this.identifier = x;
        this.pos = new Position();
    }

    /**
     * sizeX getter
     * @return the width of the tile
     */
    public Size getSizeX() {
        return sizeX;
    }

    /**
     * sizeY getter
     * @return the height of the tile
     */
    public Size getSizeY() {
        return sizeY;
    }

    /**
     * identifier getter
     * @return the identifier of the tile
     */
    public Identifier getIdentifier() {
        return identifier;
    }

    /**
     * pos getter
     * @return the position of the tile
     */
    public Position getPos() {
        return pos;
    }
}