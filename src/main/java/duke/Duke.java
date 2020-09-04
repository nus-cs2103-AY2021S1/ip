package duke;

import java.io.IOException;

/**
 * Duke class representing the chat bot.
 *
 * @author Kor Ming Soon
 */
public class Duke {

    private TaskList taskList;
    private UserInterface ui;

    /**
     * Constructor for the Duke chatbot.
     */
    public Duke() {
        Storage storage = new Storage();
        taskList = new TaskList(storage);
        ui = new UserInterface();
    }

    /**
     * Method to initialise the acceptance of commands
     */
    public String acceptCommands(String input) {
        Parser parser = new Parser(input, ui, taskList);
        return parser.retrieveResponse();
    }

    /**
     * Method to retrieve the response of Duke.
     *
     * @param input user command.
     * @return the String response.
     */
    public String getResponse(String input) {
        try {
            taskList.loadList();
            return acceptCommands(input);
        } catch (IOException e) {
            return ui.printError("File not found.");
        }
    }
}
