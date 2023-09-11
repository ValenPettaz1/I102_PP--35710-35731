public class emptyBox extends Box {
    public emptyBox() {
    }
    @Override
    public Object openBox() {
        throw new Error("Queue is empty");
    }

}
