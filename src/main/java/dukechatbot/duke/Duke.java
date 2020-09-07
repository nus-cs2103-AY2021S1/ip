package dukechatbot.duke;

import java.util.Objects;

import dukechatbot.command.Command;
import dukechatbot.constant.DukeConstants;
import dukechatbot.dukeoutput.DukeOutput;
import dukechatbot.parser.CommandParser;
import dukechatbot.storage.Storage;
import dukechatbot.tasklist.TaskList;

/**
 * Represents the chatbot.
 * Handles the input from the GUI,
 * takes appropriate action and output accordingly.
 */
public class Duke {
    
    private final TaskList taskList = new TaskList(Storage.load());

    /**
     * Gets the appropriate response for the input.
     * 
     * @param input
     * @return
     */
    public String getResponse(String input) {
        try {
            return this.respond(input);
        } catch (IndexOutOfBoundsException exception) {
            return DukeOutput.getOutput(exception.getMessage());
        }
    }
    
    private String respond(String input) throws IndexOutOfBoundsException {
        Command command = CommandParser.parse(input);
        if (Objects.isNull(command)) {
            return this.handleInvalidInput();
        } 
        return command.getCommandExecutor().execute(command, taskList);
    }

    private String handleInvalidInput() {
        return DukeOutput.getOutput(DukeConstants.INVALID_INPUT_RESPONSE);
    }

    /**
     * Returns the initial welcome response.
     * 
     * @return
     */
    public static String getGreetingMessage() {
        return DukeOutput.getOutput(DukeConstants.INITIAL_RESPONSE);
    }
}
