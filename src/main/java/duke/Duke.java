package duke;

import duke.commands.Command;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Implements a version of Duke called Wyre
 *
 * @author Eryn Seo
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean isRunning = true;

    /**
     * Creates bot object while initializing necessary components / classes
     *
     * @param filePath relative path to the task list text file
     */
    public Duke(String filePath) throws DukeException {
        ui = new Ui();
        ui.showWelcomeMessage();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
            ui.showNewSaveFileMessage();
        } catch (IOException | ClassNotFoundException e) {
            throw new DukeException("loading failed lah."); // when to catch and when to throw exception?
        }
    }

    /**
     * Runs the bot while there is user input & the user has not ended the session
     */
    public void run() {
        Scanner sc = new Scanner(System.in);

        while(sc.hasNextLine() && isRunning) {
            try {
                Parser p = new Parser();
                Command c = p.parseCommand(sc.nextLine());
                c.execute(tasks, ui, storage);
                this.isRunning = !c.isExitCommand();
            } catch (DukeException e) {
                System.out.println(e);
            }
        }
    }

    public static void main(String[] args) throws DukeException {
        new Duke("./task_list.txt").run();
    }
}