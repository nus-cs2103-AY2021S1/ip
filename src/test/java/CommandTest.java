import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.Parser;
import duke.Storage;
import duke.Ui;

import duke.exception.DukeException;

import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class CommandTest {

    @Test
    public void addTodoCommand_validInput_success() throws DukeException {
        TaskList tasks = new TaskList(new ArrayList<>());
        Ui ui = new Ui();
        Storage storage = new Storage();
        String expected = "Success! This task has been added:\n"
                + "[T][✘] junit tests\n" + "You have 1 tasks in your list now.";
        assertEquals(expected, Parser.parse("todo junit tests").execute(tasks, ui, storage));
    }

    @Test
    public void addDeadlineCommand_validInput_success() throws DukeException {
        TaskList tasks = new TaskList(new ArrayList<>());
        Ui ui = new Ui();
        Storage storage = new Storage();
        String expected = "Success! This task has been added:\n"
                + "[D][✘] junit (by: Sat, Feb 01 2020, 01:00 AM)\n" + "You have 1 tasks in your list now.";
        assertEquals(expected,
                Parser.parse("deadline junit /by 1/2/2020 1:00").execute(tasks, ui, storage));
    }

    @Test
    public void addEventCommand_invalidInput_failure() throws DukeException {
        TaskList tasks = new TaskList(new ArrayList<>());
        Ui ui = new Ui();
        Storage storage = new Storage();
        DukeException ex = Assertions.assertThrows(DukeException.class, () -> {
            Parser.parse("event eat").execute(tasks, ui, storage);
        });
        String expected = "Oh no! Your event task has an incorrect format. The task cannot be created.\n"
                + "Type 'commands' for a list of all my functions and their commands.";
        assertEquals(expected, ex.getMessage());
    }

    @Test
    public void addEventCommand_missingDescription_throwsException() throws DukeException {
        TaskList tasks = new TaskList(new ArrayList<>());
        Ui ui = new Ui();
        Storage storage = new Storage();
        DukeException ex = Assertions.assertThrows(DukeException.class, () -> {
            Parser.parse("event /at 1/2/2020 1:00").execute(tasks, ui, storage);
        });
        String expected = "Oh no! Your event task is missing a description. The task cannot be created.\n"
                + "Type 'commands' for a list of all my functions and their commands.";
        assertEquals(expected, ex.getMessage());
    }

    @Test
    public void addDeadlineCommand_missingTimeStamp_throwsException() throws DukeException {
        TaskList tasks = new TaskList(new ArrayList<>());
        Ui ui = new Ui();
        Storage storage = new Storage();
        DukeException ex = Assertions.assertThrows(DukeException.class, () -> {
            Parser.parse("deadline eat /by").execute(tasks, ui, storage);
        });
        String expected = "Oh no! Your deadline task is missing a time stamp. The task cannot be created.\n"
                + "Type 'commands' for a list of all my functions and their commands.";
        assertEquals(expected, ex.getMessage());
    }

    @Test
    public void addEventCommand_invalidDateTimeFormat_throwsException() throws DukeException {
        TaskList tasks = new TaskList(new ArrayList<>());
        Ui ui = new Ui();
        Storage storage = new Storage();
        DukeException ex = Assertions.assertThrows(DukeException.class, () -> {
            Parser.parse("event eat /at 12 feb 2020").execute(tasks, ui, storage);
        });
        String expected = "Oh no! The task date format is incorrect. \n"
                + "Please input a valid date using the format: 'dd/mm/yyyy hh:mm'. For eg, 10/8/2020 18:00\n"
                + "Type 'commands' for a list of all my functions and their commands.";
        assertEquals(expected, ex.getMessage());
    }

    @Test
    public void addDeadlineCommand_missingDescriptionAndTime_throwsException() throws DukeException {
        TaskList tasks = new TaskList(new ArrayList<>());
        Ui ui = new Ui();
        Storage storage = new Storage();
        DukeException ex = Assertions.assertThrows(DukeException.class, () -> {
            Parser.parse("deadline /by").execute(tasks, ui, storage);
        });
        String expected = "Oh no! Your deadline task is missing a description and time stamp. "
                + "The task cannot be created.\n"
                + "Type 'commands' for a list of all my functions and their commands.";
        assertEquals(expected, ex.getMessage());
    }

    @Test
    public void deleteTaskCommand_validTaskID_success() throws DukeException {
        Task todo = new Todo("read");
        Ui ui = new Ui();
        Storage storage = new Storage();
        TaskList tasks = new TaskList(new ArrayList<>());
        todo.markAsDone();
        tasks.addTask(todo);
        String expected = "Found it! This task has been successfully deleted: \n"
                + "[T][✓] read\n" + "You have 0 tasks in your list now.";
        assertEquals(expected, Parser.parse("delete 1").execute(tasks, ui, storage));
    }

    @Test
    public void deleteTaskCommand_invalidTaskID_failure() throws DukeException {
        ArrayList<Task> listOfTasks = new ArrayList<>();
        listOfTasks.add(new Todo("todo description"));
        TaskList tasks = new TaskList(listOfTasks);
        Ui ui = new Ui();
        Storage storage = new Storage();
        DukeException ex = Assertions.assertThrows(DukeException.class, () -> {
            Parser.parse("delete 10").execute(tasks, ui, storage);
        });
        String expected = "Oh no! Invalid Task! The task ID you provided is not valid. \n"
                + "Type 'commands' for a list of all my functions and their commands.";
        assertEquals(expected, ex.getMessage());
    }

    @Test
    public void completeTaskCommand_validTaskID_success() throws DukeException {
        Task todo = new Todo("adding a todo");
        Ui ui = new Ui();
        Storage storage = new Storage();
        TaskList tasks = new TaskList(new ArrayList<>());
        tasks.addTask(todo);
        String expected = "Nice! I've marked this task as done:\n"
                + "[T][✓] adding a todo";
        assertEquals(expected, Parser.parse("done 1").execute(tasks, ui, storage));
    }

    @Test
    public void completeTaskCommand_invalidTaskID_throwsException() {
        DukeException ex = Assertions.assertThrows(DukeException.class, () -> {
            Parser.parse("done 10").execute(new TaskList(new ArrayList<>()), new Ui(), new Storage());
        });
        String expected = "Oh no! Invalid Task! The task ID you provided is not valid. \n"
                + "Type 'commands' for a list of all my functions and their commands.";
        assertEquals(expected, ex.getMessage());
    }

    @Test
    public void testExitCommand() throws DukeException {
        TaskList tasks = new TaskList(new ArrayList<>());
        Ui ui = new Ui();
        Storage storage = new Storage();
        String expected = "Goodbye... For now.";
        assertEquals(expected, Parser.parse("bye").execute(tasks, ui, storage));
    }

    @Test
    public void findTaskByDateCommand_validDate_success() throws DukeException {
        TaskList tasks = new TaskList(new ArrayList<>());
        Ui ui = new Ui();
        Storage storage = new Storage();
        Parser.parse("deadline eat /by 1/2/2020 1:00").execute(tasks, ui, storage);
        Parser.parse("event play /at 1/3/2020 12:00").execute(tasks, ui, storage);
        String expected = "Here are the tasks that I have found:\n"
                + "1. [D][✘] eat (by: Sat, Feb 01 2020, 01:00 AM)";
        assertEquals(expected, Parser.parse("find_by_date 1/2/2020").execute(tasks, ui, storage));
    }

    @Test
    public void findTaskByDateCommand_invalidDate_throwsException() {
        DukeException ex = Assertions.assertThrows(DukeException.class, () -> {
            Parser.parse("find_by_date 10 May").execute(new TaskList(new ArrayList<>()), new Ui(), new Storage());
        });
        String expected = "Oh no! The task date format is incorrect. Please input a valid date using the format: 'dd/mm/yyyy'";
        assertEquals(expected, ex.getMessage());
    }

    @Test
    public void findTaskByKeywordCommand_validInput_success() throws DukeException {
        TaskList tasks = new TaskList(new ArrayList<>());
        Ui ui = new Ui();
        Storage storage = new Storage();
        Parser.parse("deadline cs2103T tutorial /by 1/2/2020 1:00").execute(tasks, ui, storage);
        Parser.parse("event cs2100 lecture /at 1/3/2020 12:00").execute(tasks, ui, storage);
        String expected = "Here are the tasks that I have found:\n"
                + "1. [D][✘] cs2103T tutorial (by: Sat, Feb 01 2020, 01:00 AM)";
        assertEquals(expected, Parser.parse("find cs2103T").execute(tasks, ui, storage));
    }

    @Test
    public void findTaskByKeywordCommand_missingKeyword_throwsException() {
        DukeException ex = Assertions.assertThrows(DukeException.class, () -> {
            Parser.parse("find").execute(new TaskList(new ArrayList<>()), new Ui(), new Storage());
        });
        String expected = "Oh no! No keyword for the search was entered. Please enter a keyword!\n"
                + "Type 'commands' for a list of all my functions and their commands.";
        assertEquals(expected, ex.getMessage());
    }

    @Test
    public void invalidCommand_invalidInput_throwsException() {
        DukeException ex = Assertions.assertThrows(DukeException.class, () -> {
            Parser.parse("find").execute(new TaskList(new ArrayList<>()), new Ui(), new Storage());
        });
        String expected = "Oh no! No keyword for the search was entered. Please enter a keyword!\n"
                + "Type 'commands' for a list of all my functions and their commands.";
        assertEquals(expected, ex.getMessage());
    }

    @Test
    public void testListCommand() throws DukeException {
        TaskList tasks = new TaskList(new ArrayList<>());
        Ui ui = new Ui();
        Storage storage = new Storage();
        Parser.parse("todo finish ip").execute(tasks, ui, storage);
        Parser.parse("event start tp /at 1/9/2020 12:00").execute(tasks, ui, storage);
        String expected = "Here are the tasks that I have found:\n"
                + "1. [T][✘] finish ip\n" + "2. [E][✘] start tp (at: Tue, Sep 01 2020, 12:00 PM)";
        assertEquals(expected, Parser.parse("list").execute(tasks, ui, storage));
    }

}
