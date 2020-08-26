package duke.dependencies.executable;

import duke.dependencies.task.Task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class CommandTest {

    @Test
    void getTask() {
        Task t = Task.createEmptyTask();
        Executable e = Command.createAddCommand(t);
        assertSame(t, e.getTask());
    }

    @Test
    void test_createAddCommand() {
        Executable e = Command.createAddCommand(Task.createEmptyTask());
        assertEquals(CommandType.ADD, e.getType());
    }

    @Test
    void test_createDoneCommand() {
        Executable e = Command.createDoneCommand(Task.createEmptyTask());
        assertEquals(CommandType.DONE, e.getType());
    }

    @Test
    void test_createListCommand() {
        Executable e = Command.createListCommand(Task.createEmptyTask());
        assertEquals(CommandType.LIST, e.getType());
    }

    @Test
    void test_createDeleteCommand() {
        Executable e = Command.createDeleteCommand(Task.createEmptyTask());
        assertEquals(CommandType.DELETE, e.getType());
    }
}