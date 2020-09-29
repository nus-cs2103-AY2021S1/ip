package duke;

import static duke.TestUtils.DEADLINE_COMMAND_CORRECT;
import static duke.TestUtils.EVENT_COMMAND_CORRECT;
import static duke.TestUtils.TODO_COMMAND_CORRECT;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.command.ByeCommand;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.ToDoCommand;
import duke.exception.InvalidCommandException;

public class ParserTest {
    @Test
    public void parse_bye_returnsByeCommand() {
        ByeCommand byeCommand = new ByeCommand();
        assertDoesNotThrow(() -> assertEquals(byeCommand, Parser.parse("bye")));
    }

    @Test
    public void parse_deadline_returnsDeadlineCommand() {
        DeadlineCommand deadlineCommand = new DeadlineCommand(DEADLINE_COMMAND_CORRECT);
        assertDoesNotThrow(() -> assertEquals(deadlineCommand, Parser.parse(DEADLINE_COMMAND_CORRECT)));
    }

    @Test
    public void parse_delete_returnsDeleteCommand() {
        String[] input = {"delete", "1"};
        DeleteCommand deleteCommand = new DeleteCommand(input);
        assertDoesNotThrow(() -> assertEquals(deleteCommand, Parser.parse("delete 1")));
    }

    @Test
    public void parse_done_returnsDoneCommand() {
        String[] input = {"done", "1"};
        DoneCommand doneCommand = new DoneCommand(input);
        assertDoesNotThrow(() -> assertEquals(doneCommand, Parser.parse("done 1")));
    }

    @Test
    public void parse_event_returnsEventCommand() {
        EventCommand eventCommand = new EventCommand(EVENT_COMMAND_CORRECT);
        assertDoesNotThrow(() -> assertEquals(eventCommand, Parser.parse(EVENT_COMMAND_CORRECT)));
    }

    @Test
    public void parse_find_returnsFindCommand() {
        String command = "find hello";
        assertDoesNotThrow(() -> assertEquals(new FindCommand(command), Parser.parse(command)));
    }

    @Test
    public void parse_list_returnsListCommand() {
        String[] input = {"list", "2020-01-01"};
        ListCommand listCommand = new ListCommand(input);
        assertDoesNotThrow(() -> assertEquals(listCommand, Parser.parse("list 2020-01-01")));
    }

    @Test
    public void parse_todo_returnsToDoCommand() {
        ToDoCommand toDoCommand = new ToDoCommand(TODO_COMMAND_CORRECT);
        assertDoesNotThrow(() -> assertEquals(toDoCommand, Parser.parse(TODO_COMMAND_CORRECT)));
    }

    @Test
    public void parse_notACommand_throwsInvalidCommandException() {
        assertThrows(InvalidCommandException.class, () -> Parser.parse("hello"));
    }
}
