package duke.parser;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;

import duke.list.ListManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import duke.exception.DukeParseException;
import duke.operation.addtaskoperation.AddDeadlineTaskOperation;
import duke.operation.addtaskoperation.AddEventTaskOperation;
import duke.operation.addtaskoperation.AddTodoTaskOperation;
import duke.operation.DeleteOperation;
import duke.operation.DoneOperation;
import duke.operation.ExitOperation;
import duke.operation.FindOperation;
import duke.operation.listoperation.ListTaskOperation;
import duke.operation.Operation;
import duke.storage.TaskStorage;
import duke.task.Todo;

public class CommandParserTest {
    private final CommandParser commandParser = new CommandParser();

    @Test
    public void parse_correctInput_success() throws DukeParseException {
        Todo mockTodo = new Todo("mock", false);
        ListManager listManager = new ListManager();
        listManager.getTaskList().addTask(mockTodo);
        TaskStorage storage = TaskStorage.createTaskStorage();

        String command = "todo read book";
        Operation operation = commandParser.parse(command, listManager, storage);
        assertTrue(operation instanceof AddTodoTaskOperation);

        command = "deadline return book /by 09-09-2019 1010";
        operation = commandParser.parse(command, listManager, storage);
        assertTrue(operation instanceof AddDeadlineTaskOperation);

        command = "event meeting /at 1430";
        operation = commandParser.parse(command, listManager, storage);
        assertTrue(operation instanceof AddEventTaskOperation);

        command = "list task";
        operation = commandParser.parse(command, listManager, storage);
        assertTrue(operation instanceof ListTaskOperation);

        command = "find book";
        operation = commandParser.parse(command, listManager, storage);
        assertTrue(operation instanceof FindOperation);

        command = "find read book";
        operation = commandParser.parse(command, listManager, storage);
        assertTrue(operation instanceof FindOperation);

        command = "done 1";
        operation = commandParser.parse(command, listManager, storage);
        assertTrue(operation instanceof DoneOperation);

        command = "delete 1";
        operation = commandParser.parse(command, listManager, storage);
        assertTrue(operation instanceof DeleteOperation);

        command = "bye";
        operation = commandParser.parse(command, listManager, storage);
        assertTrue(operation instanceof ExitOperation);
    }

    private static Stream<Arguments> getParse_wrongInput_exceptionThrownArguments() {
        return Stream.of(
                Arguments.of("Not a command"),
                Arguments.of("todo"),
                Arguments.of("deadline"),
                Arguments.of("deadline description /by"),
                Arguments.of("deadline /by"),
                Arguments.of("event description /at"),
                Arguments.of("event /at"),
                Arguments.of("deadline description /at 12-12-2012 1200"),
                Arguments.of("event description /by 2000"),
                Arguments.of(String.format("done %d", Integer.MAX_VALUE)),
                Arguments.of("done -1"),
                Arguments.of("done 0"),
                Arguments.of("done"),
                Arguments.of(String.format("delete %d", Integer.MAX_VALUE)),
                Arguments.of("delete -1"),
                Arguments.of("delete 0"),
                Arguments.of("delete"),
                Arguments.of("find"),
                Arguments.of("list")
        );
    }

    @ParameterizedTest
    @MethodSource("getParse_wrongInput_exceptionThrownArguments")
    public void parse_wrongInput_exceptionThrown(String command) {
        Todo mockTodo = new Todo("mock", false);
        ListManager listManager = new ListManager();
        listManager.getTaskList().addTask(mockTodo);
        TaskStorage storage = TaskStorage.createTaskStorage();
        try {
            commandParser.parse(command, listManager, storage);
        } catch (DukeParseException exception) {
            assertNotNull(exception.getMessage());
        }
    }
}
