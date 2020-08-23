package duke.command;

import duke.component.Storage;
import duke.component.StorageStub;
import duke.component.TaskList;
import duke.component.Ui;
import duke.task.Deadline;
import duke.task.Event;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

public class HappenCommandTest {
    @Test
    public void isExit_alwaysFalse() {
        assertFalse(new HappenCommand("happen on today").isExit());
    }

    public void unrecognizedCommandHelper(String s, Ui ui, TaskList list, Storage storage) {
        try {
            new HappenCommand(s).execute(ui, list, storage);
            fail();
        } catch (Exception e) {
            assertEquals("Invalid happen command input.", e.getMessage());
        }
    }

    @Test
    public void execute_unrecognizedCommand_throwException() {
        Ui ui = new Ui();
        Storage storage = new StorageStub();
        TaskList list = storage.getList();

        unrecognizedCommandHelper("happen ", ui, list, storage);
        unrecognizedCommandHelper("happen anything", ui, list, storage);
        unrecognizedCommandHelper("happen onward", ui, list, storage);
        unrecognizedCommandHelper("happen on 1 2 3", ui, list, storage);
        unrecognizedCommandHelper("happen after 1 2", ui, list, storage);
        unrecognizedCommandHelper("happen in 3 months", ui, list, storage);
        unrecognizedCommandHelper("happen between a b c", ui, list, storage);
        unrecognizedCommandHelper("happen between a", ui, list, storage);
    }

    public void invalidDateFormatHelper(String s, Ui ui, TaskList list, Storage storage) {
        try {
            new HappenCommand(s).execute(ui, list, storage);
            fail();
        } catch (Exception e) {
            assertEquals("Invalid date format. Please use yyyy-MM-dd.", e.getMessage());
        }
    }

    @Test
    public void execute_invalidDateFormat_throwException() {
        Ui ui = new Ui();
        Storage storage = new StorageStub();
        TaskList list = storage.getList();

        invalidDateFormatHelper("happen on 2020", ui, list, storage);
        invalidDateFormatHelper("happen on tomorrow", ui, list, storage);
        invalidDateFormatHelper("happen before tomorrow", ui, list, storage);
        invalidDateFormatHelper("happen between 2020-08-09 2020/09/01", ui, list, storage);
    }

    @Test
    public void execute_nonPositiveInDays_throwException() {
        Ui ui = new Ui();
        Storage storage = new StorageStub();
        TaskList list = storage.getList();

        try {
            new HappenCommand("happen in 0 days").execute(ui, list, storage);
            fail();
        } catch (Exception e) {
            assertEquals("Please input a positive integer for happen in command.", e.getMessage());
        }

        try {
            new HappenCommand("happen in -3 days").execute(ui, list, storage);
            fail();
        } catch (Exception e) {
            assertEquals("Please input a positive integer for happen in command.", e.getMessage());
        }
    }

    @Test
    public void execute_invalidBetween_throwException() {
        Ui ui = new Ui();
        Storage storage = new StorageStub();
        TaskList list = storage.getList();

        try {
            new HappenCommand("happen between 2020-09-01 2020-08-01").execute(ui, list, storage);
            fail();
        } catch (Exception e) {
            assertEquals("Latter date is before former date for happen between.", e.getMessage());
        }
    }

    @Test
    public void execute_validCommand_showList() {
        Ui ui = new Ui();
        Storage storage = new StorageStub();
        TaskList list = storage.getList();
        try {
            LocalDate today = LocalDate.now();
            String td = today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate later = LocalDate.now().plusDays(2);
            String lt = later.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            list.add(new Deadline("assignment 1", "2021-09-01"));
            list.add(new Deadline("assignment 2", "2021-09-02"));
            list.add(new Deadline("assignemnt 3", "2019-09-01"));
            list.add(new Deadline("today assign 1", td));
            list.add(new Deadline("today assign 2", td));
            list.add(new Deadline("in 2 days", lt));
            list.add(new Event("meeting 1", "2021-09-01 11:00"));
            list.add(new Event("meeting 2", "2021-09-01 19:00"));
            list.add(new Event("today meeting 1", td + " 12:00"));
            list.add(new Event("later meeting", lt + " 13:00"));

            assertEquals("happening today 3",
                    new HappenCommand("happen on today").execute(ui, list, storage));
            assertEquals("happening on Sep 1 2021 3",
                    new HappenCommand("happen on 2021-09-01").execute(ui, list, storage));
            assertEquals("happening on Aug 31 2019 0",
                    new HappenCommand("happen on 2019-08-31").execute(ui, list, storage));
            assertEquals("happening before today 1",
                    new HappenCommand("happen before today").execute(ui, list, storage));
            assertEquals("happening before Jan 1 2020 1",
                    new HappenCommand("happen before 2020-01-01").execute(ui, list, storage));
            assertEquals("happening before Jan 1 2018 0",
                    new HappenCommand("happen before 2018-01-01").execute(ui, list, storage));
            assertEquals("happening after today 6",
                    new HappenCommand("happen after today").execute(ui, list, storage));
            assertEquals("happening after Jan 1 2021 4",
                    new HappenCommand("happen after 2021-01-01").execute(ui, list, storage));
            assertEquals("happening in 1 days 3",
                    new HappenCommand("happen in 1 days").execute(ui, list, storage));
            assertEquals("happening in 2 days 5",
                    new HappenCommand("happen in 2 days").execute(ui, list, storage));
            assertEquals("happening in 4 days 5",
                    new HappenCommand("happen in 4 days").execute(ui, list, storage));
            assertEquals("happening between Aug 31 2021 and Sep 30 2021 4",
                    new HappenCommand("happen between 2021-08-31 2021-09-30").execute(ui, list, storage));
            assertEquals("happening between Sep 1 2021 and Sep 2 2021 4",
                    new HappenCommand("happen between 2021-09-01 2021-09-02").execute(ui, list, storage));
        } catch (Exception e) {
            fail();
        }
    }
}
