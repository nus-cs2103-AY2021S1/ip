package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class TaskTest {
    private static Stream<Arguments> getArguments() {
        LocalDateTime dateTimeOne = LocalDateTime.of(2010, 5, 30, 14, 20);
        LocalDateTime dateTimeTwo = LocalDateTime.of(2020, 8, 8, 8, 8);
        return Stream.of(
                Arguments.of(new Todo("return book", true),
                        "T;1;return book"),
                Arguments.of(new Todo("return", false),
                        "T;0;return"),
                Arguments.of(new Deadline("complete assignment", true, dateTimeOne),
                        "D;1;complete assignment;May 30 2010 14:20"),
                Arguments.of(new Deadline("long long long description", false, dateTimeTwo),
                        "D;0;long long long description;Aug 08 2020 08:08"),
                Arguments.of(new Event("short event", true, dateTimeTwo),
                        "E;1;short event;08:08"),
                Arguments.of(new Event("return book", false, dateTimeOne),
                        "E;0;return book;14:20")
        );
    }

    @ParameterizedTest
    @MethodSource("getArguments")
    public void testConvertToStorageString(Task task, String actual) {
        String converted = task.convertToStorageString();
        assertEquals(converted, actual + "\n");
    }
}
