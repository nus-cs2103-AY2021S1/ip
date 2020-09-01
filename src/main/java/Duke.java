import java.io.IOException;
import java.util.Scanner;

/**
 * Represents the Duke Chatbot.
 * Allows users to store a list of Tasks, consisting of three types,
 * namely Todos, Events and Deadlines. Users input, change and delete
 * such items via text commands to the bot.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;


    /**
     * Builds an instance of the Duke Chatbot.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        tasks = new TaskList(storage.load());
    }

    /**
     * Runs the chatbot instance.
     * Chatbot will begin listening for commands with this method.
     */
    private String run(String inputText) {
        try {
            Command command = ui.parseCommand(inputText);
            String output = "";

            switch (command.name) {
            case "find":
                return tasks.findTasks(command.message);
            case "bye":
                storage.saveTasks();
                return "That's it? That's a shame. Well, see you later then.";
            case "list":
                return tasks.printList();
            case "done":
                return tasks.taskDone(command.index);
            case "delete":
                return tasks.removeFromList(command.index);
            case "error":
                return command.message;
            default:
                return tasks.addToList(command.task);
            }
        } catch (IOException ex) {
            return ex.getMessage();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    protected String getResponse(String input) {
        return this.run(input);
    }

}
