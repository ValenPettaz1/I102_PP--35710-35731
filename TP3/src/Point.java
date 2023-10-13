public class Point {
    private int x;
    private int y;
    private int z;

    public Point (int xCoord, int yCoord, int zCoord){
        this.x = xCoord;
        this.y = yCoord;
        this.z = zCoord;
    }

    public static Point add(Point point) {return new Point(x + point.x, y + point.y, z + point.z);}

    public int getX() {return x;}
    public int getY() {return y;}
    public int getZ() {return z;}
}
