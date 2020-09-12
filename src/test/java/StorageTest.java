import org.junit.jupiter.api.Test;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDos;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class StorageTest {
    @Test
    public void emptyFile() {
        //Simulating an empty or non existent file
        Storage store = new Storage("data/dike.txt");
        TaskList list = store.load();
        ArrayList<Task> tasks = list.getTasks();
        assertEquals(new ArrayList<>(), tasks);
    }

    @Test
    public void saveReopenTest() {
        // simulating what would happen if you load up a file and then write to it and close it again
        File f = new File("data/doke.txt");
        f.delete();
        TaskList list = Storage.loadTest("data/doke.txt");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        list.update(new ToDos("help me out"));
        list.update(new Deadline("I need your help", LocalDateTime.parse("23/02/2020 12:00", formatter)));
        list.update(new Event("We need to talk about the tests", LocalDateTime.parse("23/02/2020 12:00", formatter)));
        Storage.saveTest("data", "doke.txt");
        TaskList newList = Storage.loadTest("data/doke.txt");
        assertEquals(list.toString(), newList.toString());
    }
}
