package duke;

import duke.command.Command;
import duke.command.ExitCommand;

import java.util.Scanner;

/**
 * Main class of duke
 */
public class Duke {

    /** Pathname of the local main data file */
    final String MAIN_DATA_PATH_NAME = "./data/duke_data.csv";

    /** Pathname of the local archive data file */
    final String ARCHIVE_DATA_PATH_NAME = "./data/duke_archive.csv";

    /** Storage */
    Storage storage;

    /** Task list*/
    TaskList tasks;

    /** Archive task list */
    TaskList archive;

    /** User Interface using command line */
    Ui ui;

    /**
     * Constructor
     */
    Duke() {
        storage = new Storage(MAIN_DATA_PATH_NAME, ARCHIVE_DATA_PATH_NAME);
        tasks = storage.loadTasksFromMainData();
        archive = storage.loadTasksFromArchiveData();
        ui = new Ui();
    }

    /**
     * Main method of Duke.
     *
     * @param args String arguments (not necessary)
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.runDukeOnCli();
    }

    /**
     * Runs Duke on the command line.
     */
    public void runDukeOnCli() {
        ui.printHello();
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            Command command = Parser.parse(input);
            String[] outputStrings = command.execute(storage, tasks, archive, ui);
            ui.print(outputStrings);

            if (command instanceof ExitCommand) {
                break;
            }
        }
    }

    /**
     * Gets the startup message as a string.
     *
     * @return Startup message as a string.
     */
    String getStartupMessage() {
        String[] strings = ui.getHelloStrings();

        if (strings.length == 0) {
            return "Hello!";
        }

        StringBuilder output = new StringBuilder(strings[0]);
        for (int i = 1; i < strings.length; i++) {
            output.append("\n");
            output.append(strings[i]);
        }
        return output.toString();
    }

    /**
     * Retrieves the response from an input as a String.
     *
     * @return Response
     */
    String getResponse(String input) {
        Command command = Parser.parse(input);
        String[] strings = command.execute(storage, tasks, archive, ui);
        StringBuilder output;

        if (strings.length >= 1) {
            output = new StringBuilder(strings[0]);
            for (int i = 1; i < strings.length; i++) {
                output.append("\n");
                output.append(strings[i]);
            }
            return output.toString();
        } else {
            return "No response";
        }
    }
}
