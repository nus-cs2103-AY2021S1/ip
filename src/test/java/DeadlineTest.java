import duke.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void nameTest() {
        Deadline deadlineTest = new Deadline("reading","2019-08-27");
        String DukeOutput = "[D]"+ "["+"\u2718" + "]" + "  reading" +" (by: Aug 27 2019)";
        assertEquals(DukeOutput, deadlineTest.toString());
    }

    @Test
    public void addNewDeadlineTest() throws EmptyInputException, IOException, NoResponseException {
        TaskList tasks = new TaskList(new ArrayList<Task>());
        Ui ui = new Ui();
        Parser parser = new Parser(ui , tasks);
        String expectedOutput = "____________________________________________________________\n" +
                "     Got it. I've added this task:\n" +
                "       [D][✘]  CS2103 (by: Aug 27 2019)\n" +
                "     Now you have 1 task in the list.\n" +
                "____________________________________________________________\n";
        String actualOutput = parser.parse("deadline CS2103 /by 2019-08-27");
        assertEquals(expectedOutput,actualOutput );
    }

    @Test
    public void deleteDeadline() throws EmptyInputException, IOException, NoResponseException {
        Deadline DeadlineTest1 = new Deadline("homework","2019-08-27");
        Deadline DeadlineTest2 = new Deadline("PI","2020-09-21");
        ArrayList<Task> tasksArr = new ArrayList<>();
        tasksArr.add(DeadlineTest1);
        tasksArr.add(DeadlineTest2);
        TaskList tasks = new TaskList(tasksArr);
        Ui ui = new Ui();
        Parser parser = new Parser(ui , tasks);
        String expectedOutput = "____________________________________________________________\n" +
                "     Noted. I've removed this task:\n" +
                "       [D][✘]  homework (by: Aug 27 2019)\n" +
                "     Now you have 1 tasks in the list.\n" +
                "____________________________________________________________";
        String actualOutput = parser.parse("delete 1");
        assertEquals(expectedOutput,actualOutput );
    }





}
