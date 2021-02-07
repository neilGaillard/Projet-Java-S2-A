package Cards;

import Enumerations.Color;
import Enumerations.Size;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Class representing a deck of cards
 * @author Neil GAILLARD, ALexis BANG
 */
public class CardDeck {

    private final ArrayList<Card> deck;
    private static final int CARDNUMBER = 33;
    private static final int COLORCARDSNUMBER = 18;
    private static final int TAILLECARDSNUMBER = 15;

    public CardDeck(){
        this.deck = new ArrayList<>(CARDNUMBER);

        for (int i = 0; i < COLORCARDSNUMBER; i += 2){
            this.addCard(new Card(Color.BLUE, Size.UNDEFINED));
            this.addCard(new Card(Color.BLUE, Size.UNDEFINED));
        }
        for (int i = 0; i < TAILLECARDSNUMBER; i += 3){
            this.addCard(new Card(Color.NEUTRAL, Size.TAILLE1));
            this.addCard(new Card(Color.NEUTRAL, Size.TAILLE2));
            this.addCard(new Card(Color.NEUTRAL, Size.TAILLE3));
        }
        this.shuffle();
    }

    /**
     * Deck' size getter
     * @return the number of cards currently in the deck
     */
    public int getSize(){
        return this.deck.size();
    }

    /**
     * Returns a boolean indicating if the deck is empty or not
     * @return true is the card deck is not empty, false overwise.
     */
    public boolean isNotEmpty() {
        return (this.deck.size() != 0);
    }

    /**
     * Returns a boolean indicating if the deck is full or not
     * @return true is the card deck is full, false overwise.
     */
    public boolean isFull(){
        return this.deck.size() == CARDNUMBER;
    }

    /**
     * Add a card in the deck
     * @param c the card to add
     */
    public void addCard(Card c){
        assert(!this.isFull());
        this.deck.add(c);
    }

    /**
     * Remove a card from the deck
     */
    public void removeTopCard(){
        assert(this.isNotEmpty());
        this.deck.remove(this.deck.size() - 1);
    }

    /**
     * Shuffle the deck
     */
    public void shuffle(){
        assert(this.isNotEmpty());
        Collections.shuffle(this.deck);
    }

    /**
     * Returns the card at the top of the deck
     * @return the card at the top of the deck
     */
    public Card getTopCard(){
        assert (this.isNotEmpty());
        return this.deck.get(this.deck.size() - 1);
    }
}