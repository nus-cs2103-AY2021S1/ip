package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import duke.exception.EmptyTextException;
import duke.exception.InvalidFormatByeException;
import duke.exception.InvalidFormatDateException;
import duke.exception.InvalidFormatDeleteException;
import duke.exception.InvalidFormatDoneException;
import duke.exception.InvalidFormatFindException;
import duke.exception.InvalidFormatListException;
import duke.exception.UnknownCommandException;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class ParserTest {
    private Parser p;
    @BeforeEach
    void init() {
        p = new Parser();
    }
    @Nested
    class FormatDateTime {
        @Test
        public void testFormatDateTime() {
            try {
                LocalDateTime obj = LocalDateTime.of(2020, 8, 25, 16, 23);
                assertEquals(obj, p.formatDateTime("2020-08-25 1623"));
            } catch (InvalidFormatDateException e) {
                System.out.println(e);
            }
        }

        @Test
        @DisplayName("Testing the InvalidFormatDateException in the method FormatDateTime")
        public void writeInvalidFormatDateException() {
            assertThrows(InvalidFormatDateException.class, () -> p.formatDateTime("2020/20/13 1600"));
        }

        @Test
        @DisplayName("Testing the DateTimeException in the method FormatDateTime (receives InvalidFormatDateException)")
        public void writeInvalidDateTimeException() {
            assertThrows(InvalidFormatDateException.class, () -> p.formatDateTime("2020-30-30 1600"));
        }
    }
    @Nested
    class Parse {
        // missing on how to test created objects
        @Test
        @DisplayName("Testing the UnknownCommandException in the method Parse")
        public void testUnknownCommandException() {
            TaskList tasks = new TaskList();
            Storage storage = new Storage();
            Ui ui = new Ui();
            assertThrows(UnknownCommandException.class, () -> p.parse("blah").execute(tasks, ui, storage));
        }

        @Test
        @DisplayName("Testing the InvalidFormatByeException in the method Parse")
        public void writeInvalidFormatByeException() {
            assertThrows(InvalidFormatByeException.class, () -> p.parse("bye 2"));
        }

        @Test
        @DisplayName("Testing the InvalidFormatListException in the method Parse")
        public void writeInvalidFormatListException() {
            assertThrows(InvalidFormatListException.class, () -> p.parse("list 2"));
        }

        @Test
        @DisplayName("Testing the InvalidFormatDoneException in the method Parse")
        public void writeInvalidFormatDoneException() {
            assertThrows(InvalidFormatDoneException.class, () -> p.parse("done here"));
        }

        @Test
        @DisplayName("Testing the EmptyTextException in the method Parse")
        public void writeEmptyTextException() {
            assertThrows(EmptyTextException.class, () -> p.parse("todo"));
        }

        @Test
        @DisplayName("Testing the InvalidFormatDeleteException in the method Parse")
        public void writeInvalidFormatDeleteException() {
            assertThrows(InvalidFormatDeleteException.class, () -> p.parse("delete"));
        }

        @Test
        @DisplayName("Testing the InvalidFormatDeleteException in the method Parse")
        public void writeInvalidFormatFindException() {
            assertThrows(InvalidFormatFindException.class, () -> p.parse("find hello world"));
        }
    }
}
