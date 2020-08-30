package ultron.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import ultron.Storage;
import ultron.TaskList;
import ultron.UI;
import ultron.exceptions.UltronException;
import ultron.tasks.Todo;

public class DeleteCommandTest {
    /**
     * Check if the isExit value is correct for Delete Command.
     */
    @Test
    public void isExitTest() {
        DeleteCommand deleteCommand = new DeleteCommand("");
        assertFalse(deleteCommand.isExit());
    }
    /**
     * Check if the delete command is deleting correctly.
     */
    @Test
    public void executeTest() {
        //Create the command
        DeleteCommand deleteCommand = new DeleteCommand("1");

        /* Create relevant argument */
        TaskList tasklist = new TaskList(new ArrayList<>());
        UI ui = new UI();
        Storage storage = new Storage("test.txt");

        /* Add item to task list */
        tasklist.add(Todo.parseCommand("hello"));
        try {
            deleteCommand.execute(tasklist, ui, storage);
            assertEquals(0, tasklist.size());
        } catch (UltronException e) {
            assert false;
        }
    }

    /**
     * Check if delete command ran if there was an argument
     */
    @Test
    public void executeNumberErrorTest() {
        //Give an alphabet for argument
        DeleteCommand deleteCommand = new DeleteCommand("a");
        try {
            TaskList tasklist = new TaskList(new ArrayList<>());
            UI ui = new UI();
            Storage storage = new Storage("test.txt");
            deleteCommand.execute(tasklist, ui, storage);
            assert false;
        } catch (UltronException e) {
            assert true;
        }
    }
    /**
     * Check if delete command for error if there are too much arguments
     */
    @Test
    public void executeTooMuchArgsTest() {
        //Give an alphabet for argument
        DeleteCommand deleteCommand = new DeleteCommand("1 2 3");
        try {
            TaskList tasklist = new TaskList(new ArrayList<>());
            UI ui = new UI();
            Storage storage = new Storage("test.txt");
            deleteCommand.execute(tasklist, ui, storage);
            assert false;
        } catch (UltronException e) {
            assert true;
        }
    }
    /**
     * Check if there are no arguments for delete command
     */
    @Test
    public void executeTooFewArgsTest() {
        //Give an alphabet for argument
        DeleteCommand deleteCommand = new DeleteCommand("");
        try {
            TaskList tasklist = new TaskList(new ArrayList<>());
            UI ui = new UI();
            Storage storage = new Storage("test.txt");
            deleteCommand.execute(tasklist, ui, storage);
            assert false;
        } catch (UltronException e) {
            assert true;
        }
    }
}
