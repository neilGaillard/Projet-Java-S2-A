package game;

import Cards.CardDeck;
import Enumerations.Color;
import Enumerations.Identifier;
import Enumerations.Size;
import Tiles.Tile;
import Tiles.TileDeck;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Class for the management of all the differents elements of the game : the tiles, cards, the wall to fill
 * @author Neil GAILLARD
 */
public class GameAssets {

    private static final int MAXSIZEY = 30;
    private static final int SIZEX = 5;
    private final Identifier[][] wall;

    private int nbLevels;

    private final CardDeck cardDeck;

    private final TileDeck redTileDeck;
    private final TileDeck blueTileDeck;

    private final ArrayList<Tile> disponibleTiles;

    public GameAssets(){
        this.nbLevels = 0;

        this.wall = new Identifier[MAXSIZEY][SIZEX];
        for (int i = 0; i < MAXSIZEY; ++i){
            Arrays.fill(this.wall[i], Identifier.VOID);
        }

        this.cardDeck = new CardDeck();
        this.redTileDeck = new TileDeck(Color.RED);
        this.blueTileDeck = new TileDeck(Color.BLUE);

        this.disponibleTiles = new ArrayList<>();
    }

    /**
     * nbLevel getter
     * @return the number of levels (lines completed from the bottom of the wall
     */
    public int getNbLevels() {
        return nbLevels;
    }

    /**
     * cardDeck getter
     * @return the card deck (not the array)
     */
    public CardDeck getCardDeck() {
        return cardDeck;
    }

    /**
     * redTileDeck getter
     * @return the red tiles (not the array)
     */
    public TileDeck getRedTileDeck() {
        return redTileDeck;
    }

    /**
     * blueTileDeck getter
     * @return the blue tiles (not the array)
     */
    public TileDeck getBlueTileDeck() {
        return blueTileDeck;
    }


    /**
     * Choose where to put the neutral tile at the beginning of the game
     * @param c the player's choice
     * @return a String to print in order to let the player know if everything went well or not
     */
    public String placeNeutralTile(int c){
        switch (c) {
            case 1:
                Tile neutralTile = new Tile(Size.TAILLE1, Size.TAILLE3, Color.NEUTRAL, Identifier.x);
                neutralTile.getPos().setX(0);
                neutralTile.getPos().setY(0);
                placeTile(neutralTile);
                break;
            case 2:
                neutralTile = new Tile(Size.TAILLE1, Size.TAILLE3, Color.NEUTRAL, Identifier.x);
                neutralTile.getPos().setX(SIZEX - 1);
                neutralTile.getPos().setY(0);
                placeTile(neutralTile);
                break;
            case 3:
                neutralTile = new Tile(Size.TAILLE3, Size.TAILLE1, Color.NEUTRAL, Identifier.x);
                neutralTile.getPos().setX(0);
                neutralTile.getPos().setY(0);
                placeTile(neutralTile);
                break;
            case 4:
                neutralTile = new Tile(Size.TAILLE3, Size.TAILLE1, Color.NEUTRAL, Identifier.x);
                neutralTile.getPos().setX(SIZEX - 3);
                neutralTile.getPos().setY(0);
                placeTile(neutralTile);
                break;
            default:
                return "Entrez un nombre entre 1 et 4";
        }
        return "";
    }

    /**
     * Pick a card on top of the card deck, and define the disponible tiles
     * @return a String to print in order to let the player know the condition written on the chosen card
     */
    public String pickCardAndTiles(){
        assert(this.cardDeck.isNotEmpty());
        Color colorCondition;
        Size sizeCondition;
        disponibleTiles.clear();

        colorCondition = this.cardDeck.getTopCard().getColor();
        sizeCondition = this.cardDeck.getTopCard().getRequiredSize();

        this.redTileDeck.defineDisponibleTiles(disponibleTiles, sizeCondition, colorCondition);
        this.blueTileDeck.defineDisponibleTiles(disponibleTiles, sizeCondition, colorCondition);

        if (sizeCondition == Size.UNDEFINED)
            return "Condition couleur : " + colorCondition;
        else
            return "Condition taille : " + sizeCondition;
    }

    /**
     * Method allowing the plater to select which tile they want to place, and where
     * @return a Tile to place, or a Tile representing an error in the input
     */
    public Tile selectTileToPlace(){
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        int x, y;
        if (!s.equals("next") && !s.equals("stop")) {
            int choix = Integer.parseInt(s);
            if (choix <= this.disponibleTiles.size() && choix > 0) {
                Tile tileToPlace = this.disponibleTiles.get(Integer.parseInt(s) - 1);
                x = sc.nextInt() - 1;
                y = sc.nextInt() - 1;
                if (x >= 0 && x + Size.getInt(tileToPlace.getSizeX()) - 1 < SIZEX && y >= 0) {
                    tileToPlace.getPos().setX(x);
                    tileToPlace.getPos().setY(y);
                    return tileToPlace;
                }
                else {
                    return new Tile(Size.TAILLE3, Size.TAILLE3, Color.NEUTRAL, Identifier.VOID); //INCORRECT X AND Y
                }
            }
            else
                return new Tile(Size.TAILLE2, Size.TAILLE2, Color.NEUTRAL, Identifier.VOID); //INCORRECT TILE NUMBER
        }
        else if (s.equals("next"))
            return new Tile(Size.UNDEFINED, Size.UNDEFINED, Color.NEUTRAL, Identifier.VOID);
        else
            return new Tile(Size.TAILLE1, Size.TAILLE1, Color.NEUTRAL, Identifier.VOID);
    }

    public String placeTile(Tile tileToPlace){
        if (tileToPlace.getIdentifier() != Identifier.VOID) { //IF THERE WERE NO ERROR DURING THE INPUT
            if (this.wall[tileToPlace.getPos().getY()][tileToPlace.getPos().getX()] == Identifier.VOID) {
                for (int i = tileToPlace.getPos().getY(); i < Size.getInt(tileToPlace.getSizeY()) +
                        tileToPlace.getPos().getY(); ++i) {
                    for (int j = tileToPlace.getPos().getX(); j < Size.getInt(tileToPlace.getSizeX()) +
                            tileToPlace.getPos().getX(); ++j) {
                        this.wall[i][j] = tileToPlace.getIdentifier();
                    }
                }
                if (tileToPlace.getIdentifier() != Identifier.x) {
                    if (this.isFloating(tileToPlace) || this.isTouching(tileToPlace) || this.isSameSize(tileToPlace)) {
                        this.removeTile(tileToPlace);
                        if (this.isFloating(tileToPlace))
                            return "Veuillez ne pas placer de carreaux au dessus d'une seule case vide.";
                        if (this.isTouching(tileToPlace))
                            return "Le carreau doit en toucher un autre";
                        if (this.isSameSize(tileToPlace))
                            return "Le coté d'un carreau ne peut correspondre parfaitement à la taille d'un carreau adjacent";
                    }
                        this.cardDeck.removeTopCard();

                        this.redTileDeck.removeTile(tileToPlace.getIdentifier());
                        this.blueTileDeck.removeTile(tileToPlace.getIdentifier());
                    }
                return "Carreau Placé";
            }
            else
                return "Un carreau est déjà présent ici.";
        }
        else if (tileToPlace.getSizeY() == Size.UNDEFINED) {
                this.cardDeck.shuffle();
            return "Vous passez le tour et le paquet de carte a été mélangé.";
        }
        else if (tileToPlace.getSizeY() == Size.TAILLE1){
            Game.stopGame();
            return "Vous quittez le jeu";
        }
        else if (tileToPlace.getSizeY() == Size.TAILLE2)
            return "Entrez un bon numéro de carreau s'il vous plait";
        else
            return "Impossible de placer le carreau à ces coordonnées";
    }

    /**
     * Check if a tile has a void case below it
     * @param t the tile to check, and its position
     * @return true if there is void below it, false overwise
     */
    public boolean isFloating(Tile t){
        if (t.getPos().getY() > 0) {
            for (int i = t.getPos().getX(); i < t.getPos().getX() + Size.getInt(t.getSizeX()); ++i) {
                if (this.wall[t.getPos().getY() - 1][i] == Identifier.VOID)
                    return true;
            }
        }
        return false;
    }

    /**
     * Check if a tile has a tile next to it
     * @param t the tile to check, and its position
     * @return true if there is a tile next to it, false overwise
     */
    public boolean isTouching(Tile t) {
        for (int i = t.getPos().getY(); i < t.getPos().getY() + Size.getInt(t.getSizeY()); ++i) {
            if (t.getPos().getX() != 0) {
                if (this.wall[i][t.getPos().getX() - 1] != Identifier.VOID) {
                    return false;
                }
            }
            if (t.getPos().getX() + Size.getInt(t.getSizeX()) < SIZEX) {
                if (this.wall[i][t.getPos().getX() + Size.getInt(t.getSizeX())] != Identifier.VOID) {
                    return false;
                }
            }
        }
        if (t.getPos().getY() > 0) {
            for (int i = t.getPos().getX(); i < t.getPos().getX() + Size.getInt(t.getSizeX()); ++i) {
                if (this.wall[t.getPos().getY() - 1][i] != Identifier.VOID)
                    return false;
            }
        }
        return true;
    }

    /**
     * Check if a tile has a size which touch perfectly a tile with the same size.
     * @param t the tile to check, and its position
     * @return true if there is it does touche a tile with the same size and corresponds at 100%, false overwise
     */
    public boolean isSameSize(Tile t){
        boolean verification = false;
        boolean oneCorner = false;

        /*
        We want to chech these corners :
            X X O X X
            X A A A X
            O A A A O
            X A A A O
            X X O X X
        A being the tile, O the non-checked spaces and X the checked spaces

        If 2 touching X does not have the same identifier, it is then probable that the size rule is not respected.
        If 2 touching X have the same identifier, it is probable the tile does not touch a tile with the same size.
         */

        //LEFT
        if (t.getPos().getX() > 0) {
            //LEFT BOTTOM
            if (t.getPos().getY() > 0) {
                if (wall[t.getPos().getY()][t.getPos().getX() - 1] != wall[t.getPos().getY() - 1][t.getPos().getX() - 1])
                    verification = true;
            }
            else
                oneCorner = true;
            //LEFT TOP
            if (wall[t.getPos().getY() + Size.getInt(t.getSizeY()) - 1][t.getPos().getX() - 1] !=
                    wall[t.getPos().getY() + Size.getInt(t.getSizeY())][t.getPos().getX() - 1]) {
                if (verification || oneCorner)
                    return true;
            }
        }
        verification = false;
        oneCorner = false;
        //RIGHT
        if (t.getPos().getX() + Size.getInt(t.getSizeX()) < SIZEX) {
            //RIGHT BOTTOM
            if (t.getPos().getY() > 0) {
                if (wall[t.getPos().getY()][t.getPos().getX() + Size.getInt(t.getSizeX())] !=
                        wall[t.getPos().getY() - 1][t.getPos().getX() + Size.getInt(t.getSizeX())])
                    verification = true;
            }
            else
                oneCorner = true;
            //RIGHT TOP
            if (wall[t.getPos().getY() + Size.getInt(t.getSizeY()) - 1][t.getPos().getX() + Size.getInt(t.getSizeX())] !=
                    wall[t.getPos().getY() + Size.getInt(t.getSizeY())][t.getPos().getX() + Size.getInt(t.getSizeX())]){
                if (verification || oneCorner)
                    return true;
            }
        }
        verification = false;
        oneCorner = false;
        //BOTTOM
        if (t.getPos().getY() > 0){
            //BOTTOM LEFT
            if (t.getPos().getX() > 0){
                if (wall[t.getPos().getY() - 1][t.getPos().getX()] != wall[t.getPos().getY() - 1][t.getPos().getX() - 1]){
                    verification = true;
                }
            }
            else
                oneCorner = true;
            //BOTTOM RIGHT
            if (t.getPos().getX() + Size.getInt(t.getSizeX()) < SIZEX){
                if ( wall[t.getPos().getY() - 1][t.getPos().getX() + Size.getInt(t.getSizeX())] !=
                        wall[t.getPos().getY() - 1][t.getPos().getX() + Size.getInt(t.getSizeX()) - 1]) {
                    return verification || oneCorner;
                }
            }
        }
        return false;
    }

    /**
     * Remove a tile from the wall if rules are not respected
     * @param tileToRemove the tile to remove and its position
     */
    public void removeTile(Tile tileToRemove){
        for (int i = tileToRemove.getPos().getY(); i < Size.getInt(tileToRemove.getSizeY()) +
                tileToRemove.getPos().getY(); ++i) {
            for (int j = tileToRemove.getPos().getX(); j < Size.getInt(tileToRemove.getSizeX()) +
                    tileToRemove.getPos().getX(); ++j) {
                this.wall[i][j] = Identifier.VOID;
            }
        }
    }

    /**
     * Update the level number of the game
     */
    public void levelUp() {
        boolean voidInLine = false;
        for (int i=0; i <= SIZEX - 1; ++i) {
            if (wall[this.nbLevels][i] == Identifier.VOID) {
                voidInLine = true;
                break;
            }
        }
        if (!voidInLine)
            ++this.nbLevels;
    }

    /**
     * Allow the print of the disponible tiles
     * @return returns a string with the disponible tiles next to each other
     */
    public String getStringDisponibleTiles(){
        StringBuilder s = new StringBuilder();
        int sizeMaxY = 0;
        for (Tile t : this.disponibleTiles) {
            if (Size.getInt(t.getSizeY()) > sizeMaxY)
                sizeMaxY = Size.getInt(t.getSizeY());
        }
        for (int j = sizeMaxY; j > 0;--j) {
            for (Tile t : this.disponibleTiles) {
                if (j <= Size.getInt(t.getSizeY()))
                    s.append(t.getIdentifier().toString().repeat(Size.getInt(t.getSizeX())));
                else
                    s.append(" ".repeat(Size.getInt(t.getSizeX())));
                s.append("     ");
            }
            s.append("\n");
        }
        return s.toString();
    }

    /**
     * Returns the wall in a String from
     * @return the wall to print
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        int render;

        for (int i = MAXSIZEY - 1; i >= 0; --i){
            render = 0;

            for (int j = 0; j < SIZEX; ++j){
                if (this.wall[i][j] == Identifier.VOID) {
                    if (i > 0 && wall[i - 1][j] == Identifier.VOID)
                        ++render;
                }
            }
            if (render != this.wall[0].length) {
                s.append((i + 1));

                if (i < 9)
                    s.append(" ");
                s.append(" |");

                for (int j = 0; j < SIZEX; ++j) {
                    s.append(this.wall[i][j].toString());
                    s.append("|");
                }
                s.append("\n");
            }
        }
        s.append("    1 2 3 4 5");
        return s.toString();
    }
}