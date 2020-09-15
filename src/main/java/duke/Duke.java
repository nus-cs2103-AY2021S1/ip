package duke;

import duke.command.Command;
import duke.command.ExitCommand;

/**
 * Encapsulates a friendly personal assistant that helps keep track of tasks to be done.
 * Duke has been renamed to Dude.
 */
public class Duke {
    private final String path = "./data/duke.txt";
    private Storage storage;
    private TaskList taskList;
    private Parser parser;
    private boolean isExit;

    /**
     * Initializes duke.
     */
    public Duke() {
        try {
            storage = new Storage(path);
            taskList = new TaskList(storage.getTasks());
            parser = new Parser();
            isExit = false;
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    private String listen(String input) {
        try {
            Command command = parser.parse(input);
            if (command instanceof ExitCommand) {
                isExit = true;
            }
            command.execute(storage, taskList);
            String response = Ui.getMessages();
            return response;
        } catch (DukeException e) {
            String response = e.getMessage();
            return response;
        }
    }

    public String getGreeting() {
        Ui.addMessage("Hello! My name is Dude.");
        Ui.addMessage("What can I do for you?");
        return Ui.getMessages();
    }

    public String getResponse(String input) {
        return listen(input);
    }

    public boolean isExit() {
        return isExit;
    }
}
