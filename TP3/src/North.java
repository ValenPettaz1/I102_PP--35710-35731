public class North extends Cardinal{

    @Override
    public Cardinal turnRight() {return Nemo.east;}

    @Override
    public Cardinal turnLeft() {return Nemo.west;}
}
