package Cards;

import Enumerations.Color;
import Enumerations.Size;

/**
 * Class representing a card element
 * @author Neil GAILLARD, Alexis BANG
 */
public class Card {
    private final Color color;
    private final Size requiredSize;

    public Card(Color color, Size requiredSize){
        this.color = color;
        this.requiredSize = requiredSize;
    }

    /**
     * Color getter
     * @return the color of the card
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * RequiredSize getter
     * @return the size indicated on the card
     */
    public Size getRequiredSize() {
        return this.requiredSize;
    }
}