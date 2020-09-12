package duke;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddTodoCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.UnknownCommand;
import duke.exception.WrongFormatException;

public class ParserTest {
    @Test
    public void parse_validExitCommandByeAllLowerCase_exitCommandReturned() {
        try {
            assertTrue(Parser.parse("bye") instanceof ExitCommand);
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void parse_validExitCommandByeAllUpperCase_exitCommandReturned() {
        try {
            assertTrue(Parser.parse("BYE") instanceof ExitCommand);
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void parse_validExitCommandByeMixedCase_exitCommandReturned() {
        try {
            assertTrue(Parser.parse("ByE") instanceof ExitCommand);
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void parse_validExitCommandBLowerCase_exitCommandReturned() {
        try {
            assertTrue(Parser.parse("b") instanceof ExitCommand);
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void parse_validExitCommandBUpperCase_exitCommandReturned() {
        try {
            assertTrue(Parser.parse("B") instanceof ExitCommand);
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void parse_invalidExitCommandTooManyArguments_unknownCommandReturned() {
        try {
            assertTrue(Parser.parse("bye bye") instanceof UnknownCommand);
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void parse_validListCommandListAllLowerCase_listCommandReturned() {
        try {
            assertTrue(Parser.parse("list") instanceof ListCommand);
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void parse_validListCommandListAllUpperCase_listCommandReturned() {
        try {
            assertTrue(Parser.parse("LIST") instanceof ListCommand);
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void parse_validListCommandListMixedCase_listCommandReturned() {
        try {
            assertTrue(Parser.parse("LisT") instanceof ListCommand);
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void parse_validListCommandLsAllLowerCase_listCommandReturned() {
        try {
            assertTrue(Parser.parse("ls") instanceof ListCommand);
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void parse_validListCommandLsAllUpperCase_listCommandReturned() {
        try {
            assertTrue(Parser.parse("LS") instanceof ListCommand);
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void parse_validListCommandLsMixedCase_listCommandReturned() {
        try {
            assertTrue(Parser.parse("lS") instanceof ListCommand);
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void parse_validListCommandLLowerCase_listCommandReturned() {
        try {
            assertTrue(Parser.parse("l") instanceof ListCommand);
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void parse_validListCommandLUpperCase_listCommandReturned() {
        try {
            assertTrue(Parser.parse("L") instanceof ListCommand);
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void parse_invalidListCommandTooManyArguments_unknownCommandReturned() {
        try {
            assertTrue(Parser.parse("list all") instanceof UnknownCommand);
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void parse_validDoneCommandDoneAllLowerCase_doneCommandReturned() {
        try {
            assertTrue(Parser.parse("done 2") instanceof DoneCommand);
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void parse_validDoneCommandDoneAllUpperCase_doneCommandReturned() {
        try {
            assertTrue(Parser.parse("DONE 1") instanceof DoneCommand);
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void parse_validDoneCommandDoneMixedCase_doneCommandReturned() {
        try {
            assertTrue(Parser.parse("Done 3") instanceof DoneCommand);
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void parse_validDoneCommandDnAllLowerCase_doneCommandReturned() {
        try {
            assertTrue(Parser.parse("dn 3") instanceof DoneCommand);
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void parse_validDoneCommandDnAllUpperCase_doneCommandReturned() {
        try {
            assertTrue(Parser.parse("DN 3") instanceof DoneCommand);
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void parse_validDoneCommandDnMixedCase_doneCommandReturned() {
        try {
            assertTrue(Parser.parse("dN 3") instanceof DoneCommand);
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void parse_invalidDoneCommandExtraWhitespaces_wrongFormatExceptionThrown() {
        try {
            Parser.parse("done    4");
            fail();
        } catch (WrongFormatException e) {
            assertTrue(true);
        }
    }

    @Test
    public void parse_invalidDoneCommandNoTaskIndex_wrongFormatExceptionThrown() {
        try {
            Parser.parse("done");
            fail();
        } catch (WrongFormatException e) {
            assertTrue(true);
        }
    }

    @Test
    public void parse_invalidDoneCommandNoWhitespace_unknownCommandReturned() {
        try {
            assertTrue(Parser.parse("done3") instanceof UnknownCommand);
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void parse_validDeleteCommandDeleteAllLowerCase_deleteCommandReturned() {
        try {
            assertTrue(Parser.parse("delete 1") instanceof DeleteCommand);
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void parse_validDeleteCommandDeleteAllUpperCase_deleteCommandReturned() {
        try {
            assertTrue(Parser.parse("DELETE 3") instanceof DeleteCommand);
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void parse_validDeleteCommandDeleteMixedCase_deleteCommandReturned() {
        try {
            assertTrue(Parser.parse("deletE 7") instanceof DeleteCommand);
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void parse_validDeleteCommandDelAllLowerCase_deleteCommandReturned() {
        try {
            assertTrue(Parser.parse("del 5") instanceof DeleteCommand);
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void parse_validDeleteCommandDelAllUpperCase_deleteCommandReturned() {
        try {
            assertTrue(Parser.parse("DEL 5") instanceof DeleteCommand);
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void parse_validDeleteCommandDelMixedCase_deleteCommandReturned() {
        try {
            assertTrue(Parser.parse("dEl 5") instanceof DeleteCommand);
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void parse_invalidDeleteCommandExtraWhitespaces_wrongFormatExceptionThrown() {
        try {
            Parser.parse("delete    4");
            fail();
        } catch (WrongFormatException e) {
            assertTrue(true);
        }
    }

    @Test
    public void parse_invalidDeleteCommandNoTaskIndex_wrongFormatExceptionThrown() {
        try {
            Parser.parse("delete");
            fail();
        } catch (WrongFormatException e) {
            assertTrue(true);
        }
    }

    @Test
    public void parse_invalidDeleteCommandNoWhitespace_unknownCommandReturned() {
        try {
            assertTrue(Parser.parse("delete3") instanceof UnknownCommand);
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void parse_validTodoCommandTodoAllLowerCase_addTodoCommandReturned() {
        try {
            assertTrue(Parser.parse("todo read book") instanceof AddTodoCommand);
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void parse_validTodoCommandTodoAllUpperCase_addTodoCommandReturned() {
        try {
            assertTrue(Parser.parse("TODO read book") instanceof AddTodoCommand);
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void parse_validTodoCommandTodoMixedCase_addTodoCommandReturned() {
        try {
            assertTrue(Parser.parse("ToDo read book") instanceof AddTodoCommand);
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void parse_validTodoCommandTdAllLowerCase_addTodoCommandReturned() {
        try {
            assertTrue(Parser.parse("td read book") instanceof AddTodoCommand);
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void parse_validTodoCommandTdAllUpperCase_addTodoCommandReturned() {
        try {
            assertTrue(Parser.parse("TD read book") instanceof AddTodoCommand);
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void parse_validTodoCommandTdMixedCase_addTodoCommandReturned() {
        try {
            assertTrue(Parser.parse("Td read book") instanceof AddTodoCommand);
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void parse_validTodoCommandTLowerCase_addTodoCommandReturned() {
        try {
            assertTrue(Parser.parse("t read book") instanceof AddTodoCommand);
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void parse_validTodoCommandTUpperCase_addTodoCommandReturned() {
        try {
            assertTrue(Parser.parse("T read book") instanceof AddTodoCommand);
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void parse_validTodoCommandExtraWhitespaces_addTodoCommandReturned() {
        try {
            assertTrue(Parser.parse("todo     read book    ") instanceof AddTodoCommand);
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void parse_invalidTodoCommandNoDescription_wrongFormatExceptionThrown() {
        try {
            Parser.parse("todo");
            fail();
        } catch (WrongFormatException e) {
            assertTrue(true);
        }
    }

    @Test
    public void parse_invalidTodoCommandDescriptionIsWhitespace_wrongFormatExceptionThrown() {
        try {
            Parser.parse("todo      ");
            fail();
        } catch (WrongFormatException e) {
            assertTrue(true);
        }
    }

    @Test
    public void parse_invalidTodoCommandNoWhitespaceAfterTodo_unknownCommandReturned() {
        try {
            assertTrue(Parser.parse("todoread book") instanceof UnknownCommand);
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void parse_invalidTodoCommandNoWhitespaceAfterTd_unknownCommandReturned() {
        try {
            assertTrue(Parser.parse("tdread book") instanceof UnknownCommand);
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void parse_invalidTodoCommandNoWhitespaceAfterT_unknownCommandReturned() {
        try {
            assertTrue(Parser.parse("tread book") instanceof UnknownCommand);
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void parse_validEventCommandEventAllLowerCase_addEventCommandReturned() {
        try {
            assertTrue(Parser.parse("event this /at there") instanceof AddEventCommand);
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void parse_validEventCommandEventAllUpperCase_addEventCommandReturned() {
        try {
            assertTrue(Parser.parse("EVENT this /at there") instanceof AddEventCommand);
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void parse_validEventCommandEventMixedCase_addEventCommandReturned() {
        try {
            assertTrue(Parser.parse("EVENt this /at there") instanceof AddEventCommand);
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void parse_validEventCommandEvAllLowerCase_addEventCommandReturned() {
        try {
            assertTrue(Parser.parse("ev this /at there") instanceof AddEventCommand);
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void parse_validEventCommandEvAllUpperCase_addEventCommandReturned() {
        try {
            assertTrue(Parser.parse("EV this /at there") instanceof AddEventCommand);
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void parse_validEventCommandEvMixedCase_addEventCommandReturned() {
        try {
            assertTrue(Parser.parse("eV this /at there") instanceof AddEventCommand);
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void parse_validEventCommandELowerCase_addEventCommandReturned() {
        try {
            assertTrue(Parser.parse("e this /at there") instanceof AddEventCommand);
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void parse_validEventCommandEUpperCase_addEventCommandReturned() {
        try {
            assertTrue(Parser.parse("E this /at there") instanceof AddEventCommand);
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void parse_validEventCommandExtraWhitespaces_addEventCommandReturned() {
        try {
            assertTrue(Parser.parse("event    this     /at    there") instanceof AddEventCommand);
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void parse_validEventCommandNoWhitespacesAroundDelimiter_addEventCommandReturned() {
        try {
            assertTrue(Parser.parse("event this/atthere") instanceof AddEventCommand);
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void parse_invalidEventCommandNoDescription_wrongFormatExceptionThrown() {
        try {
            assertTrue(Parser.parse("event") instanceof AddEventCommand);
            fail();
        } catch (WrongFormatException e) {
            assertTrue(true);
        }
    }

    @Test
    public void parse_invalidEventCommandDescriptionNoDelimiter_wrongFormatExceptionThrown() {
        try {
            Parser.parse("event this there");
            fail();
        } catch (WrongFormatException e) {
            assertTrue(true);
        }
    }

    @Test
    public void parse_invalidEventCommandDescriptionWrongDelimiter_wrongFormatExceptionThrown() {
        try {
            assertTrue(Parser.parse("event this /by there") instanceof AddEventCommand);
            fail();
        } catch (WrongFormatException e) {
            assertTrue(true);
        }
    }

    @Test
    public void parse_invalidEventCommandDescriptionNoLocation_wrongFormatExceptionThrown() {
        try {
            Parser.parse("event this /at");
            fail();
        } catch (WrongFormatException e) {
            assertTrue(true);
        }
    }

    @Test
    public void parse_invalidEventCommandDescriptionNoTask_wrongFormatExceptionThrown() {
        try {
            Parser.parse("event /at there");
            fail();
        } catch (WrongFormatException e) {
            assertTrue(true);
        }
    }

    @Test
    public void parse_invalidEventCommandNoWhitespaceAfterEvent_unknownCommandReturned() {
        try {
            assertTrue(Parser.parse("eventthis /at there") instanceof UnknownCommand);
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void parse_invalidEventCommandNoWhitespaceAfterEv_unknownCommandReturned() {
        try {
            assertTrue(Parser.parse("evthis /at there") instanceof UnknownCommand);
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void parse_invalidEventCommandNoWhitespaceAfterE_unknownCommandReturned() {
        try {
            assertTrue(Parser.parse("ethis /at there") instanceof UnknownCommand);
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void parse_validDeadlineCommandDeadlineAllLowerCase_addDeadlineCommandReturned() {
        try {
            assertTrue(Parser.parse("deadline this /by 2020-10-21 1345") instanceof AddDeadlineCommand);
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void parse_validDeadlineCommandDeadlineAllUpperCase_addDeadlineCommandReturned() {
        try {
            assertTrue(Parser.parse("DEADLINE this /by 2020-10-21 1345") instanceof AddDeadlineCommand);
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void parse_validDeadlineCommandDeadlineMixedCase_addDeadlineCommandReturned() {
        try {
            assertTrue(Parser.parse("dEaDlInE this /by 2020-10-21 1345") instanceof AddDeadlineCommand);
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void parse_validDeadlineCommandDLowerCase_addDeadlineCommandReturned() {
        try {
            assertTrue(Parser.parse("d this /by 2020-10-21 1345") instanceof AddDeadlineCommand);
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void parse_validDeadlineCommandDUpperCase_addDeadlineCommandReturned() {
        try {
            assertTrue(Parser.parse("D this /by 2020-10-21 1345") instanceof AddDeadlineCommand);
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void parse_validDeadlineCommandExtraWhitespaces_addDeadlineCommandReturned() {
        try {
            assertTrue(Parser.parse("deadline    this     /by    2020-10-21 1345")
                    instanceof AddDeadlineCommand);
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void parse_validDeadlineCommandNoWhitespacesAroundDelimiter_addDeadlineCommandReturned() {
        try {
            assertTrue(Parser.parse("deadline this/by2020-10-21 1345") instanceof AddDeadlineCommand);
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void parse_invalidDeadlineCommandExtraWhitespacesInDeadline_wrongFormatExceptionThrown() {
        try {
            Parser.parse("deadline this /by 2020-10-21     1345");
            fail();
        } catch (WrongFormatException e) {
            assertTrue(true);
        }
    }

    @Test
    public void parse_invalidDeadlineCommandNoWhitespaceInDeadline_wrongFormatExceptionThrown() {
        try {
            Parser.parse("deadline this /by 2020-10-211345");
            fail();
        } catch (WrongFormatException e) {
            assertTrue(true);
        }
    }

    @Test
    public void parse_invalidDeadlineCommandInvalidDate_wrongFormatExceptionThrown() {
        try {
            Parser.parse("deadline this /by 2020-13-21 1345");
            fail();
        } catch (WrongFormatException e) {
            assertTrue(true);
        }
    }

    @Test
    public void parse_invalidDeadlineCommandInvalidTime_wrongFormatExceptionThrown() {
        try {
            Parser.parse("deadline this /by 2020-13-21 1365");
            fail();
        } catch (WrongFormatException e) {
            assertTrue(true);
        }
    }

    @Test
    public void parse_invalidDeadlineCommandNoDescription_wrongFormatExceptionThrown() {
        try {
            Parser.parse("deadline");
            fail();
        } catch (WrongFormatException e) {
            assertTrue(true);
        }
    }

    @Test
    public void parse_invalidDeadlineCommandDescriptionNoDelimiter_wrongFormatExceptionThrown() {
        try {
            Parser.parse("deadline this 2020-10-21 1345");
            fail();
        } catch (WrongFormatException e) {
            assertTrue(true);
        }
    }

    @Test
    public void parse_invalidDeadlineCommandDescriptionWrongDelimiter_wrongFormatExceptionThrown() {
        try {
            Parser.parse("deadline this /at 2020-10-21 1345");
            fail();
        } catch (WrongFormatException e) {
            assertTrue(true);
        }
    }

    @Test
    public void parse_invalidDeadlineCommandDescriptionNoDeadline_wrongFormatExceptionThrown() {
        try {
            Parser.parse("deadline this /by");
            fail();
        } catch (WrongFormatException e) {
            assertTrue(true);
        }
    }

    @Test
    public void parse_invalidDeadlineCommandDescriptionNoTask_wrongFormatExceptionThrown() {
        try {
            Parser.parse("deadline /by 2020-10-21 1345");
            fail();
        } catch (WrongFormatException e) {
            assertTrue(true);
        }
    }

    @Test
    public void parse_invalidDeadlineCommandNoWhitespaceAfterDeadline_unknownCommandReturned() {
        try {
            assertTrue(Parser.parse("deadlinethis /by 2020-10-21 1345") instanceof UnknownCommand);
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void parse_invalidDeadlineCommandNoWhitespaceAfterD_unknownCommandReturned() {
        try {
            assertTrue(Parser.parse("dthis /by 2020-10-21 1345") instanceof UnknownCommand);
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void parse_validFindCommandFindAllLowerCase_findCommandReturned() {
        try {
            assertTrue(Parser.parse("find homework") instanceof FindCommand);
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void parse_validFindCommandFindAllUpperCase_findCommandReturned() {
        try {
            assertTrue(Parser.parse("FIND homework") instanceof FindCommand);
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void parse_validFindCommandFindMixedCase_findCommandReturned() {
        try {
            assertTrue(Parser.parse("fINd homework") instanceof FindCommand);
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void parse_invalidFindCommandNoWhitespace_unknownCommandReturned() {
        try {
            assertTrue(Parser.parse("findhomework") instanceof UnknownCommand);
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void parse_invalidFindCommandNoDescription_wrongFormatExceptionThrown() {
        try {
            Parser.parse("find");
            fail();
        } catch (WrongFormatException e) {
            assertTrue(true);
        }
    }

    @Test
    public void parse_invalidFindCommandDescriptionIsWhitespace_wrongFormatExceptionThrown() {
        try {
            Parser.parse("find            ");
            fail();
        } catch (WrongFormatException e) {
            assertTrue(true);
        }
    }

    @Test
    public void parse_unknownCommand_unknownCommandReturned() {
        try {
            assertTrue(Parser.parse("foo") instanceof UnknownCommand);
        } catch (WrongFormatException e) {
            fail();
        }
    }
}
