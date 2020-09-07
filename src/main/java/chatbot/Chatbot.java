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

    public Chatbot() {
        final Path dataLocation = Path.of("chatbot.txt");

        ui = new Ui();
        taskStorage = new Storage(dataLocation);

        try {
            taskList = new TaskList(taskStorage.loadTasks());
            assert taskList != null : "Task list is not supposed to be null.";
        } catch (ChatbotException e) {
            e.printStackTrace();
        }
    }

    public String getResponse(String input) {

        String response = "";

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

    public String greeting() {
        return ui.greet();
    }
}
