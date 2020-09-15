package duke.parts;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import duke.error.EventDateParseException;
import duke.task.Event;
import duke.task.ToDo;

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
