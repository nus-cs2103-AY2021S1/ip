package duke.ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.PrintStream;
import java.io.ByteArrayOutputStream;


public class UiTest {
    
    private static final String LINE = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    private static final String SPACER = "               ";
    private static final String LOGO = SPACER + " ____        _        \n"
            + SPACER + "|  _ \\ _   _| | _____ \n"
            + SPACER + "| | | | | | | |/ / _ \\\n"
            + SPACER + "| |_| | |_| |   <  __/\n"
            + SPACER + "|____/ \\__,_|_|\\_\\___|\n";
    //@@author Jonathan Cook
    // Reused from https://www.baeldung.com/java-testing-system-out-println
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    
    Ui ui;
    @BeforeEach
    void init() {
        ui = new Ui();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    @DisplayName("formatter for input message")
    public void testMessageFormatter() {
        ui.messageFormatter(() -> System.out.println("hello world"));
        String str = LINE + "\n" + "hello world\n" + LINE;
        assertEquals(str, outputStreamCaptor.toString().trim());
    }
    
    @Test
    @DisplayName("user's login greetings")
    public void testGreetings() {
        ui.greetings();
        String str = LINE + "\n" + " *** Opening and loading relevant documents into duke.Duke ***\n" + LOGO + "\n" 
                + LINE+ "\n"+ "\n" + LINE + "\n" + "Hello! I'm duke.Duke ^.^\n" + "What can I do for you?\n" + LINE;
        assertEquals(str, outputStreamCaptor.toString().trim());
    }
    @Test
    @DisplayName("user's logout message")
    public void testGoodBye() {
        ui.goodBye();
        String str = LINE + "\n" + "Bye ^.^, Hope to see you again soon!!!\n" + LINE;
        assertEquals(str, outputStreamCaptor.toString().trim());
    }
    
    @Test
    @DisplayName("error message")
    public void testPrintException() {
        ui.printException("ERROR");
        String str = LINE + "\n" + "ERROR\n" + LINE;
        assertEquals(str, outputStreamCaptor.toString().trim());
    }
}
