public class Point {
    private int x;
    private int y;


    public Point (int xCoord, int yCoord){
        this.x = xCoord;
        this.y = yCoord;
    }

    public Point add(Point point) {return new Point(x + point.x, y + point.y);}

    public int getX() {return x;}
    public int getY() {return y;}

}
