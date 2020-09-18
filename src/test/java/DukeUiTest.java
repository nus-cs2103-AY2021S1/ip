import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.Ui;


public class DukeUiTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private Ui ui = new Ui(new Scanner(System.in));

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void userCommandTest() {
        String help = "Here are the commands you can use:\n"
                + "1. Help\n"
                + "2. List\n"
                + "3. Add\n"
                + "4. Done\n"
                + "5. Delete\n"
                + "6. Find\n"
                + "7. Priority\n"
                + "8. Bye\n";
        ui.printHelp();
        assertEquals(help.trim(), outputStreamCaptor.toString().trim().replace("\r", ""));
    }

    @BeforeEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}
