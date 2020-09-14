package duke;

import duke.logic.Parser;
import duke.util.Storage;
import duke.util.TaskList;

import java.util.Scanner;

/**
 * Represents a personal assistant chatbot to help a person keep track of various things.
 */
public class Duke {

    /** UI for Duke to display messages. */
    private final Ui ui;

    /** Parser for Duke to parse inputs. */
    private final Parser parser;

    /**
     * Creates an instance of Duke.
     *
     * @param hasGui If there is a GUI or not.
     */
    public Duke(boolean hasGui) {
        TaskList taskList = new TaskList();
        this.ui = new Ui(hasGui);
        Storage storage;
        if (hasGui) {
            String currDir = System.getProperty("user.dir");
            storage = new Storage(currDir + "/data", currDir + "/data/duke.txt", this.ui, taskList);
        } else {
            storage = new Storage("../data", "../data/duke.txt", this.ui, taskList);
        }
        storage.checkSavedFile();
        this.parser = new Parser(storage);
    }

    /**
     * Main entry point for a CLI-based Duke.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Duke duke = new Duke(false);
        duke.runDukeForCli();
    }

    /**
     * Retrieves a response for the GUI.
     *
     * @param input Input string.
     * @return Result of feeding Parser the input.
     */
    public String getResponseForGui(String input) {
        try {
            return this.parser.processInput(input);
        } catch (DukeInputException e) {
            return this.ui.displayError(e.getMessage());
        }
    }

    /**
     * Runs Duke for the CLI.
     */
    private void runDukeForCli() {
        this.ui.displayIntroduction();
        this.readInputs();
    }

    /**
     * Reads the inputs from the user.
     */
    private void readInputs() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String nextInput = sc.nextLine();
            try {
                this.parser.processInput(nextInput);
            } catch (DukeInputException e) {
                this.ui.displayError(e.getMessage());
            }

            // Exit the program if user says bye
            if (nextInput.equals("bye")) {
                break;
            }
        }
        sc.close();
    }
}
