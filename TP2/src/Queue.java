import java.util.ArrayList;
import java.util.List;

public class Queue {

	private static Box emptyBox = new EmptyBox();
	
	private List<Box> queue = new ArrayList<>(List.of(emptyBox));

	public boolean isEmpty() {
		return queue.size() == 1;
	}

	public Queue add(Object cargo) {
		addFilledBoxsAsFirst(cargo);
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

	private void addFilledBoxsAsFirst(Object cargo) {
		queue.remove(size());
		queue.add(new FilledBox(cargo));
		queue.add(emptyBox);
	}

}
