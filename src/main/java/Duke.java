import commands.Command;
import exceptions.*;
import utils.Parser;
import utils.Storage;
import tasks.*;
import utils.UI;

import java.util.*;

public class Duke {

    private static ResourceBundle strings;
    private static UI ui;
    private static TaskList tasks;
    private static Storage storage;

    private static void initializeDuke() throws DukeIOException {
        // read the appropriate string resources
        strings = ResourceBundle.getBundle("resources.StringsBundle", Locale.ENGLISH);
        ui = new UI();
        storage = Storage.createStorage("database", "tasks.ser");
        tasks = new TaskList(storage.load());
        tasks.setObserver(storage);
    }

    public static void main(String[] args) {

        try {
            initializeDuke();
        } catch (DukeIOException e) {
            ui.print(e.getMessage());
        }
        ui.print(strings.getString("output.greeting"));

        String input, mainCommand = "", output;
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
