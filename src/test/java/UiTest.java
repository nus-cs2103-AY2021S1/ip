import duke.Ui;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    @Test
    public void displayTaskAdd_todo() {
        //Set output stream to capture displayed text
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        String testTodoText = "This is a test todo";
        Todo testTodo = new Todo(testTodoText);
        new Ui().displayTaskAdd(testTodo, 3);

        String expected = "─────" + "\n"
                + "Got it. I've added this task: \n"
                + testTodo.toString()
                + "\nNow you have 3 tasks in the list\n"
                + "─────" + "\r\n";

        assertEquals(expected, outputStream.toString());
    }
}
