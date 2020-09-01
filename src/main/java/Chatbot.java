package main.java;

import main.java.commands.Command;
import main.java.data.TaskList;
import main.java.exception.ChatbotException;
import main.java.parser.Parser;
import main.java.storage.Storage;
import main.java.ui.Ui;

import java.nio.file.Path;

public class Chatbot {

    private static TaskList taskList;
    private static Storage taskStorage;
    private static Ui ui;

    public Chatbot() {
        final Path dataLocation = Path.of("chatbot.txt");

        ui = new Ui();
        taskStorage = new Storage(dataLocation);

        try {
            taskList = new TaskList(taskStorage.loadTasks());
        } catch (ChatbotException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public String getResponse(String input) {

        String response = "";

        try {
            Command command = Parser.parse(input);
            response = command.execute(taskList, ui, taskStorage);
        } catch (ChatbotException e) {
            response = e.getMessage();
        }

        return response;
    }

    public String greeting() {
        return ui.greet();
    }
}
