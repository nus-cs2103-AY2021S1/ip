package duke.tests;

import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.commands.AddCommand;
import duke.commands.Command;
import duke.commands.DoneCommand;
import duke.exceptions.*;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.ToDo;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    Task TODO = new ToDo("Todo Testing", false);
    Task DEADLINE = new Deadline("Deadline Testing", false,
            LocalDateTime.of(2020, 8, 30, 16, 0 ));
    Task EVENT  = new Deadline("Event Testing", false,
            LocalDateTime.of(2020, 11, 21, 9, 30 ));

    @Test
    public void parser_invalidTaskException(){
        String commandLine = "wrong command words";
        try {
            Parser.parse(commandLine);
        } catch (DukeException e) {
            assertEquals(e.getMessage(), DukeInvalidTaskException.ERROR_INVALID_TASK);
        }
    }

    @Test
    public void parser_dateTimeParseException(){
        String commandLine = "deadline Deadline Testing /by 2020-2020-2020 12:00";
        try {
            Parser.parse(commandLine);
        } catch (DukeException e) {
            assertEquals(e.getMessage(), DukeDateTimeParseException.ERROR_INVALID_DATE);
        }
    }

    @Test
    public void parser_alreadyDoneException(){
        TaskList taskList = new TaskList(new ArrayList<Task>());
        Ui ui = new Ui();
        Storage storage = new Storage();

        Task doneToDo = new ToDo("Todo Testing", true);
        taskList.addToPlanner(doneToDo);

        try {
            Command command = new DoneCommand(0);
            command.execute(taskList, ui, storage);
        } catch (DukeException e) {
            assertEquals(e.getMessage(), DukeAlreadyDoneException.ERROR_ALREADY_DONE);
        }
    }

    @Test
    public void parser_invalidIndexException(){
        TaskList taskList = new TaskList(new ArrayList<Task>());
        Ui ui = new Ui();
        Storage storage = new Storage();

        Task doneToDo = new ToDo("Todo Testing", true);
        taskList.addToPlanner(doneToDo);

        try {
            Command command = new DoneCommand(1);
            command.execute(taskList, ui, storage);
        } catch (DukeException e) {
            assertEquals(e.getMessage(), DukeInvalidIndexException.ERROR_INVALID_INDEX);
        }
    }

    @Test
    public void parser_deadlineFormatException(){
        String commandLine = "deadline Testing without Date";
        try {
            Parser.parse(commandLine);
        } catch (DukeException e) {
            assertEquals(e.getMessage(), DukeDeadlineFormatException.ERROR_DEADLINE_FORMAT);
        }
    }

    @Test
    public void parser_deadlineEventException(){
        String commandLine = "event Testing without Date";
        try {
            Parser.parse(commandLine);
        } catch (DukeException e) {
            assertEquals(e.getMessage(), DukeEventFormatException.ERROR_EVENT_FORMAT);
        }
    }

    @Test
    public void parser_emptyDeadlineException(){
        String commandLine = "deadline";
        try {
            Parser.parse(commandLine);
        } catch (DukeException e) {
            assertEquals(e.getMessage(), DukeEmptyDeadlineException.ERROR_EMPTY_DEADLINE_TASK);
        }
    }

    @Test
    public void parser_emptyToDoException(){
        String commandLine = "todo";
        try {
            Parser.parse(commandLine);
        } catch (DukeException e) {
            assertEquals(e.getMessage(), DukeEmptyToDoException.ERROR_EMPTY_TODO_TASK);
        }
    }

    @Test
    public void parser_emptyEventException(){
        String commandLine = "event";
        try {
            Parser.parse(commandLine);
        } catch (DukeException e) {
            assertEquals(e.getMessage(), DukeEmptyEventException.ERROR_EMPTY_EVENT_TASK);
        }
    }

    @Test
    public void parser_emptyActionException(){
        String commandLine = "done";
        try {
            Parser.parse(commandLine);
        } catch (DukeException e) {
            assertEquals(e.getMessage(), DukeEmptyActionException.ERROR_EMPTY_ACTION_TASK);
        }
    }

    @Test
    public void parser_parse_addToDo(){
        String commandLine = "todo Todo Testing";
        Command command = new AddCommand(TODO);
        try {
            assertEquals(Parser.parse(commandLine).toString(), command.toString());
        } catch (DukeException e) {
            e.getMessage();
        }
    }

    @Test
    public void parser_parse_addDeadline(){
        String commandLine = "deadline Deadline Testing /by 2020-8-30 16:00";
        Command command = new AddCommand(DEADLINE);
        try {
            assertEquals(Parser.parse(commandLine).toString(), command.toString());
        } catch (DukeException e) {
            e.getMessage();
        }
    }

    @Test
    public void parser_parse_addEvent(){
        String commandLine = "deadline Event Testing /at 2020-11-21 9:30";
        Command command = new AddCommand(EVENT);
        try {
            assertEquals(Parser.parse(commandLine).toString(), command.toString());
        } catch (DukeException e) {
            e.getMessage();
        }
    }







}
