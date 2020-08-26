package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskTest {

    @Test
    void TaskGetStatusNumTest_notDone() {
        Task task=new Task("test1", false, TaskTypes.TODO);
        assertEquals("0", task.getStatusNum());
    }
    @Test
    void TaskGetStatusNumTest_done() {
        Task task=new Task("test2", true, TaskTypes.TODO);
        assertEquals("1", task.getStatusNum());
    }

    @Test
    void TaskWriteToFileTest() {
        Task task=new Task("test3", true, TaskTypes.DEADLINE);
        assertEquals("D | 1 | test3", task.writeToFile());
    }
}