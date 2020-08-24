package Duke.Main;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import Duke.Tasks.*;

public class DukeTest {
    @Test
    public void StorageTest(){
        Storage storage = new Storage("notapath");
        try {
            List<Task> lst = storage.getFileContents();
            // Error should be thrown here
            fail("Storage class not supposed to accept random paths");
        } catch (Exception e) {
            String errMsg = "java.io.FileNotFoundException: notapath (No such file or directory)";
            assertEquals(e.toString(), errMsg);
        }
    }

    @Test
    public void ParserTest() {
        assertEquals(
                Parser.parseDateTime(Parser.strToDate("2019-04-18 1800")),
                "Apr 18 2019, 6.00PM"
        );
    }

    @Test
    public void TaskLiskTest() {
        Storage storage = new Storage("./data/tasks.txt");
        TaskList tasks = new TaskList(storage);
        String[] todo1 = {"T", "0", "write book"};
        String[] event2 = {"E", "0", "borrow book", "2019-04-18 1300"};
        String[] deadline3 = {"D", "0", "return book", "2019-04-19 1800"};
        try {
            tasks.addTask(todo1);
            tasks.addTask(event2);
            tasks.addTask(deadline3);

            tasks.completeTask(1);
            tasks.completeTask(2);
            tasks.completeTask(3);

            tasks.deleteTask(tasks.getSize());
            tasks.deleteTask(tasks.getSize());
            tasks.deleteTask(tasks.getSize());
            assertTrue(true);
        } catch (Exception e) {
//            System.out.println("Working Directory = " + System.getProperty("user.dir"));
            fail("One of the TaskList has failed");
        }
    }


}
