package Tasks;

import Errors.ErrorExceptions;
import org.junit.jupiter.api.Test;


class TaskManagerTest {
    String date = "12-12-1212 1200";
    String dir = "./TestingSave.txt";
    final task t1 = new Todo("Test 1","[T]");
    final task t2 = new Deadline("Test 2","[D]");
    final task t3 = new Event("Test 3","[E]");

    @Test
    void getTask() throws ErrorExceptions {
//        TaskManager.newTask("Test 1","[T]",null,dir);
//        TaskManager.newTask("Test 2","[D]",date,dir);
//        TaskManager.newTask("Test 3","[E]",date,dir);
//        assertEquals(t1,TaskManager.getTask(0));
//        assertEquals(t1,TaskManager.getTask(1));
//        assertEquals(t1,TaskManager.getTask(2));
    }

    @Test
    void storeIndex() {
    }

    @Test
    void read() {
    }

    @Test
    void getStore() {
    }
}
