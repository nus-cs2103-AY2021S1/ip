package duke;

import duke.exception.*;
import duke.task.TaskType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents the list of tasks together with a counter
 * to keep track of pending tasks.
 */
public class TaskList implements Cloneable {
    private static final String DONE_MESSAGE = "Good job! This task is now marked done:";
    private static final String DELETED_MESSAGE = "Alright! This task is now deleted:";
    private static final String LIST_HINT = "(Use 'list' command to see your updated list.)";
    private static final String NO_MATCH = "No matches found!";
    private List<Task> tasks;
    private int numOfPendingTasks;

    TaskList() {
        this.tasks = new ArrayList<>();
        this.numOfPendingTasks = 0;
    }

    TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.numOfPendingTasks = 0;
        for (Task task : tasks) {
            if (!task.isDone) {
                incrementPendingTasks();
            }
        }
    }

    /**
     * Checks if a call to task index in the CLI is valid.
     *
     * @param target Integer number.
     * @return true if target is usable as an array access.
     */
    public boolean isWithinValidRange(int target) {
        return target >= 0 && target < tasks.size();
    }

    /**
     * Adds one count to pending task.
     */
    public void incrementPendingTasks() {
        this.numOfPendingTasks++;
    }

    /**
     * Subtracts one count to pending task.
     */
    public void decrementPendingTasks() {
        this.numOfPendingTasks--;
    }

    /**
     * Gets the intended access index of a Task in TaskList.
     *
     * @param parser Parser which contains the processed command line.
     * @return The corresponding array access.
     * @throws DukeException If the task is empty,
     *         no integer index provided,
     *         or index given is out of range.
     */
    public int getTaskID(Parser parser) throws DukeException {
        if (tasks.isEmpty()) {
            throw new EmptyTasksException("Task is empty");
        }
        if (Objects.isNull(parser.getTaskNumber())) {
            throw new NullIndexException("Target is null");
        }

        int target = parser.getTaskNumber() - 1;
        if (!isWithinValidRange(target)) {
            throw new RangeIndexException("Range is invalid");
        }

        return target;
    }

    /**
     * Attempts to check a Task as done, then display a success message.
     * If the checking as done fails, a fail message is displayed instead.
     *
     * @param parser
     */
    public String done(Parser parser) {
        try {
            Task targetTask = tasks.get(getTaskID(parser));
            if (targetTask.isDone) {
                throw new AlreadyDoneIndexException("Task already done");
            }
            targetTask.markAsDone();
            decrementPendingTasks();
            String toReturn = DONE_MESSAGE + "\n" + targetTask;
            return Ui.displayMessage(toReturn);
        } catch (DukeException e) {
            return Ui.displayMessage(e.toString());
        }
    }

    /**
     * Attempts to delete a Task, then display a success message
     * If the deletion fails, a fail message is displayed instead
     *
     * @param parser
     */
    public String delete(Parser parser) {
        try {
            Task targetTask = tasks.get(getTaskID(parser));
            tasks.remove(targetTask);

            if (!targetTask.isDone) {
                decrementPendingTasks();
            }

            String toReturn = DELETED_MESSAGE + "\n" + targetTask;

            return Ui.displayMessage(toReturn);
        } catch (DukeException e) {
            return Ui.displayMessage(e.toString());
        }
    }

    /**
     * Attempts to add a Task to the list, then display a success message.
     * If the addition fails, a fail message is displayed instead.
     *
     * @param parser
     */
    public String add(Parser parser) {
        try {
            String commandWord = parser.getCommandWord();
            String taskName = parser.getTaskName();
            String taskDate = parser.getTaskDate();
            TaskType task = TaskType.getTaskType(commandWord);
            task.addToTasks(tasks, taskName, false, taskDate);

            int size = tasks.size();
            incrementPendingTasks();

            String toReturn = "'" + taskName + "' added to list!\n"
                    + tasks.get(size - 1) + "\n"
                    + "You now have " + size + " task(s) in your list.\n"
                    + LIST_HINT;

            return Ui.displayMessage(toReturn);
        } catch (DukeException e) {
            return Ui.displayMessage(e.toString());
        }
    }

    /**
     * Displays a list of Tasks that contains a given String.
     * If not found, displays an alternative null message.
     *
     * @param parser
     */
    public String find(Parser parser) {
        List<Task> matches = getMatchingTask(parser.comparator);

        if (matches.isEmpty()) {
            return Ui.displayMessage(NO_MATCH);
        } else {
            int count = 1;
            String toReturn = String.format("Found %d match(es) for '%s':", matches.size(), parser.comparator);

            for (Task task : matches) {
                toReturn += String.format("   %d. %s", count, task.toString());
                count++;
            }

            return Ui.displayMessage(toReturn);
        }
    }

    /**
     * Creates a sublist of Tasks with a name that contains a certain string.
     *
     * @param comparator the substring of interest.
     * @return a list of Tasks containing the substring of interest.
     */
    public List<Task> getMatchingTask(String comparator) {
        List<Task> matchingTasks = new ArrayList<>();

        for (Task task : tasks) {
            boolean emptyComparator = comparator.isBlank();
            boolean hasSubstring = task.getTaskName().contains(comparator);
            boolean isContained = hasSubstring && !emptyComparator;

            if (isContained) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    /**
     * Creates a clone of the current TaskList (for testing purposes).
     *
     * @return a shallow copy of this with no element references.
     * @throws CloneNotSupportedException
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * Class getter routines.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    public int getNumOfPendingTasks() {
        return numOfPendingTasks;
    }
}

