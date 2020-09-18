package meimei;

import meimei.command.Command;
import meimei.command.Parser;
import meimei.dukeexception.DukeException;

/**
 * The Duke program is an interactive bot that offers commands to help the
 * user keep track of a mutable list of tasks that can be of 3 types:
 * <code>Todo</code>, <code>Deadline</code> or <code>Event</code>.
 * These tasks can also be marked as done and will be saved in the hard disk.
 *
 * @author Foo Jing Yi
 */
public class MeimeiBot {
    /**
     * Storage object used by Duke to load from and write to hard disk
     */
    private Storage storage;
    /**
     * TaskList object that contains the list of tasks
     */
    private TaskList tasks;
    /**
     * Ui object that deals with interactions with the user
     */
    private Ui ui;

    private boolean isRunning;

    public MeimeiBot() {
    }

    /**
     * Public class constructor that takes in the location of a file as a string
     * indicating the relative file path.
     * The list of tasks will be loaded from and saved to this file.
     *
     * @param pathFile Relative file path.
     */
    public MeimeiBot(String pathFile) {
        this.ui = new Ui();
        this.storage = new Storage(pathFile);
        this.isRunning = false;
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
        this.isRunning = true;
        System.out.println(start());
        this.ui.showLine();
        while (isRunning) {
            try {
                String fullCommand = this.ui.readCommand();
                this.ui.showLine();
                Command command = Parser.parse(fullCommand);
                System.out.println(command.execute(this.tasks, this.storage, this.ui));
                isRunning = !command.isExit();
            } catch (DukeException e) {
                System.out.println(this.ui.returnError(e.getMessage()));
            } finally {
                this.ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new MeimeiBot("data/tasks.txt").run();
    }

    public String start() {
        this.isRunning = true;
        return this.ui.returnWelcomeMsg();
    }

    public String getResponse(String input) {
        String response;
        try {
            Command command = Parser.parse(input);
            response = command.execute(tasks, storage, ui);
            isRunning = !command.isExit();
        } catch (DukeException e) {
            e.printStackTrace();
            response = ui.returnError(e.getMessage());
        }

        return response;
    }

    public boolean isRunning() {
        return isRunning;
    }
}
