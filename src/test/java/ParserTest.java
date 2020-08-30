import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.commands.AddCommand;
import duke.commands.Command;
import duke.commands.DoneCommand;
import duke.exceptions.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests the methods and exceptions for the Parser class.
 */
public class ParserTest {
    Task TODO = new ToDo("Todo Testing", false);
    Task DEADLINE = new Deadline("Deadline Testing", false,
            LocalDateTime.of(2020, 8, 30, 16, 0 ));
    Task EVENT  = new Event("Event Testing", false,
            LocalDateTime.of(2020, 11, 21, 9, 30 ));

    @Test
    public void parser_invalidTaskException(){
        String commandLine = "wrong command words";
        assertThrows(DukeInvalidTaskException.class, () -> Parser.parse(commandLine));
    }

    @Test
    public void parser_dateTimeParseException(){
        String commandLine = "deadline Deadline Testing /by 2020-2020-2020 12:00";
        assertThrows(DukeDateTimeParseException.class, () -> Parser.parse(commandLine));
    }

    @Test
    public void parser_alreadyDoneException(){
        TaskList taskList = new TaskList(new ArrayList<Task>());
        Ui ui = new Ui();
        Storage storage = new Storage();

        Task doneToDo = new ToDo("Todo Testing", true);
        taskList.addToPlanner(doneToDo);

        assertThrows(DukeAlreadyDoneException.class, () -> {
            Command command = new DoneCommand(0);
            command.execute(taskList, ui, storage);
        });
    }

    @Test
    public void parser_invalidIndexException(){
        TaskList taskList = new TaskList(new ArrayList<Task>());
        Ui ui = new Ui();
        Storage storage = new Storage();

        Task doneToDo = new ToDo("Todo Testing", true);
        taskList.addToPlanner(doneToDo);

        assertThrows(DukeInvalidIndexException.class, () -> {
            Command command = new DoneCommand(1);
            command.execute(taskList, ui, storage);
        });
    }

    @Test
    public void parser_deadlineFormatException(){
        String commandLine = "deadline Testing without Date";
        assertThrows(DukeDeadlineFormatException.class, () -> Parser.parse(commandLine));
    }

    @Test
    public void parser_deadlineEventException(){
        String commandLine = "event Testing without Date";
        assertThrows(DukeEventFormatException.class, () -> Parser.parse(commandLine));
    }

    @Test
    public void parser_emptyDeadlineException(){
        String commandLine = "deadline";
        assertThrows(DukeEmptyDeadlineException.class, () -> Parser.parse(commandLine));
    }

    @Test
    public void parser_emptyToDoException(){
        String commandLine = "todo";
        assertThrows(DukeEmptyToDoException.class, () -> Parser.parse(commandLine));
    }

    @Test
    public void parser_emptyEventException(){
        String commandLine = "event";
        assertThrows(DukeEmptyEventException.class, () -> Parser.parse(commandLine));

    }

    @Test
    public void parser_emptyActionException(){
        String commandLine = "done";
        assertThrows(DukeEmptyActionException.class, () -> Parser.parse(commandLine));
    }

    @Test
    public void parser_parse_addToDo() throws DukeException {
        String commandLine = "todo Todo Testing";
        AddCommand test = new AddCommand(TODO);
        assertEquals(Parser.parse(commandLine).toString(), test.toString());
    }

    @Test
    public void parser_parse_addDeadline() throws DukeException {
        String commandLine = "deadline Deadline Testing /by 2020-8-30 16:00";
        Command command = new AddCommand(DEADLINE);
        assertEquals(Parser.parse(commandLine).toString(), command.toString());

    }

    @Test
    public void parser_parse_addEvent() throws DukeException {
        String commandLine = "event Event Testing /at 2020-11-21 9:30";
        Command command = new AddCommand(EVENT);
        assertEquals(Parser.parse(commandLine).toString(), command.toString());
    }
}