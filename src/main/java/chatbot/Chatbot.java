package chatbot;

import chatbot.commands.Command;
import chatbot.commands.ExitCommand;
import chatbot.data.TaskList;
import chatbot.exception.ChatbotException;
import chatbot.parser.Parser;
import chatbot.storage.Storage;
import chatbot.ui.Ui;

import java.nio.file.Path;

public class Chatbot {

    private static TaskList taskList;
    private static Storage taskStorage;
    private static Ui ui;

    private Path filePath;

    public Chatbot() {
        initialiseDataPath("chatbot.txt");
        initialiseUI();
        initialiseStorage();
        initialiseTaskList(taskStorage);
    }

    public void initialiseDataPath(String path) {
        this.filePath = Path.of(path);
    }

    public void initialiseUI() {
        ui = new Ui();
    }

    public void initialiseStorage() {
        taskStorage = new Storage(filePath);
    }

    public void initialiseTaskList(Storage store) {
        // load tasks from disk
        try {
            taskList = new TaskList(store.loadTasks());
        } catch (ChatbotException e) {
            e.printStackTrace();
        }
    }

    public String getResponse(String input) {
        String response;

        try {
            // parse user input to generate a command
            Command command = Parser.parse(input);

            // exit command -> terminate program
            if (command instanceof ExitCommand) {
                ui.exit();
            }

            // execute command to get response message
            response = command.execute(taskList, ui, taskStorage);
        } catch (ChatbotException e) {
            response = e.getMessage();
        }

        return response;
    }

    public String generateWelcomeMessage() {
        return ui.greet();
    }
}
