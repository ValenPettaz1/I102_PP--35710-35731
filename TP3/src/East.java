public class East extends Cardinal{

    @Override
    public Cardinal getRight() {
        return new South();
    }

    @Override
    public Cardinal getLeft() {
        return new North();
    }

    @Override
    public Point getFront() {
        return new Point(1, 0);
    }
}