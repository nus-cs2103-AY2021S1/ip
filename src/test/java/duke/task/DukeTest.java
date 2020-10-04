package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class DukeTest {
    @Test
    public void taskListOperationsTest() {
        //creating basic TaskList
        ArrayList<Task> al = new ArrayList<>();
        al.add(new Todo("test 1"));
        al.add(new Deadline("test 2", false , LocalDate.of(2019, 10, 10)));
        TaskList tasks = new TaskList(al);

        //testing add()
        tasks.add(new Event("test 3", false, LocalDate.of(2019, 11, 10)));
        assertEquals(3, tasks.length());

        //testing delete()
        tasks.delete(0);
        assertEquals(2, tasks.length());

        //testing get()
        String actual = tasks.get(0).toString();
        String expected = "[D][✘] test 2 (by: Oct 10 2019)";
        assertEquals(expected, actual);
    }

    @Test
    public void storageUploadTest() throws YooException, IOException {

        //upload() dummy text for testing
        Storage storage = new Storage("data/tasks.txt");
        TaskList tasks = new TaskList();
        tasks.add(new Todo("for testing"));
        storage.upload(tasks);

        //reading the uploaded dummy text
        File test = new File("data/tasks.txt");
        BufferedReader br = new BufferedReader(new FileReader(test));
        String actual = "";
        String line;
        while ((line = br.readLine()) != null) {
            actual += line;
        }

        assertEquals("T // 0 // for testing // ", actual);
        PrintWriter pw = new PrintWriter("data/tasks.txt");
        pw.close();
    }
}
