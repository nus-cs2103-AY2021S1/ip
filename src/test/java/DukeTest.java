import converter.Parser;
import exception.DukeException;
import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.ToDo;

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
    public void testTaskList1(){
        // Loading tasks
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
    public void testTaskList2(){
        //Adding and removing tasks
        TaskList taskList = new TaskList(new ArrayList<>());
        ToDo a = new ToDo("Punch");
        Deadline b = new Deadline("Fight", "2019-03-04");
        Event c = new Event("Join Fight Club", "Saturday");

        // Check Add
        taskList.add(a);

        assertEquals(taskList.listToString(), "1. [T][✘] Punch\n");

        // Check add + Remove
        taskList.add(b);
        taskList.remove(1);

        assertEquals(taskList.listToString(), "1. [D][✘] Fight (by: Mar 04 2019)\n");

        // Check markingDone
        taskList.add(c);
        taskList.markDone(1);
        taskList.markDone(2);

        assertEquals(taskList.listToString(),
                "1. [D][✓] Fight (by: Mar 04 2019)\n" +
                        "2. [E][✓] Join Fight Club (at: Saturday)\n");
    }

    @Test
    public void parserTestCase(){
        String input1 = "deadline go home /by 2019-05-06";
        String input2 = "event orbital /at tomorrow and later";
        String input3 = "todo nothing";

        Deadline d = new Deadline("go home", "2019-05-06");
        Event e = new Event("orbital", "tomorrow and later");
        ToDo t = new ToDo("nothing");

        Parser p = new Parser();

        try{
            assertEquals(d.toString(), p.task(input1).toString());
            assertEquals(e.toString(), p.task(input2).toString());
            assertEquals(t.toString(), p.task(input3).toString());
        }catch (DukeException ex){
            System.out.println(ex.getMessage());
        }
    }
}
