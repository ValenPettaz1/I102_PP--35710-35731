package queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.api.Test;

public class QueueTest {

    @Test
    public void test01QueueShouldBeEmptyWhenCreated() {
        assertTrue(new Queue().isEmpty());
    }

    @Test
    public void test02AddElementsToTheQueue() {
        assertFalse(queueWithSomething().isEmpty());
    }

    @Test
    public void test03AddedElementsIsAtHead() {
        assertEquals(something, queueWithSomething().head());
    }

    @Test
    public void test04TakeRemovesElementsFromTheQueue() {
        Queue queue = queueWithSomething();
        queue.take();
        assertTrue(queue.isEmpty());
    }

    @Test
    public void test05TakeReturnsLastAddedObject() {
        assertEquals(something, queueWithSomething().take());
    }

    @Test
    public void test06QueueBehavesFIFO() {
        Queue queue = queueWithOrderedObjects();
        assertEquals(queue.take(), firstAddedObject);
        assertEquals(queue.take(), secondAddedObject);
        assertTrue(queue.isEmpty());
    }

    @Test
    public void test07HeadReturnsFirstAddedObject() {
        Queue queue = queueWithOrderedObjects();
        assertEquals(queue.head(), firstAddedObject);
    }

    @Test
    public void test08HeadDoesNotRemoveObjectFromQueue() {
        Queue queue = queueWithSomething();
        assertEquals(1, queue.size());
        queue.head();
        assertEquals(1, queue.size());
    }

    @Test
    public void test09SizeRepresentsObjectInTheQueue() {
        assertEquals(2, new Queue().add(firstAddedObject).add(secondAddedObject).size());
    }

    @Test
    public void test10CanNotTakeWhenThereAreNoObjectsInTheQueue() {
        assertThrowsQueueIsEmpty(() -> new Queue().take());

    }

    @Test
    public void test11CanNotTakeWhenThereAreNoObjectsInTheQueueAndTheQueueHadObjects() {
        Queue queue = queueWithSomething();
        queue.take();
        assertThrowsQueueIsEmpty(() -> queue.take());
    }

    @Test
    public void test12CanNotHeadWhenThereAreNoObjectsInTheQueue() {
        assertThrowsQueueIsEmpty(() -> new Queue().head());
    }

    public static String something = "Something";
    public static String firstAddedObject = "First";
    public static String secondAddedObject = "Second";
    private Queue queueWithSomething() {
        return new Queue().add(something);
    }
    private Queue queueWithOrderedObjects() {
        Queue queue = new Queue();
        queue.add(firstAddedObject);
        queue.add(secondAddedObject);
        return queue;
    }
    private void assertThrowsQueueIsEmpty(Executable executable) {
        assertEquals(EmptyBox.queueIsEmpty, assertThrows(Error.class, executable).getMessage());
    }


}