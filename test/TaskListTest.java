import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {

    @Test
    void generateList() {
        File fakeFile = new File("../fake.txt");
        try {
            TaskList.generateList(fakeFile);
            fail();
        } catch (FileNotFoundException e) {

        }
    }

    @Test
    void size() {
        TaskList.flush();
        for (int i = 0; i < 10; i++) {
            TaskList.add(new Task(i + ""));
        }
        assertEquals(10, TaskList.size());
        TaskList.flush();
        for (int i = 0; i < 23; i++) {
            TaskList.add(new Task(i + ""));
        }
        assertEquals(23, TaskList.size());
        for (int i = 0; i < 236; i++) {
            TaskList.add(new Task(i + ""));
        }
        assertEquals(259, TaskList.size());

    }

    @Test
    void get() {
        TaskList.flush();
        TaskList.add(new Task("testing the get"));
        TaskList.add(new Task("fake"));
        assertEquals("testing the get", TaskList.get(0).description);
    }
}