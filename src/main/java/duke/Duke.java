package duke;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

/**
 * Entrypoint for the Duke chatbot.
 */
public class Duke {
    /**
     * Starts the REPL environment.
     *
     * @param args The command line parameters (unused)
     */
    public static void main(String[] args) {
        try {
            // Set the encoding of `System.out` to UTF-8.
            System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out), true, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            System.err.println(e.getMessage());
        }
        Repl.run();
    }
}
