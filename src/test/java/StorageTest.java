import org.junit.Test;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDos;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

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
        Storage store = new Storage("data/doke.txt");
        TaskList list = store.load();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        list.update(new ToDos("help me out"));
        list.update(new Deadline("I need your help", LocalDateTime.parse("23/02/2020 12:00", formatter)));
        list.update(new Event("We need to talk about the tests", LocalDateTime.parse("23/02/2020 12:00", formatter)));
        store.save();
        TaskList newList = store.load();
        assertEquals(list.toString(), newList.toString());
    }
}
