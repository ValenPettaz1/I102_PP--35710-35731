package submarine;

public class South extends Cardinal{

    @Override
    public Cardinal getRight() {return new West();}

    @Override
    public Cardinal getLeft() {return new East();}

    @Override
    public Coordinates getFront() {
        return new Coordinates(0, -1);
    }
}