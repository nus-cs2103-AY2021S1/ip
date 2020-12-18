package duke.command;

import java.util.Arrays;
import java.util.List;

import duke.exception.InvalidCommandException;
import duke.response.NormalResponse;
import duke.response.Response;
import duke.task.Task;
import duke.task.TaskList;


// Handles all the logic behind any "done" command from the user.
public class TagCommand extends Command {
    private static final String ERROR_INVALID_INDEX = "Please input a valid index.";
    private static final String ERROR_RESERVED_KEYWORD = Task.TAGS_DELIMITER + " is a reserved keyword.";
    private static final String RESPONSE_TAGS_ADDED = "I have added the tags to the following task:\n  ";
    private static final String RESPONSE_NO_TAGS_ADDED = "There are no tags to be added.";
    private static final String RESPONSE_TAGS_REMOVED = "These are the tags that were removed:\n";
    private static final String RESPONSE_UPDATED_TASK = "These are the remaining tags for the specified task:\n  ";
    private static final String RESPONSE_NO_TAGS_REMOVED = "There are no tags to be removed.";

    /**
     * Executes any "tag" command issued by the user.
     * Adds the tags provided by the user to the task specified by the user.
     *
     * @param in String "tag" command issued by user.
     * @param taskList TaskList list that contains tasks added by the user.
     * @return Response response message to user.
     * @throws InvalidCommandException If an invalid index is provided.
     */
    public static Response execute(String in, TaskList taskList) throws InvalidCommandException {
        String[] details = in.replaceFirst("tag ", "").split(" ");
        String[] tags = Arrays.copyOfRange(details, 1, details.length);
        for (String tag : tags) {
            if (tag.contains(Task.TAGS_DELIMITER)) {
                throw new InvalidCommandException(ERROR_RESERVED_KEYWORD);
            }
        }

        try {
            int index = Integer.parseInt(details[0]);
            String response;
            if (index < 0) {
                response = removeTagsFromTask(Math.abs(index) - 1, taskList, tags);
            } else {
                response = addTagsToTask(index - 1, taskList, tags);
            }
            return new NormalResponse(response);
        } catch (NumberFormatException e) {
            throw new InvalidCommandException(ERROR_INVALID_INDEX);
        }
    }

    private static String addTagsToTask(int index, TaskList taskList, String[] tagsToAdd)
            throws InvalidCommandException {
        try {
            Task task = taskList.get(index);
            task.addTags(tagsToAdd);
            taskList.update(index);

            boolean hasTagsToAdd = tagsToAdd.length > 0;

            String response = hasTagsToAdd
                    ? RESPONSE_TAGS_ADDED + task.toString()
                    : RESPONSE_NO_TAGS_ADDED;
            return response;
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCommandException(ERROR_INVALID_INDEX);
        }
    }

    private static String removeTagsFromTask (int index, TaskList taskList, String[] tagsToRemove)
            throws InvalidCommandException {
        try {
            Task task = taskList.get(index);
            List<String> removedTags = task.removeTags(tagsToRemove);
            taskList.update(index);
            String removedTagsFormatted = String.join("\n", removedTags) + "\n";

            boolean wasTaskUpdated = removedTags.size() > 0;

            String response = wasTaskUpdated
                    ? RESPONSE_TAGS_REMOVED + removedTagsFormatted + RESPONSE_UPDATED_TASK + task.toString()
                    : RESPONSE_NO_TAGS_REMOVED;
            return response;
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCommandException(ERROR_INVALID_INDEX);
        }
    }
}
