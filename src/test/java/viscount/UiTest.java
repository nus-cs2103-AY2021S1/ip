package viscount;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import java.nio.charset.StandardCharsets;
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

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
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
    private final InputStream standardIn = System.in;
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
        new Ui().showWelcome();
        String welcomeMessage = "Good day to you! I'm Viscount.\nWhat can I do for you on this blessed day?";
        
        String expectedResult = VISCOUNT_LOGO + System.lineSeparator() + putInChatBox(welcomeMessage);

        assertEquals(expectedResult, outputStreamCaptor.toString());
    }
    
    @Test
    @DisplayName("Show exit message")
    public void showExit_inputCommand_success() {
        new Ui().showExit();
        String exitMessage = "Farewell my friend, I hope to see you again!";

        String expectedResult = putInChatBox(exitMessage);

        assertEquals(expectedResult, outputStreamCaptor.toString());
    }

    @ParameterizedTest
    @ValueSource(strings = {"list", "todo t1", "deadline t2 /by 24-08-2020 1000", " "})
    @DisplayName("Read input message")
    public void readInput_inputCommand_success(String command) {
        ByteArrayInputStream inputStreamCaptor = new ByteArrayInputStream(command.getBytes());

        String expectedResult = command;
        String actualResult = new Ui(inputStreamCaptor).readInput();

        assertEquals(expectedResult, actualResult);
    }

    private static String putInChatBox(String message) {
        return HORIZONTAL_LINE + System.lineSeparator() +
                message + System.lineSeparator() +
                HORIZONTAL_LINE + System.lineSeparator() +
                System.lineSeparator();
    }
}
