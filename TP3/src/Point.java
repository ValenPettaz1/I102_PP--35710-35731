public class Point {
    public static Point Up = new Point(0, 0, 1);
    public static Point Down = new Point(0, 0, -1);

    private int x;
    private int y;
    private int z;

    public Point (int xCoord, int yCoord, int zCoord){
        this.x = xCoord;
        this.y = yCoord;
        this.z = zCoord;
    }

    public Point add(Point point) {return new Point(x + point.x, y + point.y, z + point.z);}

    public int getX() {return x;}
    public int getY() {return y;}
    public int getZ() {return z;}
}
