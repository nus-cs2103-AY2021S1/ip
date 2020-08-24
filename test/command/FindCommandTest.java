package command;

import org.junit.jupiter.api.Test;
import task.Task;
import task.ToDo;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FindCommandTest {

    private List<Task> taskList = new ArrayList<>(1);

    @Test
    void isModifying_false() {
        assertFalse(new FindCommand(taskList, "placebo").hasUndo());
    }

    @Test
    void isExit_false() {
        assertFalse(new FindCommand(taskList, "placebo").isExit());
    }

    @Test
    void testExecute() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        // Attempt to match
        taskList.add(new ToDo("Eat some chips"));
        new FindCommand(taskList, "t some c").execute();
        assertEquals("Here are the matching tasks in your list:\r\n" +
                "1. [T][✗] Eat some chips\r\n" +
                "Number of tasks found: 1\r\n",
                outContent.toString());

        System.setOut(originalOut);
    }
}