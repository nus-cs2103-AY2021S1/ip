package duke;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import javafx.application.Application;

/**
 * Entrypoint for the Duke chatbot.
 */
public class Duke {
    /**
     * Starts the Duke chatbot.
     *
     * @param args The command line parameters (unused)
     */
    public static void main(String[] args) {
        launchGui();
        // TODO: Add option to launch Duke in CLI mode.
    }

    /**
     * Launches the command line interface version of the Duke chatbot.
     */
    private static void launchCli() {
        try {
            // Set the encoding of `System.out` to UTF-8.
            System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out), true, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            System.err.println(e.getMessage());
        }
        Repl.run();
    }

    /**
     * Launches the graphical user interface version of the Duke chatbot.
     */
    private static void launchGui() {
        Application.launch(Gui.class);
    }
}
