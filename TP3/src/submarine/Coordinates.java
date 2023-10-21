package submarine;

import java.util.Objects;

public class Coordinates {
    private int x;
    private int y;

    public Coordinates(int xCoord, int yCoord){
        this.x = xCoord;
        this.y = yCoord;
    }

    public Coordinates add(Coordinates coordinates) {return new Coordinates(x + coordinates.x, y + coordinates.y);}

    public int getX() {return x;}

    public int getY() {return y;}

    public boolean equals( Object obj ) {
        return this == obj ||
                (obj != null &&
                getClass() == obj.getClass()) &&
                x == ((Coordinates) obj).x && y == ((Coordinates) obj).y;
    }
}