public class North extends Cardinal{

    @Override
    public Cardinal turnRight() {return new East();}

    @Override
    public Cardinal turnLeft() {return new West();}

    @Override
    public Point getFront() {
        return new Point(0, 1, 0);
    }
}
