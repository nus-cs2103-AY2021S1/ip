package duke;

import java.util.Scanner;

/**
 * Represents a personal assistant chatbot to help a person keep track of various things.
 */
public class Duke {

    /** UI for Duke to display messages. */
    private final Ui ui;

    /** Storage for Duke to store tasks. */
    private final Storage storage;

    /** Parser for Duke to parse inputs. */
    private final Parser parser;

    /**
     * Creates an instance of Duke.
     */
    public Duke() {
        TaskList taskList = new TaskList();
        this.ui = new Ui();
        this.storage = new Storage("../data", "../data/duke.txt", this.ui, taskList);
        this.parser = new Parser(this.storage);
    }

    /**
     * Runs Duke.
     */
    public void run() {
        this.storage.checkSaveFile();
        this.ui.printIntroduction();
        this.readInputs();
    }

    /**
     * Reads the inputs from the user.
     */
    public void readInputs() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String nextInput = sc.nextLine();
            try {
                this.parser.processInput(nextInput);
            } catch (DukeException e) {
                this.ui.printError(e.getMessage());
            }

            // Exit the program if user says bye
            if (nextInput.equals("bye")) {
                break;
            }
        }
        sc.close();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
