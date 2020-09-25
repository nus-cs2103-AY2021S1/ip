package duke.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import duke.ui.textui.Ui;

public class UiTest {

    private static final String HELLO_DUKE = "Welcome back";
    private static final String GOODBYE_MSG = "Bye ^.^, Hope to see you again soon!!!";
    private Ui ui;

    @BeforeEach
    void init() {
        ui = new Ui();
    }

    @Test
    @DisplayName("formatter for input message")
    public void messageFormatter_formatTheInputMessage_success() {
        String messageFormatterMessage = ui.messageFormatter("hello world");
        String str = "hello world\n";
        assertEquals(str, messageFormatterMessage);
    }

    @Test
    @DisplayName("user's login greetings")
    public void greetings_checkGreetingFromDuke_success() {
        String greetingMessage = ui.greetings();
        String str = HELLO_DUKE + "\n";
        assertEquals(str, greetingMessage);
    }

    @Test
    @DisplayName("user's logout message")
    public void goodBye_checkGoodByeMessageFromDuke_success() {
        String goodbyeMessage = ui.goodBye();
        String str = GOODBYE_MSG + "\n";
        assertEquals(str, goodbyeMessage);
    }

    @Test
    @DisplayName("error message")
    public void printException_checkExceptionMessagePrinting_success() {
        String errorMessage = ui.printException("ERROR");
        String str = "ERROR\n";
        assertEquals(str, errorMessage);
    }
}
