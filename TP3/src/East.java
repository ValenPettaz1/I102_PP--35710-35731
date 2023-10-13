public class East extends Cardinal{

    public East() {super("East");}

    public String getDirection() {
        return super.getDirection();
    }

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
        return new Point(1, 0, 0);
    }
}