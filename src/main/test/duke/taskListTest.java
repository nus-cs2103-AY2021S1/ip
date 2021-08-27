package duke;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class taskListTest {

    @Test
    void addingTask() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        duke.taskList tasks = new taskList();
        tasks.addTodo("homework", true);
        assertEquals("Got it. I've added this task:\n[T] [✕] homework\n" +
                "Now you have 1 tasks in the list.\n" , outContent.toString());

        outContent.reset();
        tasks.addDeadline("assignment", "2021-01-01", true);
        assertEquals("Got it. I've added this task:\n" +
                "[D] [✕] assignment (by: Jan 1 2021 )\n" +
                "Now you have 2 tasks in the list.\n", outContent.toString());

        outContent.reset();
        tasks.addEvent("carnival", "Monday 4-5PM", true);
        assertEquals("Got it. I've added this task:\n" +
                "[E] [✕] carnival (at: Monday 4-5PM )\n" +
                "Now you have 3 tasks in the list.\n", outContent.toString());
    }
}