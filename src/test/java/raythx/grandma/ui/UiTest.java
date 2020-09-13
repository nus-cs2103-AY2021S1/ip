package raythx.grandma.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import raythx.grandma.exception.InvalidIndexException;

public class UiTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    private Ui ui;

    //@@author raythx98-reused
    //Reused from https://www.baeldung.com/java-testing-system-out-println with minor modifications
    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        ui = new Ui();
        ui.appendMessage("This is a test string.\n");
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
    //@@author

    /**
     * Something.
     */
    @Test
    public void resetTextOnScreen_testString_resetCorrectly() {
        ui.resetTextOnScreen();
        String expectedResult = "";
        String actualResult = ui.getTextOnScreen();
        assertEquals(expectedResult, actualResult);
    }

    /**
     * Something.
     */
    @Test
    public void appendHelpMessage_testString_appendCorrectly() {
        ui.appendHelpMessage();
        String expectedResult = "This is a test string.\n"
                + "These r wud u tell your ol' grandma here...\nYu wan add more tasks\n"
                + "        todo {description} #{tag} \n                /by {DDMMYY HHmm}\n"
                + "        deadline {description} #{tag} \n                /by {DDMMYY HHmm}\n"
                + "        event {description} #{tag} \n                /at {DDMMYY HHmm}\n"
                + "Yu wan remove tasks coz incompetent\n        delete {task number}\n"
                + "Yu wan mark task as completed\n        done {task number}\n"
                + "Yu wan see ur tasks coz u forget\n        list\n"
                + "Yu wan find ur tasks coz u noob\n        find {keyword}\n"
                + "Yu wanna gtfo\n        bye";
        String actualResult = ui.getTextOnScreen();
        assertEquals(expectedResult, actualResult);
    }

    /**
     * Something.
     */
    @Test
    public void getError_invalidIndexException_messagePrinted() {
        String actualResult = ui.getError(new InvalidIndexException());
        String expectedResult = new InvalidIndexException().getMessage();
        assertEquals(expectedResult, actualResult);
    }
}
