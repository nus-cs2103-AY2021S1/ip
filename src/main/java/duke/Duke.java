package duke;

import java.util.List;
import java.util.Scanner;

import duke.commands.CommandExecution;
import duke.commands.EnumCommand;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * A class that represents the Duke application which contains the main method in the class.
 */
public class Duke {
    private static final String filePath = "duke.txt";
    private Storage storage;
    private TaskList results;
    private Ui ui;

    /**
     * Sets up the user interface and load list from file storage.
     *
     * @param filePath the path of the file storage of list of tasks.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            results = new TaskList();
            List<Task> taskList = storage.readFromFile();
            taskList.stream().forEach(task -> results.add(task));
        } catch (DukeException e) {
            ui.showError(e);
            results = new TaskList();
        }
    }

    /**
     * Constructs a Duke object without taking in arguments.
     */
    public Duke() {

    }

    /**
     * The main method of the application.
     *
     * @param args the arguments of the main method.
     */
    public static void main(String[] args) {
        new Duke(filePath).run();
    }


    /**
     * Scans the user input and responds to command inputs.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        ui.greetings();
        while (sc.hasNextLine()) {
            String instruction = sc.nextLine();
            try {
                EnumCommand enumCommand = Parser.parseCommand(instruction);
                String commandResult = CommandExecution.executeCommand(enumCommand, instruction, results);
                storage.storeToFile(results);
            } catch (Exception e) {
                ui.showError(e);
            }
        }
    }


    /**
     * Returns a string representing the response by the program after user input.
     *
     * @param input a string of input by the user.
     * @return a string representing the response by the program after user input.
     */
    public String getResponse(String input) {
        try {
            EnumCommand enumCommand = Parser.parseCommand(input);
            String commandResult = CommandExecution.executeCommand(enumCommand, input, results);
            storage.storeToFile(results);
            return commandResult;
        } catch (Exception e) {
            return ui.showError(e);
        }
    }

}
