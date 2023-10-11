public class West extends Cardinal{

    @Override
    public Cardinal turnRight() {
        return Nemo.north;
    }

    @Override
    public Cardinal turnLeft() {return Nemo.south;}
}