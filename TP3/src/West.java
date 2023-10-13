public class West extends Cardinal{

    public West() {
        super("West");
    }

    public String getDirection() {
        return super.getDirection();
    }

    @Override
    public Cardinal getRight() {
        return new North();
    }

    @Override
    public Cardinal getLeft() {return new South();}
    @Override
    public Point getFront() {
        return new Point(-1, 0,0);
    }
}