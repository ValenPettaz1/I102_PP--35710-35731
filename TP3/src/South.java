public class South extends Cardinal{

    public South() {
        super("South");
    }
    public String getDirection() {
        return super.getDirection();
    }

    @Override
    public Cardinal getRight() {return new West();}

    @Override
    public Cardinal getLeft() {return new East();}

    @Override
    public Point getFront() {
        return new Point(0, -1,0);
    }
}