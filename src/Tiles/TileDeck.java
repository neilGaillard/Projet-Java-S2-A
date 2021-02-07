package Tiles;

import Enumerations.Color;
import Enumerations.Identifier;
import Enumerations.Size;

import java.util.ArrayList;

/**
 * Class representing a single tile of deck with only one color
 * @author Neil GAILLARD, Alexis BANG
 */
public class TileDeck {
    private static final int NUMBERTILESPERDECK = 9;
    private static final int SIZENUMBER = 3;

    private final Color color;

    private ArrayList<Tile> tiles;

    public TileDeck(Color color){
        this.tiles = new ArrayList<>(NUMBERTILESPERDECK);
        this.color = color;

        for (int i = 0; i < NUMBERTILESPERDECK; ++i){
            this.addTile(new Tile(Size.defineSize((i / SIZENUMBER)+1), Size.defineSize((i % SIZENUMBER) + 1), this.color));
        }
    }

    /**
     * size getter
     * @return the number of tiles in the deck
     */
    public int getSize(){
        return this.tiles.size();
    }

    /**
     * Add a tile in the deck
     * @param t the tile to add
     */
    public void addTile(Tile t){
        assert (!this.isFull());
        this.tiles.add(t);
    }

    /**
     * Remove a tile in the deck
     * @param id the identifier of the tile to remove
     */
    public void removeTile(Identifier id){
        assert (this.isNotEmpty());
        for (int i = 0; i < this.tiles.size(); ++i){
            if (this.tiles.get(i).getIdentifier().equals(id)) {
                this.tiles.remove(i);
                break;
            }
        }
    }

    /**
     * Returns a boolean indicating if the deck is empty or not
     * @return true is the tile deck is not empty, false overwise.
     */
    public boolean isNotEmpty() {
        return this.tiles.size() != 0;
    }

    /**
     * Returns a boolean indicating if the deck is empty or not
     * @return true is the tile deck is full, false overwise.
     */
    public boolean isFull(){
        return this.tiles.size() == NUMBERTILESPERDECK;
    }

    /**
     * Define which are the tiles corresponding to a size and a color condition (on a card)
     * @param d the array list of disponible tiles to fill
     * @param sizeCondition the size condition
     * @param colorCondition the color condition
     */
    public void defineDisponibleTiles(ArrayList<Tile> d, Size sizeCondition, Color colorCondition){
        for (Tile t : this.tiles) {
            if (this.color == colorCondition || (t.getSizeX() == sizeCondition || t.getSizeY() == sizeCondition))
                d.add(t);
        }
    }
}
