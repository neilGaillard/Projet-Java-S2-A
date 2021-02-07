package Enumerations;

import org.jetbrains.annotations.NotNull;

/**
 * Size enumeration : define the size of Tiles or the size indicated on some Cards.
 * @author Neil Gaillard, Alexis Bang
 */
public enum Size {
    UNDEFINED,
    TAILLE1,
    TAILLE2,
    TAILLE3;

    /**
     * Returns the Size enumeration variable which correspond to the given int.
     * @param i The int to convert into a Size
     * @return the Size of the concerned int variable.
     */
    public static Size defineSize(int i){
        assert (i < 4 && i > 0);
        switch (i){
            case 1:
                return TAILLE1;
            case 2:
                return TAILLE2;
            case 3:
                return TAILLE3;
            default:
                throw new IllegalStateException("Unexpected defineSize value: " + i);
        }
    }

    /**
     * Return the integer which corresponds to the given Size.
     * @param s the Size to convert in int
     * @return The size of the concerned Size variable.
     */
    public static int getInt(@NotNull Size s){
        switch (s){
            case TAILLE1:
                return 1;
            case TAILLE2:
                return 2;
            case TAILLE3:
                return 3;
            default:
                throw new IllegalStateException("Unexpected value: " + s.toString());
        }
    }
}