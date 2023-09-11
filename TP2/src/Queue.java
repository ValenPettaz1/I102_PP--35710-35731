import java.util.ArrayList;
import java.util.List;

public class Queue {

    private final Box nothing = new emptyBox();
    private List<Box> queue = new ArrayList<>(List.of(nothing));

    public boolean isEmpty() {
        return queue.size() == 1;
    }

    public Queue add(Object cargo) {
        addFilledBoxsFirst(cargo);
        return this;
    }


    public Object take() {
        return queue.remove(0).openBox();

    }

    public Object head() {
        return queue.get(0).openBox();
    }

    public int size() {
        return (queue.size()-1);
    }

    private void addFilledBoxsFirst(Object cargo) {
        queue.remove(size());
        queue.add(new filledBox(cargo));
        queue.add(nothing);
    }

}
