package duke.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UiTest {
    private static final String LINE = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    private static final String HELLO_DUKE = "      " + "Hello! I'm Duke ^.^";
    private static final String QUESTION = "What can I do for you?";
    private static final String GOODBYE_MSG = "Bye ^.^, Hope to see you again soon!!!";
    private Ui ui;
    @BeforeEach
    void init() {
        ui = new Ui();
    }

    @Test
    @DisplayName("formatter for input message")
    public void testMessageFormatter() {
        String messageFormatterMessage = ui.messageFormatter("hello world");
        String str = "hello world\n";
        assertEquals(str, messageFormatterMessage);
    }
    @Test
    @DisplayName("user's login greetings")
    public void testGreetings() {
        String greetingMessage = ui.greetings();
        String str = HELLO_DUKE + "\n" + QUESTION + "\n";
        assertEquals(str, greetingMessage);
    }
    @Test
    @DisplayName("user's logout message")
    public void testGoodBye() {
        String goodbyeMessage = ui.goodBye();
        String str = GOODBYE_MSG + "\n";
        assertEquals(str, goodbyeMessage);
    }
    @Test
    @DisplayName("error message")
    public void testPrintException() {
        String errorMessage = ui.printException("ERROR");
        String str = "ERROR\n";
        assertEquals(str, errorMessage);
    }
}
