package parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import duke.DukeException;

public class ParserTest {
    private LocalDate date1 = LocalDate.parse("2020-04-23");
    private LocalDate date2 = LocalDate.parse("2020-12-05");

    @Test
    public void formatDateTest1 () throws DukeException {
        assertEquals("Apr 23 2020 11.30pm", Parser.formatDateString(date1, "23/04/2020 2330"));
    }

    @Test
    public void formatDateTest2 () throws DukeException {
        assertEquals("Dec 5 2020 5.23am", Parser.formatDateString(date2, "5/12/2020 0523"));
    }

    @Test
    public void formatTimeTest1 () throws DukeException {
        assertEquals("11.30pm", Parser.formatTime("2330"));
    }

    @Test
    public void formatTimeTest2 () throws DukeException {
        assertEquals("5.23am", Parser.formatTime("0523"));
    }
}
