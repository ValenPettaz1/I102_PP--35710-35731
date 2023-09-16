package queue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Queue {

    private List<Box> queue = new ArrayList<>(Arrays.asList(new EmptyBox()));

    public boolean isEmpty() {
        return size() == 0;
    }

    public Queue add(Object cargo) {
        queue.add(size(), new FilledBox(cargo));
        return this;
    }

    public Object take() {
        return queue.remove(0).openBox();
    }

    public Object head() {
        return queue.get(0).openBox();
    }

    public int size() {
        return (queue.size() - 1);
    }
}
