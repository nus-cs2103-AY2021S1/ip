package duke;

import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.DeleteCommand;
import duke.command.AddTodoCommand;
import duke.command.AddEventCommand;
import duke.command.AddDeadlineCommand;
import duke.command.UnknownCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    @Test
    public void parse_validByeCommand_exitCommand() {
        assertTrue(Parser.parse("bye") instanceof ExitCommand);
    }

    @Test
    public void parse_invalidByeCommandWrongCasing_unknownCommand() {
        assertTrue(Parser.parse("Bye") instanceof UnknownCommand);
    }

    @Test
    public void parse_validListCommand_listCommand() {
        assertTrue(Parser.parse("list") instanceof ListCommand);
    }

    @Test
    public void parse_invalidListCommandWrongCasing_unknownCommand() {
        assertTrue(Parser.parse("LIST") instanceof UnknownCommand);
    }

    @Test
    public void parse_validDoneCommand_doneCommand() {
        assertTrue(Parser.parse("done 2") instanceof DoneCommand);
    }

    @Test
    public void parse_invalidDoneCommandWithExtraWhitespaces_doneCommand() {
        assertTrue(Parser.parse("done    4") instanceof DoneCommand);
    }

    @Test
    public void parse_invalidDoneCommandNoTaskIndex_doneCommand() {
        assertTrue(Parser.parse("done") instanceof DoneCommand);
    }

    @Test
    public void parse_invalidDoneCommandWrongCasing_unknownCommand() {
        assertTrue(Parser.parse("Done 3") instanceof UnknownCommand);
    }

    @Test
    public void parse_invalidDoneCommandNoWhitespace_unknownCommand() {
        assertTrue(Parser.parse("done3") instanceof UnknownCommand);
    }

    @Test
    public void parse_validDeleteCommand_deleteCommand() {
        assertTrue(Parser.parse("delete 1") instanceof DeleteCommand);
    }

    @Test
    public void parse_invalidDeleteCommandWithExtraWhitespaces_deleteCommand() {
        assertTrue(Parser.parse("delete    4") instanceof DeleteCommand);
    }

    @Test
    public void parse_invalidDeleteCommandNoTaskIndex_deleteCommand() {
        assertTrue(Parser.parse("delete") instanceof DeleteCommand);
    }

    @Test
    public void parse_invalidDeleteCommandWrongCasing_unknownCommand() {
        assertTrue(Parser.parse("Delete 5") instanceof UnknownCommand);
    }

    @Test
    public void parse_invalidDeleteCommandNoWhitespace_unknownCommand() {
        assertTrue(Parser.parse("delete3") instanceof UnknownCommand);
    }

    @Test
    public void parse_validTodoCommand_addTodoCommand() {
        assertTrue(Parser.parse("todo read book") instanceof AddTodoCommand);
    }

    @Test
    public void parse_validTodoCommandExtraWhitespaces_addTodoCommand() {
        assertTrue(Parser.parse("todo     read book    ") instanceof AddTodoCommand);
    }

    @Test
    public void parse_invalidTodoCommandNoDescription_addTodoCommand() {
        assertTrue(Parser.parse("todo") instanceof AddTodoCommand);
    }

    @Test
    public void parse_invalidTodoCommandDescriptionIsWhitespace_addTodoCommand() {
        assertTrue(Parser.parse("todo      ") instanceof AddTodoCommand);
    }

    @Test
    public void parse_invalidTodoCommandWrongCasing_unknownCommand() {
        assertTrue(Parser.parse("Todo read book") instanceof UnknownCommand);
    }

    @Test
    public void parse_invalidTodoCommandNoWhitespace_unknownCommand() {
        assertTrue(Parser.parse("todoread book") instanceof UnknownCommand);
    }

    @Test
    public void parse_validEventCommand_addEventCommand() {
        assertTrue(Parser.parse("event this /at there") instanceof AddEventCommand);
    }

    @Test
    public void parse_validEventCommandExtraWhitespaces_addEventCommand() {
        assertTrue(Parser.parse("event    this     /at    there") instanceof AddEventCommand);
    }

    @Test
    public void parse_validEventCommandNoWhitespacesAroundAt_addEventCommand() {
        assertTrue(Parser.parse("event this/atthere") instanceof AddEventCommand);
    }

    @Test
    public void parse_invalidEventCommandNoDescription_addEventCommand() {
        assertTrue(Parser.parse("event") instanceof AddEventCommand);
    }

    @Test
    public void parse_invalidEventCommandDescriptionNoAt_addEventCommand() {
        assertTrue(Parser.parse("event this there") instanceof AddEventCommand);
    }

    @Test
    public void parse_invalidEventCommandDescriptionNoAt2_addEventCommand() {
        assertTrue(Parser.parse("event this /by there") instanceof AddEventCommand);
    }

    @Test
    public void parse_invalidEventCommandDescriptionNoVenue_addEventCommand() {
        assertTrue(Parser.parse("event this /at") instanceof AddEventCommand);
    }

    @Test
    public void parse_invalidEventCommandDescriptionNoTask_addEventCommand() {
        assertTrue(Parser.parse("event /at there") instanceof AddEventCommand);
    }

    @Test
    public void parse_invalidEventCommandWrongCasing_unknownCommand() {
        assertTrue(Parser.parse("Event this /at there") instanceof UnknownCommand);
    }

    @Test
    public void parse_invalidEventCommandNoWhitespace_unknownCommand() {
        assertTrue(Parser.parse("eventthis /at there") instanceof UnknownCommand);
    }

    @Test
    public void parse_validDeadlineCommand_addDeadlineCommand() {
        assertTrue(Parser.parse("deadline this /by then") instanceof AddDeadlineCommand);
    }

    @Test
    public void parse_validDeadlineCommandExtraWhitespaces_addDeadlineCommand() {
        assertTrue(Parser.parse("deadline    this     /by    then") instanceof AddDeadlineCommand);
    }

    @Test
    public void parse_validDeadlineCommandNoWhitespacesAroundAt_addDeadlineCommand() {
        assertTrue(Parser.parse("deadline this/bythen") instanceof AddDeadlineCommand);
    }

    @Test
    public void parse_invalidDeadlineCommandNoDescription_addDeadlineCommand() {
        assertTrue(Parser.parse("deadline") instanceof AddDeadlineCommand);
    }

    @Test
    public void parse_invalidDeadlineCommandDescriptionNoBy_addDeadlineCommand() {
        assertTrue(Parser.parse("deadline this then") instanceof AddDeadlineCommand);
    }

    @Test
    public void parse_invalidDeadlineCommandDescriptionNoBy2_addDeadlineCommand() {
        assertTrue(Parser.parse("deadline this /at then") instanceof AddDeadlineCommand);
    }

    @Test
    public void parse_invalidDeadlineCommandDescriptionNoVenue_addDeadlineCommand() {
        assertTrue(Parser.parse("deadline this /by") instanceof AddDeadlineCommand);
    }

    @Test
    public void parse_invalidDeadlineCommandDescriptionNoTask_addDeadlineCommand() {
        assertTrue(Parser.parse("deadline /by then") instanceof AddDeadlineCommand);
    }

    @Test
    public void parse_invalidDeadlineCommandWrongCasing_unknownCommand() {
        assertTrue(Parser.parse("Deadline this /by then") instanceof UnknownCommand);
    }

    @Test
    public void parse_invalidDeadlineCommandNoWhitespace_unknownCommand() {
        assertTrue(Parser.parse("deadlinethis /by then") instanceof UnknownCommand);
    }
}
