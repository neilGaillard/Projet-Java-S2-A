package game;

/**
 * Class representing the whole game, and the non-gameplay mechanics
 * @author Neil GAILLARD, Alexis BANG
 */
public class Game {
    private final GameAssets gameAssets;

    private static boolean running;

    public Game(){
        this.gameAssets = new GameAssets();
        running = true;
    }

    /**
     * gameAssets getter
     * @return returns the game assets
     */
    public GameAssets getGameAssets() {
        return gameAssets;
    }

    /**
     * points getter
     * @return the number of points calculated from the number of levels and the number of remaining tiles and cards
     */
    public int getPoints() {
        int points = this.gameAssets.getNbLevels()*5
                - (this.getGameAssets().getRedTileDeck().getSize() + this.getGameAssets().getBlueTileDeck().getSize())
                - this.getGameAssets().getCardDeck().getSize();
        return Math.max(points, 0);
    }

    /**
     * Say if the game should still run or not
     * @return true if the game should still run, false overwise
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * Check if the card deck is empty in order to stop the game or not.
     */
    public void emptyCardDeck(){
        if (!this.gameAssets.getCardDeck().isNotEmpty())
            stopGame();
    }

    /**
     * Check if both tile decks are empty in order to stop the game or not
     */
    public void emptyTileDecks(){
        if (!this.gameAssets.getBlueTileDeck().isNotEmpty() && !this.gameAssets.getRedTileDeck().isNotEmpty())
            stopGame();
    }

    /**
     * Put the running boolean to false which should stop the game after the while loop is over in the Appli class.
     */
    public static void stopGame(){
        running = false;
    }
}