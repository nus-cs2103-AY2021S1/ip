package juke.command;

import java.io.IOException;

import juke.Storage;
import juke.TaskList;

/**
 * Represents the command to exit the chatbot.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exiting of chatbot, upon saving of tasks to file.
     * @param taskList List of tasks
     * @param storage Storage of tasks onto disk
     * @return Response Text to be output by chatbot upon exiting.
     */
    @Override
    public String executeCommand(TaskList taskList, Storage storage) {
        try {
            storage.saveTasksToFile();
            return "That's it? That's a shame. Well, see you later then.";
        } catch (IOException exception) {
            return exception.getMessage();
        }
    }
}
