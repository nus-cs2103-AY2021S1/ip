package duke;


import java.util.ArrayList;
import java.util.Collections;
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
     * Adds a new item to the list.
     *
     * @param descriptionString String to be added.
     * @return Status string to be printed.
     * @throws DukeException Duke exception.
     */
    public String add(String descriptionString) throws DukeException {
        Task newTask = addHelper(descriptionString);

        return "Orite, I've added this:\n"
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
     * Helper function for adding an item.
     *
     * @param descriptionString String to be added.
     * @return Task added.
     * @throws DukeInvalidDescriptionException Invalid description.
     * @throws DukeInvalidCommandException     Invalid command.
     */
    private Task addHelper(String descriptionString) throws DukeInvalidDescriptionException, DukeInvalidCommandException {
        String[] strArr = Parser.parseLineToArray(descriptionString);
        TaskType taskType = Parser.getTaskKeyword(descriptionString);

        Task newTask = this.handleTasks(strArr, taskType);

        assert newTask != null;
        this.taskList.add(newTask);

        return newTask;
    }


    /**
     * Helper function to get new task.
     *
     * @param strArr   Parsed string array of user input.
     * @param taskType Parsed type of task.
     * @return New task to be added.
     * @throws DukeInvalidDescriptionException Invalid description in user input.
     * @throws DukeInvalidCommandException     Invalid task type in user input.
     */
    private Task handleTasks(String[] strArr, TaskType taskType) throws DukeInvalidDescriptionException, DukeInvalidCommandException {
        String formattedDescriptionString;
        Task newTask;

        try {
            switch (taskType) {
            case TODO:
                formattedDescriptionString = Parser.getItemSubstring(strArr);
                newTask = new Todo(formattedDescriptionString);
                break;
            case DEADLINE:
                formattedDescriptionString = Parser.getItemSubstring(strArr);
                newTask = new Deadline(formattedDescriptionString);
                break;
            case EVENT:
                formattedDescriptionString = Parser.getItemSubstring(strArr);
                newTask = new Event(formattedDescriptionString);
                break;
            default:
                String invalidCommand = strArr[0];
                throw new DukeInvalidCommandException(String.format("Yo whatchu mean by `%s`???", invalidCommand));
            }

            assert newTask != null;
            return newTask;

        } catch (DukeNoDescriptionException e) {
            throw new DukeInvalidDescriptionException(String.format("Hey hey where's my description for `%s`?", taskType));
        } catch (DukeNoDateException e) {
            throw new DukeInvalidDescriptionException(String.format("Mate  you gave me an invalid description for `%s`!", taskType));
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

        return String.format("Orite, this is marked as done:\n\t%s", targetTask.toString());
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
        return "Okay, removed this:\n"
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
        matchedTasks.add("Here's what I found man:");

        for (int i = 0; i < this.taskList.size(); i++) {
            Task currTask = this.taskList.get(i);
            if (currTask.matches(keyword)) {
                matchedTasks.add(String.format("%d. %s", i + 1, currTask));
            }
        }

        if (matchedTasks.size() > 1) {
            return String.join("\n", matchedTasks);
        } else {
            return "There's nothing found!";
        }

    }


    /**
     * Sorts tasks chronologically.
     *
     * @return Status string to be printed.
     */
    public String sort() {
        if (this.taskList.size() == 0) {
            return "List is empty yo! Ain't nothing to sort!";
        }

        Collections.sort(this.taskList);

        return "Yes yes, tasks sorted chronologically:\n" + this.toString();

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
        return String.format("You have %d tasks in the list man! Get your stuff done asap", this.taskList.size());
    }


    @Override
    public String toString() {
        if (this.taskList.size() == 0) {
            return "You list is empty bruh!";
        } else {
            StringBuilder outputString = new StringBuilder();
            outputString.append("Get your stuff done:\n");

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
