package duke.command;

import duke.stub.task.TaskListStub;
import duke.task.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListCommandTest {
    @Test
    public void execute() {
        TaskList taskListStub = new TaskListStub();
        String actual = ListCommand.execute(taskListStub);
        String[] expected = new String[]{
                "Here are the tasks in your list:",
                "1.This todo stub was not marked as done",
                "2.This todo stub was not marked as done",
                "3.This todo stub was not marked as done",
                "4.This todo stub was not marked as done",
                "5.This todo stub was not marked as done",
                "6.This todo stub was not marked as done",
                "7.This todo stub was not marked as done",
                "8.[✘] event this is an event stub",
                "9.This todo stub was not marked as done",
                "10.This deadline stub was not marked as done"
        };
        assertEquals(String.join("\n", expected), actual);
    }
}
