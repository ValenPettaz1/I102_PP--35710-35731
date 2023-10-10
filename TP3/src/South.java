public class South extends Cardinal{
    public South() {}

    @Override
    public Cardinal turnRight() {
        return new West();
    }

    @Override
    public Cardinal turnLeft() {
        return new East();
    }

}
