package bot;

import bot.command.Command;
import bot.util.DateParser;

import java.io.IOException;
import java.util.Scanner;

/**
 * Encompasses the UI, logic, model and all other factors that make up the application.
 */
public class Bot {
    private String dateFormatPath = "./src/main/resources/dateFormats/dateFormats.txt";
    private String name;
    private Storage storage;
    private TaskList taskList;
    private Parser parser;
    private Ui ui;

    /**
     * Constructor for bot.
     *
     * @param name name of the bot
     * @param filePath the relative path where user's data is stored
     */
    public Bot(String name, String filePath) {
        this.name = name;
        this.ui = new Ui(this.name);
        this.storage = new Storage(filePath);
        this.parser = new Parser();
        try {
            this.taskList = new TaskList(storage.loadFileContents());
            DateParser.loadDateFormats(dateFormatPath);
        } catch (IOException e) {
            ui.showLoadingError();
            this.taskList = new TaskList();
        }
    }

    public String getResponse(String input) {
        try {
            Command cmd = parser.parseInput(input);
            String response = cmd.run(this.taskList, this.storage);
            return response;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * Start the bot's interaction with user.
     */
    public void init(Scanner scanner) {
        boolean isEnded = false;
        ui.greet();

        while (!isEnded) {
            try {
                Command cmd = parser.parseInput(scanner.nextLine());
                String response = cmd.run(this.taskList, this.storage);
                ui.showMessage(response);
                isEnded = cmd.isExitCommand();
            } catch (Exception e) {
                ui.showMessage(e.getMessage());
            }
        }
    }
}
