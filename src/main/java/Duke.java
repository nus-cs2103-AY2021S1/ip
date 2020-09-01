import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

import commands.Command;
import exceptions.DukeException;
import exceptions.DukeIoException;
import tasks.TaskList;
import utils.Parser;
import utils.Storage;
import utils.UI;

/**
 * The main driver Class for the Duke Application.
 */
public class Duke {

    private static ResourceBundle strings;
    private static UI ui;
    private static TaskList tasks;
    private static Storage storage;

    private static void initializeDuke() throws DukeIoException {
        // read the appropriate string resources
        strings = ResourceBundle.getBundle("StringsBundle", Locale.ENGLISH);
        ui = new UI();
        storage = Storage.createStorage("database", "tasks.ser");
        tasks = new TaskList(storage.load());
        tasks.setObserver(storage);
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

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
                        .execute(parameters, tasks);
                ui.print(output);
            } catch (DukeException e) {
                ui.print(e.getMessage());
            }

        }
    }

}
