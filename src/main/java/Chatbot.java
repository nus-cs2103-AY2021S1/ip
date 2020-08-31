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

    public static void main(String[] args) {

        boolean exitProgram = false;
        final Path dataLocation = Path.of("chatbot.txt");

        // initialization
        ui = new Ui();
        taskStorage = new Storage(dataLocation);

        try {
            taskList = new TaskList(taskStorage.loadTasks());
        } catch (ChatbotException e) {
            ui.showErrorMessage(e.getMessage());
            System.exit(0);
        }

        ui.greet();

        while(!exitProgram) {
            try {
                String fullCommand = ui.readCommand();
                Command command = Parser.parse(fullCommand);
                command.execute(taskList, ui, taskStorage);
                exitProgram = command.isExit();
            } catch (ChatbotException e) {
                ui.showErrorMessage(e.getMessage());
            }
        }
    }
}
