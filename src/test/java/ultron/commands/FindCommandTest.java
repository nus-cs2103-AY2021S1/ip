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

public class FindCommandTest {
    /**
     * Check if the isExit value of Find command is correct.
     */
    @Test
    public void isExitTest() {
        FindCommand findCommand = new FindCommand("");
        assertFalse(findCommand.isExit());
    }
    /**
     * Check if the find command returns the correct output.
     */
    @Test
    public void executeFind1Test() {

        //Create the find command
        FindCommand findCommand = new FindCommand("hello");

        //Create variables
        TaskList taskList = new TaskList(new ArrayList<>());
        UI ui = new UI();
        Storage storage = new Storage("test.txt");

        /* Add Tasks to task list */
        taskList.add(Todo.parseCommand("hello"));
        taskList.add(Todo.parseCommand("heo"));
        taskList.add(Todo.parseCommand("helios"));

        //Execute the command
        try {
            findCommand.execute(taskList, ui, storage);
            assertEquals(
                String.format("Why do you always bothering me?\n1. %s\n", Todo.parseCommand("hello")),
                ui.getMessage());
        } catch (UltronException e) {
            assert false;
        }

    }

    /**
     * Check if the find command returns the correct output.
     */
    @Test
    public void executeNoneTest() {

        //Create the find command
        FindCommand findCommand = new FindCommand("hello");

        //Create variables
        UI ui = new UI();
        Storage storage = new Storage("test.txt");
        TaskList taskList = new TaskList(new ArrayList<>());

        /* Add Tasks to task list */
        taskList.add(Todo.parseCommand("heo"));
        taskList.add(Todo.parseCommand("helios"));

        //Execute the command
        try {
            findCommand.execute(taskList, ui, storage);
            assertEquals(
                "Why do you always bothering me?\nThere is literally nothing here\n",
                ui.getMessage());
        } catch (UltronException e) {
            assert false;
        }

    }
    /**
     * Test Errors when executing.
     */
    @Test
    public void executeErrorTest() {
        //Create the find command
        FindCommand findCommand = new FindCommand("");

        //Create variables
        TaskList taskList = new TaskList(new ArrayList<>());
        UI ui = new UI();
        Storage storage = new Storage("test.txt");

        //Execute the command
        try {
            findCommand.execute(taskList, ui, storage);
            assert false;
        } catch (UltronException e) {
            assert true;
        }

    }
}
