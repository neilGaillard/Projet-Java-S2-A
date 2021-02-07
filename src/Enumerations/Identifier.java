package Enumerations;

/**
 * Enumeration of the differents identifier the tiles can have, as well as the different states the wall can take.
 * @author Neil Gaillard, Alexis Bang
 */
public enum Identifier {
    //VOID STATE
    VOID,

    //NEUTRAL TILE
    x,

    //RED TILES
    A,
    B,
    C,
    D,
    E,
    F,
    G,
    H,
    I,

    //BLUE TILES
    a,
    b,
    c,
    d,
    e,
    f,
    g,
    h,
    i;


    /**
     * Define the identifier of a Tile depending on its properties
     * @param x the width of the suggested tile
     * @param y the height of the suggested tile
     * @param color the color of the suggested tile
     * @return the identifier of the suggested tile
     */
    public static Identifier defineState(Size x, Size y, Color color){
        if (color == Color.RED){
            if (x == Size.TAILLE1 && y == Size.TAILLE1)
                return A;
            if (x == Size.TAILLE1 && y == Size.TAILLE2)
                return B;
            if (x == Size.TAILLE2 && y == Size.TAILLE1)
                return C;
            if (x == Size.TAILLE2 && y == Size.TAILLE2)
                return D;
            if (x == Size.TAILLE1 && y == Size.TAILLE3)
                return E;
            if (x == Size.TAILLE3 && y == Size.TAILLE1)
                return F;
            if (x == Size.TAILLE2 && y == Size.TAILLE3)
                return G;
            if (x == Size.TAILLE3 && y == Size.TAILLE2)
                return H;
            if (x == Size.TAILLE3 && y == Size.TAILLE3)
                return I;
        }
        else if (color == Color.BLUE){
            if (x == Size.TAILLE1 && y == Size.TAILLE1)
                return a;
            if (x == Size.TAILLE1 && y == Size.TAILLE2)
                return b;
            if (x == Size.TAILLE2 && y == Size.TAILLE1)
                return c;
            if (x == Size.TAILLE2 && y == Size.TAILLE2)
                return d;
            if (x == Size.TAILLE1 && y == Size.TAILLE3)
                return e;
            if (x == Size.TAILLE3 && y == Size.TAILLE1)
                return f;
            if (x == Size.TAILLE2 && y == Size.TAILLE3)
                return g;
            if (x == Size.TAILLE3 && y == Size.TAILLE2)
                return h;
            if (x == Size.TAILLE3 && y == Size.TAILLE3)
                return i;
        }
        return VOID;
    }

    /**
     * Converts the identfier into a String
     * @return the indentifier converted into a String
     */
    @Override
    public String toString() {
        switch (this){
            case VOID:
                return " ";
            case a:
                return "a";
            case b:
                return "b";
            case c:
                return "c";
            case d:
                return "d";
            case e:
                return "e";
            case f:
                return "f";
            case g:
                return "g";
            case h:
                return "h";
            case i:
                return "i";
            case A:
                return "A";
            case B:
                return "B";
            case C:
                return "C";
            case D:
                return "D";
            case E:
                return "E";
            case F:
                return "F";
            case G:
                return "G";
            case H:
                return "H";
            case I:
                return "I";
            case x:
                return "x";
        }
        return "";
    }
}