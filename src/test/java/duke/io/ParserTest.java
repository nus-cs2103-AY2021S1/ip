package duke.io;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    Parser parser = new Parser();
    
    @Test
    public void getDataAndDescriptionTest() {
        String dummyTask = "deadline feed cat tuna /by 2312 26-8-2020";
        String [] actualDateAndDescription = parser.getDateAndDescription(dummyTask.split(" "));
        String [] expected = { "2312 26-8-2020", "feed cat tuna" };
        assertArrayEquals(actualDateAndDescription, expected);
    }
    
    @Test
    public void dateAndTimeFormatterTest() {
        String dummyEventTime = "31-8-2022 Wedding 2222 Prepare gift";
        ArrayList<Object> actual = parser.dateAndTimeFormatter(dummyEventTime);
        ArrayList<Object> expected = new ArrayList<>();
        expected.add(LocalDate.of(2022, 8, 31));
        expected.add("10.22pm");
        assertEquals(actual, expected);
    }
}
