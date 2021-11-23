package manager;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

public class ParserTest {

    @Test
    public void testHandleUserInputWithScanner() {

        Parser parser = new Parser();

        String input =
                "todo disarm neutrino bomb \n"
                        + "done 1\n"
                        + "event go on a fun adventure /at now \n"
                        + "delete 1\n"
                        + "deadline try inception /by tomorrow 7am\n"
                        + "list\n"
                        + "hello cosmos!\n"
                        + "delete all\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        parser.handleUserInput();

        assertEquals("Oohhh, your task list is empty. Let's add some tasks!\r\n"
                        + "Ooooohh yeahhhh cannnnn do, I'm Mr. Meeseeks! I've added this task:\r\n"
                        + "[T][✘] disarm neutrino bomb\r\n"
                        + "Now you have 1 tasks in the list.\r\n"
                        + "\r\n"
                        + "Oooh yeahhh, good job! I've marked this task as done:\r\n"
                        + "[T][✓] disarm neutrino bomb\r\n"
                        + "\r\n"
                        + "Ooooohh yeahhhh cannnnn do, I'm Mr. Meeseeks! I've added this task:\r\n"
                        + "[E][✘] go on a fun adventure (at: now)\r\n"
                        + "Now you have 2 tasks in the list.\r\n"
                        + "\r\n"
                        + "I'm Mr. Meeseeks, look at me! I've removed this task:\r\n"
                        + "[T][✓] disarm neutrino bomb\r\n"
                        + "Now you have 1 tasks in the list.\r\n"
                        + "\r\n"
                        + "Ooooohh yeahhhh cannnnn do, I'm Mr. Meeseeks! I've added this task:\r\n"
                        + "[D][✘] try inception (by: tomorrow 7am)\r\n"
                        + "Now you have 2 tasks in the list.\r\n"
                        + "\r\n"
                        + "Yes sireee, look at me! Here are the tasks in your list:\r\n"
                        + "1.[E][✘] go on a fun adventure (at: now)\r\n"
                        + "2.[D][✘] try inception (by: tomorrow 7am)\r\n"
                        + "\r\n"
                        + "Oohh, I have to fulfill my purpose so I can go away! "
                        + "Please ensure your "
                        + "command format is correct and try again.\r\n"
                        + "\r\n"
                        + "All done! You have 0 tasks now.\r\n"
                        + "\r\n",
                output.toString());
    }

    @Test
    public void testHandleUserInput() {

        Parser parser = new Parser();

        String input = "todo get schwifty";
        assertEquals("Ooooohh yeahhhh cannnnn do, I'm Mr. Meeseeks! I've added this task:\r\n"
                + "[T][✘] get schwifty\r\n"
                + "Now you have 1 tasks in the list.\r\n", parser.handleUserInput(input));

        input = "deadline get out of simulation /by 2020-12-20 1600";
        assertEquals("Ooooohh yeahhhh cannnnn do, I'm Mr. Meeseeks! I've added this task:\r\n"
                + "[D][✘] get out of simulation (by: Dec 20 2020, 04:00 PM)\r\n"
                + "Now you have 2 tasks in the list.\r\n", parser.handleUserInput(input));

        input = "delete 8";
        assertEquals("Oohhh, the number entered is invalid, but don't worry! "
                + "You have 2 tasks in your list.\r\n", parser.handleUserInput(input));

        input = "event run in a stream /at 2020-10-23";
        assertEquals("Ooooohh yeahhhh cannnnn do, I'm Mr. Meeseeks! I've added this task:\r\n"
                + "[E][✘] run in a stream (at: Oct 23 2020)\r\n"
                + "Now you have 3 tasks in the list.\r\n", parser.handleUserInput(input));

        input = "done 2";
        assertEquals("Oooh yeahhh, good job! I've marked this task as done:\r\n"
                + "[D][✓] get out of simulation (by: Dec 20 2020, 04:00 PM)\r\n", parser.handleUserInput(input));

        input = "list";
        assertEquals("Yes sireee, look at me! Here are the tasks in your list:\r\n"
                + "1.[T][✘] get schwifty\r\n"
                + "2.[D][✓] get out of simulation (by: Dec 20 2020, 04:00 PM)\r\n"
                + "3.[E][✘] run in a stream (at: Oct 23 2020)\r\n", parser.handleUserInput(input));

        input = "done 10";
        assertEquals("Oohhh, the number entered is invalid, but don't worry! "
                + "You have 3 tasks in your list.\r\n", parser.handleUserInput(input));

        input = "delete 3";
        assertEquals("I'm Mr. Meeseeks, look at me! I've removed this task:\r\n"
                + "[E][✘] run in a stream (at: Oct 23 2020)\r\n"
                + "Now you have 2 tasks in the list.\r\n", parser.handleUserInput(input));

        input = "wubbalubbadubdub!";
        assertEquals("Oohh, I have to fulfill my purpose so I can go away! "
                + "Please ensure your command format is correct and try again.\r\n",
                parser.handleUserInput(input));

        input = "find get";
        assertEquals("Oohhh look at me! Here are the matching tasks in your list:\r\n"
                + "1.[T][✘] get schwifty\r\n"
                + "2.[D][✓] get out of simulation (by: Dec 20 2020, 04:00 PM)\r\n",
                parser.handleUserInput(input));

        input = "delete all";
        assertEquals("All done! You have 0 tasks now.\r\n", parser.handleUserInput(input));
    }
}
