public class North extends Cardinal{

    @Override
    public Cardinal getRight() {return new East();}

    @Override
    public Cardinal getLeft() {return new West();}

    @Override
    public Point getFront() {
        return new Point(0, 1, 0);
    }
}
