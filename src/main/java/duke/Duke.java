package duke;

import duke.command.Command;
import duke.command.Parser;
import duke.dukeexception.DukeException;

/**
 * The Duke program is an interactive bot that offers commands to help the
 * user keep track of a mutable list of tasks that can be of 3 types:
 * <code>Todo</code>, <code>Deadline</code> or <code>Event</code>.
 * These tasks can also be marked as done and will be saved in the hard disk.
 *
 * @author Foo Jing Yi
 */
public class Duke {
    /** Storage object used by Duke to load from and write to hard disk */
    private Storage storage;
    /** TaskList object that contains the list of tasks */
    private TaskList tasks;
    /** Ui object that deals with interactions with the user */
    private Ui ui;

    public Duke() {}

    /**
     * Public class constructor that takes in the location of a file as a string
     * indicating the relative file path.
     * The list of tasks will be loaded from and saved to this file.
     *
     * @param pathFile Relative file path.
     */
    public Duke(String pathFile) {
        this.ui = new Ui();
        this.storage = new Storage(pathFile);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            this.tasks = new TaskList();
        }
    }

    /**
     * Runs the bot in the terminal.
     */
    public void run() {
        this.ui.showLine();
        System.out.println(this.ui.returnWelcomeMsg());
        this.ui.showLine();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = this.ui.readCommand();
                this.ui.showLine();
                Command command = Parser.parse(fullCommand);
                System.out.println(command.execute(this.tasks, this.storage, this.ui));
                isExit = command.isExit();
            } catch (DukeException e) {
                System.out.println(this.ui.returnError(e.getMessage()));
            } finally {
                this.ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        String response = "Test";
        try {
            Command command = Parser.parse(input);
            response = command.execute(tasks, storage, ui);
        } catch (DukeException e) {
            e.printStackTrace();
            response = ui.returnError(e.getMessage());
        }

        return response;
    }
}
