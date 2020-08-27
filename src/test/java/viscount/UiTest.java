package viscount;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import viscount.task.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    private static final String VISCOUNT_LOGO = "        _  _____  _____                  _    \n" +
            "       (_)/ ____|/ ____|                | |   \n" +
            " __   ___| (___ | |     ___  _   _ _ __ | |_  \n" +
            " \\ \\ / / |\\___ \\| |    / _ \\| | | | '_ \\| __| \n" +
            "  \\ V /| |____) | |___| (_) | |_| | | | | |_  \n" +
            "   \\_/ |_|_____/ \\_____\\___/ \\__,_|_| |_|\\__|";
    private static final String HORIZONTAL_LINE = "__________________________________________________";
    private static final String TICK_ICON = "\u2713";
    private static final String CROSS_ICON = "\u2718";
    
    private static final List<Task> DEFAULT_TASKS = Arrays.asList(
            new Todo("t1", false),
            new Deadline("t2", true, LocalDateTime.of(2020, 8, 24, 10, 0)),
            new Deadline("t3", false, LocalDateTime.of(2020, 8, 27, 11, 0)),
            new Event("t4", true, LocalDateTime.of(2020, 8, 24, 11, 0)),
            new Event("t5", false, LocalDateTime.of(2020, 8, 27, 12, 0)));

    //@@author sc-arecrow-reused
    //Reused from https://www.baeldung.com/java-testing-system-out-println with minor modifications
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    
    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }
    
    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
    //@@author
    
    @Test
    @DisplayName("Show welcome message")
    public void showWelcome_invokeMethod_success() {
        String welcomeMessage = "Good day to you! I'm Viscount.\nWhat can I do for you on this blessed day?";
        String expectedResult = VISCOUNT_LOGO + System.lineSeparator() + putInChatBox(welcomeMessage);
        
        new Ui().showWelcome();
        String actualResult = outputStreamCaptor.toString();

        assertEquals(expectedResult, actualResult);
    }
    
    @Test
    @DisplayName("Show exit message")
    public void showExit_inputCommand_success() {
        String exitMessage = "Farewell my friend, I hope to see you again!";
        String expectedResult = putInChatBox(exitMessage);
        
        new Ui().showExit();
        String actualResult = outputStreamCaptor.toString();
        
        assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Show list with empty modifier and empty date string")
    public void showList_emptyModifierAndDate_success() {
        String listMessage = "Here are the tasks in your list:\n"
                + "1.[T][" + CROSS_ICON + "] t1\n"
                + "2.[D][" + TICK_ICON + "] t2 (by: Aug 24 2020, 10:00)\n"
                + "3.[D][" + CROSS_ICON + "] t3 (by: Aug 27 2020, 11:00)\n"
                + "4.[E][" + TICK_ICON + "] t4 (at: Aug 24 2020, 11:00)\n"
                + "5.[E][" + CROSS_ICON + "] t5 (at: Aug 27 2020, 12:00)";
        String expectedResult = putInChatBox(listMessage).replaceAll("\\p{Cntrl}", "");
        
        new Ui().showList(DEFAULT_TASKS, "", "");
        String actualResult = outputStreamCaptor.toString().replaceAll("\\p{Cntrl}", "");

        assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Show list with specified date")
    public void showList_specifiedDate_success() {
        String listMessage = "Here are the tasks occurring on Aug 24 2020 in your list:\n"
                + "1.[D][" + TICK_ICON + "] t2 (by: Aug 24 2020, 10:00)\n"
                + "2.[E][" + TICK_ICON + "] t4 (at: Aug 24 2020, 11:00)";
        String expectedResult = putInChatBox(listMessage).replaceAll("\\p{Cntrl}", "");

        List<Task> filteredTasks = Arrays.asList(
                new Deadline("t2", true, LocalDateTime.of(2020, 8, 24, 10, 0)),
                new Event("t4", true, LocalDateTime.of(2020, 8, 24, 11, 0)));

        new Ui().showList(filteredTasks, "", "Aug 24 2020");
        String actualResult = outputStreamCaptor.toString().replaceAll("\\p{Cntrl}", "");

        assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Show list with today date")
    public void showList_todayDate_success() {
        String listMessage = "Here are the tasks occurring today in your list:\n"
                + "1.[D][" + CROSS_ICON + "] t3 (by: Aug 27 2020, 11:00)\n"
                + "2.[E][" + CROSS_ICON + "] t5 (at: Aug 27 2020, 12:00)";
        String expectedResult = putInChatBox(listMessage).replaceAll("\\p{Cntrl}", "");

        List<Task> filteredTasks = Arrays.asList(
                new Deadline("t3", false, LocalDateTime.of(2020, 8, 27, 11, 0)),
                new Event("t5", false, LocalDateTime.of(2020, 8, 27, 12, 0)));

        new Ui().showList(filteredTasks, "", "today");
        String actualResult = outputStreamCaptor.toString().replaceAll("\\p{Cntrl}", "");

        assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Show list with specified modifier")
    public void showList_specifiedModifier_success() {
        String listMessage = "Here are the deadlines in your list:\n"
                + "1.[D][" + TICK_ICON + "] t2 (by: Aug 24 2020, 10:00)\n"
                + "2.[D][" + CROSS_ICON + "] t3 (by: Aug 27 2020, 11:00)";
        String expectedResult = putInChatBox(listMessage).replaceAll("\\p{Cntrl}", "");

        List<Task> filteredTasks = Arrays.asList(
                new Deadline("t2", true, LocalDateTime.of(2020, 8, 24, 10, 0)),
                new Deadline("t3", false, LocalDateTime.of(2020, 8, 27, 11, 0)));

        new Ui().showList(filteredTasks, "deadline", "");
        String actualResult = outputStreamCaptor.toString().replaceAll("\\p{Cntrl}", "");

        assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Show list with specified modifier and date string")
    public void showList_specifiedModifierAndDate_success() {
        String listMessage = "Here are the deadlines occurring on Aug 24 2020 in your list:\n"
                + "1.[D][" + TICK_ICON + "] t2 (by: Aug 24 2020, 10:00)";
        String expectedResult = putInChatBox(listMessage).replaceAll("\\p{Cntrl}", "");

        List<Task> filteredTasks = Arrays.asList(
                new Deadline("t2", true, LocalDateTime.of(2020, 8, 24, 10, 0)));

        new Ui().showList(filteredTasks, "deadline", "Aug 24 2020");
        String actualResult = outputStreamCaptor.toString().replaceAll("\\p{Cntrl}", "");

        assertEquals(expectedResult, actualResult);
    }
    
    @Test
    @DisplayName("Show add task message")
    public void showAdd_task_success() {
        String addMessage = "Very well. I've added this todo:\n"
                + "[T][" + CROSS_ICON + "] t1\n"
                + "Now you have 1 tasks in the list.";
        String expectedResult = putInChatBox(addMessage).replaceAll("\\p{Cntrl}", "");
        
        new Ui().showAdd(new Todo("t1", false), 1);
        String actualResult = outputStreamCaptor.toString().replaceAll("\\p{Cntrl}", "");

        assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Show done task message")
    public void showDone_task_success() {
        String doneMessage = "Very good! I have marked this todo as done:\n"
                + "[T][" + TICK_ICON + "] t1\n";
        String expectedResult = putInChatBox(doneMessage).replaceAll("\\p{Cntrl}", "");

        new Ui().showDone(new Todo("t1", true));
        String actualResult = outputStreamCaptor.toString().replaceAll("\\p{Cntrl}", "");

        assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Show delete task message")
    public void showDelete_task_success() {
        String deleteMessage = "Very well. I've removed this todo:\n"
                + "[T][" + TICK_ICON + "] t1\n"
                + "Now you have 0 tasks in the list.";
        String expectedResult = putInChatBox(deleteMessage).replaceAll("\\p{Cntrl}", "");

        new Ui().showDelete(new Todo("t1", true), 0);
        String actualResult = outputStreamCaptor.toString().replaceAll("\\p{Cntrl}", "");

        assertEquals(expectedResult, actualResult);
    }

    @ParameterizedTest
    @ValueSource(strings = {"list", "todo t1", "deadline t2 /by 24-08-2020 1000", " "})
    @DisplayName("Read input message")
    public void readInput_inputCommand_success(String command) {

        ByteArrayInputStream inputStreamCaptor = new ByteArrayInputStream(command.getBytes());
        String actualResult = new Ui(inputStreamCaptor).readInput();

        assertEquals(command, actualResult);
    }

    private static String putInChatBox(String message) {
        return HORIZONTAL_LINE + System.lineSeparator() +
                message + System.lineSeparator() +
                HORIZONTAL_LINE + System.lineSeparator() +
                System.lineSeparator();
    }
}
