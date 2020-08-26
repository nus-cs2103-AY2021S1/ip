package Duke;

import java.time.format.DateTimeParseException;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

/**
 * Represents a serializable list of tasks in Duke.
 */
public class TaskList implements Serializable {
    private final List<Task> itemList = new ArrayList<>();

    /**
     * Adds a Task to the list given a string representing the task.
     *
     * @param item A string representing the task to add.
     * @return The Task instance created and added.
     * @throws DukeException if an invalid item string is given.
     */
    public Task addItem(String item) throws DukeException {
        String type = getItemType(item);
        Task taskToAdd = null;

        switch(type) {
        case "todo":
            if (item.trim().equals("todo")) {
                throw new DukeException("Todos must have non-empty "
                                                + "descriptions!");
            }

            String todoDescription = item.split("todo")[1].trim();
            taskToAdd = new Todo(todoDescription);
            break;
        case "deadline":
            try {
                taskToAdd = new Deadline(
                        getItemDescription(item, type),
                        getItemParameter(item, type)
                );
                break;
            } catch (DateTimeParseException e) {
                throw new DukeException("Invalid date entered");
            }
        case "event":
            taskToAdd = new Event(
                    getItemDescription(item, type),
                    getItemParameter(item, type)
            );
            break;
        default:
            throw new DukeException("Unrecognized task/command.");
        }

        itemList.add(taskToAdd);
        return taskToAdd;
    }

    //--------start of item literal parsers---------//

    /**
     * Returns the type of a task, which is one of: todo, deadline, event.
     *
     * @param item The string representing the task.
     * @return A string representing the task type.
     */
    String getItemType(String item) {
        return item.split(" ")[0].trim();
    }

    /**
     * Returns the description of a task based on its type.
     *
     * @param item The string representing the task.
     * @param itemType The string representing the task type.
     * @return A string containing the task description.
     */
    String getItemDescription(String item, String itemType) {
        // remove itemtype, which is the first word of the item literal
        return splitItemLiteral(item, itemType)[0]
                .split(itemType)[1]
                .trim();
    }

    /**
     * Returns the task parameter of a deadline/task (date/time).
     *
     * @param item The string representing the task.
     * @param itemType The string representing the task type.
     * @return A string containing the parameter.
     */
    String getItemParameter(String item, String itemType) {
        return splitItemLiteral(item, itemType)[1].trim();
    }

    /**
     * Splits a string literal representing a deadline/event by "/by" and "/at" respectively.
     *
     * @param item The string representing the task.
     * @param itemType The string representing the task type.
     * @return An array of strings containing the substrings before and after the respective delimiters.
     */
    String[] splitItemLiteral(String item, String itemType) {
        String delimiter = itemType.equals("deadline") ? "/by" : "/at";
        return item.split(delimiter);
    }
    //--------end of item literal parsers---------//

    /**
     * Marks a task as "done".
     *
     * @param itemIndex The 1-based index of the task in the task list.
     * @throws IllegalArgumentException if the index is larger than the length of the task list.
     */
    void markAsDone(int itemIndex) throws IllegalArgumentException {
        if (itemIndex > itemList.size()) {
            throw new IllegalArgumentException("Item #" + itemIndex + " does "
                                                       + "not exist and cannot "
                                                       + "be marked as done.");
        }
        itemList.get(itemIndex - 1).markAsDone();
    }

    /**
     * Removes a task from the task list.
     *
     * @param itemIndex The 1-based index of the task in the task list.
     * @throws IllegalArgumentException if the index is larger than the length of the task list.
     */
    void removeItem(int itemIndex) throws IllegalArgumentException {
        if (itemIndex > itemList.size()) {
            throw new IllegalArgumentException("Item #" + itemIndex + " does "
                                                       + "not exist and cannot "
                                                       + "be removed.");
        }
        Task removed = itemList.remove(itemIndex - 1);
        Ui.showSuccessfulRemoval(removed);
    }

    /**
     * Prints the contents of the task list to standard out.
     */
    void printList() {
        for (int i = 1; i <= itemList.size(); i++) {
            System.out.println(i + ". " + itemList.get(i - 1));
        }
    }
}
