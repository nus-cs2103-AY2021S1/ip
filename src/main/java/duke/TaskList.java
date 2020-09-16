package duke;

import duke.exception.*;
import duke.task.TaskType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Represents the list of tasks together with a counter
 * to keep track of pending tasks.
 */
public class TaskList {
    private static final String DONE_MESSAGE = "Good job! This task is now marked done:";
    private static final String DELETED_MESSAGE = "Alright! This task is now deleted:";
    private static final String LIST_HINT = "(type 'list' to see changes.)";
    private static final String NO_MATCH_MESSAGE = "No matches found!";
    private static final String UPDATED_MESSAGE = "Got it! Your task has been updated.";
    private List<Task> tasks;
    private int numOfPendingTasks;
    private boolean toReturnAsValid = true;

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

    public void incrementPendingTasks() {
        this.numOfPendingTasks++;
        assert numOfPendingTasks > 0 : "Amount of pending tasks was negative; something went wrong.";
    }

    public void decrementPendingTasks() {
        this.numOfPendingTasks--;
        assert numOfPendingTasks >= 0 : "Called decrement to an empty list; should be impossible.";
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
            validate(false);
            throw new EmptyTasksException("Task is empty");
        }
        if (Objects.isNull(parser.getTaskNumber())) {
            validate(false);
            throw new NullIndexException("Target is null");
        }

        int target = parser.getTaskNumber() - 1;
        if (!isWithinValidRange(target)) {
            validate(false);
            throw new RangeIndexException("Range is invalid");
        }

        validate(true);
        return target;
    }

    /**
     * Attempts to check a Task as done, then display a success message.
     * If the checking as done fails, a fail message is displayed instead.
     *
     * @param parser current {@code Parser}
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
            validate(true);
            return Ui.displayMessage(toReturn);
        } catch (DukeException e) {
            validate(false);
            return Ui.displayMessage(e.toString());
        }
    }

    /**
     * Attempts to delete a Task, then display a success message
     * If the deletion fails, a fail message is displayed instead
     *
     * @param parser current {@code Parser}
     */
    public String delete(Parser parser) {
        try {
            Task targetTask = tasks.get(getTaskID(parser));
            tasks.remove(targetTask);

            if (!targetTask.isDone) {
                decrementPendingTasks();
            }

            String toReturn = DELETED_MESSAGE + "\n" + targetTask;
            validate(true);
            return Ui.displayMessage(toReturn);
        } catch (DukeException e) {
            validate(false);
            return Ui.displayMessage(e.toString());
        }
    }

    /**
     * Attempts to add a Task to the list, then display a success message.
     * If the addition fails, a fail message is displayed instead.
     *
     * @param parser current {@code Parser}
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
                    + "You now have " + size + " task(s) in your list.\n\n"
                    + LIST_HINT;

            validate(true);
            return Ui.displayMessage(toReturn);
        } catch (DukeException e) {
            validate(false);
            return Ui.displayMessage(e.toString());
        }
    }

    /**
     * Displays a list of Tasks that contains a given String.
     * If not found, displays an alternative null message.
     *
     * @param parser current {@code Parser}
     */
    public String find(Parser parser) {
        List<Task> matches = getMatchingTask(parser.comparator);

        if (matches.isEmpty()) {
            validate(false);
            return Ui.displayMessage(NO_MATCH_MESSAGE);
        } else {
            int count = 1;
            StringBuilder toReturn = new StringBuilder(String.format("Found %d match(es) for '%s':", matches.size(), parser.comparator));

            for (Task task : matches) {
                toReturn.append(String.format("\n   %d. %s", count, task.toString()));
                count++;
            }

            validate(true);
            return Ui.displayMessage(toReturn.toString());
        }
    }

    private List<Task> getMatchingTask(String comparator) {
        boolean noComparator = comparator.isBlank();
        if (noComparator) {
            return new ArrayList<>();
        } else {
            return tasks.stream()
                    .filter(task -> task.getTaskName().contains(comparator))
                    .collect(Collectors.toList());
        }
    }

    public String update(Parser parser) {
        try {
            Task targetTask = tasks.get(getTaskID(parser));
            Task clonedTask = (Task) targetTask.clone();
            String changeWord = parser.getChangeWord();
            String target = parser.getChangeTarget();

            switch (changeWord) {
            case "name":
                targetTask.setTaskName(target);
                break;
            case "date":
                targetTask.setDate(LocalDate.parse(target));
                break;
            case "undo":
                if (targetTask.isDone) {
                    targetTask.markAsUndone();
                    incrementPendingTasks();
                } else {
                    targetTask.markAsDone();
                    decrementPendingTasks();
                }
                break;
            default:
                assert false : changeWord;
            }

            String toReturn = UPDATED_MESSAGE + "\n"
                    + "  from:  " + clonedTask + "\n"
                    + "  to:    " + targetTask + "\n\n"
                    + LIST_HINT;
            validate(true);
            return Ui.displayMessage(toReturn);
        } catch (DukeException | CloneNotSupportedException e) {
            validate(false);
            return Ui.displayMessage(e.toString());
        }
    }

    /**
     * Creates a clone of the current TaskList (for testing purposes).
     *
     * @return a shallow copy of this with no element references.
     * @throws CloneNotSupportedException when cloning process is invalid.
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * Class getter and setter routines.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    public int getNumOfPendingTasks() {
        return numOfPendingTasks;
    }

    public boolean isToReturnAsValid() {
        return toReturnAsValid;
    }

    private void validate(boolean toReturnAsValid) {
        this.toReturnAsValid = toReturnAsValid;
    }
}

