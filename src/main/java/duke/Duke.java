package duke;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;

import duke.utils.ResourceHandler;
import javafx.application.Application;

/**
 * Entrypoint for the Duke chatbot.
 */
public class Duke {
    /**
     * Starts the Duke chatbot with the specified command line parameters if applicable.
     *
     * @param args the command line parameters
     */
    public static void main(String[] args) {
        if (args.length > 0) {
            switch (args[0].toLowerCase()) {
            case "-h":
            case "--help":
                System.out.println(ResourceHandler.getString("duke.help"));
                break;
            case "-c":
            case "--console":
                launchCli();
                break;
            case "-g":
            case "--graphical":
                launchGui();
                break;
            default:
                System.out.println(MessageFormat.format(ResourceHandler.getString("duke.invalidCommand"), args[0]));
            }
        } else {
            launchGui();
        }
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
