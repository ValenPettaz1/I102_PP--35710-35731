public class South extends Cardinal{

    @Override
    public Cardinal turnRight() {return Nemo.west;}

    @Override
    public Cardinal turnLeft() {return Nemo.east;}
}