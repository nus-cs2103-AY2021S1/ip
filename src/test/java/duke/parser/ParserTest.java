package duke.parser;

import duke.exception.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;


public class ParserTest {
    
    Parser p;
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
        public void testInvalidFormatDateException() {
            assertThrows(InvalidFormatDateException.class, () -> p.formatDateTime("2020/20/13 1600"));
        }

        @Test
        @DisplayName("Testing the DateTimeException in the method FormatDateTime (receives InvalidFormatDateException)")
        public void testDateTimeException() {
            assertThrows(InvalidFormatDateException.class, () -> p.formatDateTime("2020-30-30 1600"));
        }
        
    }
    
    @Nested
    class Parse {
        // missing on how to test created objects
        
        @Test
        @DisplayName("Testing the UnknownCommandException in the method Parse")
        public void testUnknownCommandException() {
            assertThrows(UnknownCommandException.class, () -> p.parse("blah"));
        }

        @Test
        @DisplayName("Testing the InvalidFormatByeException in the method Parse")
        public void testInvalidFormatByeException() {
            assertThrows(InvalidFormatByeException.class, () -> p.parse("bye 2"));
        }

        @Test
        @DisplayName("Testing the InvalidFormatListException in the method Parse")
        public void testInvalidFormatListException() {
            assertThrows(InvalidFormatListException.class, () -> p.parse("list 2"));
        }

        @Test
        @DisplayName("Testing the InvalidFormatDoneException in the method Parse")
        public void testInvalidFormatDoneException() {
            assertThrows(InvalidFormatDoneException.class, () -> p.parse("done here"));
        }

        @Test
        @DisplayName("Testing the EmptyTextException in the method Parse")
        public void testEmptyTextException() {
            assertThrows(EmptyTextException.class, () -> p.parse("todo"));
        }

        @Test
        @DisplayName("Testing the InvalidFormatDeleteException in the method Parse")
        public void testInvalidFormatDeleteException() {
            assertThrows(InvalidFormatDeleteException.class, () -> p.parse("delete"));
        }

        @Test
        @DisplayName("Testing the InvalidFormatDeleteException in the method Parse")
        public void testInvalidFormatFindException() {
            assertThrows(InvalidFormatFindException.class, () -> p.parse("find hello world"));
        }
        
    }


}
