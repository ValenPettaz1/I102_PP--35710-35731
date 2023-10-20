package submarine;

public class North extends Cardinal{

    @Override
    public Cardinal getRight() {return new East();}

    @Override
    public Cardinal getLeft() {return new West();}

    @Override
    public Coordinates getFront() {
        return new Coordinates(0, 1);
    }
}
