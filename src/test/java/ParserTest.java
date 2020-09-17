import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import juke.Storage;
import juke.TaskList;
import juke.Ui;
import juke.command.Command;
import juke.task.Deadline;
import juke.task.Event;
import juke.task.Todo;

public class ParserTest {

    @Test
    public void testTodoParsing() {
        Ui ui = new Ui();
        Storage storage = new Storage();
        TaskList taskList = new TaskList(new ArrayList<>());

        String stringToParse = "todo eat eggs";
        String expectedText = "Alright matey, I've added this task:\n[T][\u2718] eat eggs\nYou have 1 tasks in total.";
        Command parsedCommand = ui.parseCommand(stringToParse);
        String actualText = parsedCommand.executeCommand(taskList, storage);

        assertEquals(expectedText, actualText);
    }

    @Test
    public void testEventParsing() {
        Ui ui = new Ui();
        Storage storage = new Storage();
        TaskList taskList = new TaskList(new ArrayList<>());

        String stringToParse = "event eat eggs /at 2020-09-17";
        String expectedText = "Alright matey, I've added this task:\n[E][\u2718] eat eggs (at: Sep 17 2020)\n"
                + "You have 1 tasks in total.";
        Command parsedCommand = ui.parseCommand(stringToParse);
        String actualText = parsedCommand.executeCommand(taskList, storage);

        assertEquals(expectedText, actualText);
    }

    @Test
    public void testDeleteParsing() {
        Ui ui = new Ui();
        Storage storage = new Storage();
        TaskList taskList = new TaskList(new ArrayList<>());
        taskList.addToList(new Event("eat eggs", "2020-09-17"));

        String stringToParse = "delete 1";
        String expectedText = "Well, if you insist. I've removed:\n[E][\u2718] eat eggs (at: Sep 17 2020)";
        Command parsedCommand = ui.parseCommand(stringToParse);
        String actualText = parsedCommand.executeCommand(taskList, storage);

        assertEquals(expectedText, actualText);
    }

    @Test
    public void testDoneParsing() {
        Ui ui = new Ui();
        Storage storage = new Storage();
        TaskList taskList = new TaskList(new ArrayList<>());
        taskList.addToList(new Event("eat eggs", "2020-09-17"));

        String stringToParse = "done 1";
        String expectedText = "Good Job, this task is now done:\n[E][\u2713] eat eggs (at: Sep 17 2020)";
        Command parsedCommand = ui.parseCommand(stringToParse);
        String actualText = parsedCommand.executeCommand(taskList, storage);

        assertEquals(expectedText, actualText);
    }

    @Test
    public void testFindParsing() {
        Ui ui = new Ui();
        Storage storage = new Storage();
        TaskList taskList = new TaskList(new ArrayList<>());
        taskList.addToList(new Event("eat eggs", "2020-09-17"));
        taskList.addToList(new Deadline("cook eggs", "2020-09-17"));
        taskList.addToList(new Todo("wash dishes"));

        String stringToParse = "find dishes";
        String expectedText = "Here are the tasks you're trying to find.\n1. [T][\u2718] wash dishes\n";
        Command parsedCommand = ui.parseCommand(stringToParse);
        String actualText = parsedCommand.executeCommand(taskList, storage);

        assertEquals(expectedText, actualText);
    }

    @Test
    public void testListParsing() {
        Ui ui = new Ui();
        Storage storage = new Storage();
        TaskList taskList = new TaskList(new ArrayList<>());
        taskList.addToList(new Event("eat eggs", "2020-09-17"));
        taskList.addToList(new Deadline("cook eggs", "2020-09-17"));
        taskList.addToList(new Todo("wash dishes"));

        String stringToParse = "list";
        String expectedText = "1. [E][\u2718] eat eggs (at: Sep 17 2020)\n"
                + "2. [D][\u2718] cook eggs (by: Sep 17 2020)\n"
                + "3. [T][\u2718] wash dishes\n";
        Command parsedCommand = ui.parseCommand(stringToParse);
        String actualText = parsedCommand.executeCommand(taskList, storage);

        assertEquals(expectedText, actualText);
    }

    @Test
    public void testUpdateDateParsing() {
        Ui ui = new Ui();
        Storage storage = new Storage();
        TaskList taskList = new TaskList(new ArrayList<>());
        taskList.addToList(new Event("eat eggs", "2020-09-17"));

        String stringToParse = "update /number 1 /date 2020-09-30";
        String expectedText = "I've changed the task date! The task now looks like this:\n"
                + "[E][\u2718] eat eggs (at: Sep 30 2020)";
        Command parsedCommand = ui.parseCommand(stringToParse);
        String actualText = parsedCommand.executeCommand(taskList, storage);

        assertEquals(expectedText, actualText);
    }

    @Test
    public void testUpdateDescriptionParsing() {
        Ui ui = new Ui();
        Storage storage = new Storage();
        TaskList taskList = new TaskList(new ArrayList<>());
        taskList.addToList(new Event("eat eggs", "2020-09-17"));

        String stringToParse = "update /number 1 /description fry eggs";
        String expectedText = "I've changed the task description! The task now looks like this:\n"
                + "[E][\u2718] fry eggs (at: Sep 17 2020)";
        Command parsedCommand = ui.parseCommand(stringToParse);
        String actualText = parsedCommand.executeCommand(taskList, storage);

        assertEquals(expectedText, actualText);
    }

    @Test
    public void testUpdateDateAndDescriptionParsing() {
        Ui ui = new Ui();
        Storage storage = new Storage();
        TaskList taskList = new TaskList(new ArrayList<>());
        taskList.addToList(new Event("eat eggs", "2020-09-17"));

        String stringToParse = "update /number 1 /description fry eggs /date 2020-09-30";
        String expectedText = "I've changed the task description and date! The task now looks like this:\n"
                + "[E][\u2718] fry eggs  (at: Sep 30 2020)";
        Command parsedCommand = ui.parseCommand(stringToParse);
        String actualText = parsedCommand.executeCommand(taskList, storage);

        assertEquals(expectedText, actualText);
    }
}
