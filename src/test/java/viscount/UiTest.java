package viscount;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import viscount.task.Deadline;
import viscount.task.Event;
import viscount.task.Task;
import viscount.task.Todo;

public class UiTest {
    private static final String TICK_ICON = "\u2713";
    private static final String CROSS_ICON = "\u2718";

    private static final List<Task> DEFAULT_TASKS = Arrays.asList(
            new Todo("t1", false),
            new Deadline("t2", true, LocalDateTime.of(2020, 8, 24, 10, 0)),
            new Deadline("t3", false, LocalDateTime.of(2020, 8, 27, 11, 0)),
            new Event("t4", true, LocalDateTime.of(2020, 8, 24, 11, 0)),
            new Event("t5", false, LocalDateTime.of(2020, 8, 27, 12, 0)));

    @Test
    @DisplayName("Get welcome message")
    public void getWelcomeMessage_invokeMethod_success() {
        String expectedResult = "Good day to you! I'm Viscount.\nWhat can I do for you on this blessed day?";

        String actualResult = new Ui().getWelcomeMessage();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Get list response with empty modifier and empty date string")
    public void getListResponse_emptyModifierAndDate_success() {
        String expectedResult = "Here are the tasks in your list:\n"
                + "1.[T][" + CROSS_ICON + "] t1\n"
                + "2.[D][" + TICK_ICON + "] t2 (by: Aug 24 2020, 10:00)\n"
                + "3.[D][" + CROSS_ICON + "] t3 (by: Aug 27 2020, 11:00)\n"
                + "4.[E][" + TICK_ICON + "] t4 (at: Aug 24 2020, 11:00)\n"
                + "5.[E][" + CROSS_ICON + "] t5 (at: Aug 27 2020, 12:00)";

        String actualResult = new Ui().getListResponse(DEFAULT_TASKS, "", "");

        assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Get list response with specified date")
    public void getListResponse_specifiedDate_success() {
        String expectedResult = "Here are the tasks occurring on Aug 24 2020 in your list:\n"
                + "1.[D][" + TICK_ICON + "] t2 (by: Aug 24 2020, 10:00)\n"
                + "2.[E][" + TICK_ICON + "] t4 (at: Aug 24 2020, 11:00)";

        List<Task> filteredTasks = Arrays.asList(
                new Deadline("t2", true, LocalDateTime.of(2020, 8, 24, 10, 0)),
                new Event("t4", true, LocalDateTime.of(2020, 8, 24, 11, 0)));
        String actualResult = new Ui().getListResponse(filteredTasks, "", "Aug 24 2020");

        assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Get list response with today date")
    public void getListResponse_todayDate_success() {
        String expectedResult = "Here are the tasks occurring today in your list:\n"
                + "1.[D][" + CROSS_ICON + "] t3 (by: Aug 27 2020, 11:00)\n"
                + "2.[E][" + CROSS_ICON + "] t5 (at: Aug 27 2020, 12:00)";

        List<Task> filteredTasks = Arrays.asList(
                new Deadline("t3", false, LocalDateTime.of(2020, 8, 27, 11, 0)),
                new Event("t5", false, LocalDateTime.of(2020, 8, 27, 12, 0)));
        String actualResult = new Ui().getListResponse(filteredTasks, "", "today");

        assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Get list response with specified modifier")
    public void getListResponse_specifiedModifier_success() {
        String expectedResult = "Here are the deadlines in your list:\n"
                + "1.[D][" + TICK_ICON + "] t2 (by: Aug 24 2020, 10:00)\n"
                + "2.[D][" + CROSS_ICON + "] t3 (by: Aug 27 2020, 11:00)";

        List<Task> filteredTasks = Arrays.asList(
                new Deadline("t2", true, LocalDateTime.of(2020, 8, 24, 10, 0)),
                new Deadline("t3", false, LocalDateTime.of(2020, 8, 27, 11, 0)));
        String actualResult = new Ui().getListResponse(filteredTasks, "deadline", "");

        assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Get list response with specified modifier and date string")
    public void getListResponse_specifiedModifierAndDate_success() {
        String expectedResult = "Here are the deadlines occurring on Aug 24 2020 in your list:\n"
                + "1.[D][" + TICK_ICON + "] t2 (by: Aug 24 2020, 10:00)";

        List<Task> filteredTasks = Arrays.asList(
                new Deadline("t2", true, LocalDateTime.of(2020, 8, 24, 10, 0)));
        String actualResult = new Ui().getListResponse(filteredTasks, "deadline", "Aug 24 2020");

        assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Get add task response")
    public void getAddResponse_task_success() {
        String expectedResult = "Very well. I've added this todo:\n"
                + "[T][" + CROSS_ICON + "] t1\n"
                + "Now you have 1 tasks in the list.";

        String actualResult = new Ui().getAddResponse(new Todo("t1", false), 1);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Get done task response")
    public void getDoneResponse_task_success() {
        String expectedResult = "Very good! I have marked this todo as done:\n"
                + "[T][" + TICK_ICON + "] t1";

        String actualResult = new Ui().getDoneResponse(new Todo("t1", true));

        assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Get delete task response")
    public void getDeleteResponse_task_success() {
        String expectedResult = "Very well. I've removed this todo:\n"
                + "[T][" + TICK_ICON + "] t1\n"
                + "Now you have 0 tasks in the list.";

        String actualResult = new Ui().getDeleteResponse(new Todo("t1", true), 0);

        assertEquals(expectedResult, actualResult);
    }
}
