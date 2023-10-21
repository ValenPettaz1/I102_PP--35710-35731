package submarine;

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
    public Coordinates getFront() {
        return new Coordinates(1, 0);
    }
}