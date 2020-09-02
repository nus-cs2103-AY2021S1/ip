import java.io.IOException;

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
     * Runs the Chatbot instance.
     * Chatbot will execute commands based on inputText.
     */
    private String run(String inputText) {
        try {
            Command command = ui.parseCommand(inputText);

            switch (command.name) {
            case "find":
                return tasks.findTasks(command.message);
            case "bye":
                storage.saveTasksToFile();
                return "That's it? That's a shame. Well, see you later then.";
            case "list":
                return tasks.printList();
            case "done":
                return tasks.makeTaskDone(command.index);
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
