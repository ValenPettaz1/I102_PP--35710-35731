public class East extends Cardinal{

    public East() {
    }

    @Override
    public Cardinal turnRight() {
        return new West();
    }

    @Override
    public Cardinal turnLeft() {
        return new North();
    }
}
