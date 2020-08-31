package bot;

import bot.command.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import bot.util.*;
import bot.task.*;

public class Bot {
    private String name;
    private Storage storage;
    private TaskList taskList;
    private Parser parser;
    private Ui ui;

    /**
     * Constructor for bot.
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
        } catch (IOException e) {
            ui.showLoadingError();
            this.taskList =  new TaskList();
        }
    }

    /**
     * Start the bot's interaction with user.
     */
    public void init(Scanner scanner) {
        boolean isEnded = false;
        ui.greet();

        while(!isEnded) {
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
