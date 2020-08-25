package duke.dependencies.parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserTest {

    private final Parser p = Parser.initParser();

    private final String validList = "list";
    private final String validDelete = "delete 1";
    private final String invalidDelete = "delete";

    private final String validDone = "done 1";
    private final String invalidDone = "done";
    private final String validEvent = "event meeting /at 2020-08-30";
    private final String invalidEvent = "event     ";
    private final String validDeadline = "deadline return books /by 2020-08-13";
    private final String invalidDeadline = "deadline      ";
    private final String validTodo = "todo run 100km";
    private final String invalidTodo = "todo      ";
    private final String invalidDate = "event /at 2020-30-08";
    private final String emptyDate = "event meeting /at ";
    private final String rubbishCommand = "fewfewfewfewfewf";

    @Test
    void test_EmptyTask_Response() {
        assertEquals("You have to tell me the task before I can do anything!!! O.o", p.parseAndExec(invalidTodo));
    }

    @Test
    void test_UnspecifiedDate_Response() {
        assertEquals("You need to give me the date!!!", p.parseAndExec(emptyDate));
    }

    @Test
    void test_UnknownCommand_Response() {
        assertEquals("C'mon, you know I don't understand this!", p.parseAndExec(rubbishCommand));
    }

    @Test
    void test_InvalidDate_Response() {
        assertEquals("I don't understand the date you are giving -_-\n" +
                        "Please give in the following formats:\n" +
                        "MM/dd/yyyy or yyyy-MM-dd",
                p.parseAndExec(invalidDate));
    }

}