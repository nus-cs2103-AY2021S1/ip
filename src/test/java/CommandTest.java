import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import duke.Parser;
import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;


public class CommandTest {

    @Test
    public void executeTodoCommand_validInput_success() throws DukeException {
        List<Task> emptyListOfTasks = new ArrayList<>();
        TaskList tasks = new TaskList(emptyListOfTasks);
        Ui ui = new Ui();
        Storage storage = new Storage();
        String expected = "Success! This task has been added:\n"
                + "[T][✘] junit tests\n" + "You have 1 tasks in your list now.";
        assertEquals(expected, Parser.parse("todo junit tests").execute(tasks, ui, storage));
    }

    @Test
    public void executeTodoCommand_missingDescription_throwsException() {
        List<Task> emptyListOfTasks = new ArrayList<>();
        TaskList tasks = new TaskList(emptyListOfTasks);
        Ui ui = new Ui();
        Storage storage = new Storage();
        DukeException ex = Assertions.assertThrows(DukeException.class, () -> {
            Parser.parse("todo").execute(tasks, ui, storage);
        });
        String expected = "Oh no! Your todo task description is empty. The task cannot be created.\n"
                + "Type 'help' for a list of all my functions and their commands.";
        assertEquals(expected, ex.getMessage());
    }

    @Test
    public void executeDeadlineCommand_validInput_success() throws DukeException {
        List<Task> emptyListOfTasks = new ArrayList<>();
        TaskList tasks = new TaskList(emptyListOfTasks);
        Ui ui = new Ui();
        Storage storage = new Storage();
        String expected = "Success! This task has been added:\n"
                + "[D][✘] junit (by: Sat, Feb 01 2020, 01:00 AM)\n" + "You have 1 tasks in your list now.";
        assertEquals(expected,
                Parser.parse("deadline junit /by 1/2/2020 1:00").execute(tasks, ui, storage));
    }

    @Test
    public void executeEventCommand_invalidArguments_throwsException() {
        List<Task> emptyListOfTasks = new ArrayList<>();
        TaskList tasks = new TaskList(emptyListOfTasks);
        Ui ui = new Ui();
        Storage storage = new Storage();
        DukeException ex = Assertions.assertThrows(DukeException.class, () -> {
            Parser.parse("event eat").execute(tasks, ui, storage);
        });
        String expected = "Oh no! Your event task has an incorrect format. The task cannot be created.\n"
                + "Type 'help' for a list of all my functions and their commands.";
        assertEquals(expected, ex.getMessage());
    }

    @Test
    public void executeEventCommand_missingDescription_throwsException() {
        List<Task> emptyListOfTasks = new ArrayList<>();
        TaskList tasks = new TaskList(emptyListOfTasks);
        Ui ui = new Ui();
        Storage storage = new Storage();
        DukeException ex = Assertions.assertThrows(DukeException.class, () -> {
            Parser.parse("event /at 1/2/2020 1:00").execute(tasks, ui, storage);
        });
        String expected = "Oh no! Your event task is missing a description. The task cannot be created.\n"
                + "Type 'help' for a list of all my functions and their commands.";
        assertEquals(expected, ex.getMessage());
    }

    @Test
    public void executeDeadlineCommand_missingTimeStamp_throwsException() {
        List<Task> emptyListOfTasks = new ArrayList<>();
        TaskList tasks = new TaskList(emptyListOfTasks);
        Ui ui = new Ui();
        Storage storage = new Storage();
        DukeException ex = Assertions.assertThrows(DukeException.class, () -> {
            Parser.parse("deadline eat /by").execute(tasks, ui, storage);
        });
        String expected = "Oh no! Your deadline task is missing a time stamp. The task cannot be created.\n"
                + "Type 'help' for a list of all my functions and their commands.";
        assertEquals(expected, ex.getMessage());
    }

    @Test
    public void executeEventCommand_invalidDateTimeFormat_throwsException() {
        List<Task> emptyListOfTasks = new ArrayList<>();
        TaskList tasks = new TaskList(emptyListOfTasks);
        Ui ui = new Ui();
        Storage storage = new Storage();
        DukeException ex = Assertions.assertThrows(DukeException.class, () -> {
            Parser.parse("event eat /at 12 feb 2020").execute(tasks, ui, storage);
        });
        String expected = "Oh no! The task date format is incorrect. \n"
                + "Please input a valid date using the format: 'dd/mm/yyyy hh:mm'. For eg, 10/8/2020 18:00\n"
                + "Type 'help' for a list of all my functions and their commands.";
        assertEquals(expected, ex.getMessage());
    }

    @Test
    public void executeDeadlineCommand_missingDescriptionAndTime_throwsException() {
        List<Task> emptyListOfTasks = new ArrayList<>();
        TaskList tasks = new TaskList(emptyListOfTasks);
        Ui ui = new Ui();
        Storage storage = new Storage();
        DukeException ex = Assertions.assertThrows(DukeException.class, () -> {
            Parser.parse("deadline /by").execute(tasks, ui, storage);
        });
        String expected = "Oh no! Your deadline task is missing a description and time stamp. "
                + "The task cannot be created.\n"
                + "Type 'help' for a list of all my functions and their commands.";
        assertEquals(expected, ex.getMessage());
    }

    @Test
    public void executeDeleteCommand_validTaskID_success() throws DukeException {
        List<Task> emptyListOfTasks = new ArrayList<>();
        Task todo = new Todo("read");
        Ui ui = new Ui();
        Storage storage = new Storage();
        TaskList tasks = new TaskList(emptyListOfTasks);
        todo.markAsDone();
        tasks.addTask(todo);
        String expected = "Found it! This task has been successfully deleted: \n"
                + "[T][✓] read\n" + "You have 0 tasks in your list now.";
        assertEquals(expected, Parser.parse("delete 1").execute(tasks, ui, storage));
    }

    @Test
    public void executeDeleteCommand_invalidTaskID_throwsException() {
        List<Task> listOfTasks = new ArrayList<>();
        Todo todo = new Todo("todo description");
        listOfTasks.add(todo);
        TaskList tasks = new TaskList(listOfTasks);
        Ui ui = new Ui();
        Storage storage = new Storage();
        DukeException ex = Assertions.assertThrows(DukeException.class, () -> {
            Parser.parse("delete 10").execute(tasks, ui, storage);
        });
        String expected = "Oh no! Invalid Task! The task ID you provided is not valid.";
        assertEquals(expected, ex.getMessage());
    }

    @Test
    public void executeDoneCommand_validTaskID_success() throws DukeException {
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
    public void executeDoneCommand_invalidTaskID_throwsException() {
        List<Task> emptyListOfTasks = new ArrayList<>();
        TaskList tasks = new TaskList(emptyListOfTasks);
        Ui ui = new Ui();
        Storage storage = new Storage();
        DukeException ex = Assertions.assertThrows(DukeException.class, () -> {
            Parser.parse("done 10").execute(tasks, ui, storage);
        });
        String expected = "Oh no! Invalid Task! The task ID you provided is not valid.";
        assertEquals(expected, ex.getMessage());
    }

    @Test
    public void executeExitCommand() throws DukeException {
        List<Task> emptyListOfTasks = new ArrayList<>();
        TaskList tasks = new TaskList(emptyListOfTasks);
        Ui ui = new Ui();
        Storage storage = new Storage();
        String expected = "Goodbye... For now.";
        assertEquals(expected, Parser.parse("bye").execute(tasks, ui, storage));
    }

    @Test
    public void executeFindByDateCommand_validDate_success() throws DukeException {
        List<Task> emptyListOfTasks = new ArrayList<>();
        TaskList tasks = new TaskList(emptyListOfTasks);
        Ui ui = new Ui();
        Storage storage = new Storage();
        Parser.parse("deadline eat /by 1/2/2020 1:00").execute(tasks, ui, storage);
        Parser.parse("event play /at 1/3/2020 12:00").execute(tasks, ui, storage);
        String expected = "Here are the tasks that I have found:\n"
                + "1. [D][✘] eat (by: Sat, Feb 01 2020, 01:00 AM)";
        assertEquals(expected, Parser.parse("find_by_date 1/2/2020").execute(tasks, ui, storage));
    }

    @Test
    public void executeFindByDateCommand_missingDate_throwsException() {
        List<Task> emptyListOfTasks = new ArrayList<>();
        TaskList tasks = new TaskList(emptyListOfTasks);
        Ui ui = new Ui();
        Storage storage = new Storage();
        DukeException ex = Assertions.assertThrows(DukeException.class, () -> {
            Parser.parse("find_by_date").execute(tasks, ui, storage);
        });
        String expected = "Oh no! No task date provided. "
                + "Please input a valid date using the format: 'dd/mm/yyyy'\n"
                + "Type 'help' for a list of all my functions and their commands.";
        assertEquals(expected, ex.getMessage());
    }

    @Test
    public void executeFindByKeywordCommand_validInput_success() throws DukeException {
        List<Task> emptyListOfTasks = new ArrayList<>();
        TaskList tasks = new TaskList(emptyListOfTasks);
        Ui ui = new Ui();
        Storage storage = new Storage();
        Parser.parse("deadline cs2103T tutorial /by 1/2/2020 1:00").execute(tasks, ui, storage);
        Parser.parse("event cs2100 lecture /at 1/3/2020 12:00").execute(tasks, ui, storage);
        String expected = "Here are the tasks that I have found:\n"
                + "1. [D][✘] cs2103T tutorial (by: Sat, Feb 01 2020, 01:00 AM)";
        assertEquals(expected, Parser.parse("find cs2103T").execute(tasks, ui, storage));
    }

    @Test
    public void executeFindByKeywordCommand_missingKeyword_throwsException() {
        List<Task> emptyListOfTasks = new ArrayList<>();
        TaskList tasks = new TaskList(emptyListOfTasks);
        Ui ui = new Ui();
        Storage storage = new Storage();
        DukeException ex = Assertions.assertThrows(DukeException.class, () -> {
            Parser.parse("find").execute(tasks, ui, storage);
        });
        String expected = "Oh no! No keyword for the search was entered. Please enter a keyword!\n"
                + "Type 'help' for a list of all my functions and their commands.";
        assertEquals(expected, ex.getMessage());
    }

    @Test
    public void executeInvalidCommand_invalidInput_throwsException() {
        List<Task> emptyListOfTasks = new ArrayList<>();
        TaskList tasks = new TaskList(emptyListOfTasks);
        Ui ui = new Ui();
        Storage storage = new Storage();
        DukeException ex = Assertions.assertThrows(DukeException.class, () -> {
            Parser.parse("blah").execute(tasks, ui, storage);
        });
        String expected = "Oh no! Invalid Function!\n"
                + "Type 'help' for a list of all my functions and their commands.";
        assertEquals(expected, ex.getMessage());
    }

    @Test
    public void executeTagCommand_validInput_success() throws DukeException {
        List<Task> emptyListOfTasks = new ArrayList<>();
        TaskList tasks = new TaskList(emptyListOfTasks);
        Ui ui = new Ui();
        Storage storage = new Storage();
        Todo todo = new Todo("tag tasks");
        tasks.addTask(todo);
        String expected = "Nice! I've added the tag to your task:\n"
                + "[T][✘][#fun] tag tasks";
        assertEquals(expected, Parser.parse("tag 1 fun").execute(tasks, ui, storage));
    }

    @Test
    public void executeTagCommand_missingTaskID_throwsException() {
        List<Task> emptyListOfTasks = new ArrayList<>();
        TaskList tasks = new TaskList(emptyListOfTasks);
        Ui ui = new Ui();
        Storage storage = new Storage();
        Todo todo = new Todo("tag tasks");
        tasks.addTask(todo);
        DukeException ex = Assertions.assertThrows(DukeException.class, () -> {
            Parser.parse("tag fun").execute(tasks, ui, storage);
        });
        String expected = "Oh no! Your tag command has missing arguments\n"
                + "Type 'help' for a list of all my functions and their commands.";
        assertEquals(expected, ex.getMessage());
    }

    @Test
    public void executeTagCommand_missingTagArgument_throwsException() {
        List<Task> emptyListOfTasks = new ArrayList<>();
        TaskList tasks = new TaskList(emptyListOfTasks);
        Ui ui = new Ui();
        Storage storage = new Storage();
        Todo todo = new Todo("tag tasks");
        tasks.addTask(todo);
        DukeException ex = Assertions.assertThrows(DukeException.class, () -> {
            Parser.parse("tag 1").execute(tasks, ui, storage);
        });
        String expected = "Oh no! Your tag command has missing arguments\n"
                + "Type 'help' for a list of all my functions and their commands.";
        assertEquals(expected, ex.getMessage());
    }

    @Test
    public void executeListCommand() throws DukeException {
        List<Task> emptyListOfTasks = new ArrayList<>();
        TaskList tasks = new TaskList(emptyListOfTasks);
        Ui ui = new Ui();
        Storage storage = new Storage();
        Parser.parse("todo finish ip").execute(tasks, ui, storage);
        Parser.parse("event start tp /at 1/9/2020 12:00").execute(tasks, ui, storage);
        String expected = "Here are the tasks that I have found:\n"
                + "1. [T][✘] finish ip\n" + "2. [E][✘] start tp (at: Tue, Sep 01 2020, 12:00 PM)";
        assertEquals(expected, Parser.parse("list").execute(tasks, ui, storage));
    }

}
