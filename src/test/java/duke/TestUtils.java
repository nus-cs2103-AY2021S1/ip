package duke;

import java.time.LocalDate;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

public class TestUtils {
    public static final String TODO_UNDONE_STRING = "[T][\u2718] hello";
    public static final String TODO_DONE_STRING = "[T][\u2713] hello";
    public static final String TODO_UNDONE_PRINT = "T | 0 | hello";
    public static final String TODO_DONE_PRINT = "T | 1 | hello";
    public static final String DEADLINE_UNDONE_STRING = "[D][\u2718] hello (by: 1 Jan 2020)";
    public static final String DEADLINE_DONE_STRING = "[D][\u2713] hello (by: 1 Jan 2020)";
    public static final String DEADLINE_UNDONE_PRINT = "D | 0 | hello | 2020-01-01";
    public static final String DEADLINE_DONE_PRINT = "D | 1 | hello | 2020-01-01";
    public static final String EVENT_UNDONE_STRING = "[E][\u2718] hello (at: 1 Jan 2020)";
    public static final String EVENT_DONE_STRING = "[E][\u2713] hello (at: 1 Jan 2020)";
    public static final String EVENT_UNDONE_PRINT = "E | 0 | hello | 2020-01-01";
    public static final String EVENT_DONE_PRINT = "E | 1 | hello | 2020-01-01";

    public static final String TODO_COMMAND_CORRECT = "todo hello";
    public static final String TODO_COMMAND_EMPTY = "todo";
    public static final String DEADLINE_COMMAND_CORRECT = "deadline hello /by 2020-01-01";
    public static final String DEADLINE_COMMAND_INCORRECT_FORMAT = "deadline hello by 2020-01-01";
    public static final String DEADLINE_COMMAND_INCORRECT_DATE = "deadline hello /by 2020";
    public static final String DEADLINE_COMMAND_EMPTY = "deadline";
    public static final String EVENT_COMMAND_CORRECT = "event hello /at 2020-01-01";
    public static final String EVENT_COMMAND_INCORRECT_FORMAT = "event hello at 2020-01-01";
    public static final String EVENT_COMMAND_INCORRECT_DATE = "event hello /at 2020";
    public static final String EVENT_COMMAND_EMPTY = "event";

    public static ToDo createUndoneToDo() {
        return new ToDo("hello", false);
    }

    public static ToDo createDoneToDo() {
        return new ToDo("hello", true);
    }

    public static Deadline createUndoneDeadline() {
        return new Deadline("hello", false, LocalDate.parse("2020-01-01"));
    }

    public static Deadline createUndoneDeadlineDifferentDate() {
        return new Deadline("hello", false, LocalDate.parse("2019-01-01"));
    }

    public static Deadline createUndoneDeadlineDifferentTitle() {
        return new Deadline("hi", false, LocalDate.parse("2019-01-01"));
    }

    public static Deadline createDoneDeadline() {
        return new Deadline("hello", true, LocalDate.parse("2020-01-01"));
    }

    public static Event createUndoneEvent() {
        return new Event("hello", false, LocalDate.parse("2020-01-01"));
    }

    public static Event createDoneEvent() {
        return new Event("hello", true, LocalDate.parse("2020-01-01"));
    }
}
