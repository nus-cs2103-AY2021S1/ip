package manager;

import main.java.manager.Parser;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void testHandleUserInput() {

        Parser parser = new Parser();

        String input =
                "todo disarm neutrino bomb \n" +
                "done 1\n" +
                "event go on a fun adventure /at now \n" +
                "delete 1\n" +
                "deadline try inception /by tomorrow 7am\n" +
                "list\n" +
                "hello cosmos!\n" + // gibberish
                "delete all\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        parser.handleUserInput();

        assertEquals("Here are your saved tasks:\r\n" +
                        "Got it. I've added this task:\r\n" +
                        "[T][✘] disarm neutrino bomb\r\n" +
                        "Now you have 1 tasks in the list.\r\n" +
                        "Nice! I've marked this task as done:\r\n" +
                        "[T][✓] disarm neutrino bomb\r\n" +
                        "Got it. I've added this task:\r\n" +
                        "[E][✘] go on a fun adventure (at: now)\r\n" +
                        "Now you have 2 tasks in the list.\r\n" +
                        "Noted. I've removed this task:\r\n" +
                        "[T][✓] disarm neutrino bomb\r\n" +
                        "Now you have 1 tasks in the list.\r\n" +
                        "Got it. I've added this task:\r\n" +
                        "[D][✘] try inception (by: tomorrow 7am)\r\n" +
                        "Now you have 2 tasks in the list.\r\n" +
                        "Here are the tasks in your list:\r\n" +
                        "1.[E][✘] go on a fun adventure (at: now)\r\n" +
                        "2.[D][✘] try inception (by: tomorrow 7am)\r\n" +
                        "Not sure what you mean. " +
                        "Please ensure your command format is correct and try again.\r\n",
                output.toString());
    }
}
