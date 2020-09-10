package duke.command;

import duke.exception.InvalidCommandException;
import duke.task.Task;
import duke.task.TaskList;

import java.util.Arrays;

// Handles all the logic behind any "done" command from the user.
public class TagCommand extends Command {
    private static final String ERROR_INVALID_INDEX = "Please input a valid index.";
    private static final String ERROR_RESERVED_KEYWORD = "Please do not use " + Task.TAGS_DELIMITER + ".";
    private static final String RESPONSE_TAGS_ADDED = "I have added the tags to the following task:\n  ";
    private static final String RESPONSE_NO_TAGS_ADDED = "There were no tags to be added.";

    /**
     * Executes any "tag" command issued by the user.
     * Adds the tags provided by the user to the task specified by the user.
     *
     * @param in String "tag" command issued by user.
     * @param taskList TaskList list that contains tasks added by the user.
     * @return String response message to user.
     * @throws InvalidCommandException If an invalid index is provided.
     */
    public static String execute(String in, TaskList taskList) throws InvalidCommandException {
        try {
            String[] details = in.replaceFirst("tag ", "").split(" ");
            String[] tagsToAdd = Arrays.copyOfRange(details, 1, details.length);
            for (String tag : tagsToAdd) {
                if (tag.contains(Task.TAGS_DELIMITER)) {
                    throw new InvalidCommandException(ERROR_RESERVED_KEYWORD);
                }
            }
            int index = Integer.parseInt(details[0]) - 1;
            Task task = taskList.get(index);
            task.addTags(tagsToAdd);
            taskList.update(index);

            boolean hasTagsToAdd = tagsToAdd.length > 0;

            String response = hasTagsToAdd
                    ? RESPONSE_TAGS_ADDED + task.toString()
                    : RESPONSE_NO_TAGS_ADDED;
            return response;
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidCommandException(ERROR_INVALID_INDEX);
        }
    }
}
