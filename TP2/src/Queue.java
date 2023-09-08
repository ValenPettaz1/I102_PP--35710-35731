import java.util.ArrayList;
import java.util.List;

public class Queue {
    private List<Object> queue = new ArrayList<>();

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public Queue add(Object cargo) {
        queue.add(cargo);
        return this;
    }

    public Object take() {

        if (queue.isEmpty()) {
            throw new Error("Queue is empty");
        }
        return queue.remove(0);

    }

    public Object head() {
        return queue.get(0);
    }


    public int size() {
        return queue.size();
    }
}
