package duke.parts;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import duke.error.EventDateParseException;
import duke.task.Event;
import duke.task.ToDo;
import org.junit.jupiter.api.Test;

public class StorageTest {
    @Test
    public void loadTest() throws EventDateParseException, IOException {
        TaskList tasks = new TaskList();
        Storage s = new Storage("./data/test1.txt");
        tasks.addTask(new ToDo("do something"), s);
        Event e = new Event("cool event", "2020-10-10");
        tasks.addTask(e, s);
        File file = new File("./data/test1.txt");

        BufferedReader br = new BufferedReader(new FileReader(file));
        assertEquals(br.readLine(), "T//0//do something");
        assertEquals(br.readLine(), "E//0//cool event//2020-10-10");
    }
}
