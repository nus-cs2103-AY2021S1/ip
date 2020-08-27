import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeUiTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    Ui ui = new Ui();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void userCommandTest() {
        String help = "Here are the commands you can use:\n"
                + "1. help\n"
                + "2. add\n"
                + "3. list\n"
                + "4. done\n"
                + "5. delete\n"
                + "6. date\n"
                + "7. bye\n";
        ui.printHelp();
        assertEquals(help.trim(), outputStreamCaptor.toString().trim().replace("\r", ""));
    }

    @BeforeEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}
