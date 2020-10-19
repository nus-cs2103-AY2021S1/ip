import duke.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.ArrayList;


public class ToDoTest {
    @Test
    public void nameTest(){
        ToDo todoTest = new ToDo("homework");
        String DukeOutput = "[T]"+ "["+"\u2718" + "]" + "  homework";
        assertEquals(DukeOutput, todoTest.toString());
    }

    @Test
    public void addNewTodoTest() throws EmptyInputException, IOException, NoResponseException {
        TaskList tasks = new TaskList(new ArrayList<Task>());
        Ui ui = new Ui();
        Parser parser = new Parser(ui , tasks);
        String expectedOutput = "____________________________________________________________\n" +
                "     Got it. I've added this task:\n" +
                "       [T][✘]  PI\n" +
                "     Now you have 1 task in the list.\n" +
                "____________________________________________________________\n";
        String actualOutput = parser.parse("todo PI");
        assertEquals(expectedOutput,actualOutput );
    }

    @Test
    public void deleteTodo() throws EmptyInputException, IOException, NoResponseException {
        ToDo todoTest = new ToDo("homework");
        ArrayList<Task> tasksArr = new ArrayList<>();
        tasksArr.add(todoTest);
        TaskList tasks = new TaskList(tasksArr);
        Ui ui = new Ui();
        Parser parser = new Parser(ui , tasks);
        String expectedOutput = "____________________________________________________________\n" +
                "     Noted. I've removed this task:\n" +
                "       [T][✘]  homework\n" +
                "     Now you have 0 tasks in the list.\n" +
                "____________________________________________________________";
        String actualOutput = parser.parse("delete 1");
        assertEquals(expectedOutput,actualOutput );
    }

}
