public class East extends Cardinal{

    @Override
    public Cardinal turnRight() {
        return new South();
    }

    @Override
    public Cardinal turnLeft() {
        return new North();
    }

    @Override
    public Point getFront() {
        return new Point(1, 0, 0);
    }
}