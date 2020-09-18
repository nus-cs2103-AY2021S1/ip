package duke;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.Locale;

import duke.utils.Store;
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
        // Set the locale on startup.
        String language = Store.getConfigManager().getProperty("language");
        String country = Store.getConfigManager().getProperty("country");
        Locale locale = new Locale(language, country);
        Store.getResourceHandler().setLocale(locale);

        // Parse command line parameters
        if (args.length > 0) {
            switch (args[0].toLowerCase()) {
            case "-h":
            case "--help":
                System.out.println(Store.getResourceHandler().getString("duke.help"));
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
                System.out.println(MessageFormat.format(
                        Store.getResourceHandler().getString("duke.invalidCommand"), args[0]));
            }
        } else {
            launchGui();
        }
    }

    /**
     * Launches the command line interface version of the Duke chatbot.
     */
    private static void launchCli() {
        // Set the encoding of `System.out` to UTF-8.
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out), true, StandardCharsets.UTF_8));
        Repl.run();
    }

    /**
     * Launches the graphical user interface version of the Duke chatbot.
     */
    private static void launchGui() {
        Application.launch(Gui.class);
    }
}
