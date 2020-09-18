package meimei;

import meimei.botexception.BotException;
import meimei.command.Command;
import meimei.command.Parser;

/**
 * Meimei Bot is an interactive bot that offers commands to help the
 * user keep track of a mutable list of tasks that can be of 3 types:
 * <code>Todo</code>, <code>Deadline</code> or <code>Event</code>.
 * These tasks can also be marked as done and will be saved in the hard disk.
 *
 * @author Foo Jing Yi
 */
public class MeimeiBot {
    /**
     * Storage object used by Meimei Bot to load from and write to hard disk
     */
    private final Storage storage;
    /**
     * TaskList object that contains the list of tasks
     */
    private TaskList tasks;
    /**
     * Ui object that deals with interactions with the user
     */
    private final Ui ui;

    private boolean isRunning;

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
        } catch (BotException e) {
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
            } catch (BotException e) {
                System.out.println(this.ui.returnError(e.getMessage()));
            } finally {
                this.ui.showLine();
            }
        }
    }

    /**
     * Main method for running bot in the terminal.
     *
     * @param args Arguments as pass from command line.
     */
    public static void main(String[] args) {
        new MeimeiBot("data/tasks.txt").run();
    }

    /**
     * Runs the bot for the GUI.
     *
     * @return The welcome message that the user sees on starting the bot.
     */
    public String start() {
        this.isRunning = true;
        return this.ui.returnWelcomeMsg();
    }

    /**
     * Gets a response from the bot based on the input through the CLI of the GUI.
     *
     * @param input The user input from the CLI of the GUI.
     * @return The reply from Meimei Bot to the user.
     */
    public String getResponse(String input) {
        String response;
        try {
            Command command = Parser.parse(input);
            response = command.execute(tasks, storage, ui);
            isRunning = !command.isExit();
        } catch (BotException e) {
            e.printStackTrace();
            response = ui.returnError(e.getMessage());
        }

        return response;
    }

    /**
     * Informs the caller of the method on whether the bot is running.
     *
     * @return Boolean indicating whether the bot is running.
     */
    public boolean isRunning() {
        return isRunning;
    }
}
