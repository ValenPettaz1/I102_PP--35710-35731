public class East extends Cardinal{

    @Override
    public Cardinal turnRight() {
        return Nemo.south;
    }

    @Override
    public Cardinal turnLeft() {
        return Nemo.north;
    }
}