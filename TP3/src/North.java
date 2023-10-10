public class North extends Cardinal{
    public North() {}

    @Override
    public Cardinal turnRight() {
        return new East();
    }

    @Override
    public Cardinal turnLeft() {
        return new West();
    }
}
