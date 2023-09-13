public class EmptyBox extends Box {

    public EmptyBox() {
    }
    @Override
    public Object openBox() {
        throw new Error(queueIsEmpty);
    }

    public static final String queueIsEmpty = "Queue is empty";

}
