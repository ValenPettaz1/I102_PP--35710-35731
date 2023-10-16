public class South extends Cardinal{

    @Override
    public Cardinal turnRight() {return new West();}

    @Override
    public Cardinal turnLeft() {return new East();}

    @Override
    public Point getFront() {
        return new Point(0, -1,0);
    }
}