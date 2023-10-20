package submarine;

public class West extends Cardinal{

    @Override
    public Cardinal getRight() {
        return new North();
    }

    @Override
    public Cardinal getLeft() {return new South();}

    @Override
    public Coordinates getFront() {
        return new Coordinates(-1, 0);
    }
}