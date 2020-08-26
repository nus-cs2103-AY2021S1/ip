package duke.parser;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageParserTest {
    private static StorageParser storageParser = new StorageParser();

    private static Stream<Arguments> getArguments() {
        LocalDateTime dateTimeOne = LocalDateTime.of(2010, 5, 30, 14, 20);
        LocalDateTime dateTimeTwo = LocalDateTime.of(2020, 8, 8, 8, 8);
        return Stream.of(
                Arguments.of(new Todo("return book", true),
                        "T;1;return book"),
                Arguments.of(new Todo("return", false),
                        "T;0;return"),
                Arguments.of(new Deadline("complete assignment", true, dateTimeOne),
                        "D;1;complete assignment;May 30 2010 02:20 pm"),
                Arguments.of(new Deadline("long long long description", false, dateTimeTwo),
                        "D;0;long long long description;Aug 08 2020 08:08 am"),
                Arguments.of(new Event("short event", true, dateTimeTwo),
                        "E;1;short event;08:08 am"),
                Arguments.of(new Event("return book", false, dateTimeOne),
                        "E;0;return book;02:20 pm")
        );
    }

    @ParameterizedTest
    @MethodSource("getArguments")
    public void testConvertTaskToStorage(Task task, String actual) {
        String converted = storageParser.convertTaskToStorage(task);
        assertEquals(converted, actual + "\n");
    }

    @ParameterizedTest
    @MethodSource("getArguments")
    public void convertStorageToTask_correctSymbol_success(
            Task actual, String storage) throws DukeException {
        Task converted = storageParser.convertStorageToTask(storage);
        assertEquals(converted, actual);
    }

    private static Stream<Arguments> getConvertStorageToTaskExceptionArguments() {
        return Stream.of(
                Arguments.of("X;0;wrong symbol", "line"),
                Arguments.of("T;no completion status", "todo"),
                Arguments.of("D;1;no datetime", "deadline"),
                Arguments.of("E;1;no time", "event")
        );
    }

    @ParameterizedTest
    @MethodSource("getConvertStorageToTaskExceptionArguments")
    public void convertStorageToTask_wrongSymbol_exceptionThrown(String storageString, String task) {
        try {
            storageParser.convertStorageToTask(storageString);
        } catch (DukeException exception) {
            String err = String.format(
                    "Apologies. It appears this %s: '%s' is corrupted.", task, storageString);
            assertEquals(err, exception.getMessage());
        }
    }
}
