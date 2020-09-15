package ekud;

import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

import ekud.commands.Command;
import ekud.exceptions.DukeException;
import ekud.exceptions.DukeIoException;
import ekud.utils.Parser;
import ekud.utils.ui.Cli;
import ekud.utils.ui.Ui;

/**
 * The main driver Class for the ekud.Duke Application.
 */
public class Duke {

    private static ResourceBundle strings;
    private static Ui ui;

    /**
     * Instantiates a new ekud.Duke.
     */
    public Duke() {
        try {
            initializeDuke();
        } catch (DukeIoException e) {
            ui.print(e.getMessage());
        }
    }

    private static void initializeDuke() throws DukeIoException {
        // read the appropriate string resources
        strings = ResourceBundle.getBundle("StringsBundle", Locale.ENGLISH);
        Command.setupCommands();
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        ui = new Cli();

        try {
            initializeDuke();
        } catch (DukeIoException e) {
            ui.print(e.getMessage());
        }
        ui.print(strings.getString("output.greeting"));

        String input;
        String mainCommand = "";
        String output;
        HashMap<String, String> parameters;

        while (!mainCommand.equals(strings.getString("command.bye"))) {
            input = ui.read();
            mainCommand = Parser.parseMainCommand(input);
            parameters = Parser.parseParameters(input);

            try {
                output = Command.createCommand(mainCommand)
                        .execute(parameters);
                ui.print(output);
            } catch (DukeException e) {
                ui.print(e.getMessage());
            }

        }
    }

    public String getResponse(String input) {
        String mainCommand = Parser.parseMainCommand(input);
        HashMap<String, String> parameters = Parser.parseParameters(input);

        try {
            return Command.createCommand(mainCommand)
                    .execute(parameters);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
