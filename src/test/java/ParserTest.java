import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    @Test
    public void parser_commandTypeBye_expectedBehaviour() {
        Parser parser = new Parser();
        try {
            Command byeCommand = parser.parse("bye");
            assert(byeCommand instanceof ByeCommand);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void parser_commandTypeList_expectedBehaviour() {
        Parser parser = new Parser();
        try {
            Command listCommand = parser.parse("list");
            assert(listCommand instanceof ListCommand);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void parser_commandTypeDone_expectedBehaviour() {
        Parser parser = new Parser();
        try {
            Command doneCommand = parser.parse("done 2");
            assert(doneCommand instanceof DoneCommand);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void parser_commandTypeDelete_expectedBehaviour() {
        Parser parser = new Parser();
        try {
            Command deleteCommand = parser.parse("delete 2");
            assert(deleteCommand instanceof DeleteCommand);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void parser_commandTypeDelete_exceptionThrown() {
        Parser parser = new Parser();
        assertThrows(DukeException.class, () -> parser.parse("delete"));
    }

    @Test
    public void parser_commandTypeAdd_expectedBehaviour() {
        Parser parser = new Parser();
        try {
            Command addTodoCommand = parser.parse("todo Test1");
            assert(addTodoCommand instanceof AddTodoCommand);
            Command addDeadlineCommand = parser.parse("deadline Test2 /by 2020-10-10");
            assert(addDeadlineCommand instanceof AddDeadlineCommand);
            Command addEventCommand = parser.parse("event Test3 /at 2020-10-10");
            assert(addEventCommand instanceof AddEventCommand);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
