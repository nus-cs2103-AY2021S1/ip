package duke;

import duke.exception.*;
import duke.command.*;
import java.io.IOException;

/**
 * The chat bot class to handle the internal logic.
 */
public class ChatBot {
    private final Ui ui;
    private final Parser parser;
    private TaskList tasks;

    /**
     * Constructor of the chat bot.
     * Construct a new chat bot with new UI, and initialised storage handling. Try to load the previous data file,
     * if failed create a new data file. If Duke cannot create a new one, <code>IOException</code> will be caught and
     * prints information for the user.
     */
    public ChatBot() {
        ui = new Ui();
        ui.showLoading();
        parser = new Parser();
        try {
            tasks = new TaskList(Storage.readList());
            ui.showLoaded(tasks.size());
        } catch (NoDataFileException e) {
            tasks = new TaskList();
            ui.showLoadedNew();
        } catch (IOException e) {
            ui.showLoadingError();
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
}
