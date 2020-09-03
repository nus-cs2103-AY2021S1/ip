import duke.Ui;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    Ui ui = new Ui();

    @Test
    public void testGetServantSpeak() {
        assertEquals("Duke:\n", ui.getServantSpeak());
    }

    @Test
    public void testGetUserPrompt() {
        assertEquals("Your Command Sire:", ui.getUserPrompt());
    }

    @Test
    public void testWelcomeMessage() {
        // Setup of print stream
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        PrintStream standardOut = System.out;

        System.setOut(new PrintStream(outputStreamCaptor));

        String expectedOutput = "Duke:\n"
                + "    Greetings my Liege.\n"
                + "    Why have you summoned me?\n\n"
                + "    You may type \"help\" for a list"
                + " of available commands.\n\n";

        ui.welcomeMessage();
        assertEquals(expectedOutput, outputStreamCaptor.toString());

        // Teardown
        System.setOut(standardOut);
    }
}
