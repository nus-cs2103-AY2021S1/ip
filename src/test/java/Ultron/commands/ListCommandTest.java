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

public class ListCommandTest {
    /**
     * Check if isExit is correct for List command
     */
    @Test
    public void isExitTest() {
        ListCommand listCommand = new ListCommand("");
        assertFalse(listCommand.isExit());
    }
    /**
     * Check if the list command has the correct output with no items
     */
    @Test
    public void executeNoneTest() {
        ListCommand listCommand = new ListCommand("");

        TaskList taskList = new TaskList(new ArrayList<>());
        UI ui = new UI();
        Storage storage = new Storage("test.txt");

        try {
            listCommand.execute(taskList, ui, storage);
            assertEquals("You have no business with me\n", ui.getMessage());
        } catch (UltronException e) {
            assert false;
        }
    }
    /**
     * Check if the list command has the correct output with some items
     */
    @Test
    public void executeSomeTest() {
        ListCommand listCommand = new ListCommand("");

        TaskList taskList = new TaskList(new ArrayList<>());
        UI ui = new UI();
        Storage storage = new Storage("test.txt");

        //Add the items to the task list
        taskList.add(new Todo("Hello"));

        try {
            listCommand.execute(taskList, ui, storage);
            assertEquals(
                String.format("Heh, you cant even remember what you had\n1.%s\n", new Todo("Hello")),
                ui.getMessage());
        } catch (UltronException e) {
            assert false;
        }
    }
    /**
     * Check if the list command can detect too much arguments
     */
    @Test
    public void executeErrorTest() {
        ListCommand listCommand = new ListCommand("error arguments");

        TaskList taskList = new TaskList(new ArrayList<>());
        UI ui = new UI();
        Storage storage = new Storage("test.txt");

        //Add the items to the task list
        taskList.add(new Todo("Hello"));

        try {
            listCommand.execute(taskList, ui, storage);
            assert false;
        } catch (UltronException e) {
            assert true;
        }
    }

}
