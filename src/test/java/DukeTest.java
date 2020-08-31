import Duke.Deadline;
import Duke.Event;
import Duke.Task;
import Duke.TaskList;
import Duke.ToDo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final static String sysLineSep = System.lineSeparator();

    @BeforeEach
    public void setUpStreams(){
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStream() {
        System.setOut(originalOut);
        try {
            FileWriter fw = new FileWriter("data/duke.txt", false);
            fw.write("");
            fw.close();

        }catch (IOException e) {
            System.out.println("file not loaded");
        }
    }

    @Test
    public void testCase1(){
        ArrayList<Task> testList = new ArrayList<>(Arrays.asList(
                new ToDo("Punch"),
                new Deadline("Fight", "2019-03-04"),
                new Event("Join Fight Club", "Saturday")
        ));

        TaskList taskList = new TaskList(testList);
        System.out.print(taskList.listToString());
        String expectedList = "1. [T][✘] Punch\n" +
                "2. [D][✘] Fight (by: Mar 04 2019)\n" +
                "3. [E][✘] Join Fight Club (at: Saturday)\n";
        assertEquals(expectedList,outContent.toString());
    }

    @Test
    public void testCase2(){
        ByteArrayInputStream in = new ByteArrayInputStream("todo nothing\nbye\n".getBytes());
        System.setIn(in);

        // Run main
        Duke.main(new String[0]);
        String expectedResponse = "Hello! I'm Duke\nWhat can I do for you?" + sysLineSep +
                "Go it. I've added this task:\n[T]" + "[" + Task.CROSS + "]" + " nothing" + sysLineSep +
                "Now you have 1 task in the list." + sysLineSep +
                "Bye. Hope to see you again soon!" + sysLineSep;

        assertEquals(expectedResponse, outContent.toString());

    }
}