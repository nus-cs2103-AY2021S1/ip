package chatbot.parser;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import chatbot.commands.AddCommand;
import chatbot.commands.Command;
import chatbot.commands.DeleteCommand;
import chatbot.commands.DoneCommand;
import chatbot.commands.FindCommand;
import chatbot.commands.SortCommand;
import chatbot.common.Message;
import chatbot.data.Deadline;
import chatbot.data.Task;
import chatbot.data.Todo;
import chatbot.exception.ChatbotException;
import chatbot.exception.ParseException;

public class ParserTest {

    @Test
    public void parseAddCommand_validCommand_success() throws ChatbotException {
        // Test add todo task
        Command addTodoCmd = ChatbotParser.parseCommand("todo read book");
        assertTrue(addTodoCmd instanceof AddCommand);
        assertEquals(((AddCommand) addTodoCmd).getToAdd().getDescription(), "read book");

        // Test add deadline
        Command addDeadlineCmd = ChatbotParser.parseCommand("deadline return book /by 2020-10-10");
        assertTrue(addDeadlineCmd instanceof AddCommand);
        assertEquals(((AddCommand) addDeadlineCmd).getToAdd().getDescription(), "return book");
        assertEquals(((AddCommand) addDeadlineCmd).getToAdd().getDate().toString(), "2020-10-10");

        // Test add event
        Command addEventCmd = ChatbotParser.parseCommand("event attend wedding /at 2020-11-11");
        assertTrue(addEventCmd instanceof AddCommand);
        assertEquals(((AddCommand) addEventCmd).getToAdd().getDescription(), "attend wedding");
        assertEquals(((AddCommand) addEventCmd).getToAdd().getDate().toString(), "2020-11-11");
    }

    @Test
    public void parseAddCommand_emptyToDoDescription_exceptionThrown() {
        try {
            ChatbotParser.parseCommand("todo ");
        } catch (ChatbotException ex) {
            assertEquals(ex.getMessage(), Message.MESSAGE_EMPTY_TASK);
        }
    }

    @Test
    public void parseAddCommand_emptyDeadlineDescription_exceptionThrown() {
        try {
            ChatbotParser.parseCommand("deadline /by 2020-20-20 ");
        } catch (ChatbotException ex) {
            assertEquals(ex.getMessage(), Message.MESSAGE_EMPTY_TASK);
        }
    }

    @Test
    public void parseAddCommand_emptyEventDescription_exceptionThrown() {
        try {
            ChatbotParser.parseCommand("event /at 2020-20-20 ");
        } catch (ChatbotException ex) {
            assertEquals(ex.getMessage(), Message.MESSAGE_EMPTY_TASK);
        }
    }

    @Test
    public void parseAddCommand_deadlineEmptyDate_exceptionThrown() {
        try {
            ChatbotParser.parseCommand("deadline return book /by ");
        } catch (ChatbotException ex) {
            assertEquals(ex.getMessage(), Message.MESSAGE_EMPTY_DATE);
        }
    }

    @Test
    public void parseAddCommand_eventEmptyDate_exceptionThrown() {
        try {
            ChatbotParser.parseCommand("event attend wedding /at ");
        } catch (ChatbotException ex) {
            assertEquals(ex.getMessage(), Message.MESSAGE_EMPTY_DATE);
        }
    }

    @Test
    public void parseDeleteCommand_validCommand_success() throws ChatbotException {
        Command deleteCmd1 = ChatbotParser.parseCommand("delete 1");
        assertTrue(deleteCmd1 instanceof DeleteCommand);
        assertEquals(((DeleteCommand) deleteCmd1).getIndex(), 1);

        Command deleteCmd2 = ChatbotParser.parseCommand("delete 2");
        assertTrue(deleteCmd2 instanceof DeleteCommand);
        assertEquals(((DeleteCommand) deleteCmd2).getIndex(), 2);
    }

    @Test
    public void parseDeleteCommand_invalidIndex_exceptionThrown() throws ChatbotException {
        try {
            ChatbotParser.parseCommand("delete -1");
        } catch (ParseException ex) {
            assertEquals(ex.getMessage(), Message.MESSAGE_ITEM_DO_NOT_EXIST);
        }
    }

    @Test
    public void parseDoneCommand_validCommand_success() throws ChatbotException {
        Command doneCmd1 = ChatbotParser.parseCommand("done 1");
        assertTrue(doneCmd1 instanceof DoneCommand);
        assertEquals(((DoneCommand) doneCmd1).getIndex(), 1);

        Command doneCmd2 = ChatbotParser.parseCommand("done 2");
        assertTrue(doneCmd2 instanceof DoneCommand);
        assertEquals(((DoneCommand) doneCmd2).getIndex(), 2);
    }

    @Test
    public void parseDoneCommand_invalidIndex_exceptionThrown() throws ChatbotException {
        try {
            ChatbotParser.parseCommand("done -1");
        } catch (ParseException ex) {
            assertEquals(ex.getMessage(), Message.MESSAGE_ITEM_DO_NOT_EXIST);
        }
    }

    @Test
    public void parseFindByKeywordCommand_validCommand_success() throws ChatbotException {
        Todo task1 = Todo.newTodo("todo with the keyword submit");
        Todo task2 = Todo.newTodo("todo without the keyword");

        Command findCmd = ChatbotParser.parseCommand("find submit");

        assertTrue(findCmd instanceof FindCommand);
        Predicate<Task> findByKeyWordPred = ((FindCommand) findCmd).getPred();
        assertTrue(findByKeyWordPred.test(task1));
        assertTrue(!findByKeyWordPred.test(task2));
    }

    @Test
    public void parseFindByDateCommand_validCommand_success() throws ChatbotException {
        Deadline dl1 = Deadline.newDeadline("return book", LocalDate.parse("2020-08-25"));
        Deadline dl2 = Deadline.newDeadline("return book", LocalDate.parse("2020-01-25"));

        Command findCmd = ChatbotParser.parseCommand("date 2020-08-25");

        assertTrue(findCmd instanceof FindCommand);
        Predicate<Task> findByDatePred = ((FindCommand) findCmd).getPred();
        assertTrue(findByDatePred.test(dl1));
        assertTrue(!findByDatePred.test(dl2));
    }

    @Test
    public void parseSortByDateCommand_validCommand_sucess() throws ChatbotException {
        Deadline laterTask = Deadline.newDeadline("later event", LocalDate.parse("2020-08-25"));
        Deadline earlierTask = Deadline.newDeadline("earlier event", LocalDate.parse("2020-01-25"));

        Command sortCmd = ChatbotParser.parseCommand("sort /by latest");
        assertTrue(sortCmd instanceof SortCommand);
        Comparator<Task> comparator = ((SortCommand) sortCmd).getComparator();
        assertEquals(comparator.compare(laterTask, earlierTask), -1);
        assertEquals(comparator.compare(earlierTask, laterTask), 1);
    }

    @Test
    public void parseCommand_invalidCommand_exceptionThrown() {
        try {
            ChatbotParser.parseCommand("Some Unrecognized Command");
            fail();
        } catch (ChatbotException e) {
            assertEquals(e.getMessage(), Message.MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
