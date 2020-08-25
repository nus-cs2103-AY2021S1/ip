import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UITest {

    private final ByteArrayOutputStream OUT_CONTENT = new ByteArrayOutputStream();
    private final PrintStream ORIGINAL_OUT = System.out;
    private final InputStream ORIGINAL_IN = System.in;

    private final String LINE = " ____________________________________________________________";

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(OUT_CONTENT));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(ORIGINAL_OUT);
        System.setIn(ORIGINAL_IN);
    }

    @Test
    public void testShowGreeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        UI.printGreeting();
        String expected = "Hello from\n" +
                logo +
                "\n" +
                LINE +
                "\nHello! I'm Duke\nWhat can I do for you?\n" +
                LINE +
                "\n";
        assertEquals(expected, OUT_CONTENT.toString());
    }

    @Test
    public void testPrintTaskAdd(){
        UI.printTaskAdd(new ToDo("Read Book"), 1);
        Task toDo = new ToDo("Read Book");
        String expected = LINE +
                "\n" +
                "Got it. I've added this task:" +
                "\n" +
                toDo.toString() +
                "\n" +
                "Now you have " +
                String.valueOf(1) +
                " tasks in the list.\n" +
                LINE +
                "\n";
        assertEquals(expected, OUT_CONTENT.toString());
    }

    @Test
    public void testPrintDeleteMessage(){
        UI.printDeleteMessage(new ToDo("Read Book"), 1);
        Task toDo = new ToDo("Read Book");
        String expected = LINE +
                "\n" +
                "Noted. I've removed this task:" +
                "\n" +
                toDo.toString() +
                "\n" +
                "Now you have " +
                String.valueOf(1) +
                " tasks in the list.\n" +
                LINE +
                "\n";
        assertEquals(expected, OUT_CONTENT.toString());
    }


    @Test
    public void testByeMessage(){
        UI.printByeMessage();
        String expected = LINE +
                "\n" +
                "Bye. Hope to see you again soon!" +
                "\n" +
                LINE +
                "\n";
        assertEquals(expected, OUT_CONTENT.toString());
    }




}