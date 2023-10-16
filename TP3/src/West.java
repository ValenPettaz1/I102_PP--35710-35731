public class West extends Cardinal{

    @Override
    public Cardinal turnRight() {
        return new North();
    }

    @Override
    public Cardinal turnLeft() {return new South();}
    @Override
    public Point getFront() {
        return new Point(-1, 0,0);
    }
}