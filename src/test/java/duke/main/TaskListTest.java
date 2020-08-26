package duke.main;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

   @Test
   public void testSize() {
       TaskList tasks = new TaskList();
       tasks.add(new EventStub());
       tasks.add(new TodoStub());
       tasks.add(new DeadlineStub());
       assertEquals(3, tasks.size());
   }

    @Test
    public void testDelete() {
        TaskList tasks = new TaskList();
        tasks.add(new EventStub());
        tasks.add(new TodoStub());
        tasks.delete(1);
        assertEquals(1, tasks.size());
    }
}
