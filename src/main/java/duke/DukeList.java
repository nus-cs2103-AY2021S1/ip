package duke;


import java.util.ArrayList;
import java.util.List;

import duke.exceptions.DukeException;
import duke.exceptions.DukeInvalidCommandException;
import duke.exceptions.DukeInvalidDescriptionException;
import duke.exceptions.DukeNoDateException;
import duke.exceptions.DukeNoDescriptionException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskType;
import duke.tasks.Todo;


/**
 * DukeList class for Duke.
 * Stores tasks.
 */
public class DukeList {

    private static final int DEFAULT_CAPACITY = 100;
    private final List<Task> taskList;
    private final Storage store = new Storage("data", "duke.txt");


    /**
     * Creates a new DukeList with default capacity of 100.
     */
    public DukeList() {
        this.taskList = new ArrayList<>(DEFAULT_CAPACITY);
    }


    /**
     * Creates a new DukeList with custom capacity.
     *
     * @param capacity List capacity to override default capacity.
     */
    public DukeList(int capacity) {
        this.taskList = new ArrayList<>(capacity);
    }


    /**
     * Helper function for adding an item.
     *
     * @param descriptionString String to be added.
     * @return Task added.
     * @throws DukeInvalidDescriptionException Invalid description.
     * @throws DukeInvalidCommandException     Invalid command.
     */
    private Task addHelper(String descriptionString) throws DukeInvalidDescriptionException, DukeInvalidCommandException {
        Task newTask;

        String[] strArr = Parser.parseLineToArray(descriptionString);
        TaskType taskType = Parser.getTaskKeyword(descriptionString);

        String formattedDescriptionString;

        switch (taskType) {
        case TODO:
            try {
                formattedDescriptionString = Parser.getItemSubstring(strArr);
                newTask = new Todo(formattedDescriptionString);
                break;
            } catch (DukeNoDescriptionException e) {
                throw new DukeInvalidDescriptionException(String.format("OOPS!!! The description of a `%s` cannot be empty.", taskType));
            }

        case DEADLINE:
            try {
                formattedDescriptionString = Parser.getItemSubstring(strArr);
                newTask = new Deadline(formattedDescriptionString);
                break;
            } catch (DukeNoDescriptionException e) {
                throw new DukeInvalidDescriptionException(String.format("OOPS!!! The description of a `%s` cannot be empty.", taskType));
            } catch (DukeNoDateException e) {
                throw new DukeInvalidDescriptionException(String.format("OOPS!!! The description of `%s` is invalid.", taskType));
            }

        case EVENT:
            try {
                formattedDescriptionString = Parser.getItemSubstring(strArr);
                newTask = new Event(formattedDescriptionString);
                break;
            } catch (DukeNoDescriptionException e) {
                throw new DukeInvalidDescriptionException(String.format("OOPS!!! The description of a `%s` cannot be empty.", taskType));
            } catch (DukeNoDateException e) {
                throw new DukeInvalidDescriptionException(String.format("OOPS!!! The description of `%s` is invalid.", taskType));
            }

        default:
            String invalidCommand = strArr[0];
            throw new DukeInvalidCommandException(String.format("OOPS!!! I'm sorry, but I don't know what `%s` means :-(", invalidCommand));
        }

        this.taskList.add(newTask);

        return newTask;
    }


    /**
     * Adds a new item to the list.
     *
     * @param descriptionString String to be added.
     * @return Status string to be printed.
     * @throws DukeException Duke exception.
     */
    public String add(String descriptionString) throws DukeException {
        Task newTask = addHelper(descriptionString);

        return "Got it. I've added this task:\n"
                + String.format("\t%s\n", newTask.toString())
                + String.format("%s", this.getListStats());
    }


    /**
     * Adds a new item to the list.
     * This method is only accessed privately.
     *
     * @param descriptionString Description string of task to be added.
     * @param isDone            True if task is already completed, false otherwise.
     * @throws DukeInvalidDescriptionException Invalid description text.
     * @throws DukeInvalidCommandException     Invalid command.
     */
    private void add(String descriptionString, boolean isDone) throws DukeInvalidDescriptionException, DukeInvalidCommandException {
        Task newTask = addHelper(descriptionString);

        if (isDone) {
            newTask.markAsDone();
        }
    }


    /**
     * Marks an item as done.
     *
     * @param index Index of item to be marked as done.
     *              This index is the printed index, not the actual index in the list.
     * @return Status string to be printed.
     * @throws NullPointerException Invalid index.
     */
    public String markAsDone(int index) throws IndexOutOfBoundsException {
        Task targetTask = this.taskList.get(index - 1);
        targetTask.markAsDone();

        return String.format("Nice! I've marked this task as done:\n\t%s", targetTask.toString());
    }


    /**
     * Deletes an item from the list.
     *
     * @param index Index of ite to be deleted.
     *              This index is the printed index, not the actual index in the list.
     * @return Status string to be printed.
     * @throws IndexOutOfBoundsException Invalid index.
     */
    public String delete(int index) throws IndexOutOfBoundsException {
        Task removedTask = this.taskList.remove(index - 1);
        return "Noted. I've removed this task:\n"
                + String.format("\t%s\n", removedTask.toString())
                + String.format("%s", this.getListStats());
    }


    /**
     * Finds tasks based on keyword.
     * Output index are the indexes in original dukeList.
     *
     * @param keyword Keyword to be searched for.
     * @return String representation of all matched tasks.
     */
    public String find(String keyword) {
        List<String> matchedTasks = new ArrayList<>();
        matchedTasks.add("Here are the matching tasks in your list:");

        for (int i = 0; i < this.taskList.size(); i++) {
            Task currTask = this.taskList.get(i);
            if (currTask.matches(keyword)) {
                matchedTasks.add(String.format("%d. %s", i + 1, currTask));
            }
        }

        if (matchedTasks.size() > 1) {
            return String.join("\n", matchedTasks);
        } else {
            return "No matching tasks found!";
        }

    }


    /**
     * Writes all tasks in list to file.
     */
    public void writeToFile() {
        for (Task t : this.taskList) {
            this.store.addToFileBuffer(t);
        }
        this.store.writeToFile();
    }


    /**
     * Loads items that are read from file into the list.
     */
    public void loadFromFile() {
        String[][] parsedLines = this.store.readFromFile();
        for (String[] parsedLine : parsedLines) {
            String descriptionString = parsedLine[0];
            String isDoneString = parsedLine[1];

            this.add(descriptionString, isDoneString.equals("1"));
        }
    }


    /**
     * Gets the string representation of the statistics of the list.
     *
     * @return String representation of statistics of the list.
     */
    private String getListStats() {
        return String.format("Now you have %d tasks in the list.", this.taskList.size());
    }


    @Override
    public String toString() {
        if (this.taskList.size() == 0) {
            return "List is currently empty!";
        } else {
            StringBuilder outputString = new StringBuilder();
            outputString.append("Here are the tasks in your list:\n");

            for (int i = 0; i < this.taskList.size(); i++) {
                String currTaskStr = String.format("%d: %s", i + 1, this.taskList.get(i).toString());
                outputString.append(currTaskStr);

                // add new line and tab only if not at the end of the list
                if (i < taskList.size() - 1) {
                    outputString.append("\n");
                }
            }

            return outputString.toString();
        }
    }

}
