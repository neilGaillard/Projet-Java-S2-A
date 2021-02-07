package game;

import java.util.Scanner;

/**
 * Main class
 * @author Neil GAILLARD, Alexis BANG
 * Git repository  : https://framagit.org/Overflow/projet-java
 */
public class Appli {
    public static void main(String[] args) {
        Game game = new Game();
        Scanner sc = new Scanner(System.in);
        String neutral;

        System.out.println("Selectionnez ou placer la pièce neutre (1-4)");
        System.out.println("1 : Vertical, Gauche");
        System.out.println("2 : Vertical, Droite");
        System.out.println("3 : Horizontal, Gauche");
        System.out.println("4 : Horizontal, Droite");
        do {
            neutral = (game.getGameAssets().placeNeutralTile(sc.nextInt()));
        } while (!neutral.equals(""));


        while (game.isRunning()){
            System.out.println(game.getGameAssets().pickCardAndTiles());

            System.out.println(game.getGameAssets().toString());

            System.out.println(Appli.renderDisponibleTiles(game));

            System.out.println(game.getGameAssets().placeTile(game.getGameAssets().selectTileToPlace()));

            game.getGameAssets().levelUp();

            game.emptyCardDeck();
            game.emptyTileDecks();
        }
        String s = "Partie terminée. Points : " +
                game.getPoints() +
                " (" +
                game.getGameAssets().getNbLevels() +
                " niveaux atteinds, " +
                game.getGameAssets().getCardDeck().getSize() +
                " cartes restantes, " +
                (game.getGameAssets().getBlueTileDeck().getSize() +
                        game.getGameAssets().getRedTileDeck().getSize()) +
                " carreaux restants).";
        System.out.println(s);
    }

    /**
     * returns the string of all the disponible tiles
     * @param game the game and its assets
     * @return the string for rendering purposes
     */
    public static String renderDisponibleTiles(Game game) {
        return "Carreaux Disponibles :\n" +
                game.getGameAssets().getStringDisponibleTiles() +
                "Selectionnez le numéro du carreau à placer, la cordonnée X et la coordonnée Y";
    }
}