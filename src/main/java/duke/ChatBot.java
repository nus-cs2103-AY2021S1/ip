package duke;

import java.io.IOException;

import duke.command.Command;
import duke.exception.NoDataFileException;

/**
 * The chat bot class to handle the internal logic.
 */
public class ChatBot {
    private final Ui ui;
    private final Parser parser;
    private TaskList tasks;
    private String welcomeMsg;
    private boolean isExit;

    /**
     * Constructor of the chat bot.
     * Construct a new chat bot with new UI, and initialised storage handling.
     * Try to load the previous data file, if failed create a new data file.
     * If Duke cannot create a new one, <code>IOException</code> will be caught
     * and prints information for the user.
     */
    public ChatBot() {
        ui = new Ui();
        ui.showLoading();
        isExit = false;
        welcomeMsg = "Data loading...\n";
        parser = new Parser();
        try {
            tasks = new TaskList(Storage.readList());
            ui.showLoaded(tasks.size());
            welcomeMsg += "Previous data found!\nNow you have "
                    + tasks.size() + " task in the list!";
        } catch (NoDataFileException e) {
            tasks = new TaskList();
            ui.showLoadedNew();
            welcomeMsg += "Did not find any previous stored data and "
                    + "new data file created!\nWelcome!";
        } catch (IOException e) {
            ui.showLoadingError();
            welcomeMsg += "Oops! Cannot access your data file and no" +
                    "\nnew data file has been created!";
        }
    }

    /**
     * The entry point to run the chat bot.
     */
    public void start() {
        ui.welcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String response = ui.receiveChat();
                ui.printHorizontal();
                Command cmd = parser.parse(response);
                cmd.execute(tasks, ui);
                Storage.save(tasks.getList());
                isExit = cmd.isExit();
            } catch (Exception e) {
                ui.printLine(e.getMessage());
            }
            ui.printHorizontal();
        }
    }

    public String getWelcomeMsg() {
        return welcomeMsg + "\nHello there! What can I do for you?";
    }

    public String getResponse(String input) {
        try {
            Command cmd = parser.parse(input);
            if (cmd.isExit()) isExit = true;
            cmd.execute(tasks, ui);
            Storage.save(tasks.getList());
            return cmd.getMessage();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public boolean isExit() {
        return isExit;
    }
}
