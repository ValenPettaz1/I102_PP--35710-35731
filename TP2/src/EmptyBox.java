public class EmptyBox extends Box {
    public EmptyBox() {
    }
    @Override
    public Object openBox() {
        throw new Error("Queue is empty");
    }

}
