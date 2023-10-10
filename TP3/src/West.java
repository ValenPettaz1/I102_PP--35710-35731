public class West extends Cardinal{
    public West() {}

    @Override
    public Cardinal turnRight() {
        return new North();
    }

    @Override
    public Cardinal turnLeft() {
        return new South();
    }
}
