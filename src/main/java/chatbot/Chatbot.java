package chatbot;

import java.nio.file.Path;

import chatbot.commands.Command;
import chatbot.commands.ExitCommand;
import chatbot.data.TaskList;
import chatbot.exception.ChatbotException;
import chatbot.parser.ChatbotParser;
import chatbot.storage.Storage;
import chatbot.ui.Ui;

public class Chatbot {

    private static TaskList taskList;
    private static Storage taskStorage;
    private static Ui ui;

    private Path filePath;

    /**
     * Constructs a chatbot with storage capabilities.
     */
    public Chatbot() {
        initialiseDataPath();
        initialiseUI();
        initialiseStorage();
        initialiseTaskList(taskStorage);
    }

    /**
     * Generates a response from user input.
     * @param input user input
     * @return response message
     */
    public String getResponse(String input) {
        String response;

        try {
            // parse user input to generate a command
            Command command = ChatbotParser.parseCommand(input);

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

    /**
     * Generates a welcome message.
     * @return Welcome message
     */
    public String generateWelcomeMessage() {
        return ui.getWelcomeMessage();
    }

    private void initialiseDataPath() {
        String path = "chatbot.txt";
        this.filePath = Path.of(path);
    }

    private void initialiseUI() {
        ui = new Ui();
    }

    private void initialiseStorage() {
        taskStorage = new Storage(filePath);
    }

    private void initialiseTaskList(Storage store) {
        // load tasks from disk
        try {
            taskList = new TaskList(store.loadTasks());
        } catch (ChatbotException e) {
            e.printStackTrace();
        }
    }
}
