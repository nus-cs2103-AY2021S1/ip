package duke;

import duke.task.Task;
import duke.tool.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void markAsDoneTest1() {
        Task sample = new Task("sample");
        sample.markAsDone();
        Task sampleInList = new Task("sample1");
        TaskList list = new TaskList();
        list.add(sampleInList);
        list.markDone(0);
        assertEquals(sample.getStatus(), list.getTasks().get(0).getStatus());
    }

    @Test
    public void markAsDoneTest2() {
        Task sample = new Task("sample");
        sample.markAsDone();
        Task sampleInList1 = new Task("sample1");
        Task sampleInList2 = new Task("sample2");
        TaskList list = new TaskList();

        list.add(sampleInList1);
        list.add(sampleInList2);

        list.markDone(1);
        assertEquals(sample.getStatus(), list.getTasks().get(1).getStatus());
    }
}
